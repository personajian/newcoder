package cn.edu.seu.demo.connectionpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**动态代理构造一个Connection，在调用commit()方法调用时休眠100毫秒
 * @Author personajian
 * @Date 2017/9/1 22:12
 */
public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }

    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(
                ConnectionDriver.class.getClassLoader(),
                new Class<?>[]{Connection.class},
                new ConnectionHandler());
    }
}
