package cn.edu.seu.demo.thread;

import java.util.concurrent.CyclicBarrier;

/**《Java并发编程艺术》P192
 * @Author personajian
 * @Date 2017/9/2 16:09
 */
public class TestCyclicBarrier2 {
    //public CyclicBarrier(int parties, Runnable barrierAction)：用于在线程到达屏障时，优先执行barrierAction。
    static CyclicBarrier c=new CyclicBarrier(2,new BarrierAction());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //子线程
                try {
                    c.await();
                } catch (Exception e) {
                }
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

    static class BarrierAction implements Runnable{

        @Override
        public void run() {
            System.out.println(3);
        }
    }

}
