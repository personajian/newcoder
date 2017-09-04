package cn.edu.seu.demo.connectionpool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author personajian
 * @Date 2017/9/1 22:16
 */
public class ConnectionPoolTest {
    //数据库连接池
    static ConnectionPool pool = new ConnectionPool(5);
    //保证所有ConnectionRunner能够同时开始
    static CountDownLatch start = new CountDownLatch(1);
    //main线程将会等待所有ConnectionRunner结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        //线程数量
        int threadCount = 20;

        end = new CountDownLatch(threadCount);

        //每个线程尝试获取的线程数目
        int count = 20;

        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }

        start.countDown();
        end.await();

        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection： " + notGot);
    }


    static class ConnectionRunner implements Runnable {

        int count;
        AtomicInteger got;
        AtomicInteger noGot;

        ConnectionRunner(int count, AtomicInteger got, AtomicInteger noGot) {
            this.count = count;
            this.got = got;
            this.noGot = noGot;
        }

        @Override
        public void run() {
            //保证所有ConnectionRunner能够同时开始
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (count > 0) {
                try {
                    Connection
                            connection = pool.fetchConnection(1000);

                    if (connection != null) {
                        try {
                            //模拟耗时100毫秒提交
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        noGot.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            //main线程等待所有ConnectionRunner
            end.countDown();
        }
    }
}
