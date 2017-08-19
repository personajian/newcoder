>


简历中项目要这样写：（具体解决了什么问题）
独立开发多线程影片信息爬虫工具， 并针对线程池性能、网络异常以及反爬虫措施进行多次优化，容错性良好并达到并发请求30+的QPS。    

### 网络通信


一个url到页面全过程（让我能说多详细说多详细，最好从OSI七层的每一层去扩展）
    
    
http的请求头格式（这个真的记不太清了，只说了几个有印象的标志位）


getpost区别，post可不可以用url的方式传参。


说到了url有最大长度，就问长度有限制是get的原因还是url的原因，为什么长度会有限制，是http数据包的头的字段原因还是内容字段的原因，详细说明。（在他一步步追问下答了个差不多）


关于幂等性的详细介绍。


幂等性是http层面的问题吗，还是服务器要处理和解决的内容。（就是看你对幂等性的定性是怎么理解的）


后台服务器对于一个请求是如何做负载均衡的，有哪些策略，会出现什么样的问题，怎么解决。（说了一致性hash算法，分布式hash的特性,具体的应用场景，又非要问我知不知道这个最早在哪个公司使用的...我说这个真不知道。好像是amazon?）


说说http的缺点，无状态，明文传输。


那https是怎么做的，如何实现的？ca认证机构。


然后问我https ssl tcp三者关系，其中哪些用到了对称加密，哪些用到了非对称加密，非对称加密密钥是如何实现的。（还好我项目中涉及到了一些加密）


关于加密的私钥和公钥各自如何分配（客户端拿公钥，服务器拿私钥）


那客户端是如何认证服务器的真实身份，详细说明一下过程，包括公钥如何申请，哪一层加密哪一层解密。

>OSI七层网络模型？

>TCP/IP

>TCP三次握手、四次挥手？

>滑动窗口

>http 

>request 

>response

>get post 幂等性？

>RESTful>

>RPC

>一个url到页面全过程

>登陆验证？MD5+salt？SSO单点登陆？


### Java基础

>异常机制？

又问了异常体系，checked unchecked虚拟机原理怎么做。


### Java集合类

>HashMap原理？

1. 数据结构：数组+链表+红黑树（JDK1.8引入红黑树）；初始长度16；负载因子默认0.75
2. 哈希函数：hashCode()的高16位异或低16位
3. 解决冲突：链地址法——链表，链表长度超过8，转换为红黑树
4. 扩容：resize()，2次幂扩展，原索引+原数组大小；JDK 1.7中扩容时链表会倒置；
5. 线程安全：不安全，请使用ConcurrentHashMap

>ArrayList原理？

>LinkedList原理？


java的优先级队列，如果让你设计一个数据结构实现优先级队列如何做？

### 高性能IO

NIO、Netty、AIO、异步IO想问消息队列

### 多线程

>进程线程区别？


>进程间、线程间的通信方式？

进程间通信方式有：消息队列、共享内存、管道机制。
线程间通信方式有：
1. 共享内存机制：synchronized同步、while轮询、wait/notify机制；
2. 消息通信机制：管道通信（java.io.PipedInputStream, java.io.PipeOutputStream）、方法回调（Callable，Future）、各种同步队列阻塞队列、事件驱动、函数式编程

>Java中notify 和 notifyAll有什么区别？

1. notify()方法不能唤醒某个具体的线程，所以只有一个线程在等待的时候它才有用武之地。
2. 而notifyAll()唤醒所有线程并允许他们争夺锁确保了至少有一个线程能继续运行。

>wait() notify() notifyAll()是Object方法，而不是Thread的方法？

```java
Object#wait()
Object#notify()
Object#ontifyAll()
Thread.run()
Thread.yeild()
Thread.sleep()
Thread.join()
Thread.suspend()
Thread.suspend()
Thread.interrupt()
```

1. 监视器、锁的主体是对象！
2. JAVA提供的锁是对象级的而不是线程级的，每个对象都有锁，通过线程获得。
3. 如果线程需要等待某些锁那么调用对象中的wait()方法就有意义了。
4. 如果wait()方法定义在Thread类中，线程正在等待的是哪个锁就不明显了。
5. 简单的说，由于wait，notify和notifyAll都是锁级别的操作，所以把他们定义在Object类中因为锁属于对象。

>悲观锁 乐观锁

1. 悲观锁：独占锁synchronized。线程阻塞，上下文切换，中断，用户态内核态切换。数据库中的排它锁。
2. 乐观锁：每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止。CAS操作->出现ABA问题。

>并发容器？

>线程池？



JUC相关的集合，ConcurrentHashMap jdk7和jdk8的区别，Collections.sort函数jdk7 和 jdk8 分别怎么实现的。（总感觉这个面试官在某段时间肯定纠结过两个版本）
      

CopyOnWriteList底层是什么，适用的情况，vector的特点，实现的是List接口吗。

锁的膨胀过程，Synchronized和Lock的区别，底层的monitor实现和unsafe类的CAS函数，参数表示什么，寄存器cpu如何做）

volatile cpu和寄存器层面是怎么实现的。

线程池构造函数参数，各种类型的预设池各自的特点，ForkJoinPool是怎么实现的，多线程等等问了一个遍。



### JVM



>内存模型


>垃圾回收


>分代及回收算法


>哪些作为gc root


>收集器的特点分类


>类加载机制和双亲委派模型


>几个加载器


>tomcat类加载有什么不同，说加载顺序并不是双亲模型，具体顺序说一下

### JavaEE框架

>如何理解Spring中bean的作用域？

1. singleton：无状态的bean
2. prototype：有状态的bean

>Spring中单例bean如何实现线程安全？

1. ThreadLocal：利用ThreadLocal来实现Dao的线程安全，空间换时间，数据隔离，数据不共享。
2. 内部实现：Map键值对，存储了Map<ThreadId,value>值，每个线程都有一个变量副本。

>SpringMVC如何实现单例的多线程安全？

SpringMVC响应请求是基于方法调用的，方法是基于线程栈帧，线程私有，不共享变量，于是不存在线程安全问题。

1. 让线程存在于自己的世界中，不与其他线程共享数据。
2. 有过Java Web开发经验的人都知道，Servlet就是以单实例多线程的方式工作，和每个请求相关的数据都是通过Servlet子类的service方法（或者是doGet或doPost方法）的参数传入的。只要Servlet中的代码只使用局部变量，Servlet就不会导致同步问题。
3. Spring MVC的控制器也是这么做的，从请求中获得的对象都是以方法的参数传入而不是作为类的成员，
4. 很明显Struts 2的做法就正好相反，因此Struts 2中作为控制器的Action类都是每个请求对应一个实例。

>Spring IOC？

>Spring AOP？

1. 前置、后置、环绕、引介
2. 连接点
3. 切点
4. 增强
5. 切面：

关于AOP在spring的应用（比如事务，通知，aspectJ，slf4j的原理,和log4j的对比）

关于jdk代理和cglib第三方代理（说出对接口代理和子类继承的区别）

>Mybatis？


### 数据库

  

主键索引和普通索引的区别，组合索引怎么用会失效。


索引的前缀匹配的原理，从B树的结构上具体分析一下。


聚集索引在底层怎么实现的，数据和关键字是怎么存的。


组合索引和唯一性索引在底层实现上的区别（这个是整个一面感觉答得不好的一个问题，不太明白面试官想问啥）


sql的优化策略，慢查询日志怎么操作，参数含义。


explain 每个列代表什么含义（关于优化级别 ref 和 all，什么时候应该用到index却没用到，关于extra列出现了usetempory 和 filesort分别的原因和如何着手优化等）


show profile 怎么使用。

### 分布式


>消息队列MQ


>分布式缓存


>缓存系统的设计，kv原理，分布式缓存redis、memcashed


>负载均衡？一致性hash算法，分布式hash的特性,具体的应用场景，


### 云计算

解释下iaas.paas.saas和之间的关系，外呼接口和服务怎么调用的。


### 大数据