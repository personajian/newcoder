[TOC]

## 参考文章：

- [Java反射机制应用实践](https://www.ziwenxie.site/2017/03/22/java-reflection/)
- [谈谈Java反射机制](http://www.jianshu.com/p/6277c1f9f48d)


# 反射机制：

- 指可以在**运行时**加载、探知、使用编译期间完全未知的类。
  - 程序在运行状态中，可以**动态加载一个只有名称的类**，
  - 对于任意一个已加载的类，都能够知道**这个类的所有属性和方法**；
  - 对于任意一个对象，都能够**调用它的任意一个方法和属性**。
- 加载完类之后，在堆内存中，就产生了一个**Class类型的对象**（一个类只有一个Class对象），这个对象就包含了**完整的类的结构信息**，可以通过这个对象看到类的结构。
- 这个对象Class就像**一面镜子**，透过镜子看到**类的结构**，所以，形象的称之为：反射。

## Class类

*什么是Class类？*
- 在面向对象的世界里，**万事万物皆是对象**（包括类本身），
- 而在java语言中，static修饰的东西不是对象，但是它属于类。
- 普通的数据类型不是对象，例如：int a = 5;它不是面向对象，但是它有其包装类 Integer 或者分装类来弥补了它。
- 除了以上两种不是面向对象，其余的包括类也有它的面向对象，类是java.lang.Class的实例化对象（注意Class是大写）。也就是说：
```
Class A{}
```
当我创建了A类，那么类A本身就是一个对象，谁的对象？java.lang.Class的实例对象。

## Class类实例

**Class类的实例**表示**正在运行**的 Java 应用程序中的**类和接口**。
>**枚举**是一种类，**注释**是一种接口。
>**每个数组**属于被映射为 **Class 对象的一个类**，所有具有相同元素类型和维数的数组都共享该 Class 对象。
>**基本的 Java 类型**（boolean、byte、char、short、int、long、float 和 double）和关键字 void 也表示为 Class 对象。

## Class类的构造方法

>Class没有公共构造方法。

Class对象是在加载类时由**Java虚拟机**以及通过调用**类加载器**中的`defineClass()`方法自动构造的。

## java.lang.Class

java.lang.Class类十分特殊，用来表示**java中类型（class/interface/enum/annotation/primitive type/void）本身**。

- Class类的对象包含了**某个被加载类的结构**。一个被加载的类对应着一个Class对象。
- 当一个Class被加载，或当**加载器（classloader）**的`defineClass()`被JVM调用，JVM便自动产生于一个Class对象。

>**Class类是Refelction的实现基础**
>针对任何想动态加载、运行的类，唯有先获得相应的Class对象。

### 方法的反射

**方法是java.lang.reflect.Method类的对象**
>`java.lang.Class#getName()`:该类类型的类名称；
>`java.lang.Class#getSimpleName()`:不包含包名的类的名称；

通过方法的反射得到该类的名称步骤：
1. 获取该类的类类型：Class.getClass()
2. 通过类类型获取类的方法：getMethods()
3. 循环遍历所获取到的方法
4. 通过这些方法的getReturnType()得到返回值类型的类类型，又通过该类类型得到返回值类型的名字
5. getName()得到方法的名称，getParameterTypes()获取这个方法里面的参数类型的类类型。

### 成员变量的反射

**成员变量是java.lang.reflect.Field类的对象**。
>`java.lang.Class#getFields()`:获取所有的public的成员变量的信息；
>`java.lang.Class#getDeclaredFields()`:；获取所有自己声明的成员变量的信息；

### 构造函数的反射

**构造函数是java.lang.reflect.Constructor类的对象**。
>`java.lang.Class#getConstructor()`:获取所有的public的构造函数；
>`java.lang.Class#getDeclaredConstructor()`:获取所有的自己声明的构造函数；

![Java反射机制思维导图](https://github.com/personajian/newcoder/raw/master/resources/picture/Image-java-reflect.png)