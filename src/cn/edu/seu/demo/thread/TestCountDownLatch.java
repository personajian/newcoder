package cn.edu.seu.demo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**《Java并发编程艺术》P190
 * @Author personajian
 * @Date 2017/9/2 16:09
 */
public class TestCountDownLatch {

    static CountDownLatch count=new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                count.countDown();
                System.out.println(2);
                //count.countDown();
            }
        }).start();
        //让主线程等待，等到到CountDownLatch为0
        //count.await();
        //让主线程限时等待，不必一定等到到CountDownLatch为0
        boolean await = count.await(3, TimeUnit.SECONDS);
        System.out.println(3);
    }
}
