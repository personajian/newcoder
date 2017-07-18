#### 参考文章

1. [java中的CAS](http://www.jianshu.com/p/fb6e91b013cc)

#### CAS

CAS（Compare And Swap即比较并替换）是在处理器指令集上支持的原子操作。

**CAS有三个操作数：内存值V、旧的预期值A、要修改的值B，当且仅当预期值A和内存值V相同时，将内存值修改为B并返回true，否则什么都不做并返回false。**

java.util.concurrent.atomic包下的原子操作类都是基于CAS实现的。

java.util.concurrent.atomic中主要是调用了Unsafe类的CAS来实现硬件级别的原子操作。

Unsafe类中的compareAndSwapInt方法是一个本地方法调用，这个本地方法在openjdk中依次调用c++代码：unsafe.cpp，atomic.cpp，atomic_window_x86.inline.hpp。

```
inline jint Atomic::cmpxchg (jint exchange_value, volatile jint* dest, jint compare_value) {
    int mp = os::isMP(); //判断是否是多处理器
    _asm {
        mov edx, dest
        mov ecx, exchange_value
        mov eax, compare_value
        LOCK_IF_MP(mp)
        cmpxchg dword ptr [edx], ecx
    }
}
```


根据当前处理器类型来决定是否为cmpxchg指令添加lock前缀。

如果是多处理器，为cmpxchg指令添加lock前缀。
反之，就省略lock前缀。（单处理器会不需要lock前缀提供的内存屏障效果）


intel手册对lock前缀的说明如下：

1. 确保对内存读改写操作的原子执行。
在Pentium及之前的处理器中，带有lock前缀的指令在执行期间会锁住总线，使得其它处理器暂时无法通过总线访问内存，很显然，这个开销很大。在新的处理器中，Intel使用缓存锁定来保证指令执行的原子性。缓存锁定将大大降低lock前缀指令的执行开销。
2. 禁止该指令，与前面和后面的读写指令重排序。
3.把写缓冲区的所有数据刷新到内存中。
上面的第2点和第3点所具有的内存屏障效果，保证了**CAS同时具有volatile读和volatile写的内存语义**。

