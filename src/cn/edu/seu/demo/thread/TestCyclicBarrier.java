package cn.edu.seu.demo.thread;

import java.util.concurrent.CyclicBarrier;

/**《Java并发编程艺术》P191
 * @Author personajian
 * @Date 2017/9/2 16:09
 */
public class TestCyclicBarrier {
    //new CyclicBarrier(3)，则主线程和子线程会永远等待，因为没有第三个线程执行await()方法，即没有第三个线程到达屏障。
    static CyclicBarrier c=new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //子线程
                try {
                    c.await();
                } catch (Exception e) {
                }
                /*try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                System.out.println(1);
            }
        }).start();

        //主线程
        try {
            c.await();
        } catch (Exception e){
        }

        System.out.println(2);
    }



}
