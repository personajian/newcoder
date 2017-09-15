### 一、Executor框架

主要思想：将**任务单元**和**任务执行机制**相分离。主要分为三部分：

1. 任务单元：Runnable，Callable
2. 执行机制：核心接口`Executor`，`ExecutorSerivice`；实现类`ThreadPoolExecutor`,`ScheduledThreadPoolExecutor`
3. 异步结果：`Future`，`FutureTask`

其中执行机制Executor有两个核心实现类：

1. `ThreadPoolExecutor`：可以创建三种类型的线程池
    - `FixedThreadPool`:固定线程数
    - `SingleThreadPool`：单个线程
    - `CachedTheadPool`：按需创建线程
2. `ScheduledThreadPoolExecutor`：延迟后执行任务，或者定期执行任务。
    - `ScheduledThreadPoolExecutor`：若干线程
    - `SingleThreandScheduledExecutor`：单个线程


### 二、线程池

#### 2.1 线程池的实现原理

线程池的处理流程：

1. 提交任务，创建工作线程来执行任务；
2. 线程数>corePoolSize时，将任务放入BlockingQueue中；
3. BlockgingQueue满了时，创建新的工作线程；
4. 线程数>maxPoolSize时，执行拒绝策略。

#### 2.2 线程池的创建：

- Executor：线程池工厂； 
- TheadPoolExecutor：线程池
- ThreadFactory：线程工厂

Executor框架提供了各种类型的线程池，工厂方法有：

```java
public static ExecutorService newFixedThreadPool(int nThreads);//固定数量的线程池
public static ExecutorService newSingleThreadExecutor();//只有一个线程的线程池
public static ExecutorService newCachedThreadPool();//实际情况调整的线程池
public static ScheduledExecutorService newSingleThreadScheduledExecutor();//计划任务，线程池大小为1
public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize);//计划任务，指定线程数量
```

核心线程池实的内部实现：均使用了ThreadPoolExecutor实现的。
```java
public ThreadPoolExecutor(int corePoolSize,//指定线程池中的线程数量；
                          int maximumPoolSize,//最大线程数量；
                          long keepAliveTime,//超过corePoolSize时，多余空闲线程的存活时间；
                          TimeUnit unit,//keepAliveTime的单位；
                          BlockingQueue<Runnable> workQueue,//任务队列，被提交但尚未被执行的任务；
                          ThreadFactory threadFactory,//线程工厂，用于创建线程；
                          RejectedExecutionHandler handler)//拒绝策略
```

其中，`BlockingQueue<Runnable> workQueue`：等待队列，阻塞队列，仅用来存放Runnable对象。

1. 直接提交队列：SynchronousQueue对象；
2. 有界任务队列：ArrayBlockingQueue对象；
3. 无界任务队列：LinkedBlockingQueue对象；
4. 优先任务队列：PriorityBlockingQueue对象；

ThreadPoolExecutor核心调度代码：

```java
addWorker();//调度执行；
workQueue.offer();//进入等待队列；
```

`RejectedExecutionHandler handler`拒绝策略：

- AbortPolicy策略：直接抛出异常，阻止系统正常工作；
- CallerRunsPolicy策略：运行当前被丢弃任务；
- DiscardOldestPolicy策略：丢弃最老的一个请求；
- DiscardPolicy策略： 丢弃无法处理的任务；

#### 2.3 向线程池提交任务

1. `threadPool.execute()`:提交不需要返回值的任务，比如`Runnable`,`Thread`;
2. `ThreadPool.submit()`:提交有返回值的任务，比如`Callable`,`FutureTask`。
    - 返回的是Future类型的对象，Future#get()获得返回值可以阻塞当前线程直到完成。

#### 2.4 关闭线程池

1. `threadPool.shutDown()`：SHUTDWON——中断所有**没有正在执行**任务的线程；
2. `threadPool.shutDownNow()`：STOP——尝试停止所有**正在执行**任或者暂停任务的线程。

原理是：逐个调用线程的`interrupt()`来中断线程。

#### 2.5 配置线程池

`int N=Runtime.getRunTime().availableProcessors()`：获取当前计算机的cpu个数。

1. CPU密集型：执行线程会一直占用CPU，所以配置N+1个线程；
2. IO密集型：执行线程会大量阻塞，CPU可以切换，所有配置2*N个线程。

#### ThreadPoolExecutor详解

- ThreadPoolExecutor

```java
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler)
}
```

- FixedThreadPool

```java
public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}
```

- SingleThreadExecutor

```java
public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        (new ThreadPoolExecutor(1, 1,
                                0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>()));
}
```

- CachedThreadPool

```java
public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                  60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
}
```

- ScheduledThreadPoolExecutor

```java
public ScheduledThreadPoolExecutor(int corePoolSize) {
    super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
          new DelayedWorkQueue());
          //DelayQueue封装了一个PriorityQueue，对队列中的ScheduledFutureTask进行排序
}
```

- SingleThreadScheduledExecutor

```java
public static ScheduledExecutorService newSingleThreadScheduledExecutor() {
    return new DelegatedScheduledExecutorService
        (new ScheduledThreadPoolExecutor(1));
}
```

