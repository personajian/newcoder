package cn.edu.seu.demo.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**《Java并发编程艺术》P198
 * @Author personajian
 * @Date 2017/9/2 16:45
 */
public class TestExchanger {

    private  static final Exchanger<String> exgr=new Exchanger<>();

    private static ExecutorService threadPool= Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String A="银行流水A";
                try {
                    exgr.exchange(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B="银行流水B";
                    String A= exgr.exchange(B);
                    //B=exgr.exchange(A);

                    System.out.println("A和B数据是否一致："+A.equals(B)+"，A录入的是："+A+"，B录入是："+B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
