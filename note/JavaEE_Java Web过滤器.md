#### 参考文章

[慕课网课程：ava Web开发技术应用——过滤器]http://www.imooc.com/video/4458

应用场景：登录注销过滤；请求错误过滤
过滤器的定义：是一个服务器端的组件，它可以截取用户端的请求与相应信息，并对这些信息过滤。

#### 1. 工作原理？

![Image-javaee-filter-1.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-filter-1.png)

#### 2. 生命周期？

在Web.xml中配置过滤器-->过滤器随Web容器启动-->实例化-->初始化-->init()-->doFilter()-->destroy()

过滤器的生命周期：

![Image-javaee-filter-2.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-filter-2.png)

init(), doFilter(), destroy():

![Image-javaee-filter-3.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-filter-3.png)

web.xml配置：

![Image-javaee-filter-4.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-filter-4.png)

问题环节：

- 过滤器能够否改变用户请求的Web资源呢？也就是能否改变用户请求的路径？YES.例如没有登录的用户请求会过滤到登录界面。
- 过滤器能否直接返回数据，能不能直接处理用户请求？NO.Fiter是特殊的Servlet，但并不是标准的Servlet，并不对数据请求做处理。要么跳转到用户请求的资源，要么重定向其他的web资源。

#### 3. 过滤器链

web项目中多个过滤器是如何实现的？

- 多个过滤器，每个过滤器都有url-pattern.
- 假如url-pattern不一样，过滤器就不相关，井水不犯河水。
- 假如url-pattern一样，过滤器就组成了过滤器链。

多个过滤器对应同一个用户路径执行顺序如何？

- Chain.doFilter()：
    - 先执行放行前的代码Code1（顺序）
    - 执行Chain.doFiler()放行
    - 链返回时，执行放行后的代码Code2（逆序）

![Image-javaee-filter-5.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-filter-5.png)

#### 4. 过滤器类型？

![Image-javaee-filter-6.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-filter-6.png)

![Image-javaee-filter-7.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-filter-7.png)

![Image-javaee-filter-8.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-filter-8.png)

#### 5. 过滤器应用

![Image-javaee-filter-9.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-filter-9.png)

登录认证及编码转换

- 原始方式：每个Servlet都进行登录的验证，繁琐。

```java
package com.imooc.serlvet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username);

        if("admin".equals(username) && "admin".equals(password)){
            //校验通过
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect(request.getContextPath()+"/sucess.jsp");
        }else{
            //校验失败
            response.sendRedirect(request.getContextPath()+"/fail.jsp");
        }

    }

    public void init() throws ServletException {
        // Put your code here
    }

}
```

- 进阶方式：利用配置过滤器来实现登录的验证。

```java
package com.imooc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    private FilterConfig config; //在web.xml中配置不需要登录过滤的页面（login,fail,success）
    
    //将web.xml的过滤器初始配置信息FilterConfig载入
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        config = arg0;
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession();

        String noLoginPaths = config.getInitParameter("noLoginPaths");
       
        //统一的字符编码过滤。
        String charset = config.getInitParameter("charset");
        if(charset==null){
            charset = "UTF-8";
        }
        request.setCharacterEncoding(charset);

        if(noLoginPaths!=null){
            String[] strArray = noLoginPaths.split(";");
            for (int i = 0; i < strArray.length; i++) {

                if(strArray[i]==null || "".equals(strArray[i])) continue;

                if(request.getRequestURI().indexOf(strArray[i])!=-1 ){
                    arg2.doFilter(arg0, arg1);
                    return;
                }
            }

        }

        if(session.getAttribute("username")!=null){
            arg2.doFilter(arg0, arg1);
        }else{
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    public void destroy() {

    }
     
}
```


#### 总结：
