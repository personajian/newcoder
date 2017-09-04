package cn.edu.seu.demo.thread;

import java.util.concurrent.TimeUnit;

/**《Java并发编程艺术》P92 理解中断
 * 声明抛出InterruptedException的方法（例如Thread.sleep(long mills)）
 * 在抛出InterruptedException之前，
 * JVM会先把线程的中断标志位清除，
 * 然后抛出InterruptedException，此时调用isInterrupted()方法将会返回false。
 * @Author personajian
 * @Date 2017/9/2 14:29
 */
public class ThreadInterrupted {
    public static void main(String[] args) throws InterruptedException {
        //睡眠线程
        Thread sleepThread=new Thread(new SleepRunner(),"SleepThread");
        sleepThread.setDaemon(true);
        //运行线程
        Thread busyThread=new Thread(new BusyRunner(),"BusyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        //模拟休眠5秒，等待子线程运行起来
        TimeUnit.SECONDS.sleep(5);

        //中断两个子线程（但sleepThread是会抛出中断异常的）
        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("SleepThread's Interrupted is "+sleepThread.isInterrupted());
        System.out.println("BusyThread's Interruped is "+busyThread.isInterrupted());

        //防止立即退出
        TimeUnit.SECONDS.sleep(2);
    }

    static class SleepRunner implements Runnable{

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable{

        @Override
        public void run() {
            while(true){

            }
        }
    }
}
