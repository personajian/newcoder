[TOC]

### 同步容器

#### 原末

A:同步容器->串行化访问容器状态->保证线程的安全性->严重降低了并发性，吞吐量严重降低。

B:

S:对多线程并发访问设计，提供了并发性能较好的并发容器，引入了java.util.concurrent包。

主要解决了两个问题： 

1. 避免synchronized，提供并发性。 
2. 并发安全的复合操作，保证并发环境下的迭代操作不会出错。
util.concurrent中容器在迭代时，可以不封装在synchronized中，可以保证不抛异常，但是未必每次看到的都是"最新的、当前的"数据。

#### 并发容器的种类和作用

- ConcurrentHashMap代替同步的Map（Collections.synchronized（new HashMap()）），众所周知，HashMap是根据散列值分段存储的，同步Map在同步的时候锁住了所有的段，而ConcurrentHashMap加锁的时候根据散列值锁住了散列值锁对应的那段，因此提高了并发性能。ConcurrentHashMap也增加了对常用复合操作的支持，比如"若没有则添加"：putIfAbsent()，替换：replace()。这2个操作都是原子操作。
- CopyOnWriteArrayList和CopyOnWriteArraySet分别代替List和Set，主要是在遍历操作为主的情况下来代替同步的List和同步的Set，这也就是上面所述的思路：迭代过程要保证不出错，除了加锁，另外一种方法就是"克隆"容器对象。
- ConcurrentLinkedQuerue是一个先进先出的队列。它是非阻塞队列。
- ConcurrentSkipListMap可以在高效并发中替代SoredMap（例如用Collections.synchronzedMap包装的TreeMap）。
- ConcurrentSkipListSet可以在高效并发中替代SoredSet（例如用Collections.synchronzedSet包装的TreeMap）。

#### ConcurrentHashMap

JDK 1.8之前，ConcurrentHashMap都是采用**锁分离（锁分段）技术**来实现线程安全的。

参考文章有：

1. [Java并发编程：并发容器之ConcurrentHashMap（转载）](http://www.cnblogs.com/dolphin0520/p/3932905.html)
2. [Java并发编程之ConcurrentHashMap](http://www.iteye.com/topic/1103980)
3. [ConcurrentHashMap之实现细节](http://www.iteye.com/topic/344876)
4. [聊聊并发（四）深入分析ConcurrentHashMap](http://ifeve.com/ConcurrentHashMap/)

因为分段锁技术已经被取代了，就不赘述。


而JDK 1.8中，ConcurrentHashMap却采用的是**CAS算法+synchronized内置锁**来实现线程安全的。

参考文章有：

1. [java内存模型](http://www.jianshu.com/p/d3fda02d4cae)
2. [java中的Unsafe](http://www.jianshu.com/p/a16d638bc921)
3. [java中的CAS](http://www.jianshu.com/p/fb6e91b013cc)
4. [深入浅出java同步器AQS](http://www.jianshu.com/p/d8eeb31bee5c)
5. [深入浅出ReentrantLock](http://www.jianshu.com/p/4358b1466ec9)
6. [《Java源码分析》：ConcurrentHashMap JDK1.8](http://blog.csdn.net/u010412719/article/details/52145145)
7. [ Java集合--JDK 1.8 ConcurrentHashMap 源码剖析](http://blog.csdn.net/qq924862077/article/details/74530103)
8. [jdk1.8的HashMap和ConcurrentHashMap](https://my.oschina.net/pingpangkuangmo/blog/817973)
9. [Java并发编程总结4——ConcurrentHashMap在jdk1.8中的改进](http://www.cnblogs.com/everSeeker/p/5601861.html)
10. [谈谈ConcurrentHashMap1.7和1.8的不同实现](http://www.jianshu.com/p/e694f1e868ec)


文章1,2,3,4,5分别是JDK 1.8实现线程安全依赖的底层技术。

>HashMap是线程不安全的。

>Hashtable是线程安全的。

采用在每个方法来添加了synchronized关键字来修饰，即Hashtable是针对整个table的锁定，这样就导致HashTable容器在竞争激烈的并发环境下表现出效率低下。

效率低下的原因说的更详细点：是因为所有访问HashTable的线程都必须竞争同一把锁。当一个线程访问HashTable的同步方法时，其他线程访问HashTable的同步方法时，可能会进入阻塞或轮询状态。如线程1使用put进行添加元素，线程2不但不能使用put方法添加元素，并且也不能使用get方法来获取元素，所以竞争越激烈效率越低。

基于Hashtable的缺点，人们就开始思考，假如容器里有多把锁，每一把锁用于锁容器其中一部分数据，那么当多线程访问容器里不同数据段的数据时，线程间就不会存在锁竞争，从而可以有效的提高并发访问效率呢？？这就是我们的“锁分离”技术，这也是ConcurrentHashMap实现的基础。

>ConcurrentHashMap使用的就是锁分段技术

ConcurrentHashMap由多个Segment组成(Segment下包含很多Node，也就是我们的键值对了)，每个Segment都有把锁来实现线程安全，当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。

因此，关于ConcurrentHashMap就转化为了对Segment的研究。这是因为，ConcurrentHashMap的get、put操作是直接委托给Segment的get、put方法，

>JDK 1.8中，采用的是**CAS算法+synchronized内置锁**来实现线程安全的。Segment虽保留，但已经简化属性，仅仅是为了兼容旧版本。

- CAS算法；unsafe.compareAndSwapInt(this, valueOffset, expect, update);  CAS(Compare And Swap)，意思是如果valueOffset位置包含的值与expect值相同，则更新valueOffset位置的值为update，并返回true，否则不更新，返回false。
- 与Java8的HashMap有相通之处，底层依然由“数组”+链表+红黑树；
- 底层结构存放的是TreeBin对象，而不是TreeNode对象；
- CAS作为知名无锁算法，那ConcurrentHashMap就没用锁了么？当然不是，hash值相同的链表的头结点还是会synchronized上锁。 