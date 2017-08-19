[TOC]

## 1. 线程引入的开销

1. 上下文切换：JVM和OS的开销、缓存缺失
2. 内存同步：synchronized和volatile提供可见性使用了内存栅栏（刷新缓存，使缓存无效，刷新硬件的写缓存，以及停止执行管道）——性能影响，抑制编译器优化，禁止指令优化重排序。ps：synchronized针对非竞争的同步进行了优化；JVM会去掉一些不会发生竞争的锁；逸出分析；锁粒度粗化
3. 阻塞：竞争的同步需要OS的介入，增加开销。ps：自旋锁或者挂起被阻塞线程。

## 2. 线程安全级别

线程安全性级别：（1是@Immutable，2和3是@ThreadSafe,4和5@NotThreadSafe）

1. 不可变：不可变对象一定是线程安全的。
2. 相对线程安全：调用者不需要额外的同步措施，例如java.util.Vector
3. 绝对线程安全：通常意义上的线程（对象+锁）。安全单独操作是线程安全的，但是特定顺序的连续调用需要使用额外的同步手段来保证。例如：Vector,Hashtable,Collection的synchronizedCollection()方法包装的结合。
4. 线程兼容：对象本身并不是线程安全，但是调用端正确地使用同步手段来保证。Java API的大部分类，ArrayList,HashMap。
5. 线程对立无法再多线程环境中并发使用的代码。

## 3. 线程安全的实现

1. 互斥同步：临界区、互斥量、信号量（synchronized，ReentrantLock）
2. 非阻塞同步：基于冲突监检测的乐观并发，需要硬件层面的支持（例如CAS）
3. 无同步方案：可重入代码（reentrant code），线程本地存储（ThreadLoal）

## 4. 线程带来的问题

### 4.1 安全性问题

1. 一定线程安全
1. 绝对线程安全
1. 相对线程安全
1. 线程兼容
1. 线程对立

### 4.2 活跃性问题

1. 死锁
2. 饥饿
3. 活锁

### 4.3 性能问题

1. 服务时间长
2. 响应不灵敏
3. 吞吐率过低
4. 资源消耗高
5. 可伸缩性差

## 5. 提高锁性能的方式：

- 减少锁持有时间：尽量缩小同步代码块
- 减少锁粒度：锁分解（保护多个相互独立的状态变量的锁分解成一个锁保护一个变量，转化为非竞争的锁 ）和锁分段（对一组独立对象上的锁进行分解）
- 放弃独占锁：读写锁（ReadWriteLock）、不可变量对象（final）、原子变量(整数或对象引用的原子操作、CAS)
- 锁分离：
- 锁粗化：使邻近的同步代码块用一个锁合并起来。

## 6. Java虚拟机的锁优化：

- 锁偏向：一个线程获得了锁，那么锁就进入了偏向模式。当这个线程再次请求是，无须再做任何同步操作。
- 轻量级锁：将对象头部作为指针，指向持有锁的线程堆栈内部。
- 自旋锁：当前线程空循环，等待锁
- 锁消除：去除不可能存在共享资源竞争的锁。

## 7. 有锁（悲观锁）

### 7.1 同步控制：（锁+对象）

#### synchronized内置锁  

1. 原生语法层面的互斥锁。
2. synchronized关键词编译后，在同步块前后分别形成`monitorenter`和`monitorexit`两个字节码指令，需要一个reference类型的参数指明要**锁定和解锁的对象**。
1. synchronized同步块对同一条线程来说是**可重入的**。
2. synchronized同步块在已进入的线程**执行完之前**，会**阻塞**后面其他线程的进入。
3. 非公平锁：允许插队。
3. 重量级锁。

#### ReentrantLock可重入锁，显式锁  

1. API层面的互斥锁。
2. 相较于synchronized更为灵活：无条件的、可轮询的、定时的（通过`ReentrantLock#tryLock()`方法实现，避免死锁发生）、可中断锁获取。
2. 非块结构的加锁：通过降低锁的粒度提高代码的可伸缩性。`ReentrantLock#lock()`和`ReentrantLock#unlock()`方法配合try/finally语句块（离开被保护块必须清除锁）来完成。示例有：连锁式加锁（Hand-Over Locking）或者锁耦合（Lock Coupling）。
3. 高级功能：
    4. 等待可中断：`ReentrantLock#lockInterruptibly()`放弃等待，改为处理其他事。
    5. 可实现公平锁：按照申请锁的时间顺序来依次获得锁。
    6. 锁可绑定多个条件（Condition）：一个ReentrantLock可以同时绑定多个Condition对象；而synchronized中，多于一个条件关联时候需要添加额外的一个锁。 

#### Condition加锁条件  

1. 可重入锁ReentrantLock的好搭档，与ReentrantLock相绑定。
    2. `Condition#await()`:与`Object.wait()`相似，使当前线程等待，同时释放当前锁。
    3.  `Condition#awaitUninterruptibly()`：与await()相似，但是不会再等待过程中响应中断。
    4.  `Condition#singal()`：与`Object.notify()`相似，唤醒一个在等待中的线程。
    5.  `Condition#singalAll()`：唤醒所有等待的线程。

#### Semaphore信号量  

1. 广义上说，信号量是对锁的扩展，可以指定**多个线程同时访问某一个资源**。
    1. `public void acquire()`:尝试获得一个准入的许可。若无法获得，则线程会等待，直到有线程释放一个许可或者当前线程被中断。
    2. `public void acquireUninterruptibly()`：与`acquire()`相似，但是不相应中断。
    3. `public boolean tryAcquire()`：尝试获得一个许可，如果成功返回true；失败则返回false，不会等待，立即返回。
    4. `public boolean tryAcquire(long timeout, TimeUnit unit)`
    5. `public void release()`：用于在线程访问资源结束后，释放一个许可，以使其他等待许可的线程可以进行资源访问。

#### ReadWriteLock读写分离锁  

1. 读写锁：一个资源可以被多个读操作访问，或者被一个写操作访问，但两者不能同时进行。
2. 读读不互斥，读写互斥，写写互斥。
2. 可选操作：
    3. 释放优先
    4. 读线程插队
    5. 重入性
    6. 降级
    7. 升级
3.

#### CountDownLatch倒计时器（闭锁）  

1. 用来控制线程等待，让某一个线程等待知道倒计时结束，再开始执行。

#### FutureTask（闭锁）  


#### CyclicBarrier循环栅栏  

1. 与CountDownLatch类似，可以实现线程间的计数等待。
2. Latch：阻止线程继续执行，要求线程在栅栏处等待。
3. Cyclic：计数器可以反复使用。

#### Exchanger栅栏  


#### LockSupport阻塞工具  

1. 线程阻塞工具，使用了类似信号量的机制。可以在线程内任意位置让线程阻塞。
2. 与Thread.suspend()相比，弥补了由于resume()在前发生，导致线程无法继续执行的情况。
3. 与Object#wait()相比，不需要先获得某个对象的锁，也不会抛出InterruptedException。
    4. LockSuppor.park()：阻塞当前线程，类似还有parkNanos()、parkUtil()等。

### 7.2 线程池：

### 7.3 JDK并发容器：

> Map->ConcurrentHashMap

- 分段锁。
- 替代*同步+Map*方式

> List->CopyOnWriteArrayList

- 高效读取，不变模式下的。
- 读操作>>写操作：迭代>>修改。
- 底层基础数据引用（可见性，读）+容器副本（修改，写）。

> SortedMap->ConcurrentSkipListMap  
> SortedSet->ConcurrentSkipListSet 

- 随机数据结构

> Queue->ConcurrentLinkedQueue

- 高效读写队列。


> PriorityQueue



> Dueue

#### 阻塞队列、生产者消费者模式  

- 基于数据共享通道。

>  LinkedBlockingQueue  
>  ArrayBlockingQueue 

- FIFO队列
- 按优先级排序
 
>  PriorityBlockingQueue  

- 自然顺序（实现Comparable方法）
- 使用Comparator

>  SynchronousQueue  

- 维护一组线程，这些线程等待着把这些袁术加入或移除队列。

## 8 无锁（乐观锁）

- CAS：比较交换
- AtomicInteger：无锁的线程安全整数
- Unsafe类：Java中的指针
- AtomicReference：无锁的对象引用
- AtomicStampedReference：带有时间戳的对象引用
- AtomicIntegerArray：数组也能无锁
- AtomicIntegerFieldUpdater：让普通变量也享受原子操作
- Vector：无锁的Vector实现
- SynchronousQueue：让线程见互相帮助

## 相关名词

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


```
## 线程安全性

## 对象的共享

## 对象的组合

## 基础构件模块

## 任务执行机制（Executor）

## 执行任务

## 任务取消

## 任务关闭

## 线程池的使用

## GUI

## 避免活跃性危险

## 性能与可伸缩性

## 并发程序的测试

## 显式锁

## 同步工具

## 原子变量

## 非阻塞同步机制

## Java内存模型
```
