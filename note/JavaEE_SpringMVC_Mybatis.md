### 2016年8月25日17:16:11     《Java EE企业应用实战》 P705

1. 从用户的角度来看：
  - 用户发出HTTP请求，当MVC框架的controller组建拦截到用户请求时
  - ->将调用系统的业务逻辑组件
  - ->业务逻辑组件将调用系统的DAO组件
  - ->DAO组件则依赖于sessionfactory和datasource等底层组件实现数据库访问。
2. 从系统实现角度：
  - ioc容器先创建sessionfactory和datasource等底层组件
  - ->然后将底层组件注入到dao组件，提供一个完整的dao组件
  - ->并将此dao组件注入给业务逻辑组件，从而提供一个完整的业务逻辑组件
  - ->而业务逻辑组件又注入给控制器组件，控制器组件负责拦截器用户请求
  - ->并将处理结果呈现给用户。
    
这一系列的衔接都是由spring的ioc容器提供的！

### 2016年8月26日20:10:58 Java就业班

![Image-javaee-springmvc-mybatis-1.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-springmvc-mybatis-1.png)


### 2016年8月30日10:02:38     StackOverflow

In my opinion, you have to distinguish between the MVC pattern and the 3-tier architecture. To sum up:

3-tier architecture:（三层架构）

- data: persisted data;
- service: logical part of the application;
- presentation: hmi, webservice...

The MVC pattern takes place in the presentation tier of the above architecture (for a webapp):
MVC模式取代了三层架构中的表示层。

- data: ...;持久层！
- service: ...;业务逻辑层！
- presentation:表示层！
  - controller: intercepts the HTTP request and returns the HTTP response;控制器：拦截请求和返回应答
  - model: stores data to be displayed/treated;模型：存储数据用来展示和处理   
  - view: organises output/display.视图：组织输出和显示。

Life cycle of a typical HTTP request:

- The user sends the HTTP request;用户发请求
- The controller intercepts it;控制器拦截
- The controller calls the appropriate service;控制器调用相应业务
- The service calls the appropriate dao, which returns some persisted data (for example);业务调用相应的dao，dao返回持久数据给业务
- The service treats the data, and returns data to the controller;业务处理dao返回的数据，并返回数据给控制层
- The controller stores the data in the appropriate model and calls the appropriate view;控制层存储数据到相应的模型中，并调用相应的视图
- The view get instantiated with the model's data, and get returned as the HTTP response.模型数据实例化视图，作为http应答返回给用户！

### 2017年2月17日10:14:51     传智播客-燕青

先Mybatis负责持久层，让Mybatist同Spring整合。Dao，Service托管给Spring，持久层细节交给Mybatis。

**注意！！！此时还不是web项目，只是用Spring+Mybatis完成了Bean管理，数据存取，事物管理。**

转化成为Web工程，才需要到了SpringMVC框架！！

普通项目->Web项目：配置Web.xml，加入SpringMVC框架的中央处理器->DisparaterServlet：控制所有请求的转发。

![Image-javaee-springmvc-mybatis-2.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-springmvc-mybatis-2.png)

第一步：整合 dao 层
     mybatis 和 spring 整合，通过 spring 管理 mapper 接口。
     使用 mapper 的扫描器自动扫描 mapper 接口在 spring 中进行注册。
第二步：整合 service 层
     通过 spring 管理 service 接口。
     使用配置方式将 service 接口配置在 spring 配置文件中。
     实现事务控制。
第三步：整合 springmvc
     由于 springmvc 是 spring 的模块，不需要整合。

Mybatis能完成结果集到pojo的自动映射；
SpringMVC能完成自动的参数绑定；
为什么南软要用hashmap手动映射和绑定？开发效率低，一旦有数据库字段变化，全部手动改变！ 面向对象编程，面向变化编程，面向接口编程！

- ->ORM框架的意义就在于 对象关系模型，将数据库 同 对象 相关联起来，以解放程序员的负担。
- ->SpringMVC框架 完成 控制器-模型-视图 三者的分治统一，让程序员关注于自己的业务逻辑！层层调用，面向对象。


### Mybatis框架：

第一天：基础知识（重点，内容量多）
  对原生态jdbc程序（单独使用jdbc开发）问题总结
  mybatis框架原理 （掌握）
  mybatis入门程序
       用户的增. 删. 改. 查
  mybatis开发dao两种方法：
  原始dao开发方法（程序需要编写dao接口和dao实现类）（掌握）
  mybaits的mapper接口（相当于dao接口）代理开发方法（掌握）
  mybatis配置文件SqlMapConfig.xml
  mybatis核心：
  mybatis输入映射（掌握）
  mybatis输出映射（掌握）
mybatis的动态sql（掌握）

第二天：高级知识
  订单商品数据模型分析
  高级结果集映射（一对一. 一对多. 多对多）
  mybatis延迟加载
  mybatis查询缓存（一级缓存. 二级缓存）
  mybaits和spring进行整合（掌握）
  mybatis逆向工程

#### 原生JDBC开发的弊端：
1. 数据库连接，使用时就创建，不使用立即释放，对数据库进行频繁连接开启和关闭，造成数据库资源浪费，影响 数据库性能。
设想：使用数据库连接池管理数据库连接。
2. 将sql语句**硬编码**到java代码中，如果sql语句修改，需要重新编译java代码，不利于系统维护。
设想：将**sql语句**配置在xml配置文件中，即使sql变化，不需要对java代码进行重新编译。
3. 向preparedStatement中设置参数，对占位符号位置和设置参数值，硬编码在java代码中，不利于系统维护。
设想：将sql语句及占位符号和参数全部配置在xml中。
4. 从resutSet中遍历结果集数据时，存在硬编码，将获取表的字段进行硬编码，，不利于系统维护。
设想：将查询的结果集，自动映射成java对象。

->数据库连接浪费，硬编码不利用维护和扩展 ，手动结果集映射，->引出ORM框架来解决这些问题！**（新技术的出现都是为了解决旧技术不能应对的问题！）**

#### 框架原理：

![Image-javaee-springmvc-mybatis-3.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-springmvc-mybatis-3.png)

不理解原始DAO开发的痛苦->怎么能体会到Mapper接口代理开发的便利！

原始dao开发方法
     （程序需要编写dao接口和dao实现类）（掌握）：需要配置dao.java;mapper.java;mapper.xml，比较繁琐。
     根据Mybatis配置文件->获得会话工厂SqlSessionFactory->SqlSession->sqlSession.selectOne();sqlSession.selectList()。
     
总结原始 dao开发问题

1. dao接口实现类方法中存在大量模板方法，设想能否将这些代码提取出来，大大减轻程序员的工作量。

2. 调用sqlsession方法时将statement的id硬编码了

3. 调用sqlsession方法时传入的变量，由于sqlsession方法使用泛型，即使变量类型传入错误，在编译阶段也不报错，不利于程序员开发。

->
mybaits的mapper接口
     （相当于dao接口）代理开发方法（掌握）：
     程序员只需要根据dao接口（mapper接口）书写mapper.xml就行了，mybatis-spring整合包，自动扫描mapper.xml。
     程序员还需要编写mapper.xml映射文件
     程序员编写mapper接口需要遵循一些开发规范，mybatis可以自动生成mapper接口实现类代理对象。

开发规范：
1. 在mapper.xml中namespace等于mapper接口地址

2. mapper.java接口中的方法名和mapper.xml中statement的id一致

3. mapper.java接口中的方法输入参数类型和mapper.xml中statement的parameterType指定的类型一致。

4. mapper.java接口中的方法返回值类型和mapper.xml中statement的resultType指定的类型一致。

总结：
以上开发规范主要是对下边的代码进行统一生成：

User user = sqlSession.selectOne("test.findUserById", id);
sqlSession.insert("test.insertUser", user);

所以，代理对象内部还是调用了selectOne后者selectList完成数据库查询的。

输入映射. 输出映射：
->输入映射：将java简单类型. hashmap. 包装对象pojo 转化成为 sql语句的参数。java类型->sql类型。
->输出映射：将sql结果集 转化成为 java语言的对象。
     手动映射：column->property
     自动映射：列名->pojo类型的属性名，一一对应。

使用resultType进行输出映射，只有查询出来的列名和pojo中的属性名一致，该列才可以映射成功。
如果查询出来的列名和pojo的属性名不一致，通过定义一个resultMap对列名和pojo属性名之间作一个映射关系。

这样一个语句简单作用于所有列被自动映射到 HashMap 的键上,这由 resultType 属性 指定。这在很多情况下是有用的,但是 HashMap 不能很好描述一个领域模型。那样你的应 用程序将会使用 JavaBeans 或 POJOs(Plain Old Java Objects,普通 Java 对象)来作为领域 模型。MyBatis 对两者都支持。


#### SpringMVC框架：

![Image-javaee-springmvc-mybatis-4.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-springmvc-mybatis-4.png)

![Image-javaee-springmvc-mybatis-5.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-springmvc-mybatis-5.png)

- DispatcherServlet 前端控制器：接收 request，进行 response
- HandlerMapping 处理器映射器：根据 url 查找 Handler。（可以通过 xml 配置方式，注解方式）
- HandlerAdapter 处理器适配器：根据特定规则去执行 Handler，编写 Handler 时需要按照 HandlerAdapter 的要求去编写。
- Handler 处理器（后端控制器）：需要程序员去编写， 常用注解开发方式。
- Handler 处理器执行后结果 是 ModelAndView，具体开发时 Handler 返回方法值类型包括 ：ModelAndView. String（逻辑视图名）、 void（通过在 Handler 形参中添加 request 和 response，类似原始 servlet 开发方式，注意：可以通过指定 response 响应的结果类型实现 json 数据输出）
- View resolver 视图解析器：根据逻辑视图名生成真正的视图（在 springmvc 中使用 View 对象表示）
- View 视图:jsp 页面，仅是数据展示，没有业务逻辑。

组件：Handler=Controller

1. 前端控制器 DispatcherServlet（不需要程序员开发）
作用接收请求，响应结果，相当于转发器，中央处理器。
有了 DispatcherServlet 减少了其它组件之间的耦合度。
2、 处理器映射器 HandlerMapping(不需要程序员开发)
作用：根据请求的 url 查找 Handler（mapping到handler=映射到handler）
3. 处理器适配器 HandlerAdapter
作用：按照特定规则（ HandlerAdapter 要求的规则）去执行 Handler（adapter到hander=适配到多个handler）
4. 处理器 Handler(需要程序员开发)
注意：编写 Handler 时按照 HandlerAdapter 的要求去做，这样适配器才可以去正确执行 Handler
5. 视图解析器 View resolver(不需要程序员开发)
作用：进行视图解析，根据逻辑视图名解析成真正的视图（ view）
6. 视图 View(需要程序员开发 jsp)
View 是一个接口，实现类支持不同的 View 类型（ jsp、 freemarker、 pdf...）

