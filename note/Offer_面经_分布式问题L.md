#### Mamcache与Redis的区别？

1. [MongoDB 或者 redis 可以替代 memcached 吗?](https://www.zhihu.com/question/19645807)
2. [论述Redis和Memcached的差异](https://yq.aliyun.com/articles/60981?utm_campaign=wenzhang&utm_medium=article&utm_source=QQ-qun&utm_content=m_10100#)

和memcached更为接近的是redis。它们都是内存型数据库，数据保存在内存中，通过tcp直接存取，优势是速度快，并发高，缺点是数据类型有限，查询功能不强，一般用作缓存。在我们团队的项目中，一开始用的是memcached，后来用redis替代。

相比memcached：
1. redis具有持久化机制，可以定期将内存中的数据持久化到硬盘上。
2. redis具备binlog功能，可以将所有操作写入日志，当redis出现故障，可依照binlog进行数据恢复。
3. redis支持virtual memory，可以限定内存使用大小，当数据超过阈值，则通过类似LRU的算法把内存中的最不常用数据保存到硬盘的页面文件中。
4. redis原生支持的数据类型更多，使用的想象空间更大。
5. 前面有位朋友所提及的一致性哈希，用在redis的sharding中，一般是在负载非常高需要水平扩展时使用。我们还没有用到这方面的功能，一般的项目，单机足够支撑并发了。redis 3.0将推出cluster，功能更加强大。
6. redis更多优点，请移步官方网站查询。

#### 分布式协议

[《大规模分布式存储系统》]()

两阶段提交协议（2PC）保证多个节点操作的**原子性**，用来实现分布式事务。

1. 协调者
2. 参与者

Paxos协议：解决多个节点之间的**一致性**问题。
Paxos与2PC：副本一致性+操作原子性。

2PC与Paxos的相互作用：

1. 2PC保证多个数据分片上的操作原子性
2. Paxos解决2PC协议中协调者宕机问题