package cn.edu.seu.practice.thread;

import java.util.concurrent.*;

/**创建线程实例的三种方式：继承Thread类；实现Runnable接口；实现Callable接口。
 * @Author personajian
 * @Date 2017/7/31 20:55
 */
public class ThreadNewTask {

    public static void main(String[] args) {
        method1();
        method2();
        method3();
    }

    /**继承Thread类
     * @Param
     * @Return
     */
    public static void method1() {
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Started");
            }
        }.start();
    }

    /**实现Runnable接口
     * @Param
     * @Return
     */
    public static void method2() {

        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Started");
            }
        }).start();
    }

    /**实现Callable接口
     * @Param
     * @Return
     */
    public static void method3() {

        //工作单元（任务）——线程
        Callable task =new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " Started");
                //求和
                return 1 + 1;            }
        };

        //Executor框架，线程池
        ExecutorService es = Executors.newFixedThreadPool(1);
        //Executor执行任务，Future获得Callable的回调结果
        Future future = es.submit(task);
        try {
            System.out.println("Calculate Completed Sum：" + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();
    }

}
