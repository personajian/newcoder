package cn.edu.seu.demo.thread;

/**
 * @Author personajian
 * @Date 2017/7/31 21:25
 */
public class ThreadWaitNotify {
    public static void main(String[] args) throws InterruptedException {
        ThreadWaitNotify task=new ThreadWaitNotify();
        new Thread(() ->task.work()).start();

        //等待3000毫秒后唤醒，继续工作。
        Thread.sleep(3000);
        task.continueWork();
    }

    public synchronized void work() {
        System.out.println("Begin Work");
        try {
            //等待唤醒
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work End");
    }

    public synchronized void continueWork() {
        //唤醒等待线程
        notifyAll();
    }


}
