[TOC]

#### 参考文章

1. [Java不能实现真正泛型的原因？](https://www.zhihu.com/question/28665443/answer/118148143)
2. 《Effective Java》 第25条

### 一、Java不能实现真正泛型的原因？

#### Java实现泛型与C++的区别

1. 需要泛型化的类型（主要是容器（Collections）类型），以前有的就保持不变，然后平行地加一套泛型化版本的新类型；
2. 直接把已有的类型泛型化，让所有需要泛型化的已有类型都原地泛型化，不添加任何平行于已有类型的泛型版。

.NET选择了1来实现泛型，而Java则选择了2。

#### 向后兼容性

导致Java 5引入的泛型采用擦除式实现的根本原因是兼容性上的取舍，而不是“实现不了”的问题。


二进制向后兼容性：保证非泛型的代码依旧能够运行。

```java
ArrayList - raw type
ArrayList<E> - open generic type (assuming E is type variable)
ArrayList<String> - closed generic type
ArrayList<?> - unbounded wildcard type
```

（注意ArrayList作为raw type，与实例化泛型类型ArrayList<Object>、通配符泛型类型ArrayList<?>并不直接等价）


```java
ArrayList<Integer> ilist = new ArrayList<Integer>();
ArrayList<String> slist = new ArrayList<String>();
ArrayList list; // raw type
list = ilist; // assigning closed generic type to raw type
list = slist; // ditto
```

于是对泛型实例化的ArrayList<Integer>和ArrayList<String>来说，非泛型的ArrayList必须是它们的共同超类型（super type）。

要支持raw type，最直接的办法就是通过擦除法来实现泛型。

1. 编译时类型确实时泛型的，（编译器保证）
2. 但编译结束后泛型信息被擦除，
3. 最后的结果就是raw type
4. （加上一些额外的类型检查，如果有wildcard bounds的话还会带上bounds信息）。

但类型擦除也带来了一些问题：

>对泛型类型ArrayList<E>来说，泛型参数E被擦除后就变成了Object，那么这里我们要让int、long与Object赋值兼容…
GJ / Java 5说：这个问题有点麻烦，赶不及在这个版本发布前完成了，就先放着不管吧。于是Java 5的泛型就不支持原始类型，而我们不得不写恶心的ArrayList<Integer>、ArrayList<Long>…
这就是一个偷懒了的地方。

### 二、为什么不建议使用泛型数组？

#### 第一点：协变/不可变

**数组是协变的（covariant）。**

>如果Sub是Super的子类型，那么数组类型Sub[]就是Super[]的子类型。

**泛型是不可变的（invariant）。**

>对于任意两个不同类型的Type1和Type2，List<Type1>既不是List<Type2>的子类型，也不是List<Type2>的超类型。

这说明泛型有缺陷么？但实际上可以说数组是有缺陷的。

#### 第二点：具体化/不可具体化

不可具体化的类型：是指其**运行时**表示法包含的信息比它的**编译时**表示法包含的信息更少类型。

**数组是具体化的。**

因此数组会在**运行时**才知道并检查元素类型的约束。

**泛型是不可具体化的。**

因此泛型只在**编译时**强化了他们的类型信息，并在**运行时**丢弃（或者擦除）他们的元素类型信息。

>擦除就是使泛型可以与没有使用泛型的代码随意进行互用。

#### 为什么创建泛型数组是非法的？

>因为泛型数组不是类型安全的。

1. 数组：
    1. 协变：没有编译期类型安全
    2. 可具体化：保证运行时类型安全
2. 泛型：
    1. 不可变：保证编译期类型安全
    2. 不可具体化：没有运行时类型安全

协变/不可变：对于编译期来说。

具体化/不可具体化：对于运行时来说。
 


