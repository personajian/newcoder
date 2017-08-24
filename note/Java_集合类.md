[TOC]

## 参考文章

- [通过分析 JDK 源代码研究 TreeMap 红黑树算法实现](https://www.ibm.com/developerworks/cn/java/j-lo-tree/index.html)
- [Java 8系列之重新认识HashMap](https://tech.meituan.com/java-hashmap.html)
- [Java集合对象排序](https://www.nowcoder.com/discuss/20072?type=1&order=0&pos=42&page=1?from=wb)
- [关于Java Collections的几个常见问题](http://www.importnew.com/23715.html)
- [Java LinkedHashMap源码解析](http://www.importnew.com/16695.html)
- [ConcurrentHashMap的红黑树实现分析](http://www.jianshu.com/p/23b84ba9a498)


####

### 一个图

![Image-java-collection.png](https://github.com/personajian/newcoder/raw/master/note/picture/Image-java-collection.png)


- 一个类：Collections工具类
- 三个知识点：Generic, Auto-boxing, Foreach
- 六个接口：Collection-->Set, List, Map; Iterator, Comparator

所有”排序“的类都实现了Comparable接口。
自然排序：Comparable
定制排序：Comparator
java集合类主要由两个接口派生而来：Collection和Map


    * Set:罐子、无序集合-->元素
    * List:数组、有序集合-->索引
    * Map:罐子、映射集合-->键值


#### Set:数学意义上的”集合“概念。（元素不重复）

HashSet底层哈希算法的实现，存取和查找方便。-->LinkedHashSet底层利用链表维护元素的次序。
SortSet-->TreeSet底层红黑树实现，确保元素处于排序状态。所有都必须实现Comparable接口。
EnumSet内部以位向量的形势存储。所有的元素必须是指定枚举类型的枚举值。

各种Set实现类的性能分析：

1. HashSet性能总是比TreeSet好（添加、查询操作）。
因为TreeSet需要额外的红黑树来维护集合元素的次序。--需要一个保持次序的Set时才使用TreeSet。
2. HashSet-->LinkedHashSet（插入，删除操作要慢一点）。
因为由维护链表所带来的额外开销。但由于有了链表，遍历LinkedHashSet会更快。
3. EnumSet是所有Set实现类中性能最好的，但是只能保存同一枚举类的枚举值为集合元素。

线程不安全：

- Set的三个实现类HashSet、TreeSet和EnumSet都是线程不安全的。
- 手动保证Set集合的同步性。
- 通过Collections工具类的synchronizedSortedSet方法来“包装”该Set集合。（此操作最好创建时进行，以防Set集合意外非同步。）


#### List:

ArrayList和Vector都是基于数组实现的List类，封装了一个动态的、允许再分配的Object[]数组。
ArrayList作为List主要的实现类。
Vector是一个古老的集合，具有很多缺点，尽量少用。

线程安全性：
     ArrayList线程不安全，需要Collections工具类保证ArrayList线程安全。
     Vector是线程安全的。Vectot-->Stack也是线程安全，性能较差，不推荐使用。（用ArrayDeque取代，实现栈的功能）

操作数组的工具类Arrays：
     asList(Object... a)方法：把一个数组或者指定个数的对象转换成一个List集合。
     这个List集合既不是ArrayList实现类的实例，也不是Vector实习类的实例，而是Array的内部类ArrayList。（Arrays.ArrayList是一个固定长度的List集合，只能遍历集合元素，不能增加、删除元素）
     
#### Queue接口:

 PriorityQueue：按队列元素的大小重新排序，peek(),poll()取得总是队列中最小的元素。违反了队列最基本的规则：FIFO。具有排序性质的队列，同TreeSet相似，所以就要求队列元素实现自然排序或者定制排序。

Queue-->Deque接口(Double Edge Queue):双端队列。

 ArrayDeque:和ArrayList底层实现基本相似，都采用了一个动态的、可重分配的Object[]数组来存储集合元素。推荐使用ArrayDeque，尽量避免使用Stack--因为Stack是古老的集合，性能较差。

 List, Deque-->LinkedList：（List集合、双端队列、栈）
- 是一个List集合，可以根据索引来随机访问集合中的元素。
- 是一个Deque，可以当成双端队列来使用，可以被当作”栈“来使用，也可以当作队列使用。 


#### Map:

key-value:

- key:不可重复，即Set集合。（源码是实现上，Map-->Set）
- value:重复可索引，即List集合。（是利用key对象来进行索引而已，被称为字典，或者关联数组。）


Map和Set之间的密切关系：

Map和Set的存储形势完全相同：

- Set-->HashSet,LinkedHashSet,SortedSet,TreeSet,EnumSet
- Map-->HashMap,LinkedHashSet,SortedMap,TreeMap,EnumMap

可以把key-value对中的value当成key的附庸。-->Map存放key-value对，Set存放当个元素。（从Java源码来看，是先实现了Map，然后通过包装一个所有value值为null的Map就实现了Set。）


Map的Entry内部类：
     封装了key-value对，而计算Entry存储时只考虑Entry封装的key。包含了三个方法：

     Object getKey()
     Object getValue()
     Object setValue(V value)

Map常用方法：
     Set entrySet():返回Map中包含的key-value对所组成的集合，每个集合元素都是Map.Entry对象。
     Set entryKey():返回Map中所有key组成的Set集合。

Map实现类：

1. HashMap:性能较好，线程不安全。Collections工具类保证其线程安全性。
1. Hashtable:古老，线程安全。也Vector类似，尽量少用Hashtable。
1. LinkedHashMap:双向链表来维护次序（插入次序）；迭代访问性能高。比较于HashMap、Hashtable实现了排序，比较于TreeMap避免了排序的成本。
1. Hashtable-->Properties类：读写属性文件，key、value都是字符串类型。
1. SortedMap-->TreeMap：红黑树的数据结构，每个key-value对即作为红黑树的一个节点。自然排序Comparable或者定制排序Comparator。
1. WeakHashMap:HashMap所有的key所引用的对象都是强引用，不会被垃圾回收。而WeakHashMap的key只保留着对实际对象的弱引用，意味着WeakHashMap对象的key所引用的对象没有被其他强引用变量所引用时，则这些key所引用的对象可能被垃圾回收，也可能自动删除这些key所对应的key-value对。
1. IdentityHashMap:要求两个key严格相等时才认为两个key相等。
1. EnumMap实现类:所有的key都必须是单个枚举类的枚举值。内部以数组形式保存，key按自然顺序。

各种Map实现类的性能分析：

1. HashMap和Hashtable实现机制几乎一样，Hashtableg是古老的、线程安全的集合。因此HashMap比Hashtable速度要快。
2. TreeMap通常比HashMap、Hashtable要慢（插入、删除key-value对时更慢）-->因为TreeMap底层采用红黑树来管理key-value对。有序状态，无须专门排序。keySet()-->foArray()-->binarySearch()
3. 多考虑使用HashMap，为快速查询设计的（底层是通过数组来存储key-value对）。哈希实现，快速存取。
4. 需要一个总是排好序的Map时，考虑TreeMap。
5. LinkedHashMap比HashMap要慢。因为使用链表来维护Map中key-value的添加顺序。
6. IndentityHashMap只是使用了==来判断元素相等。
7. EnumMap性能最好，但是只能使用同一个枚举类的枚举值作为key。

HashSet和HashMap：

1. 采用hash算法来决定集合中元素的存储位置，并通过hash算法来控制集合的大小。
1. hash冲突时，拉链法，链式形式存储，顺序搜索。
1. hash表的属性：容量、初始化容量、尺寸、负载因子（rehashing，时间空间折衷：高负载，高内存占用率，高查询开销；低负载，低内存占用率，高查询性能！）

#### Collections:

```java
Interface Collection<E>
    * Type Parameters:
E - the type of elements in this collection
    * All Superinterfaces:
Iterable<E>
    * All Known Subinterfaces:
BeanContext, BeanContextServices, BlockingDeque<E>, BlockingQueue<E>, Deque<E>, List<E>, NavigableSet<E>, Queue<E>, Set<E>, SortedSet<E>,TransferQueue<E>
    * All Known Implementing Classes:
AbstractCollection, AbstractList, AbstractQueue, AbstractSequentialList, AbstractSet, ArrayBlockingQueue, ArrayDeque, ArrayList,AttributeList, BeanContextServicesSupport, BeanContextSupport, ConcurrentHashMap.KeySetView, ConcurrentLinkedDeque, ConcurrentLinkedQueue,ConcurrentSkipListSet, CopyOnWriteArrayList, CopyOnWriteArraySet, DelayQueue, EnumSet, HashSet, JobStateReasons, LinkedBlockingDeque,LinkedBlockingQueue, LinkedHashSet, LinkedList, LinkedTransferQueue, PriorityBlockingQueue, PriorityQueue, RoleList, RoleUnresolvedList,Stack, SynchronousQueue, TreeSet, Vector
```

- 对List集合排序：reverse,shuffle,sort,sort(List list,Comparator),swap,rotate
- 查找、替换操作：binarySearch,max,max(Collection coll,Comparator comp),min,min(Collection coll,Comparator comp),fill,frequency,indexOfSubList,lastIndexOfSubList,replaceAll
- 同步控制：synchronizedXxx()
HashSet,TreeSet,ArrayList,ArrayDeque,LinkedList,HashMap和TreeMap都是线程不安全的。
- 设置不可变集合：emptyXxx(),singletonXxx(),unmodifiableXxx()

底层实现的理解：
     List,Set,Map

性能的比较是建立在**底层数据结构**的实现之上的：

1. 顺序表实现（数组）：索引查询速度快：ArrayList,ArrayDeque,Vector,Stack
1. 链表实现：插入删除效率高：LinkList(List+Deque)，LinkedHashMap,LinkedHashSet
1. 哈希实现：键值索引效率高：HashMap,HashSet,
1. 红黑树实现：元素排序方面性能突出：ssortedMap,sortedSet(接口)，TreeMap,TreeSet
1. 枚举实现：EnumMap，EnumSet
