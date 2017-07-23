
## 视频-JVM运行机制

### 1. JVM启动流程

![JVM启动流程](https://github.com/personajian/newcoder/raw/master/resources/picture/Image-jvm-start-flow.png)

### 2. JVM基本结构

![JVM基本结构](https://github.com/personajian/newcoder/raw/master/resources/picture/Image-jvm-base-structure.png)

#### PC寄存器

- 每个线程拥有一个PC寄存器
- 在线程创建时 创建 
- 指向下一条指令的地址
- 执行本地方法时，PC的值为undefined

#### 方法区（JDK6时，String等常量信息置于方法，JDK7时，已经移动到了堆）

- 保存装载的类信息
- 类型的常量池
- 字段，方法信息
- 方法字节码
- 通常和永久区(Perm)关联在一起

#### Java堆

- 和程序开发密切相关
- 应用系统对象都保存在Java堆中
- 所有线程共享Java堆
- 对分代GC来说，堆也是分代的

#### Java栈

- 线程私有
- 栈由一系列帧组成（因此Java栈也叫做帧栈）
- 帧保存一个方法的局部变量、操作数栈、常量池指针
- 每一次方法调用创建一个帧，并压栈–GC的主要工作区间


1. Java栈 – 局部变量表 包含参数和局部变量
2. Java栈 – 函数调用组成帧栈
3. Java栈 – 操作数栈–Java没有寄存器，所有参数传递使用操作数栈
4. Java栈 – 栈上分配
    - 堆上分配，每次需要清理空间
    - 栈上分配，函数调用完成自动清理
    - 直接分配在栈上，可以自动回收，减轻GC压力
    - 大对象或者逃逸对象无法栈上分配


栈、堆、方法区交互

![运行时数据区](https://github.com/personajian/newcoder/raw/master/resources/picture/Image-jvm-runtime-data-area.png)


### 3. 内存模型

- 每一个线程有一个工作内存和主存独立
- 工作内存存放主存中变量的值的拷贝

![内存模型](https://github.com/personajian/newcoder/raw/master/resources/picture/Image-jvm-memory-model.png)
![内存模型2](https://github.com/personajian/newcoder/raw/master/resources/picture/Image-jvm-memory-model-2.png)


### 4. 关于线程

#### volatile

- 没有volatile -server 运行 无法停止
- volatile 不能代替锁，一般认为volatile 比锁性能好（不绝对）
- 选择使用volatile的条件是：语义是否满足应用

#### 可见性

一个线程修改了变量，其他线程可以立即知道

保证可见性的方法

- volatile
- synchronized （unlock之前，写变量值回主存）
- final(一旦初始化完成，其他线程就可见)

#### 有序性

- 在本线程内，操作都是有序的
- 在线程外观察，操作都是无序的。（指令重排 或 主内存同步延时）

#### 指令重排

- 线程内串行语义
- 指令重排：破坏线程间的有序性
- 保证有序性的方法

指令重排的基本原则

- 程序顺序原则：一个线程内保证语义的串行性
- volatile规则：volatile变量的写，先发生于读
- 锁规则：解锁(unlock)必然发生在随后的加锁(lock)前
- 传递性：A先于B，B先于C 那么A必然先于C
- 线程的start方法先于它的每一个动作
- 线程的所有操作先于线程的终结（Thread.join()）
- 线程的中断（interrupt()）先于被中断线程的代码
- 对象的构造函数执行结束先于finalize()方法

### 5. 编译和解释运行

#### 解释运行

- 解释执行以解释方式运行字节码
- 解释执行的意思是：读一句执行一句

#### 编译运行（JIT）

- 将字节码编译成机器码
- 直接执行机器码
- 运行时编译
- 编译后性能有数量级的提升

