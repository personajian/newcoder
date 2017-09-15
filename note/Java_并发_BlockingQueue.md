[TOC]

#### 参考文章

- [并发队列-有界阻塞队列ArrayBlockingQueue原理探究](http://www.jianshu.com/p/ff116eaad0dd)
- [并发队列-无界阻塞优先级队列PriorityBlockingQueue原理探究](www.jianshu.com/p/fdf2163073d1
)

#### 阻塞队列

阻塞队列（BlockingQueue）是支持两个附加操作的队列。分别是：

1. 阻塞的插入：put()
2. 阻塞的移除：take()

常用于生产者-消费者模式。

JDK中提供7种阻塞队列：

1. ArrayBlockingQueue：数组，有界
2. LinkedBlockingQueue：链表，有界
3. PriorityBlockingQueue：优先级排序，无界
4. DelayQueue：优先级队列实现，无界
5. SynchronousQueue：不存储元素
6. LinkedTransferQueue：链表，无界
7. LinkedBlockingDeque：链表，双向

#### 总结

- ArrayBlockingQueue通过使用**全局独占锁**实现同时只能有一个线程进行入队或者出队操作，这个锁的粒度比较大，有点类似在方法上添加synchronized的意味。
- 其中offer,poll操作通过**简单的加锁**进行入队出队操作，
- 而put,take则使用了条件变量实现如果队列满则等待，如果队列空则等待，然后分别在出队和入队操作中发送信号激活等待线程实现同步。
- 另外相比LinkedBlockingQueue，ArrayBlockingQueue的size操作的结果是精确的，因为计算前加了全局锁。