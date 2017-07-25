[TOC]

# 参考文章：
1. [Java多线程抢占](https://blog.dreamtobe.cn/2015/03/25/Java%E5%A4%9A%E7%BA%BF%E7%A8%8B%E6%8A%A2%E5%8D%A0/)
2. [Java Synchronised机制](https://blog.dreamtobe.cn/2015/11/13/java_synchronized/)
3. [Java线程中sleep()、wait()和notify()和notifyAll()、yield()、join()等方法的用法和区别](http://zheng12tian.iteye.com/blog/1233638)
4. [Difference between wait() and sleep()](https://stackoverflow.com/questions/1036754/difference-between-wait-and-sleep)
5. 《深入理解Java虚拟机》
6. 《实战Java高并发程序设计》


### 线程生命周期

```java
public enum State{
    NEW,
    RUNNABLE,
    BLOCKED,
    WAITING,
    TIMED_WAITING,
    TERMINATED;
}
```

```java
/*Thread的方法*/
Thread#start();//启动
Thread#run();//运行
Thread#stop();//终止
Thread#interrupt();//中断：希望退出
Thread#suspend();
Thread#resume();
Thread#join();//等待线程结束：无限等待，一直阻塞当前线程，直到目标线程执行完毕；本质是让调用wait()在当前线程对象实例上。
Thread#yield();//谦让
Thread#sleep();//不会释放任何资源

/*Object的方法，是获得目标对象的监视器，用于多线程协作。*/
Object#wait();//释放目标对象的锁
Object#notify();//唤醒一个等待目标对象锁的线程
Object#notifyAll();//唤醒所有等待目标对象锁的线程
```

方法|方法来源|作用范围|效果|
---|---|---|---
interrupt|Thread|wait/sleep/join|
sleep|Thread||睡眠，不释放锁
join|Thread||等待join所在线程
yield|Thread||停止当前线程。将CPU转移给其他线程
suspend|Thread||挂起，可能导致死锁，弃用
resume|Thread||
wait|Object|在synchronized块下调用|释放目标对象的锁，进入等待池
notify|Object||唤醒一个等待目标对象锁的线程
notifyAll|Object||唤醒所有等待目标对象锁的线程

### [Java多线程抢占](https://blog.dreamtobe.cn/2015/03/25/Java%E5%A4%9A%E7%BA%BF%E7%A8%8B%E6%8A%A2%E5%8D%A0/)

#### 机制种类

- 抢占机制：对于CPU而言多个线程处于就绪线程队列，但是只有一个线程在运行状态。——Java多线程机制。
- 分时机制

### [Java Synchronised机制](https://blog.dreamtobe.cn/2015/11/13/java_synchronized/)

#### I. 锁的原末

>矛盾1:重量级锁造成线程阻塞->引入轻量级锁（例如自旋锁）来避免。

A: 重量级锁中的阻塞(挂起线程/恢复线程): 需要转入内核态中完成，有很大的性能影响。

B: 锁大多数情况都是在很短的时间执行完成。

解决方案: 引入轻量锁(通过自旋来完成锁竞争)。

>矛盾2:自旋锁占用CPU时间->默认自旋次数为10，或者失败后升级为重量级锁。

A: 轻量级锁中的自旋: 占用CPU时间，增加CPU的消耗(因此在多核处理器上优势更明显)。

B: 如果某锁始终是被长期占用，导致自旋如果没有把握好，白白浪费CPU资源。

解决方案: JDK5中引入默认自旋次数为10(用户可以通过-XX:PreBlockSpin进行修改)， JDK6中更是引入了自适应自旋（简单来说如果自旋成功概率高，就会允许等待更长的时间（如100次自旋），如果失败率很高，那很有可能就不做自旋，直接升级为重量级锁，实际场景中，HotSpot认为最佳时间应该是一个线程上下文切换的时间，而是否自旋以及自旋次数更是与对CPUs的负载、CPUs是否处于节电模式等息息相关的)。

>矛盾3:锁通过CAS对mark word修改多余->引入偏向锁，对比mw，取得锁就不用CAS。

A: 无论是轻量级锁还是重量级锁: 在进入与退出时都要通过CAS修改对象头中的Mark Word来进行加锁与释放锁。

B: 在一些情况下总是同一线程多次获得锁，此时第二次再重新做CAS修改对象头中的Mark Word这样的操作，有些多余。

解决方案: JDK6引入偏向锁(首次需要通过CAS修改对象头中的Mark Word，之后该线程再进入只需要比较对象头中的Mark Word的Thread ID是否与当前的一致，如果一致说明已经取得锁，就不用再CAS了)。

>矛盾4:多线程多为锁，偏向锁用的少->可以禁用偏向锁。

A: 项目中代码块中可能绝大情况下都是多线程访问。

B: 每次都是先偏向锁然后过渡到轻量锁，而偏向锁能用到的又很少。

解决方案: 可以使用-XX:-UseBiasedLocking=false禁用偏向锁。

>矛盾5:大量加锁中可以无效->通过JIT来进行锁消除。

A: 代码中JDK原生或其他的工具方法中带有大量的加锁。

B: 实际过程中，很有可能很多加锁是无效的(如局部变量作为锁，由于每次都是新对象新锁，所以没有意义)。

解决方法: 引入锁削除(虚拟机即时编译器(JIT)运行时，依据逃逸分析的数据检测到不可能存在竞争的锁，就自动将该锁消除)。

>矛盾6:锁粒度太小，频繁加锁解锁->引入锁膨胀。

A: 为了让锁颗粒度更小，或者原生方法中带有锁，很有可能在一个频繁执行(如循环)中对同一对象加锁。

B: 由于在频繁的执行中，反复的加锁和解锁，这种频繁的锁竞争带来很大的性能损耗。

解决方法: 引入锁膨胀(会自动将锁的范围拓展到操作序列(如循环)外, 可以理解为将一些反复的锁合为一个锁放在它们外部)。

#### synchronised基本原理

>参考《深入理解Java虚拟机》P391。

JVM会为每个对象分配一个monitor（监视器），而同时只能有一个线程可以获得该对象monitor的所有权。在线程进入时通过monitorenter尝试取得对象monitor所有权，退出时通过monitorexit释放对象monitor所有权。

monitorenter与monitorexit在编译后对称插入代码。

monitorenter: 被插入到同步代码块之前。
monitorexit: 被插到同步代码块之后或异常处。

>锁相关数据存在哪里?

对象头。

对象头结构:

数组会多1字宽(32位: 4字节)来存储数组长度

长度|内容|说明
---|---|---
1字宽|Mark Word|存储对象的hashCode或锁信息等
1字宽|Class Metadata Address|存储对象类型数据的指针
1字宽|Array length|数组长度（若是数组对象）

而对象的锁，一般只和Mark Word有关。

>各个锁的关系以及升级情况?

锁升级是单向的: 无锁 -> 偏向锁 -> 轻量级锁 -> 重量级锁

### 多线程下数据同步

这类锁/关键字主要是为了维护数据在高并发情况下的一致性/稳定性。

#### 数据库中的锁

- 共享锁(Share Lock)

又称为读锁

多个线程可并发的获得某个数据的共享锁锁，并行读取数据。在数据存在共享锁期间，不能修改数据，不能加排他锁。

如MySQL中，在查询语句最后加上LOCK IN SHARE MODE。

- 排他锁(eXclusive Lock)

又称为写锁

同能只能有一个线程可以获得某个数据的排他锁。在线程获取排他锁后，该线程可对数据读写，但是其他线程不能对该数据添加任何锁。

#### JVM的轻量级volatile

>volatile：保证可见性，不保证原子性。

如果一个共享变量被声明成volatile，java线程内存模型将会确保所有线程看到这个变量的值是一致的。

基本策略: 写操作时，会有Lock前缀指定，处理器会立马将修改直接写回系统内存，并且其他处理器会将该值在其上的高速缓存标为无效。
可能带来的性能消耗: 写操作实时写回内存，锁总线/锁内存。
优势: 一些场景上相比synchronized，执行成本更低(不会引起线程上下文切换以及调度)，使用更方便。

## 线程理论

### JMM原理

1. 原子性：操作的原子性，long、double类型的非原子协定；
2. 可见性：CPU，Cache，存器，主存；缓存优化，硬件优化，编译器优化
3. 有序性：指令重排只保证串行语义一直，但不保证多线程语义一致性
4. Happen-Before规则：不能指令重排的！

### 1. 线程引入的开销

1. 上下文切换：JVM和OS的开销、缓存缺失
2. 内存同步：synchronized和volatile提供可见性使用了内存栅栏（刷新缓存，使缓存无效，刷新硬件的写缓存，以及停止执行管道）——性能影响，抑制编译器优化，禁止指令优化重排序。ps：synchronized针对非竞争的同步进行了优化；JVM会去掉一些不会发生竞争的锁；逸出分析；锁粒度粗化
3. 阻塞：竞争的同步需要OS的介入，增加开销。ps：自旋锁或者挂起被阻塞线程。

### 2. 线程安全级别

线程安全性级别：（1是@Immutable，2和3是@ThreadSafe,4和5@NotThreadSafe）

1. 不可变：不可变对象一定是线程安全的。
2. 相对线程安全：调用者不需要额外的同步措施，例如java.util.Vector
3. 绝对线程安全：通常意义上的线程（对象+锁）。安全单独操作是线程安全的，但是特定顺序的连续调用需要使用额外的同步手段来保证。例如：Vector,Hashtable,Collection的synchronizedCollection()方法包装的结合。
4. 线程兼容：对象本身并不是线程安全，但是调用端正确地使用同步手段来保证。Java API的大部分类，ArrayList,HashMap。
5. 线程对立无法再多线程环境中并发使用的代码。

### 3. 线程安全的实现

1. 互斥同步：临界区、互斥量、信号量（synchronized，ReentrantLock）
2. 非阻塞同步：基于冲突监检测的乐观并发，需要硬件层面的支持（例如CAS）
3. 无同步方案：可重入代码（reentrant code），线程本地存储（ThreadLocal）

### 4. 线程带来的问题

#### 4.1 安全性问题

1. 一定线程安全
1. 绝对线程安全
1. 相对线程安全
1. 线程兼容
1. 线程对立

#### 4.2 活跃性问题

1. 死锁
2. 饥饿
3. 活锁

#### 4.3 性能问题

1. 服务时间长
2. 响应不灵敏
3. 吞吐率过低
4. 资源消耗高
5. 可伸缩性差

### 5. 提高锁性能的方式：

- 减少锁持有时间：尽量缩小同步代码块
- 减少锁粒度：锁分解（保护多个相互独立的状态变量的锁分解成一个锁保护一个变量，转化为非竞争的锁 ）和锁分段（对一组独立对象上的锁进行分解）
- 放弃独占锁：读写锁（ReadWriteLock）、不可变量对象（final）、原子变量(整数或对象引用的原子操作、CAS)
- 锁分离：
- 锁粗化：使邻近的同步代码块用一个锁合并起来。

### 6. Java虚拟机的锁优化：

- 锁偏向：一个线程获得了锁，那么锁就进入了偏向模式。当这个线程再次请求是，无须再做任何同步操作。
- 轻量级锁：将对象头部作为指针，指向持有锁的线程堆栈内部。
- 自旋锁：当前线程空循环，等待锁
- 锁消除：去除不可能存在共享资源竞争的锁。

- ThreadLocal：空间换时间，弱引用，立即回收

## 有锁（悲观锁）

### 同步控制：（锁+对象）

#### synchronized内置锁  

1. 原生语法层面的互斥锁。
2. synchronized关键词编译后，在同步块前后分别形成`monitorenter`和`monitorexit`两个字节码指令，需要一个reference类型的参数指明要**锁定和解锁的对象**。
1. synchronized同步块对同一条线程来说是**可重入的**。
2. synchronized同步块在已进入的线程**执行完之前**，会**阻塞**后面其他线程的进入。
3. 非公平锁：允许插队。
3. 重量级锁。

#### ReentrantLock可重入锁，显式锁  

可重入：允许一个线程连续两次获得同一把锁。而不会死锁。

1. API层面的互斥锁。
2. 相较于synchronized更为灵活：无条件的、可轮询的、定时的（通过`ReentrantLock#tryLock()`方法实现，避免死锁发生）、可中断锁获取。
2. 非块结构的加锁：通过降低锁的粒度提高代码的可伸缩性。`ReentrantLock#lock()`和`ReentrantLock#unlock()`方法配合try/finally语句块（离开被保护块必须清除锁）来完成。示例有：连锁式加锁（Hand-Over Locking）或者锁耦合（Lock Coupling）。
3. 高级功能：
    4. 等待可中断：`ReentrantLock#lockInterruptibly()`放弃等待，改为处理其他事。
    5. 可实现公平锁：按照申请锁的时间顺序来依次获得锁。
    6. 锁可绑定多个条件（Condition）：一个ReentrantLock可以同时绑定多个Condition对象；而synchronized中，多于一个条件关联时候需要添加额外的一个锁。 
    7. 等待限时：`ReentrantLock#tryLock()`

ReentrantLock重要方法：
```java
lock();//获得锁，若锁占用，则等待；
lockInterruptibly();//获得锁，但优先响应中断；
tryLock();//尝试获得锁，成功返回true，失败返回false，不等待；
tryLock(long time, TimeUnit unit);//给定时间内尝试获得锁；
unlock();//释放锁；
```
重入锁实现原理：

- 原子状态：使用CAS来存储当前锁的状态；
- 等待队列：没有请求到锁的线程进入等待队列；
- 阻塞原语：park()和unpark()来挂起和恢复线程。例如线程阻塞工具类：LockSupport。

#### Condition加锁条件  

- Condition+ReentrantLock=wait(),notify()+synchronized。
- 利用Condition对象，让线程在合适的时间等待，或者在某一特定时间得到通知。
- 可重入锁ReentrantLock的好搭档，与ReentrantLock相绑定。
```java
Condition#await();//与Object.wait()相似，使当前线程等待，同时释放当前锁。
Condition#awaitUninterruptibly();//与Object.wait()相似，但是不会再等待过程中响应中断。
Condition#singal();//与Object.notify()相似，唤醒一个在等待中的线程。
Condition#singalAll();//唤醒所有等待的线程。
```

#### Semaphore信号量  

1. 广义上说，信号量是对锁的扩展，可以指定**多个线程同时访问某一个资源**。
```java
public void acquire();//尝试获得一个准入的许可。若无法获得，则线程会等待，直到有线程释放一个许可或者当前线程被中断。
public void acquireUninterruptibly();//：与acquire()相似，但是不相应中断。
public boolean tryAcquire();//：尝试获得一个许可，如果成功返回true；失败则返回false，不会等待，立即返回。
public boolean tryAcquire(long timeout, TimeUnit unit);
public void release();//用于在线程访问资源结束后，释放一个许可，以使其他等待许可的线程可以进行资源访问。
```
#### ReadWriteLock读写分离锁  

1. 读写锁：一个资源可以被多个读操作访问，或者被一个写操作访问，但两者不能同时进行。
2. 读读不互斥，读写互斥，写写互斥。
2. 可选操作：
    3. 释放优先
    4. 读线程插队
    5. 重入性
    6. 降级
    7. 升级

#### CountDownLatch倒计时器（闭锁）  

1. 用来控制线程等待，让某一个线程等待知道倒计时结束，再开始执行。

```java
CountDownLatch(int count);
CountDownLatch#countdown();
CountDownLatch#wait();
```

#### FutureTask（闭锁）  


#### CyclicBarrier循环栅栏  

1. 与CountDownLatch类似，可以实现线程间的计数等待。
2. Latch：阻止线程继续执行，要求线程在栅栏处等待。
3. Cyclic：计数器可以反复使用。

```java
public CyclicBarrier(int parties,Runnable barrierAction);//barrierAction就是当计数器一次计数完成后，系统会执行的动作。
```

#### Exchanger栅栏  


#### LockSupport阻塞工具  

1. 线程阻塞工具，使用了类似信号量的机制。可以在线程内任意位置让线程阻塞。
2. 与Thread.suspend()相比，弥补了由于resume()在前发生，导致线程无法继续执行的情况。
3. 与Object#wait()相比，不需要先获得某个对象的锁，也不会抛出InterruptedException。
4. LockSuppor.park()：阻塞当前线程，类似还有parkNanos()、parkUtil()等。

### 线程池：

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


分而治之：Fork/Join框架

fork()多一个执行分支；join()表示等待；

### JDK并发容器

并发容器：取代线程不安全的容器

1. Map->ConcurrentHashMap
    - 分段锁。
    - 替代*同步+Map*方式
2. List->CopyOnWriteArrayList
    - 高效读取，不变模式下的。
    - 读操作>>写操作：迭代>>修改。
    - 底层基础数据引用（可见性，读）+容器副本（修改，写）。
3. SortedMap->ConcurrentSkipListMap 
    - 随机算法
    - 本质：同时维护多个链表，而且链表是分层的。
    - 底层：CAS+volatile 
4. SortedSet->ConcurrentSkipListSet 
    - 随机数据结构
5. Queue->ConcurrentLinkedQueue
    - 高效读写队列。
6. PriorityQueue
7. Dueue

阻塞队列BlockingQueue：生产者消费者模式；基于数据共享通道。底层实现ReentrantLock+Condition。

```java
final ReentrantLock lock;
private final Condition notEmpty;
private final Condition notFull;
put();//Blocking的关键所在！
take();

```

1. LinkedBlockingQueue  
2. ArrayBlockingQueue 
    - FIFO队列
    - 按优先级排序
3. PriorityBlockingQueue  
    - 自然顺序（实现Comparable方法）
    - 使用Comparator
4. SynchronousQueue  
    - 维护一组线程，这些线程等待着把这些元素加入或移除队列。
    - 写需要等待读。
    - Transferer.transfer()

### 8 无锁（乐观锁）

- CAS：比较交换
CAS(V,E,N)的三个参数：V要更新的值；E预期只；N新值。
- AtomicInteger：无锁的线程安全整数；
- Unsafe类：Java中的指针；封装了一些类似指针的操作，offset对象内偏移量
- AtomicReference：无锁的对象引用
- AtomicStampedReference：带有时间戳的对象引用；解决ABA问题，带有时间戳
- AtomicIntegerArray：数组也能无锁；对int[]类型的封装，使用Unsafe类通过CAS方式控制int[]
- AtomicIntegerFieldUpdater：让普通变量也享受原子操作；对int、long、普通对象进行CAS修改。
- Vector：无锁的Vector实现
- SynchronousQueue：让线程见互相帮助

#### 相关名词

- 自旋锁：自旋，jvm默认是10次吧，有jvm自己控制。for去争取锁
- 阻塞锁：被阻塞的线程，不会争夺锁。
- 可重入锁：多次进入改锁的域
- 读写锁：读写分离锁
- 互斥锁：锁本身就是互斥的
- 悲观锁：不相信这里会是安全的，必须全部上锁
- 乐观锁：相信这里是安全的。
- 公平锁：有优先级的锁
- 非公平锁：无优先级的锁
- 偏向锁：无竞争不锁，有竞争挂起，转为轻量锁
- 对象锁：锁住对象
- 线程锁：
- 锁粗化：多锁变成一个，自己处理
- 轻量级锁： CAS实现
- 锁消除：偏向锁就是锁消除的一种
- 锁膨胀：jvm实现，锁粗化
- 信号量：使用阻塞锁 实现的一种策略
- 排它锁：X锁，若事务T对数据对象A加上X锁，则只允许T读取和修改A，其他任何事务都不能再对A加任何类型的锁，直到T释放A上的锁。这就保证了其他事务在T释放A上的锁之前不能再读取和修改A。


