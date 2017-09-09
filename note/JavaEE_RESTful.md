### HTTP幂等性

旨在取代分布式事务（开销比较大）。

幂等性在数学上的定义：N次变换与1次变换的结果相同。

定义：一次和多次请求某一个资源具有相同的副作用。

HTTP方法中：

1. GET：幂等
2. POST：非幂等（创建新资源）
3. PUT：幂等（更新资源）
4. DELETE：幂等

### RESTful

Resource Representational State Transfer（资源表现状态转移）

1. Url——要什么
2. http method——干什么
3. http status code——结果如何