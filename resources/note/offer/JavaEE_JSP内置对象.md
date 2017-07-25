#### 参考文章

1. [JSP九大内置对象](http://blog.csdn.net/u013142781/article/details/50756419)

### JSP

JSP（Java Server Page）：模板引擎
     以Servlet为基础，本质还是Servlet，通过Tomcat JSPServlet引擎。

#### JSP编译器指令：include，page，taglib

1. ` <%@page...%>`：
2. `<%@include...%>`：
	1. 静态导入：源码级别的导入（合并），共享变量
	2. 动态导入：jsp:include 动态导入，对象调用

#### JSP脚本语法：

1. 注释：
	1. JSP隐藏注释<!%-- -->
	2. HTML注释<!-- -->
	3. JS注释//
2. 表达式语法：<%=...%> 表达式计算
3. 声明语法：<%!...%> 成员变量，成员方法
4. 脚本段：<%...%> Java代码


#### JSP中九大内置对象为：

>JSP九大内置对象都对应着某个Servlet类，在JSP被翻译成Servlet之后，这些内置对象会相应转换成对应的类实例。

内置|对象|类型|作用域|备注
---|---|---|---|---
request|请求对象|javax.servlet.ServletRequest|Reuqest|代表的是来自客户端的请求
response|响应对象|javax.servlet.SrvletResponse|Page|代表的是来自客户端响应
pageContext|页面上下文对象|javax.servlet.jsp.PageContext|Page|代表的是当前页面运行的一些属性
session|会话对象|javax.servlet.http.HttpSession|Session|代表服务器与客户端所 建立的会话
application|应用程序对象|javax.servlet.ServletContext|Application|负责提供应用程序在服务器中运行时的一些全局信息
out|输出对象|javax.servlet.jsp.JspWriter|Page|代表了向客户端发送数据的对象，与“response”对象不同，通过“out“对象发送的内容将时浏览器需要显示的内容
config|配置对象|javax.servlet.ServletConfig|Page|提供了一些配置信息
page|页面对象|javax.lang.Object|Page|代表了正在运行的由JSP文件产生的类对象，不建议一般读者使用
exception|例外对象|javax.lang.Throwable  |Page|代表了JSP文件运行时所产生的例外对象

#### JSP的四个作用域：

如果希望在不同的JSP页面中传递对象，可以通过xx.setAttribute("xx",sss)完成设置，可以通过xx.getAttribute("xx")获取对象。

可以传递对象的作用域：

* pageContext：当前页面存在，当发生跳转之后数据丢失
* request：当前页面存在，服务器端跳转存在，客户端跳转不存在
* session：只有浏览器关闭，session才丢失，否则一直都存在
* application：只要服务器没有重启就存在

作用范围：pageContext<request<session<application
