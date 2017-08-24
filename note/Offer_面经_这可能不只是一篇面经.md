#### 参考文章

1. [这可能不只是一篇面经](https://www.nowcoder.com/discuss/29890)

#### 算法和数据结构

1. 数组、链表、二叉树、队列、栈的各种操作（性能，场景）
2. 二分查找和各种变种的二分查找
3. 各类排序算法以及复杂度分析（快排、归并、堆）
4. 各类算法题（手写）
5. 理解并可以分析时间和空间复杂度。
6. 动态规划（笔试回回有。。）、贪心。
7. 红黑树、AVL树、Hash树、Tire树、B树、B+树。
8. 图算法（比较少，也就两个最短路径算法理解吧）
 
#### 计算机网络

1. OSI7层模型（TCP4层）
    - 每层的协议
    - url到页面的过程
2. HTTP
    - http/https 1.0、1.1、2.0
    - get/post 以及幂等性
    - http 协议头相关
    - 网络攻击（CSRF、XSS）
3. TCP/IP
    - 三次握手、四次挥手
    - 拥塞控制（过程、阈值）
    - 流量控制与滑动窗口
    - TCP与UDP比较
    - 子网划分（一般只有笔试有）
    - DDos攻击
4. (B)IO/NIO/AIO
    - 三者原理，各个语言是怎么实现的
    - Netty
    - Linux内核select poll epoll
 
#### 数据库（最多的还是mysql，Nosql有redis）

1. 索引（包括分类及优化方式，失效条件，底层结构）
2. sql语法（join，union，子查询，having，group by）
3. 引擎对比（InnoDB，MyISAM）
4. 数据库的锁（行锁，表锁，页级锁，意向锁，读锁，写锁，悲观锁，乐观锁，以及加锁的select sql方式）
5. 隔离级别，依次解决的问题（脏读、不可重复读、幻读）
6. 事务的ACID
7. B树、B+树
8. 优化（explain，慢查询，show profile）
9. 数据库的范式。
10. 分库分表，主从复制，读写分离。
11. Nosql相关（redis和memcached区别之类的，如果你熟悉redis，redis还有一堆要问的）

#### 操作系统：

1. 进程通信IPC（几种方式），与线程区别
2. OS的几种策略（页面置换，进程调度等，每个里面有几种算法）
3. 互斥与死锁相关的
4. linux常用命令（问的时候都会给具体某一个场景）
5. Linux内核相关（select、poll、epoll）

#### 编程语言（这里只说Java）：

1. 把我之后的面经过一遍，Java感觉覆盖的就差不多了，不过下面还是分个类。
2. Java基础（面向对象、四个特性、重载重写、static和final等等很多东西）
3. 集合（HashMap、ConcurrentHashMap、各种List，最好结合源码看）
4. 并发和多线程（线程池、SYNC和Lock锁机制、线程通信、volatile、ThreadLocal、CyclicBarrier、Atom包、CountDownLatch、AQS、CAS原理等等）
5. JVM（内存模型、GC垃圾回收，包括分代，GC算法，收集器、类加载和双亲委派、JVM调优，内存泄漏和内存溢出）
6. IO/NIO相关
7. 反射和代理、异常、Java8相关、序列化
8. 设计模式（常用的，jdk中有的）
9. Web相关（servlet、cookie/session、Spring<AOP、IOC、MVC、事务、动态代理>、Mybatis、Tomcat、Hibernate等）
10. 看jdk源码
