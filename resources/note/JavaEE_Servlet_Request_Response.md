[TOC]

## Servlet中的相关对象

web服务器调用Servlet时，会传递一些对象给Servlet：Request，Response，ServletConfig，ServletContext，Session，Cookie。学明白Servlet的API就会学明白Serlvet（学习JDBC也是学JDBC的API，只要搞懂了API就明白了）。

- Request
- Response
- ServletConfig
- ServletContext
- Session
- Cookie

### 1. HttpServletRequest

#### HttpServletRequest相关方法

1. 获取请求行：
```
getMethod()
getRequestURI()
getRequestURL()
```
  - URI：标识资源（任意资源）URI-->URL
  - URL：标识互联网上的资源

2. 获取请求头：
```
getDateHeader();
getHeader();
getHeaderNames();
getHeaders();
```

3. 获取请求内容，请求数据：
```
getParameter()
getParameterMap();
getparameterNames();
getParameterValues();
getInputStream()文件上传时使用。
```

request获取请求头
```java
//获取头的相关方法
public class ResponseDemo extend HttpServlet{
     public void doGet(HttpServletRequest request, HttpServletResponse response) thows ServletException, IOException{

          String headValue = request.getHeader("Accept-Encoding");

          Enumeration e = request.getHeaders("Accept-Encoding");
          while(e.hasMoreElement()){
               String value = (String) e.nextElement();
          }

          e = request.getHeadersNames();
          while(e.hasMoreElement()){
               String name = (String) e.nextElement();
               String value = request.getHeader(name);
          }
          
}
```

request获取请求数据

```java
//获取请求数据
//获取数据时一般都先检查，再使用
public class ResponseDemo extend HttpServlet{
     public void doGet(HttpServletRequest request, HttpServletResponse response) thows ServletException, IOException{
          //1.获取parameter的value值
          String value=request.getParameter();

          //2.获取所有parameter，返回的时枚举类型
          Enumeration e = request.getParameters();//username password
          while(e.hasMoreElement()){
               String name = (String) e.nextElement();
               String value = request.getParameter(name);
          }

          //3.获取相同parameter值的所有value值
          String[] values = request.getParameterValues("username");
          for(String v:values){
              sysout(v);
          }

          //4.获取request中的对象（框架时使用）
          Map<String,String[]>map = request.getParameterMap();
          User user = new User();
          try{
              BeanUtils.populate(user,map); //将请求参数中的map值，映射成bean
          }catch(Exception e1){e1.printStackTrace();}
          sysout(user);

          //5.通过InputStream获取数据，跟上传文件的方式一样
}
```

通过表单收集客户机数据

- text, password, radio, checkbox,
- file, select, textarea, hidden
- image, button给js编程用


#### request乱码问题

1. 请求参数的中文乱码问题（POST提交）
  - 浏览器post提交请求的编码：（UTF-8）
  - ->request对象传递是码值：（UTF-8对应的码值）
  - servlet中getParameter采用的编码：（默认是英文iso8859-1编码）
  - ->解决方案：request.setCharacterEncoding("UTF-8");//只对post提交有用，设置request码表

2. 请求参数的中文乱码问题（GET提交）
  - 手工处理：乱码先查iso8859-1，构建String，再去查UTF-8表
  - username=new String (username.getBytes("iso8859-1"), "UTF-8");

3. URL地址的编码
	- 超链接提交的中，跟GET提交方式一样
	- 直接更改服务器编码方式：不建议在开发中使用

测试题

```java
public class ResponseDemo extend HttpServlet{
     public void doGet(HttpServletRequest request, HttpServletResponse response) thows ServletException, IOException{
          request.setCharacterEncoding("UTF-8");//指定request的编码方式，和浏览器的提交的保持一致
          String username=request.getParameter("username");//得到了中文字符“中国”
          
          //相当于得到了
          //username=new String（"中国"）;

          response.setCharacterEncoding("gb2312");//指定了response的编码方式：“中国”以gb2312编码方式传递到response中
          response.setContentType("text/html;character=gb2312");//指定了浏览器页面的编码方式：浏览器以gb2312编码方式读取“中国”，不会乱码
          response.getWriter().write(username);
      }
}
```

#### request实现请求转发和mvc设计模式

>request对象实现请求转发：请求转发指一个web资源收到客户端请求后，通知服务器去调用另一个web资源进行处理。

请求转发的应用场景：MVC设计模式
- M:model(javabean)：封装数据
- V:view(jsp)：显示数据
- C:controller(servlet)：处理请求
	- servlet作为控制器收到请求，产生数据-->面向对象的思想-->使用javabean（model）来封装数据
	- servlet不适合做输出-->把javabean存在request域中-->交给jsp-->jsp取出javabean做输出
- -->MVC设计模式
- request对象提供了一个getRequestDispatcher方法，该方法返回一个RequestDispatcher对象，调用这个对象的forward方法可以实现请求转发。

>request.getRequestDispatcher("/index.jsp").forward(request,response);

request对象同时也是域对象（容器的作用范围），开发人员通过request对象在请求转发时，把数据通过request对象带给其他web资源处理。
- setAttribute方法
- getAttribute方法
- removeAttribute方法
- getAttributeNames方法

请求转发的细节：
- forward方法用于将请求转发到RequestDispatcher对象封装的资源。
- 如果在调用forward方法之前，在servlet程序中写入的部分内容已经被真正地传送到了客户端，forward方法将抛出IllegalStateException异常。
- 如果在调用forward方法之前向servlet引擎的缓冲区（response）中写入了内容，只要写入到缓冲区中的内容还没有被真正输出到客户端，forward方法就可以被正常执行，原来写入到输出缓冲区的内容将被清空，但是，已写入到HttpServletResponse对象中的响应头字段信息保持有效。


### 2. HttpServletResponse

#### HttpServletResponse相关方法

web服务器收到客户端的http请求，会针对每一次请求，分别创建一个用于代表请求的request对象和代表响应的response对象。
 
HttpServletResponse对象服务器的响应：这个对象中封装了向客户端发送数据、发送响应头、发送响应状态码的方法。

```java 
setStatus(int src)
setHeader(String name,Srting value)
getWriter()
getOutputStream()
```

request和response对象既然代表请求和响应，那么我们要获取客户机提交过来的数据，只需要找request对象就行了。要向客户机输出数据，只需要找response对象就行了。

#### response的outputStream字节流输出数据的问题

```java
//在servlet中用outputStream输出中文的问题
public class ResponseDemo extend HttpServlet{
     public void doGet(HttpServletRequest request, HttpServletResponse response) thows ServletException, IOException{
           response.setHeader("content-type","text/html;charset=UTF-8");//一定要指定客户机的接受响应的编码方式
           //下面写错了，浏览器会提示下载！
           //response.setHeader("content-type","text/html,charset=UTF-8");
           String data="中国";
           OutputStream out = response.getOutputStream();
           out.write(data.getBytes("UTF-8"));//程序以UTF-8码表输出到servlet中，而没有客户机不知道以何止编码读取，所以一定要在响应头中指定客户机的接受响应的编码方式！
      }

//用html技术中meta标签模拟了一个http响应头，来控制浏览器的行为
     public void doGet(HttpServletRequest request, HttpServletResponse response) thows ServletException, IOException{
           String data="中国";
           OutputStream out = response.getOutputStream();

           out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>".getByte());
           out.write(data.getBytes("UTF-8"));//程序以UTF-8码表输出的！
      }
}
```

#### response的Writer字符流输出数据的问题

```java
public class ResponseDemo extend HttpServlet{
     public void doGet(HttpServletRequest request, HttpServletResponse response) thows ServletException, IOException{
          //设置response使用的码表，以控制response以什么码表向浏览器写出数据
          response.setCharacterEncoding("UTF-8");
          //指定客户机（浏览器）以什么码表打开服务器发送的数据
          response.setHeader("content-type","text/html;charset=UTF-8");

          String data="中国";
          PrintWriter out = response.getWriter();
          out.write(data);
      }
}
```

```java
//小细节
public class ResponseDemo extend HttpServlet{
     public void doGet(HttpServletRequest request, HttpServletResponse response) thows ServletException, IOException{
          //设置response使用的码表，以控制response以什么码表向浏览器写出数据
          response.setCharacterEncoding("UTF-8");
          //指定客户机（浏览器）以什么码表打开服务器发送的数据
          response.setContentType("text/html;charset=UTF-8");

          String data="中国";
          PrintWriter out = response.getWriter();
          out.write(data);
      }
}
```

#### response实现文件下载

```java
//文件下载
public class ResponseDemo extend HttpServlet{
     public void doGet(HttpServletRequest request, HttpServletResponse response) thows ServletException, IOException{
          //获取文件地址和文件名
          this.getServletContext().getRealPath("/download/1.jpg");
          String filename=path.substring(path.lastIndexOf("\\")+1);
          //设置下载框
          response.setHeader("content-disposition","attachment;filename="+filename);
          //如果下载文件是中文名，则文件名需要经过url编码
          //response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode(filename,"UTF-8"));
          InputStream in=null;
          OutputStream out=null;
          try{
               in = new FileInputStream(path);
               int len=0;
               byte buffer[]=new byte[1024];
               //把文件写入到response的字节输出流中
               out=response.getOutputStream();
               whlie((len=in.read(buffer))>0){
                   out.write(buffer,0,len); 
               }
          }finally{
              if(in!=null){
                   try{
                         in.close();
                   } catch(Exception e){
                        e.printStackTrace(); 
                    }
              } 
          }
      }
}
```

#### response实现请求重定向

请求重定向：一个web资源收到客户机请求后，通知客户机去访问另一个web资源。
- 等于产生了两次web请求，两个request/response对象，有一定的应用场景，不能滥用，服务器会过载。
- 利用重定向技术，地址栏发生变化


```java
//实现请求重定向
public class ResponseDemo extend HttpServlet{
     public void doGet(HttpServletRequest request, HttpServletResponse response) thows ServletException, IOException{
          /*基于对http协议的理解，才能这样写出来
          response.getStatus("302");
          response.getLocation("day06/1.jsp");
          */
          //sun公司提供了API去重定向，内部还是调用了上面的两个函数
          response.sendRedirect("day06/1.jsp");
      }
}
```
应用场景
- 用户登陆成功，跳转首页
- 商品购买，跳转至购物车：转发实现的话，刷新页面就重复购买了；重定向能解决这个问题

实现方式
- response.sendRedirect();
- 实现原理就是：修改了http响应头里面的值->302状态码和location头->实现重定向。



#### response的一些细节

- getOutputStream和getWriter方法分别用于得到二进制数据、输出文本数据的ServletOutputStream、Printwriter对象。
- getOutputStream和getWriter这两个方法互相排斥，调用了其中的任何一个方法，就不能再调用另一个方法。（其实字节流可以搞定一切）
  - 同时调用的话就会抛出异常
- Servlet程序向ServletOutputStream或PrintWriter对象中写入的数据将被Servlet引擎从response里面获取，Servlet引擎将这些数据当作响应消息的正文，然后再与响应状态行和各响应头组合后输出到客户端。 
  - out-->response-->+响应行和响应头-->客户机
- Servlet的service方法结束后，Servlet引擎将检查getWriter或getOutputStream方法返回的输出流对象是否已经调用过close方法，如果没有，Servlet引擎就将调用close方法关闭该输出流对象。


### 3. ServletConfig对象

#### ServletConfig

ServletConfig对象：用于封装servlet的配置信息。

在Servlet的配置文件中，可以使用一个或多个<init-param>标签为servlet配置一些初始化参数。

```
<init-param>
       <parma-name>url</param-name>
        <param-value>jdbc:mysql://localhost::3306/test</param-value>
</init-param>

<init-param>
       <parma-name>config</param-name>
        <param-value>j/struts-config.xml</param-value>
</init-param>
```

当servlet配置了初始化参数后，web容器在创建servlet实例对象时，会自动将这些初始化参数封装到ServletConfig对象传递给servlet。接着，程序员通过ServletConfig对象就可以得到当前servlet的初始化参数信息。

```java
this.getServletConfig().getParametername();
this.getServletConfig().getParameternames();
```

#### 应用场景

ServletConfig API，举例说明该对象的作用（初始化参数：实际开发中，有些东西不适合在serlvet里面写死的，通过配置方式配给servlet）：
 
- 获得字符集编码：servlet采用的码表
- 获得数据库链接信息：servlet连接的数据库
- 获得配置文件config：servlet采用的配置文件，例如struts本身就是servlet，将配置信息放在了xml文件中。

### 4. ServletContext对象

#### 简介

web容器在启动时，它会为每个web应用程序都创建一个对应的ServletContext对象，它代表着当前web应用。（其中有管理、获取当前web应用的方法）

ServletConfig对象中维护了一ServletContext对象的引用，开发人员在编写servlet时. 
- 可以通过`ServletConfig.getServletContext()`方法获得ServletContext对象。
- `this.getServletContext()`

查看ServletContext API文档，了解ServeltContext对象的功能。
- getAttribute(java.lang.String name)
- getContext(java.lang.String uripath)
-  getInitParameterNames()
- getAttributeNames()  
- 。。。

#### 应用场景

1. 由于一个web应用中的所有servlet共享同一个ServletContext对象，所以多个servlet通过ServletContext对象实现数据共享。
	- ServletContext对象通常也被称之为context域对象（应用程序范围，上下文）
	- （web开发只有四个域：context，request，session，page）。
	- context域：应用程序（web容器）范围都能够访问的！
	- 应用场景：聊天室，聊天的话，互相在servlet中去对方的会话。
2. ServletContext域：
	- 这是一个容器
	- ServletContext域说明了这个容器的作用范围，也就是应用程序的作用的范围

3. 获取web应用的初始化参数
```
<context-param>
    <param-name>data</param-name >
    <param-value>xxxx</param-value >
</context-param>
```
	- 加载web容器时，将上面的参数，自动加载到ServletContext对象中。
	-  this.getServletContext().getInitParameter("data");
	- 应用场景：连接数据库的信息，不可能为每个servlet配置数据库信息。全局的！

4. 通过context实现servlet的转发
	- 转发：你问我借钱，我没有，我帮你找别人去借：servlet不适合做数据输出，数据转发给jsp做输出（显示）。
	```
	//把数据转发给1.jsp做展示
	String data="aaa";
	this.getServletContext().setAttirbute("data",data);
	this.gerServletContext().getRequestDispatcher("1.jsp").forward(request,response)
	
	//jsp页面 ServletContext==application但是数据时全局共享的，不适合通过context域，用request域做。
	<%
	     String data = (String)application.getAttribute("data");
	     out.write(data);
	%>
	```
	- 重定向：你问我借钱，我没有，我让你找别人去借

5. 利用ServletContext对象读取资源文件：配置文件有两种：数据之间没关系用.properties；数据之间有关系用.xml
	- 得到文件路径
	```
	//注意是tmocat发布web应用的目录，编译之后的，classes文件下面！！！
	//读取资源文件需要注意的问题：下面代码不可行，最好采用ServletContext来读资源文件的位置。
	public class ServletDemo extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	     text();
	}
	
	private void test() throws IOException{
	FileInputStream in=new FileInputStream("/classes/db.properties");//这将会是java虚拟机的路径，tomcat根目录
	Properties props=new Properties();//Properties是个Map
	props.load(in);
	
	String url = props.getProperty("url");
	String username = props.getProperty("username");
	String password = props.getProperty("password");
	}
	
	//通过servletContext的个体RealPath得到资源的绝对路径后，再通过传统流读取资源文件（能拿到资源文件的名称path）
	private void test1() throws IOException{
	String path=this.getServletContext().getRealPath("/WEN-INF/classes/db.properties");
	FileInputStream in=new FileInputStream(path);
	Properties props=new Properties();//Properties是个Map
	props.load(in);
	
	String url = props.getProperty("url");
	String username = props.getProperty("username");
	String password = props.getProperty("password");
	}
	
	}
	```
	- 读取资源文件的三种方式
	```
	//注意是tmocat发布web应用的目录，编译之后的，classes文件下面！！！
	//1.读取资源文件：
	InputStream in=this.getServletContext().getResourxeAsStream("/WEB-INF/classes/db.properties");
	//2.读取资源文件：资源文件在包内
	InputStream in=this.getServletContext().getResourxeAsStream("/WEB-INF/classes/cn/itcast/db.properties");
	//3.读取资源文件：资源文件在根目录，webroot/WEB-INF
	InputStream in=this.getServletContext().getResourxeAsStream("/db.properties");
	Properties props=new Properties();//Properties是个Map
	props.load(in);
	
	String url = props.getProperty("url");
	String username = props.getProperty("username");
	String password = props.getProperty("password");
	```
	- .properties文件（属性文件）
	```
	//db.properties
	url=jdbc:mysql://localhost:3306/test
	username=root
	password=root
	```

6. WEB应用中的普通Java程序（非servlet程序）如何读取资源文件：

	- 如果读取资源文件的程序不是servlet的话，就只能通过类装载器去读了：类装载器-->字节码-->反射
	```
	//以下代码虽然可以读取资源文件的数据，但是无法获取更新后的数据
	public class UserDao{
	
	     private static Properties dbconfig = new Properties();
	     static{         
	          try{
	               InputStream in =UserDao.class.getClassLoader().getResourceAsStream("db.properties");
	               dbconfig.load(in);
	          }catch (Exception e){
	              throw new ExceptionInInitialzerError(e); 
	          }
	     }
	     public void update(){
	          System.out.println(dbconfig.getProperty("url"));
	     }
	}
	```
	- servlet调用其他程序（非servlet程序），在其他程序中怎么如何去读取资源文件（通过类装载，文件不能太大，它是按类装载方式装载在内存中，只装载一次）
	```
	     //修改配置文件，就会出现还是读取类装载读取的原配置文件
	     //通过类装载器的方式得到资源文件的位置，再通过传统方式读取资源文件的数据，这样可以读取到更新后的数据
	     String path = UserDao.class.getClassLoader().getResource("db.properties").getPath();
	     FileInputStream in = new FileInputStream(path);
	     Properties dbconfig = new Properties();
	     dbconfig.load(in);
	     System.out.println(dbconfig.getProperty("url"));
	```


