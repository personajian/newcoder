package cn.edu.seu.practice.thread;

/**
 * @Author personajian
 * @Date 2017/7/31 21:39
 */
public class ThreadSleep {
    public static void main(String[] args) {
        new Thread(){
            public void run(){
                try {
                    System.out.println("开始睡眠...");
                    Thread.sleep(3000);
                    System.out.println("结束睡眠...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
