package cn.edu.seu.demo.connectionpool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Author personajian
 * @Date 2017/9/1 22:02
 */
public class ConnectionPool {
    //线程池作为共享变量
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0)
            for (int i = 0; i < initialSize; i++) {
                pool.add(ConnectionDriver.createConnection());
            }
    }

    /**释放数据库连接
     * @Param
     * @Return
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                //释放连接，并通知其他线程
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取数据库链接
     *
     * @Param mills：在mills秒内无法获取连接，将返回空
     * @Return
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                //等待释放连接的通知
                while (pool.isEmpty()) {
                    pool.wait();
                }
                //return pool.poll();
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                //等待释放连接的通知
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait();
                    remaining = future - System.currentTimeMillis();
                }

                Connection result = null;

                if (!pool.isEmpty()) {
                    //result = pool.poll();
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }

}
