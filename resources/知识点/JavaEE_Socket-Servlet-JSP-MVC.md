### 1.Socket编程：

是最底层的java的http通信，能获取到网站的信息。实现网络爬虫的基础。

```java
package itat.zttc.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestHttp {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",8080);
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println("GET /servlet01/reg?interest=ddd&username=abc HTTP/1.1");
            out.println("Host: localhost");
            out.println("contentType:text/html");
            out.println();
            String str = null;
            while((str=reader.readLine())!=null) {
                System.out.println(str);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
```
  
### 2. Servlet是实现动态Web技术的基础。

只要是实现了Servlet接口的Servlet 的生命周期是由Servlet容器所管理的，例如，Tomcat Web容器，为每一个Servlet自动生成对应一对HttpServletRequest/HttpServletResponse（请求和响应）。通过调用相应的doGet、doPost方法，为

```
package itat.zttc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("hello");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        for(int i=0;i<100;i++) {
            out.println("<h1>"+i+"</h1>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
```

实现HttpServlet的四个重要方法：（Servlet生命周期依次调用。）  

* public void init();  
* public void init(ServletConfig config);//带Servlet配置的初始化方法。  
* protected void service(HttpServletRequest req, HttpServletResponse resp);  
* protected void doGet(HttpServletRequest req, HttpServletResponse resp);  
* protected void destory();

```
package itat.zttc.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScopeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("init servlet");
    }

    /**
     * 如果有init的两个方法，系统会默认调用带有ServletConig的方法
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        String username = config.getInitParameter("username");
        System.out.println(username);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
                System.out.println("service is invoking");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("do get");
    }

    protected void destory() {
        System.out.println("destory");
    }
}
```

### 3. Sun公司对Servlet进行了封装，推出JSP技术。

其实JSP就是Serlvet：JSP源文件经过Web服务器，进行了JSP分析器->Java源程序->Java编译器->JSP Serlvet->HTML文件。  
由于在JSP中书写Java代码进行数据展示比较繁琐->又引入了jstl标签，EL表达式进行JavaBean的展示。->标签、表达式的本质还是转换成为了Java语言。

但是在JSP中进行业务逻辑的控制的问题并没有解决，但幸好已经有了Servlet！

1. 但在Servlet中书写html进行展示是十分痛苦的事情，Servlet的优势在于直接用Java进行业务逻辑的控制；
2. 然而在JSP中书写Java代码进行业务逻辑的控制也是十分痛苦的事情，JSP优势在于展现html，不在于书写Java代码；
3. 于是让Serlvet和JSP各司其职，Servlet进行业务逻辑的控制，JSP进行前端和数据的展示；
4. 进而引出了MVC框架，Model数据层封装了数据，View视图层利用JSP展示，Controller控制层利用Servlet进行业务逻辑的控制。

于是JSP的内置对象到头来还是封装成为了Servlet中的类型，作用域也是Servlet的作用域。  
于是由传统的直接访问jsp页面->变成了：

1. 访问url，交给Servlet进行业务逻辑控制；
2. Servlet向Request域（不同的MVC框架采用不同Model来封装数据，大多都是键值对）添加数据，并重定向到JSP页面；
3. JSP获得了Modle数据，仅仅做展示。

|内置|对象|类型|作用域|备注|
|----|----|----|------|----|
|Request|请求对象|javax.servlet.ServletRequest的子类|Reuqest|代表的是来自客户端的请求|
|Response|响应对象|javax.servlet.SrvletResponse的子类|Page|代表的是来自客户端响应|
|pageContext|页面上下文对象|javax.servlet.jsp.PageContext|Page|代表的是当前页面运行的一些属性|
|Session|会话对象|javax.servlet.http.HttpSession|Session|代表服务器与客户端所 建立的会话|
|Application|应用程序对象|javax.servlet.ServletContext|Application|负责提供应用程序在服务器中运行时的一些全局信息|
|Out|输出对象|javax.servlet.jsp.JspWriter|Page|代表了向客户端发送数据的对象，与“response”对象不同，通过“out“对象发送的内容将时浏览器需要显示的内容|
|Config|配置对象|javax.servlet.ServletConfig|Page|提供了一些配置信息
|Page|页面对象|javax.lang.Object|Page|代表了正在运行的由JSP文件产生的类对象，不建议一般读者使用|
|Exception|例外对象|javax.lang.Throwable|Page|代表了JSP文件运行时所产生的例外对象|

### 4. Filter过滤器：

本质也是Servlet，是一种特殊类型的Servlet，实现了Filter接口，调用了Filter的第三个方法。（对比Servlet是实现了Serlvet接口。）   
要编写过滤器就是编写实现Filter接口的类；

```
package itat.zttc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//自定义的字符编码过滤器
public class CharEncodingFilter implements Filter {

    private String encoding;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
//        System.out.println("before filter");
        System.out.println(encoding);
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);//chain.doFilter让执行继续
//        System.out.println("end filter");
    }
     
     //读取Filter的初始化参数，FilterConfig，避免了硬编码
    @Override
    public void init(FilterConfig cfg) throws ServletException {
        String e = cfg.getInitParameter("encoding");
        if(e==null||"".equals(e.trim())) {
            encoding = "UTF-8";
        } else {
            encoding = e;
        }
    }

    @Override
    public void destroy() {

    }

}
```

1. 重写Filter中定义的三个方法：init，doFilter，destory；（Filter生命周期中依次调用这三个方法）
2. 然后在web.xml中配置所编写的过滤器。

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">
     <!--配置了所定义的过滤器。 -->
    <filter>
        <filter-name>CharEncodingFilter</filter-name>
        <filter-class>itat.zttc.filter.CharEncodingFilter</filter-class>
          <!--配置过滤器的初始化参数 -->
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
     <!--定义了哪些url必须要经过此过滤器。 -->
    <filter-mapping>
        <filter-name>CharEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

可以定义多个过滤器，并在web.xml中配置，形成了Filter Chain（过滤器链），所请求的url都要经过Filter Chain的过滤。（比如：字符编码过滤器，安全过滤器。）

### 5. 每一个请求对应一个Servlet，会导致需要编写大量的Serlvet。每个请求仅仅是调用了一个Serlvet中的service方法，选择相应的doGet或者doPost方法来执行。 
>能不能提出一个方式，能够将业务相同的请求，有专门的一个Serlvet来管理？  

所以试图采用一个BaseSerlvet来实现灵活的可控制多个servlet。   
1. 在BaseServlet中编写业务逻辑需要的调用的方法； 

![Image-javaee-servlet-baseservlet.png](https://github.com/personajian/newcoder/raw/master/resources/picture/Image-javaee-servlet-baseservlet-forward.png)
   
2. 继承BaseServlet的Servlet通过反射机制（反射调用相应的方法），来调用业务所需的方法。  
3. 于是继承了BaseServelt的UserServlet，不要添加doGet/doPost，只需要专著于自己的所需要调用的方法。  
4. 但是有个遗憾,服务器端跳转都需要在业务方法内写上：request.getRequestDispatcher("/WEB-INF/user/register.jsp").forward(request,response);  

![Image-javaee-servlet-baseservlet-forward.png](https://github.com/personajian/newcoder/raw/master/resources/picture/Image-javaee-servlet-baseservlet-forward.png)

客户端跳转也一样  
5. 所以在BaseSerlvet对跳转的url再进行封装。 
<en-media hash="3aff54701f19ca514696ad0094341549" type="image/png"/>      
6. 在继承了BaseServlet的Servlet中只需要返回url的字符串就行了。 
<en-media hash="466a7acf0520644855b51e77be0b5b27" type="image/png"/>      
7. BaseServlet所起到的作用就是Spring MVC中的org.springframework.web.servlet.DispatcherServlet的功能，基于注解、反射机制，完成了对所有Servlet的管理。

  


  


  


  


  


  


  


  


  


  

