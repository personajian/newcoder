### Spring配置文件详解

#### 1. Spring Bean 元素说明

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- beans元素 -->
<beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
      <!-- bean元素  -->
      <!-- 属性有id name class parent abstract scope lazy-init autowire dependency-check init-method destroy-method factory-method factory-bean -->
      <!-- bean的依赖关系：parent继承关系； depends-on依赖关系；ref引用关系-->
      <bean>
            <!-- constructor元素 属性有 index type 构造注入，调用构造函数注入-->
            <constructor-arg>
                  <bean></bean>
                  <ref/>
                  <idref/>
                  <list></list>
                  <set></set>
                  <map></map>
                  <props>
                        <prop key=""></prop>
                  </props>
                  <value></value>
                  <null></null>
            </constructor-arg>
            <!-- property元素 属性有name 属性注入，调用的setXXX注入-->
            <property name=""></property>
            <description></description>
            <!-- lookup-method元素 属性有name bean 方法注入-->
            <lookup-method/>
            <!-- replaced-method元素 属性有name replacer-->
            <replaced-method>
                  <arg-type></arg-type>
            </replaced-method>
            <meta key="" value=""/>
            <qualifier></qualifier>
      </bean>
      <description></description>
      <import resource=""/>
      <alias name="" alias=""/>
</beans>
```

beans 元素：spring-beans.xsd中的根节点

![Image-javaee-spring-config-1.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-spring-config-1.png)

bean 元素：bean元素
bean节点的属性以及子元素的信息：

![Image-javaee-spring-config-2.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-spring-config-2.png)

* constructor-arg 元素：为JavaBean指定构建器参数
* property 元素：为JavaBean指定属性
* lookup-method 元素
* replace-method 元素

![Image-javaee-spring-config-3.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-spring-config-3.png)

![Image-javaee-spring-config-4.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-spring-config-4.png)

![Image-javaee-spring-config-5.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-spring-config-5.png)

![Image-javaee-spring-config-6.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-spring-config-6.png)

#### 2. Bean property 属性说明:

通过Bean的属性property进行参数注入。

- 字面值：一般是指可用字符串表示的值，这些值可以通过`<value>`元素标签进行注入。在默认情况下，基本数据类型及其封装类，String等类型都可以采取字面值注入的方式：
- 引用其他Bean：Spring IoC 容器中定义的Bean-可以互相引用，IoC容器则充当了介绍人的角色。
- 内部Bean：
- null值
- 级联属性
- 集合类型属性（List,Set,Map,强类型结合，集合合并）

简化配置：

- 字面值属性简化配置：、

![Image-javaee-spring-config-7.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-spring-config-7.png)

- 引用对象属性简化配置：

![Image-javaee-spring-config-8.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-spring-config-8.png)

自动装配：Spring提供一种自动装配的功能，就是不再使用ref进行手工装配Bean,这种方式可以减少配置文件的代码量，但是在大型项目中，不推荐使用，容易混乱：

自动装配类型：

- autowire=“byName”
- autowire=“ byType ”
- autowire=“ constructor ”
- autowire=“ autodetect ”

注意：

1. `<beans>`元素标签中的default-autowire属性可以配置全局自动匹配，其属性的默认值为no，标志不启用自动装配；
2. 在`<beans>`中定义的自动装配策略可以被`<bean>`的自动装配策略覆盖；

![Image-javaee-spring-config-9.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-javaee-spring-config-9.png)


#### 3. Bean 的依赖注入

属性注入方法

- 属性注入即通过setXXX()方法注入Bean的属性值或者依赖对象，由于属性注入方式具有可选择性和灵活高的优点，因此属性注入是实际中最常采用的注入方式。
- Spring配置文件中`<property>`元素所指定的属性名和Bean实现类的Setter方法满足Sun JavaBean的属性命名规范，即xxx的属性对应setXxx()的方法： 

构造函数注入方法

- 构造函数注入是除属性注入之外的另一种常用的注入方式，它保证一些必要的属性在Bean实例化时就得到了设置，并在实例化后就可以使用：

- 按类型匹配入参
- 按索引匹配入参
- 联合使用类型和索引匹配入参
- 通过自身类型反射匹配入参
- 循环依赖问题
- 工厂方法注入方法(工厂方法是应用中被经常使用的设计模式，也是控制翻转和单实例设计思想的主要实现法)
  + 非静态工厂方式
  + 静态工厂方法
     

#### 4. Bean 的方法注入

- 当前问题：作用域为singleton的bean1注入作用域为prototype的bean2，希望每次返回一个新的bean2的实例，但是由于bean1只实例化一次，导致bean2每次都是同一个实例
- 传统解决办法：bean1实现BeanFactoryAware接口
- 最佳解决方案：lookup方法注入
- 方法替换：实现MethodReplacer接口，替换目标Bean的方法

#### 5. Bean 的依赖关系

在Spring容器中，两个Bean之间 除了注入关系外，还存在 继承、依赖、引用 三种关系：

- 继承关系
- 依赖关系
- 引用关系
