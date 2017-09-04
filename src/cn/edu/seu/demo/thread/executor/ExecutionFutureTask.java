package cn.edu.seu.demo.thread.executor;

import java.util.concurrent.*;

/**当一个线程需要等待另一个线程把某个任务执行完后才能继续执行，此时可以使用FutureTask
 * 《Java并发编程艺术》p223
 * @Author personajian
 * @Date 2017/9/4 22:48
 */
public class ExecutionFutureTask {

    private final ConcurrentHashMap<Object,FutureTask> taskCache=new ConcurrentHashMap<>();

    private String executionTask(final String taskName) throws ExecutionException, InterruptedException {

        while(true){
            Future<String> future=taskCache.get(taskName);

            if(future==null){

                Callable<String> task=new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return taskName;
                    }
                };

                FutureTask<String> futureTask=new FutureTask<String>(task);

                future=taskCache.putIfAbsent(taskName,futureTask);

                if(future==null){
                    future=futureTask;
                    futureTask.run();
                }
            }

            try {
                return future.get();
            } catch (CancellationException e){
                taskCache.remove(taskName,future);
            }
        }
    }

}
