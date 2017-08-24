POJO是PO、BO、VO、DTO的统称，是一个抽象的概念，仅表示简单java对象。在具体项目的不同层次上延伸出了具体的称呼——PO、BO、VO、DTO。

PO：persistant object持久对象
BO：business object业务对象
VO ：value object值对象 ViewObject表现层对象
DTO ：Data Transfer Object数据传输对象
POJO ：plain ordinary java object 简单java对象

一个POJO持久化以后就是PO
直接用它传递、传递过程中就是DTO
直接用来对应表示层就是VO

DAO：
data access object数据访问对象
这个大家最熟悉，和上面几个O区别最大，基本没有互相转化的可能性和必要.
主要用来封装对数据库的访问。通过它可以把POJO持久化为PO，用PO组装出来VO、DTO

注意：（正是orm框架的出现，才导致了po概念的出现，完成数据表对应于对象的映射。）
O/R Mapping 是Object Relational Mapping（对象关系映射）的缩写。通俗点讲，就是将对象与关系数据库绑定，用对象来表示关系数据。在O/R Mapping的世界里，有两个基本的也是重要的东东需要了解，即VO，PO。
VO，值对象(Value Object)，PO，持久对象(Persisent Object)，它们是由一组属性和属性的get和set方法组成。从结构上看，它们并没有什么不同的地方。但从其意义和本质上来看是完全不同的。
１．VO是用new关键字创建，由GC回收的。
PO则是向数据库中添加新数据时创建，删除数据库中数据时削除的。并且它只能存活在一个数据库连接中，断开连接即被销毁。
２．VO是值对象，精确点讲它是业务对象，是存活在业务层的，是业务逻辑使用的，它存活的目的就是为数据提供一个生存的地方。
PO则是有状态的，每个属性代表其当前的状态。它是物理数据的对象表示。使用它，可以使我们的程序与物理数据解耦，并且可以简化对象数据与物理数据之间的转换。
３．VO的属性是根据当前业务的不同而不同的，也就是说，它的每一个属性都一一对应当前业务逻辑所需要的数据的名称。
PO的属性是跟数据库表的字段一一对应的。PO对象需要实现序列化接口。
