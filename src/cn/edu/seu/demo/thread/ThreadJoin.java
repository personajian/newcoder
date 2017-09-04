package cn.edu.seu.demo.thread;

/**
 * @Author personajian
 * @Date 2017/7/31 21:42
 */
public class ThreadJoin {

        public static void main(String[] args) throws InterruptedException {
            Thread thread0 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " Work End"));
            thread0.start();
            thread0.join();//合并到主线程，主线程将等待该子线程执行完毕才会执行

            Thread thread1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " Work End"));
            thread1.start();
            //thread0.join();//合并到主线程，主线程将等待该子线程执行完毕才会执行

            System.out.println("Main Thread Work End");
            /**
             输出：
             Thread-0 Work End
             Thread-1 Work End
             Main Thread Work End

             不使用join()：
             Main Thread Work End
             Thread-0 Work End
             Thread-1 Work End
             */

            Thread thread2 = new Thread(() -> {
                try {
                    //模拟子线程需要执行500毫秒
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " Work End");
            });
            thread2.start();
            thread2.join(100);//合并到主线程，主线程将等待该子线程执行完毕才会执行，只等待100毫秒，过时不在等。

            System.out.println("Main Thread Work End");

        }
    }

