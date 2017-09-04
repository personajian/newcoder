package cn.edu.seu.demo.thread;

import java.util.concurrent.CyclicBarrier;

/**《Java并发编程艺术》P192
 * CyclicBarrier适用于更为复杂的场景，isBroken()方法用来了解 阻塞的线程是否被中断。
 * @Author personajian
 * @Date 2017/9/2 16:09
 */
public class TestCyclicBarrier3 {
    static CyclicBarrier c=new CyclicBarrier(2);

    public static void main(String[] args) {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                //子线程
                try {
                    c.await();
                } catch (Exception e) {
                }
                System.out.println(1);
            }
        });

        thread.start();
        thread.interrupt();

        //主线程
        try {
            c.await();
        } catch (Exception e){
            System.out.println(c.isBroken());
        }
    }

}
