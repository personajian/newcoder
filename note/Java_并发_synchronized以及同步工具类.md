## 有锁（悲观锁）

基于策略：同步控制：（锁+对象）

### 一、synchronized

#### 1.1 synchronized基本原理

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

#### 1.2 synchronized内置锁  

1. 原生语法层面的互斥锁。
2. synchronized关键词编译后，在同步块前后分别形成`monitorenter`和`monitorexit`两个字节码指令，需要一个reference类型的参数指明要**锁定和解锁的对象**。
1. synchronized同步块对同一条线程来说是**可重入的**。
2. synchronized同步块在已进入的线程**执行完之前**，会**阻塞**后面其他线程的进入。
3. 非公平锁：允许插队。
3. 重量级锁。

### 二、同步工具类

#### 2.1 ReentrantLock可重入锁，显式锁  

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

#### 2.2 Condition加锁条件  

- Condition+ReentrantLock=wait(),notify()+synchronized。
- 利用Condition对象，让线程在合适的时间等待，或者在某一特定时间得到通知。
- 可重入锁ReentrantLock的好搭档，与ReentrantLock相绑定。
```java
Condition#await();//与Object.wait()相似，使当前线程等待，同时释放当前锁。
Condition#awaitUninterruptibly();//与Object.wait()相似，但是不会再等待过程中响应中断。
Condition#singal();//与Object.notify()相似，唤醒一个在等待中的线程。
Condition#singalAll();//唤醒所有等待的线程。
```

#### 2.3 Semaphore信号量  

1. 广义上说，信号量是对锁的扩展，可以指定**多个线程同时访问某一个资源**。
```java
public void acquire();//尝试获得一个准入的许可。若无法获得，则线程会等待，直到有线程释放一个许可或者当前线程被中断。
public void acquireUninterruptibly();//：与acquire()相似，但是不相应中断。
public boolean tryAcquire();//：尝试获得一个许可，如果成功返回true；失败则返回false，不会等待，立即返回。
public boolean tryAcquire(long timeout, TimeUnit unit);
public void release();//用于在线程访问资源结束后，释放一个许可，以使其他等待许可的线程可以进行资源访问。
```

#### 2.4 ReadWriteLock读写分离锁  

1. 读写锁：一个资源可以被多个读操作访问，或者被一个写操作访问，但两者不能同时进行。
2. 读读不互斥，读写互斥，写写互斥。
2. 可选操作：
    3. 释放优先
    4. 读线程插队
    5. 重入性
    6. 降级
    7. 升级

#### 2.5 CountDownLatch倒计时器（闭锁）  

1. 用来控制线程等待，让某一个线程等待知道倒计时结束，再开始执行。

```java
CountDownLatch(int count);
CountDownLatch#countdown();
CountDownLatch#wait();
```

#### 2.6 CyclicBarrier循环栅栏  

1. 与CountDownLatch类似，可以实现线程间的计数等待。
2. Latch：阻止线程继续执行，要求线程在栅栏处等待。
3. Cyclic：计数器可以反复使用。

```java
//barrierAction就是当计数器一次计数完成后，系统会执行的动作。
public CyclicBarrier(int parties,Runnable barrierAction);
```

#### 2.7 FutureTask（闭锁）  


#### 2.8 Exchanger栅栏  


#### 2.9 LockSupport阻塞工具  

1. 线程阻塞工具，使用了类似信号量的机制。可以在线程内任意位置让线程阻塞。
2. 与Thread.suspend()相比，弥补了由于resume()在前发生，导致线程无法继续执行的情况。
3. 与Object#wait()相比，不需要先获得某个对象的锁，也不会抛出InterruptedException。
4. LockSuppor.park()：阻塞当前线程，类似还有parkNanos()、parkUtil()等。