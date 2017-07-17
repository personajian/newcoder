[TOC]

## 参考文章

- [并发队列-有界阻塞队列ArrayBlockingQueue原理探究](http://www.jianshu.com/p/ff116eaad0dd)

## 总结

- ArrayBlockingQueue通过使用**全局独占锁**实现同时只能有一个线程进行入队或者出队操作，这个锁的粒度比较大，有点类似在方法上添加synchronized的意味。
- 其中offer,poll操作通过**简单的加锁**进行入队出队操作，
- 而put,take则使用了条件变量实现如果队列满则等待，如果队列空则等待，然后分别在出队和入队操作中发送信号激活等待线程实现同步。
- 另外相比LinkedBlockingQueue，ArrayBlockingQueue的size操作的结果是精确的，因为计算前加了全局锁。