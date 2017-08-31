#### 参考文章


2. [《Java并发包源码学习之AQS框架（一）概述》](http://jindong.io/2015/03/10/java-concurrent-package-aqs-overview/)
3. [《Java并发包源码学习之AQS框架（二）CLH lock queue和自旋锁》](http://jindong.io/2015/03/11/java-concurrent-package-aqs-clh-and-spin-lock/)
4. [《Java并发包源码学习之AQS框架（三）LockSupport》](http://jindong.io/2015/03/14/java-concurrent-package-aqs-locksupport-and-thread-interrupt/)
1. [Java并发包源码学习之AQS框架（四）AbstractQueuedSynchronizer源码分析](http://zhanjindong.com/2015/03/15/java-concurrent-package-aqs-AbstractQueuedSynchronizer)
2. [Java并发框架——什么是AQS框架](https://www.kancloud.cn/seaboat/java-concurrent/117874)
3. [再谈AbstractQueuedSynchronizer1：独占模式](http://www.cnblogs.com/xrq730/p/7056614.html)
4. [再谈AbstractQueuedSynchronizer2：共享模式与基于Condition的等待/通知机制实现](http://www.cnblogs.com/xrq730/p/7067904.html)
5. [再谈AbstractQueuedSynchronizer3：基于AbstractQueuedSynchronizer的并发类实现](http://www.cnblogs.com/xrq730/p/7096084.html)
6. [深入并发包-AQS](http://pettyandydog.com/2017/06/27/AQS/index.html)
7. [JAVA并发编程学习笔记之CLH队列锁](http://blog.csdn.net/aesop_wubo/article/details/7533186)

### 正文

JDK的并发包提供了各种锁及同步机制，其实现的核心类是AbstractQueuedSynchronizer，我们简称为AQS框架，它为不同场景提供了实现锁及同步机制的基本框架，为同步状态的原子性管理、线程的阻塞、线程的解除阻塞及排队管理提供了一种通用的机制。

AQS将线程封装到一个Node里面，并维护一个CHL Node FIFO队列，它是一个非阻塞的FIFO队列，也就是说在并发条件下往此队列做插入或移除操作不会阻塞，是通过自旋锁和CAS保证节点插入和移除的原子性，实现无锁快速插入。

其实AbstractQueuedSynchronizer主要就是维护了一个state属性、一个FIFO队列和线程的阻塞与解除阻塞操作。state表示同步状态，它的类型为32位整型，对state的更新必须要保证原子性。这里的队列是一个双向链表，每个节点里面都有一个prev和next，它们分别是前一个节点和后一个节点的引用。需要注意的是此双向链表除了链头其他每个节点内部都包含一个线程，而链头可以理解为一个空节点。


AQS其实就是java.util.concurrent.locks.AbstractQueuedSynchronizer这个类。 阅读Java的并发包源码你会发现这个类是整个java.util.concurrent的核心之一，也可以说是阅读整个并发包源码的一个突破口。


AQS简核心是通过一个共享变量来同步状态，变量的状态由子类去维护，而AQS框架做的是：
- 线程阻塞队列的维护
- 线程阻塞和唤醒


共享变量的修改都是通过Unsafe类提供的CAS操作完成的。AbstractQueuedSynchronizer类的主要方法是acquire和release，典型的模板方法， 下面这4个方法由子类去实现：

```java
protected boolean tryAcquire(int arg)
protected boolean tryRelease(int arg)
protected int tryAcquireShared(int arg)
protected boolean tryReleaseShared(int arg)
```

acquire方法用来获取锁，返回true说明线程获取成功继续执行，一旦返回false则线程加入到等待队列中，等待被唤醒，release方法用来释放锁。 一般来说实现的时候这两个方法被封装为lock和unlock方法。