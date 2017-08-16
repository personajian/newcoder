package cn.edu.seu.demo.thread;

/**Thread.yeild()：使一个正在运行的线程暂停执行（让出CPU）让其他线程执行，即回到就绪状态。
 * @Author personajian
 * @Date 2017/7/31 21:37
 */
public class ThreadYield {
    public  void work(){
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " Working");
            Thread.yield();
        }
    }
    public static void main(String[] args) {
        ThreadYield task = new ThreadYield();
        new Thread(() -> task.work()).start();
        new Thread(() -> task.work()).start();
        /**
         输出：
         Thread-0 Working
         Thread-1 Working
         Thread-0 Working
         Thread-1 Working
         Thread-0 Working
         Thread-1 Working
         **/
    }
}

