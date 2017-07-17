[TOC]

##参考文章：
- [Java并发编程：并发容器之ConcurrentHashMap（转载）](http://www.cnblogs.com/dolphin0520/p/3932905.html)
- [Java并发编程之ConcurrentHashMap](http://www.iteye.com/topic/1103980)
- [ConcurrentHashMap之实现细节](http://www.iteye.com/topic/344876)
- [聊聊并发（四）深入分析ConcurrentHashMap](http://ifeve.com/ConcurrentHashMap/)

## 同步容器

### 原末

A:同步容器->串行化访问容器状态->保证线程的安全性->严重降低了并发性，吞吐量严重降低。

B:

S:对多线程并发访问设计，提供了并发性能较好的并发容器，引入了java.util.concurrent包。

主要解决了两个问题： 

1. 避免synchronized，提供并发性。 
2. 并发安全的复合操作，保证并发环境下的迭代操作不会出错。
util.concurrent中容器在迭代时，可以不封装在synchronized中，可以保证不抛异常，但是未必每次看到的都是"最新的、当前的"数据。

### 并发容器的种类和作用

- ConcurrentHashMap代替同步的Map（Collections.synchronized（new HashMap()）），众所周知，HashMap是根据散列值分段存储的，同步Map在同步的时候锁住了所有的段，而ConcurrentHashMap加锁的时候根据散列值锁住了散列值锁对应的那段，因此提高了并发性能。ConcurrentHashMap也增加了对常用复合操作的支持，比如"若没有则添加"：putIfAbsent()，替换：replace()。这2个操作都是原子操作。
- CopyOnWriteArrayList和CopyOnWriteArraySet分别代替List和Set，主要是在遍历操作为主的情况下来代替同步的List和同步的Set，这也就是上面所述的思路：迭代过程要保证不出错，除了加锁，另外一种方法就是"克隆"容器对象。
- ConcurrentLinkedQuerue是一个先进先出的队列。它是非阻塞队列。
- ConcurrentSkipListMap可以在高效并发中替代SoredMap（例如用Collections.synchronzedMap包装的TreeMap）。
- ConcurrentSkipListSet可以在高效并发中替代SoredSet（例如用Collections.synchronzedSet包装的TreeMap）。

## ConcurrentHashMap


### 原末

HashMap是非线程安全的，Hashtable是线程安全的，但是由于Hashtable是采用synchronized进行同步，相当于所有线程进行读写时都去竞争一把锁，导致效率非常低下。
　　ConcurrentHashMap可以做到读取数据不加锁，并且其内部的结构可以让其在进行写操作的时候能够将锁的粒度保持地尽量地小，不用对整个ConcurrentHashMap加锁。

### 实现原理

ConcurrentHashMap允许多个修改操作并发进行，其关键在于使用了锁分离技术。它使用了多个锁来控制对hash表的不同部分进行的修改。ConcurrentHashMap内部使用段(Segment)来表示这些不同的部分，每个段其实就是一个小的hash table，它们有自己的锁。只要多个修改操作发生在不同的段上，它们就可以并发进行。

### 内部结构

内部采用了一个叫做Segment的结构，一个Segment其实就是一个类Hash Table的结构，Segment内部维护了一个链表数组

![Segment](https://github.com/personajian/newcoder/raw/master/resources/picture/currenthashmap.png)

定位一个元素的过程需要进行两次Hash操作：
第一次Hash定位到Segment
第二次Hash定位到元素所在的链表的头部
副作用：Hash的过程要比普通的HashMap要长
好处：写操作的时候可以只对元素所在的Segment进行加锁即可，不会影响到其他的Segment，
最理想的情况下，CHM可以最高同时支持Segment数量大小的写操作（刚好这些写操作都非常平均地分布在所有的Segment上）。

    static final class Segment<K,V> extends ReentrantLock implements Serializable {
        transient volatile int count;
        transient int modCount;
        transient int threshold;
        transient volatile HashEntry<K,V>[] table;
        final float loadFactor;
    }

　　详细解释一下Segment里面的成员变量的意义：
count：Segment中元素的数量
modCount：对table的大小造成影响的操作的数量（比如put或者remove操作）
threshold：阈值，Segment里面元素的数量超过这个值依旧就会对Segment进行扩容
table：链表数组，数组中的每一个元素代表了一个链表的头部
loadFactor：负载因子，用于确定threshold
HashEntry

### 不变(Immutable)和易变(Volatile)
 
ConcurrentHashMap完全允许多个读操作并发进行，读操作并不需要加锁。如果使用传统的技术，如HashMap中的实现，如果允许可以在hash链的中间添加或删除元素，读操作不加锁将得到不一致的数据。ConcurrentHashMap实现技术是保证HashEntry几乎是不可变的。HashEntry代表每个hash链中的一个节点，其结构如下所示：

可以看到除了value不是final的，其它值都是final的，这意味着不能从hash链的中间或尾部添加或删除节点，因为这需要修改next引用值，所有的节点的修改只能从头部开始。
对于put操作，可以一律添加到Hash链的头部。
但是对于remove操作，可能需要从中间删除一个节点，这就需要将要删除节点的前面所有节点整个复制一遍，最后一个节点指向要删除结点的下一个结点。这在讲解删除操作时还会详述。为了确保读操作能够看到最新的值，将value设置成volatile，这避免了加锁。
 
Segment中的元素是以HashEntry的形式存放在链表数组中的，看一下HashEntry的结构：

    static final class HashEntry<K,V> {
        final K key;
        final int hash;
        volatile V value;
        final HashEntry<K,V> next;
    }

　　可以看到HashEntry的一个特点，除了value以外，其他的几个变量都是final的，这样做是为了防止链表结构被破坏，出现ConcurrentModification的情况。

### 相关操作

#### 初始化

public ConcurrentHashMap(int initialCapacity,
                         float loadFactor, int concurrencyLevel) {
    if (!(loadFactor > 0) || initialCapacity < 0 || concurrencyLevel <= 0)
        throw new IllegalArgumentException();
  
    if (concurrencyLevel > MAX_SEGMENTS)
        concurrencyLevel = MAX_SEGMENTS;
  
    // Find power-of-two sizes best matching arguments
    int sshift = 0;
    int ssize = 1;
    while (ssize < concurrencyLevel) {
        ++sshift;
        ssize <<= 1;
    }
    segmentShift = 32 - sshift;
    segmentMask = ssize - 1;
    this.segments = Segment.newArray(ssize);
  
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    int c = initialCapacity / ssize;
    if (c * ssize < initialCapacity)
        ++c;
    int cap = 1;
    while (cap < c)
        cap <<= 1;
  
    for (int i = 0; i < this.segments.length; ++i)
        this.segments[i] = new Segment<K,V>(cap, loadFactor);
}

CurrentHashMap的初始化一共有三个参数
一个initialCapacity，表示初始的容量
一个loadFactor，表示负载参数
一个concurrentLevel，代表ConcurrentHashMap内部的Segment的数量，ConcurrentLevel一经指定，不可改变，后续如果ConcurrentHashMap的元素数量增加导致ConrruentHashMap需要扩容，ConcurrentHashMap不会增加Segment的数量，而只会增加Segment中链表数组的容量大小，这样的好处是扩容过程不需要对整个ConcurrentHashMap做rehash，而只需要对Segment里面的元素做一次rehash就可以了。
　　1.根据concurrentLevel来new出Segment，这里Segment的数量是不大于concurrentLevel的最大的2的指数，就是说Segment的数量永远是2的指数个，这样的好处是方便采用移位操作来进行hash，加快hash的过程。
        2.根据intialCapacity确定Segment的容量的大小，每一个Segment的容量大小也是2的指数，同样使为了加快hash的过程。
　　特别注意一下两个变量，分别是segmentShift和segmentMask，这两个变量在后面将会起到很大的作用，假设构造函数确定了Segment的数量是2的n次方，那么segmentShift就等于32减去n，而segmentMask就等于2的n次方减一。

#### get操作

public V get(Object key) {
    int hash = hash(key.hashCode());
    return segmentFor(hash).get(key, hash);
}

final Segment<K,V> segmentFor(int hash) {
    return segments[(hash >>> segmentShift) & segmentMask];
}

segmentFor这个函数用于确定操作应该在哪一个segment中进行
用了位操作来确定Segment，根据传入的hash值向右无符号右移segmentShift位，然后和segmentMask进行与操作，结合我们之前说的segmentShift和segmentMask的值，就可以得出以下结论：假设Segment的数量是2的n次方，根据元素的hash值的高n位就可以确定元素到底在哪一个Segment中。

确定了需要在哪一个segment中进行操作以后，接下来的事情就是调用对应的Segment的get方法：

    V get(Object key, int hash) {
        if (count != 0) { // read-volatile
            HashEntry<K,V> e = getFirst(hash);
            while (e != null) {
                if (e.hash == hash && key.equals(e.key)) {
                    V v = e.value;
                    if (v != null)
                        return v;
                    return readValueUnderLock(e); // recheck
                }
                e = e.next;
            }
        }
        return null;
    }

先看第二行代码，这里对count进行了一次判断，其中count表示Segment中元素的数量，我们可以来看一下count的定义：
transient volatile int count;
　　 可以看到count是volatile的，实际上这里里面利用了volatile的语义：
　　对volatile字段的写入操作happens-before于每一个后续的同一个字段的读操作。
　　因为实际上put、remove等操作也会更新count的值，所以当竞争发生的时候，volatile的语义可以保证写操作在读操作之前，也就保证了写操作对后续的读操作都是可见的，这样后面get的后续操作就可以拿到完整的元素内容。
　　然后，在第三行，调用了getFirst()来取得链表的头部：

    HashEntry<K,V> getFirst(int hash) {
        HashEntry<K,V>[] tab = table;
        return tab[hash & (tab.length - 1)];
    }


 　　同样，这里也是用位操作来确定链表的头部，hash值和HashTable的长度减一做与操作，最后的结果就是hash值的低n位，其中n是HashTable的长度以2为底的结果。
　　在确定了链表的头部以后，就可以对整个链表进行遍历，看第4行，取出key对应的value的值，如果拿出的value的值是null，则可能这个key，value对正在put的过程中，如果出现这种情况，那么就加锁来保证取出的value是完整的，如果不是null，则直接返回value。

#### put操作

put操作的前面也是确定Segment的过程，这里不再赘述，直接看关键的segment的put方法：

    V put(K key, int hash, V value, boolean onlyIfAbsent) {
        lock();
        try {
            int c = count;
            if (c++ > threshold) // ensure capacity
                rehash();
            HashEntry<K,V>[] tab = table;
            int index = hash & (tab.length - 1);
            HashEntry<K,V> first = tab[index];
            HashEntry<K,V> e = first;
            while (e != null && (e.hash != hash || !key.equals(e.key)))
                e = e.next;
      
            V oldValue;
            if (e != null) {
                oldValue = e.value;
                if (!onlyIfAbsent)
                    e.value = value;
            }
            else {
                oldValue = null;
                ++modCount;
                tab[index] = new HashEntry<K,V>(key, hash, first, value);
                count = c; // write-volatile
            }
            return oldValue;
        } finally {
            unlock();
        }
    }


1.首先对Segment的put操作是加锁完成的，然后在第五行，如果Segment中元素的数量超过了阈值（由构造函数中的loadFactor算出）这需要进行对Segment扩容，并且要进行rehash，关于rehash的过程大家可以自己去了解，这里不详细讲了。
2.第8和第9行的操作就是getFirst的过程，确定链表头部的位置。
3.第11行这里的这个while循环是在链表中寻找和要put的元素相同key的元素，如果找到，就直接更新更新key的value，如果没有找到，则进入21行这里，生成一个新的HashEntry并且把它加到整个Segment的头部，然后再更新count的值。

#### remove操作

Remove操作->定位Segment->调用Segment的remove()：

    V remove(Object key, int hash, Object value) {
        lock();
        try {
            int c = count - 1;
            HashEntry<K,V>[] tab = table;
            int index = hash & (tab.length - 1);
            HashEntry<K,V> first = tab[index];
            HashEntry<K,V> e = first;
            while (e != null && (e.hash != hash || !key.equals(e.key)))
                e = e.next;
      
            V oldValue = null;
            if (e != null) {
                V v = e.value;
                if (value == null || value.equals(v)) {
                    oldValue = v;
                    // All entries following removed node can stay
                    // in list, but all preceding ones need to be
                    // cloned.
                    ++modCount;
                    HashEntry<K,V> newFirst = e.next;
                    for (HashEntry<K,V> p = first; p != e; p = p.next)
                        newFirst = new HashEntry<K,V>(p.key, p.hash,
                                                      newFirst, p.value);
                    tab[index] = newFirst;
                    count = c; // write-volatile
                }
            }
            return oldValue;
        } finally {
            unlock();
        }
    }

首先remove操作也是确定需要删除的元素的位置，不过这里删除元素的方法不是简单地把待删除元素的前面的一个元素的next指向后面一个就完事了，我们之前已经说过HashEntry中的next是final的，一经赋值以后就不可修改，在定位到待删除元素的位置以后，程序就将待删除元素前面的那一些元素全部复制一遍，然后再一个一个重新接到链表上去，看一下下面这一幅图来了解这个过程：

![remove1](https://github.com/personajian/newcoder/raw/master/resources/picture/remove1.png)

假设链表中原来的元素如上图所示，现在要删除元素3，那么删除元素3以后的链表就如下图所示：

![remove2](https://github.com/personajian/newcoder/raw/master/resources/picture/remove2.png)


#### size操作

ConcurrentHashMap有一些操作是在多个Segment中进行，比如size操作，ConcurrentHashMap的size操作也采用了一种比较巧的方式，来尽量避免对所有的Segment都加锁。

前面我们提到了一个Segment中的有一个modCount变量，代表的是对Segment中元素的数量造成影响的操作的次数，这个值只增不减，
size操作就是遍历了两次Segment，每次记录Segment的modCount值，然后将两次的modCount进行比较，

- 如果相同，则表示期间没有发生过写入操作，就将原先遍历的结果返回，
- 如果不相同，则把这个过程再重复做一次，
- 如果再不相同，则就需要将所有的Segment都锁住，然后一个一个遍历了，

具体的实现大家可以看ConcurrentHashMap的源码，这里就不贴了。



