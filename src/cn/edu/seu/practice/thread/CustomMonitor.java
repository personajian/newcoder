package cn.edu.seu.practice.thread;

import sun.misc.Unsafe;

/**利用Unsafe实现对象监视器模拟moniterenter指令和monitorexit指令
 * @Author personajian
 * @Date 2017/7/31 22:05
 */
public class CustomMonitor {

    public static void main(String[] args) throws Exception {

        Unsafe unsafe=new CustomUnsafe().getUnsafe();

        Object lock = new Object();
        //注掉下面这行代码则抛出java.lang.IllegalMonitorStateException
        System.out.println("获得对象monitor之前...");
        unsafe.monitorEnter(lock);
        System.out.println("线程等待...");
        lock.wait(3000);
        System.out.println("线程等待结束...");
        unsafe.monitorExit(lock);
        System.out.println("释放对象monitor之后...");

    }
}
