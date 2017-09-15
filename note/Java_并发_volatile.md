#### 参考文章

1. [浅析Java并发编程（二）synchronized & volatile](http://www.jianshu.com/p/7713f95b1a67)
2. [聊聊并发（1）：深入分析Volatile的实现原理](http://www.importnew.com/17394.html)
3. [内存屏障与JVM并发](http://www.infoq.com/cn/articles/memory_barriers_jvm_concurrency)
4. [《深入理解JVM》]()
5. []()

#### JVM的轻量级volatile

>volatile：保证可见性，不保证原子性。

如果一个共享变量被声明成volatile，java线程内存模型将会确保所有线程看到这个变量的值是一致的。

基本策略: 写操作时，会有Lock前缀指定，处理器会立马将修改直接写回系统内存，并且其他处理器会将该值在其上的高速缓存标为无效。
可能带来的性能消耗: 写操作实时写回内存，锁总线/锁内存。
优势: 一些场景上相比synchronized，执行成本更低(不会引起线程上下文切换以及调度)，使用更方便。

#### 缓存一致性问题

- 硬件速度差异->引入缓存机制->缓存一致性问题->缓存一致性协议！

缓存一致性协议有：MSI, MESI, MOSI, Synapse, Firefly, Dragon Protocol。

#### 乱序执行优化

1. 处理器的乱序执行优化；
2. 编译器的指令重排序优化。


### Java内存模型

Java内存模型：屏蔽掉各种硬件和OS的内存访问差异。

主要目标：定义程序中各个变量的访问规则。

- 线程间：主内存，Java堆
- 线程内：工作内存，Java方法栈

#### 1 Java内存间的操作

8种基本操作：

1. lock
2. unlock
3. read
4. load
5. use
6. assign
7. store
8. write

JMM规定了上述8种操作的执行限定。


操作规则限定+volatile特殊限定->确定了Java程序中哪些内存访问操作在并发下是安全的。

#### 2 volatile特殊规则

1. 保证线程可见性（内存立即写回）：插入内存屏障，lock前缀，无效化cache，对volatile的修改CPU立即可见；
2.  禁止重排序：内存屏障->指令重排序无法越过内存屏障。

性能消耗：volatile写>volatile读（因为volatile写插入了大量内存屏障）。

JMM对volatile的特殊规则：
1. load->use:主内存刷新
2. assign->store:同步回主内存
3. use,assign:load,store:保证不会指令重排序优化

#### 3 long和double的非原子协定

JVM选择对long和double类型（64位）变量读写实现为具有原子性的操作。

#### 4 原子性、可见性和有序性

1. 原子性
    - 非原子协定
    - 原子性保证（lock，unlock->monitorenter，monitorexit->synchronized）
2. 可见性
    - volatile：内存屏障，主内存刷新
    - synchronized：同步块，lock，unlock
    - final：构造器中初始化完成，没有this引用逸出
3. 有序性：
    1. 本线程内操作有序：线程内表现为串行的语义
    2. 外线程观察操作无序：指令重排序+工作内存与主内存同步延迟
    - volatile：禁止指令重排序的语义
    - synchronized：一个变量在同一时刻只允许一条线程对其进行lock操作


#### 5 先行发生原则（happens-before）

判断数据是否存在竞争、线程是否安全的主要依据。

先行发生是在JMM中定义了两项操作之间的偏序关系——“影响”包括修改了内存中共享变量的值、发送了消息、调用了方法。

先行发生关系：无须任何同步器协助就已经存在。

1. 程序次序
2. Monitor锁
3. volatile写读
4. 线程启动、终止、中断
5. fanilize()
6. 传递性