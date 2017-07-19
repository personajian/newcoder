[TOC]

#### 参考文章

1. [Servlet总结](http://www.iteye.com/topic/766418)
2. 马士兵Servlet

### 一、HTTP基础

#### 1.1 HTTP请求：

1. 一个请求行：请求方式（GET,POST...），请求内容（-.html），请求协议
2. 若干请求头：
	- Accept: 用于告诉服务器，客户机支持的数据类型
	- Accept-Charset:用于告诉服务器，客户机采用的编码
	- Accept-Encoding:用于告诉服务器，客户机支持的数据压缩格式
	- Accept-Language:客户机的语言环境
	- Host:用于告诉服务器，想访问的主机名
	- If-Modified-Since:用于告诉服务器，资源的缓存时间
	- Referer:用于告诉服务器，它从哪个资源来访问服务器的（防盗链）
	- User-Agent:客户机的软件环境
	- Cookie：用于给服务器带数据
	- Connection:close/Keep-Alive
	- Date:
	- Range：指示服务器只传输一部分web资源，实现断点下载
3. 实体内容：

#### HTTP响应：

1. 一个状态行：
	- HTTP版本号 状态码 原因描述<CRLF> HTTP/1.1 200 OK 用于描述服务器对请求的处理结果
	- 1xx消息 2xx成功 3xx重定向 4xx客户端错误 5xx服务器错误
2. 若干响应头：服务器的基本信息以及数据的描述，可以通过客户机如何处理回送的数据（打开、下载）
	- Location：这个头配合302状态码使用，用于告诉客户机找谁去
	- Server：服务器通过这个头告诉客户机，服务器的类型
	- Content-Encoding：gzip 服务器通过这个头，告诉浏览器数据采用的压缩格式
	- Content-Lenght：服务器通过这个头，告诉浏览器回送数据的长度
	- Content-Type：服务器通过这个头，告诉浏览器回送数据的类型（01010--浏览器解析->文字，图片...）
	- Last-Modified：服务器通过这个头，告诉浏览器当前资源缓存时间
	- Refresh：服务器通过这个头，告诉浏览器隔多长时间刷新一次（聊天室、5秒后跳转）
	- Content-Dispoisiton：服务器通过这个头，告诉浏览器以下载港式打开数据
	- Transfer-Encoding：服务器通过这个头，告诉浏览器数据的传送格式（块传送）
	- ETag：缓存相头的头（表示客户机、服务器资源修改了没有，缓存用到的，做到事实更行。）
	- Expires：服务器通过这个头，告诉浏览器把回送数据缓存多长时间，-1或0，则是不缓存
	- Cache-Control：no-cache
	- Pragma：no-cache 服务器通过以上两个头，也是控制浏览器不要缓存数据
	- Connection:close/Keep-Alive
	- Date:
	- Accept-Range：说明服务器是否支持Range（断点下载）
	- Content-Range：指定了返回的web资源的字节范围
3. 实体内容

### 二、马士兵Servlet

#### Web服务器：

- IIS：
- Apache：
- Tomcat：

#### 动态网页技术：

- CGI(Common Gateway Interface)
- API常用的有(NSAPI，ISAPI)
- ASP(AVtice Server Page)-->ASP.NET
- PHP(Personal Home Page)
- JSP/Servlet(Java Server Page)

#### Servlet简介

- Servlet是服务器小应用程序
- 用来完成B/S架构下，客户端请求的响应处理
- 平台独立，性能优良，能以线程方式运行
- Servlet API为Servlet提供了统一的编程接口
- Servlet一般在容器中运行

#### 常见的Servlet服务器：Tomcat服务器

- Window平台下的批处理应用
- startup.bat;shutdown.bat
- catalina.bat:catalina start;catalina stop;catalina debug
- 目录结构：
	- server.xml:服务器的主配置文件
```
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" />
```
	- web.xml:定义所有Web应用的配置
	- tomcat-users.xml:定义了tomcat用户的信息


### 四、Servlet总结

1. 什么是Servlet
2. Servlet有什么作用
3. Servlet的生命周期
4. Servlet怎么处理一个请求
5. Servlet与JSP有什么区别
6. Servlet里的cookie技术
7. Servlet里的过滤器
8. Servlet里的监听器

#### 什么是Servlet？

Servlet是一个Java编写的程序，此程序是基于Http协议的，在服务器端运行的(如tomcat)，

是按照Servlet规范编写的一个Java类。

#### Servlet有什么作用？

主要是处理客户端的请求并将其结果发送到客户端。

#### Servlet的生命周期？

Servlet的生命周期是由Servlet的容器来控制的，它可以分为3个阶段;初始化，运行，销毁。

初始化阶段：

1. Servlet容器加载servlet类，把servlet类的.class文件中的数据读到内存中。

2. 然后Servlet容器创建一个ServletConfig对象。ServletConfig对象包含了Servlet的初始化配置信息。

3. Servlet容器创建一个servlet对象。

4. Servlet容器调用servlet对象的init方法进行初始化。

运行阶段：

1. 当servlet容器接收到一个请求时，servlet容器会针对这个请求创建servletRequest和servletResponse对象。
2. 然后调用service方法。并把这两个参数传递给service方法。
3. Service方法通过servletRequest对象获得请求的信息。并处理该请求。
4. 再通过servletResponse对象生成这个请求的响应结果。
5. 然后销毁servletRequest和servletResponse对象。

>我们不管这个请求是post提交的还是get提交的，最终这个请求都会由service方法来处理。

销毁阶段：

1. 当Web应用被终止时，servlet容器会先调用servlet对象的destrory方法，
2. 然后再销毁servlet对象，同时也会销毁与servlet对象相关联的servletConfig对象。

>我们可以在destroy方法的实现中，释放servlet所占用的资源，如关闭数据库连接，关闭文件输入输出流等。

**在这里该注意的地方：**

在servlet生命周期中，servlet的**初始化和和销毁阶段只会发生一次**，而service方法执行的次数则取决于servlet被客户端访问的次数。


#### Servlet怎么处理一个请求？

1. 当用户发送一个请求到某个Servlet的时候，Servlet容器会创建一个ServletRequst和ServletResponse对象。
2. 在ServletRequst对象中封装了用户的请求信息，然后Servlet容器把ServletRequst和ServletResponse对象传给用户所请求的Servlet，
3. Servlet把处理好的结果写在ServletResponse中，然后Servlet容器把响应结果传给用户。

#### Servlet与JSP有什么区别？

1. jsp经编译后就是servlet，也可以说jsp等于servlet。

2. **jsp更擅长页面(表现)。servlet更擅长逻辑编辑。** (最核心的区别)。

3. 在实际应用中采用Servlet来控制业务流程,而采用JSP来生成动态网页.在struts框架中,JSP位于MVC设计模式的视图层,而Servlet位于控制层。

#### Servlet里的cookie技术？

cookies是一种WEB服务器通过浏览器在访问者的硬盘上存储信息的手段，是由Netscape公司开发出来的。

cookie技术的好处：

  1. Cookie有效期限未到时，Cookie能使用户在不键入密码和用户名的情况下进入曾经浏览过的一些站点。

  2. Cookie能使站点跟踪特定访问者的访问次数、最后访问时间和访问者进入站点的路径。


创建一个cookie
```java
//里面的两个参数分别是cookie的名和cookie的值

response.addCookie(new Cookie("abc","10000000"));
```
使用cookie
```java
Cookie[] cook =request.getCookies();//用一个Cookie数组来接收

for(int j=0;j<cook.length;j++){//通过循环来打印Cookie

        cook[j].getName()://取cookie的名  
        cook[j].getValue()://去cookie的值

}

```

#### Servlet里的过滤器？

过滤器的主要作用

1. 任何系统或网站都要判断用户是否登录。

2. 网络聊天系统或论坛，功能是过滤非法文字

3. 统一解决编码

怎么创建一个过滤器：

1. 生成一个普通的class类，实现Filter接口(javax.servlet.Filter;)。

2. 重写接口里面的三个方法：init，doFilter，destroy。

3. 然后在web.xml配置过滤器。

#### Servlet里的监听器？

监听器的作用：自动执行一些操作。

三种servlet监听器:

1. 对request的监听。
2. 对session的监听。
3. 对application的监听。

怎么创建一个session监听器：

1. 生成一个普通的class类，如果是对session的监听，则实现HttpSessionListener。

2. 然后重写里面的五个方法:

```java
public void sessionCreated(HttpSessionEvent arg0) {} // 创建

public void sessionDestroyed(HttpSessionEvent arg0) {} // 销毁

public void attributeAdded(HttpSessionEvent arg0) {} // 增加

public void attributeRemoved(HttpSessionEvent arg0) {} // 删除

public void attributeReplaced(HttpSessionEvent arg0) {} // 替换
```
