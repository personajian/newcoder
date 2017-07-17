## 马士兵设计模式

>设计模式：产生多态（接口）,可扩展、可维护，更多是根据语义上的区分。

- OOA&OOD：面向对象分析，设计
- 添加优于修改：组合优先于继承
- do not repeat：将相同的代码抽取出去封装
- 设计原则：GRASP、GoF

>GRASP设计模式描述的是在OO设计中为互相协作的类分配职责的原则或者建议，而GoF的设计模式则是在更高的层次上描述一个OO系统或者其局部系统的行为以及结构上的抽象。

>GRASP设计模式的全称是General Responsibility Assignment Software Patterns，即通用职责分配软件模式。

>它定义了9个基本的OO设计原则或基本的设计构件。这9个设计模式分别是：创建者（Creator）、信息专家（Information Expert）、低耦合（Low Coupling）、控制器（Controller）、高内聚（High Cohesion）、多态性（Polymorphism）、纯虚构（Pure Fabrication）、间接性（Indirection）、防止变异（Protected Variations）。

（classpath相当于bin目录）


### 1. Observer模式：

提出问题：小孩在睡觉，醒来后要求吃东西。

第一种方法：dad主动监测child的状态，比较耗cpu资源。

第二种方法：child中增加dad的引用，状态的变化，调用dad的方法。

>矛盾：观察者对不同事件的处理？不同的观察者？child也需要变？？->扩展性差！

第三种方法：（Observer:AWT;ActionEvent,Button,ActionListener;struts2 MVC ）

* 将变化的部分抽象出来：一系列共同的特点--接口。
* 让不同的观察者实现 事件处理的接口——事件监听器WakeUpListener。
* child（被观察者中增加的是事件接口——事件监听器的引用数组！List<WakeUplistener>;addWakenUpListener();）
* 于是添加监听者只需要实现了事件监听器的接口就行了，扩展性增加。
* 优化：将需要添加的observer（变化的）写到配置文件中。
    * 缓存：需要一个监听 缓存的访问次数
    * 模拟AWT事件处理
        * Button
        * 监听线程
        * 事件派发线程

```java
package com.bjsxt.dp.observer.awt;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFrame extends Frame {
    public void launch() {
        Button b = new Button("press me");
        b.addActionListener(new MyActionListener());
        b.addActionListener(new MyActionListener2());
        this.add(b);
        this.pack();

        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TestFrame().launch();
    }

    private class MyActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("button pressed!");
        }

    }

    private class MyActionListener2 implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("button pressed 2!");
        }

    }
}
```

### 2. iterator模式

>容器遍历的实现

### 3. strategy模式

>排序算法的实现

应用场景：

* 比较策略：Comparable->实现compareTo方法时依赖于具体的比较器->Comparator
* 商场的打折策略
* 坦克的发射炮弹策略

### 4. 工厂模式

应用：Spring的bean工厂，IOC容器（控制对象产生的方法）

特点：

* 自主生产对象，而不用通过new
* 静态工厂方法：getInstance
* 单例模式

```java
private static Car car = new Car();

public Car(){}

public static Car getInstance() {

    return car;
}
```

* 多例模式（工厂模式）

```java
private static Car car = new Car();
//private static List<Car> cars = new ArrayList<Car>();

public Car(){}

public static Car getInstance() {

    return car;
}
```

任意定制交通工具的类型和生产过程。（抽象工厂模式）
任意定制交通工具的类型：抽象出同一的接口，让类实现同一接口。（工厂模式）
任意定制交通工具的生产过程：统一factory抽象类，具体的工厂交给Xxxfactory实现。（简单工厂） 

* VihecleFactory抽象类,CarFactory,PlaneFactory,
* Moveable接口，Car,Plane
* 系列产品（车，武器，食品补给）：一个工厂产生一系列产品。（抽象工厂：生产一系列产品。）

```java
package com.bjsxt.dp.factory.abstractfactory;

public abstract class AbstractFactory {
    public abstract Vehicle createVehicle();
    public abstract Weapon createWeapon();
    public abstract Food createFood();

}
```

普通工厂：可以扩展产品和工厂，但不容易产生产品系列，导致工厂泛滥。

抽象工厂：可以扩展产品系列。不容易产场具体品种，需要改动的地方很多。


应用：

Spring Bean工厂（BeanFactory->ClassPathXmlApplicationContext）：反射机制产生Bean

* 1.基于接口的类型任意定制 2. 一定程度的生产过程定制（AOP）。
* Bean容器是map，（ClassPathXmlApplicationContext）读取配置文件里面的bean，newInstance()放到容器当中。
* getBean()从Bean map中获取到Bean容器的对象。




### 5. 责任链模式

>理解tomcat filter，struts2 interceptor，过滤器，拦截器

* 消息过滤，过滤规则自己定制？Filter，doFilter()->多个过滤器组成了过滤链（如何排序过滤器？）
* 已有链条，添加自己的链条，如何组合？FilterChain过滤规则链来addFilter()装载过滤器，调用doFilter()

```java
package com.bjsxt.dp.filter;

public interface Filter {
    String doFilter(String str);
}
```

```java
package com.bjsxt.dp.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter { //这样可以实现过滤规则链 加 过滤器链
    List<Filter> filters = new ArrayList<Filter>();

    public FilterChain addFilter(Filter f) { //完成链式添加.add.add.add
        this.filters.add(f);
        return this;
    }

    public String doFilter(String str) {
        String r = str;
        for(Filter f: filters) {
            r = f.doFilter(r);
        }
        return r;
    }
}
```

* 怎么过滤器链，既处理过去的消息，又处理回来的消息？（request，response）

```java
package com.bjsxt.dp.filter;

public interface Filter {
    void doFilter(Request request, Response response, FilterChain chain);
}

package com.bjsxt.dp.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter {
    List<Filter> filters = new ArrayList<Filter>();
    int index = 0;

    public FilterChain addFilter(Filter f) {
        this.filters.add(f);
        return this;
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        if(index == filters.size()) return ;

        Filter f = filters.get(index);
        index ++;
        f.doFilter(request, response, chain); //完成FilterChain的doFilter栈式调用！！
    }
}
```

* 怎么断开链？（权限检查）



### 6. 动态代理模式

>你不必知道我存在（基于反射机制的实现），AOP是动态代理的一种实现。

* 做什么？想在原有源代码方法的前后加一些代码（不知道源代码的情况下。），例如加日志，加事务，权限（拦截器）
    * 原始的方式（静态代理）：利用继承，在原有的类上加上额外的代码。但不利用功能的叠加和扩展，会造成类爆炸。（日志前后加事务，日志前后加时间）
    * 原始的方式（静态代理）：接口（聚合）实现。（将需要被代理的对象聚合到代理对象中）。利于功能的叠加和扩展，只要代理对象们实现了相同的接口（Tank，TankTimerProxy，TankLogProxy，Movable）。

* 怎么优化？动态代理！Proxy.newProxyInstance()
    * 同一接口中的多个方法都要加上额外的代码，-->before(),after()
    * TimerProxy需要重用呢？为不同的类都加上额外的代码。对任何实现同一的接口Moveable的对象都产生代理-->根据具体的接口来实现代理。
    * 动态编译代理类proxy的代码，从而生成动态代理类//JDK Complier ,CGLib(直接使用二进制代码),ASM。不用写众多的XxxProxy类。Proxy是代理的代理！！！


```java
package com.bjsxt.compiler.test;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import com.bjsxt.proxy.Moveable;
import com.bjsxt.proxy.Tank;

public class Test1 {
    public static void main(String[] args) throws Exception{
        String rt = "\r\n";
        String src =
            "package com.bjsxt.proxy;" +  rt +
            "public class TankTimeProxy implements Moveable {" + rt +
            "    public TankTimeProxy(Moveable t) {" + rt +
            "        super();" + rt +
            "        this.t = t;" + rt +
            "    }" + rt +

            "    Moveable t;" + rt +

            "    @Override" + rt +
            "    public void move() {" + rt +
            "        long start = System.currentTimeMillis();" + rt +
            "        System.out.println(\"starttime:\" + start);" + rt +
            "        t.move();" + rt +
            "        long end = System.currentTimeMillis();" + rt +
            "        System.out.println(\"time:\" + (end-start));" + rt +
            "    }" + rt +
            "}";
        String fileName = System.getProperty("user.dir")
                            + "/src/com/bjsxt/proxy/TankTimeProxy.java";
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(src);
        fw.flush();
        fw.close();

        //compile
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(fileName);
        CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
        t.call();
        fileMgr.close();

        //load into memory and create an instance
        URL[] urls = new URL[] {new URL("file:/" + System.getProperty("user.dir") +"/src")};
        URLClassLoader ul = new URLClassLoader(urls);
        Class c = ul.loadClass("com.bjsxt.proxy.TankTimeProxy");
        System.out.println(c);

        Constructor ctr = c.getConstructor(Moveable.class);
        Moveable m = (Moveable)ctr.newInstance(new Tank());
        m.move();

    }
}
```

* 任意接口的动态代理：把接口传进去！

```java
package com.bjsxt.proxy;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

public class Proxy {
    public static Object newProxyInstance(Class infce, InvocationHandler h) throws Exception { //JDK6 Complier API, CGLib, ASM
        String methodStr = "";
        String rt = "\r\n";

        Method[] methods = infce.getMethods();
        /*
        for(Method m : methods) {
            methodStr += "@Override" + rt +
                         "public void " + m.getName() + "() {" + rt +
                             "   long start = System.currentTimeMillis();" + rt +
                            "   System.out.println(\"starttime:\" + start);" + rt +
                            "   t." + m.getName() + "();" + rt +
                            "   long end = System.currentTimeMillis();" + rt +
                            "   System.out.println(\"time:\" + (end-start));" + rt +
                         "}";
        }
        */
        for(Method m : methods) {
            methodStr += "@Override" + rt +
                         "public void " + m.getName() + "() {" + rt +
                         "    try {" + rt +
                         "    Method md = " + infce.getName() + ".class.getMethod(\"" + m.getName() + "\");" + rt +
                         "    h.invoke(this, md);" + rt +
                         "    }catch(Exception e) {e.printStackTrace();}" + rt +

                         "}";
        }

        String src =
            "package com.bjsxt.proxy;" +  rt +
            "import java.lang.reflect.Method;" + rt +
            "public class $Proxy1 implements " + infce.getName() + "{" + rt +
            "    public $Proxy1(InvocationHandler h) {" + rt +
            "        this.h = h;" + rt +
            "    }" + rt +


            "    com.bjsxt.proxy.InvocationHandler h;" + rt +

            methodStr +
            "}";
        String fileName =
            "d:/src/com/bjsxt/proxy/$Proxy1.java";
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(src);
        fw.flush();
        fw.close();

        //compile
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(fileName);
        CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
        t.call();
        fileMgr.close();

        //load into memory and create an instance
        URL[] urls = new URL[] {new URL("file:/" + "d:/src/")};
        URLClassLoader ul = new URLClassLoader(urls);
        Class c = ul.loadClass("com.bjsxt.proxy.$Proxy1");
        System.out.println(c);

        Constructor ctr = c.getConstructor(InvocationHandler.class);
        Object m = ctr.newInstance(h);
        //m.move();

        return m;
    }
}
```

* 自定义需要代理的方法。


```java
package com.bjsxt.proxy;

import java.lang.reflect.Method;

public interface InvocationHandler {
    public void invoke(Object o, Method m);
}



package com.bjsxt.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler{

    private Object target; //被任意的代理对象传入

    public TimeHandler(Object target) {
        super();
        this.target = target;
    }
k
    @Override
    public void invoke(Object o, Method m) {
        long start = System.currentTimeMillis();
        System.out.println("starttime:" + start);
        System.out.println(o.getClass().getName());
        try {
            m.invoke(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("time:" + (end-start));
    }

}
```

## 追美眉的技术：

### 1. Bridge（桥接模式）

一个类里面又另一个类的引用。两个类都不断的继承组合，组合代替继承。

### 2. Adapter（适配器模式）

* JDBC，ODBC bridge
* java.io.InputStreamReader：装饰器，适配器

### 3. Commander（命令模式）

* execute(),unDo():执行，撤销操作

### 4. State

一个类的动作是根据类内部而有不同的动作。

### 5. Mediator（调停者）

调停类与类之间的关系。

### 6. Facade（门面）

对外的，帮处理系统内部复杂的关系。

例如：统一的action来处理请求

