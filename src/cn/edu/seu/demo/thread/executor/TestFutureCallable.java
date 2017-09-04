package cn.edu.seu.demo.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**Future+Callable
 * @Author personajian
 * @Date 2017/9/4 22:25
 */
public class TestFutureCallable {

    public static void main(String[] args) {
        //创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        //创建Callable对象任务
        TestCallableTask callTask = new TestCallableTask();
        //提交任务并获取执行结果
        Future<Integer> future = es.submit(callTask);
        //关闭线程池
        es.shutdown();
        try {
            //Thread.sleep(2000);
            System.out.println("主线程在执行其他任务");

            //此线程（主线程）会等待future.get()的结果。
            if (future.get() != null) {
                //输出获取到的结果
                System.out.println("future.get()-->" + future.get());
            } else {
                //输出获取到的结果
                System.out.println("future.get()未获取到结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行完成");
    }
}