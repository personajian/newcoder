#### 参考文章

1. [Spring MVC拦截器](http://www.imooc.com/learn/498)


### 1. 概述：Spring MVC中拦截器的使用
### 2. Spring MVC拦截器的实现

#### 拦截器工作原理

- Spring MVC可以通过配置过滤器来解决乱码问题。
- 拦截器的工作原理和过滤器非常相似。

#### 拦截器的实现

1. 编写拦截器类实现HandlerInterceptor接口
```
public abstract interface org.springframework.web.servlet.HandlerInterceptor {

  public abstract boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, java.lang.Object arg2);

  public abstract void postHandle(http.HttpServletRequest arg0, HttpServletResponse arg1, java.lang.Object arg2, ModelAndView arg3)

  public abstract void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3);
}
```
2. 将拦截器类注册到Spring MVC框架中。
```
<!--使用mvc标签，将拦截器类的bean注册到Spring MVC框架中-->
<mvc:interceptors>
     <bean class="com.imooc.test.inerceptor.TestInterceptor"></bean>
</mvc:interceptors>
```
3. 配置拦截器的拦截规则
```
<!--使用mvc标签，将拦截器类的bean注册到Spring MVC框架中-->
<mvc:interceptors>
     <mvc:interceptor>
           <mvc:mapping path="/viewAll.form">    
          <bean class="com.imooc.test.inerceptor.TestInterceptor"></bean>
     </mvc:interceptor>     
</mvc:interceptors>
```

#### 拦截器方法介绍

1. preHandle方法，在请求被处理之前进行调用
	- `boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2`
	- 返回值：是否需要将当前的请求拦截下来。返回false，请求将被终止；返回true，请求将继续运行。
	- 参数：Object arg2表示被拦截的请求的目标对象（控制器中requestmapping所映射到的方法。）
调用请求，控制器中requestmapping所映射到的方法...
2. postHandle方法，在请求被处理之后进行调用
	- `void postHandle(http.HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)`
	- 参数：ModelAndView用来改变显示的视图，或修改发往试图的方法。

3. afterCompletion方法，在请求结束之后进行调用
	- `void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)```
	- 用于在视图显示之后，拦截器做要做的动作。（相当于析构方法，资源或者流的销毁。）

#### 多个拦截器应用

![Image-javaee-springmvc-interceptor-multiinterceptor.png](https://github.com/personajian/newcoder/raw/master/resources/picture/Image-javaee-springmvc-interceptor-multiinterceptor.png)

#### 拦截器的其他实现方式

```java
public abstract interface org.springframework.web.context.request.WebRequestInterceptor {

  //没有返回值，不终止请求
  public abstract void preHandle(org.springframework.web.context.request.WebRequest arg0) throws java.lang.Exception;

  public abstract void postHandle(org.springframework.web.context.request.WebRequest arg0, org.springframework.ui.ModelMap arg1) throws java.lang.Exception;

  public abstract void afterCompletion(org.springframework.web.context.request.WebRequest arg0, java.lang.Exception arg1) throws java.lang.Exception;
}
```

### 3. 拦截器的使用场景

>使用原则：处理所有请求的共同问题

1. 乱码问题
 
![Image-javaee-springmvc-interceptor-encoding.png](https://github.com/personajian/newcoder/raw/master/resources/picture/Image-javaee-springmvc-interceptor-encoding.png)

2. 解决权限验证问题

![Image-javaee-springmvc-interceptor-role.png](https://github.com/personajian/newcoder/raw/master/resources/picture/Image-javaee-springmvc-interceptor-role.png)


### 4. 拦截器与过滤器的对比

#### 拦截器和过滤器的区别

>拦截器可以处理Web应用中请求的一些通用性问题，共性问题在拦截器中处理，可以减少重复代码，便于维护。

- 过滤器Filter依赖于Servlet容器，基于回调函数，过滤范围大。
- 拦截器Interceptor依赖于框架容器，基于反射机制，只过滤请求。


更为细节的：

①拦截器是基于java的反射机制的，而过滤器是基于函数回调。
②拦截器不依赖与servlet容器，过滤器依赖与servlet容器。
③拦截器只能对action请求起作用，而过滤器则可以对几乎所有的请求起作用。
④拦截器可以访问action上下文、值栈里的对象，而过滤器不能访问。
⑤在action的生命周期中，拦截器可以多次被调用，而过滤器只能在容器初始化时被调用一次。
⑥拦截器可以获取IOC容器中的各个bean，而过滤器就不行，这点很重要，在拦截器里注入一个service，可以调用业务逻辑。


