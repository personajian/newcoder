#### 参考文章

1. [通过分析 JDK 源代码研究 TreeMap 红黑树算法实现](https://www.ibm.com/developerworks/cn/java/j-lo-tree/index.html)
2. [Java 8系列之重新认识HashMap](https://tech.meituan.com/java-hashmap.html)
3. [Java LinkedHashMap源码解析](http://www.importnew.com/16695.html)
4. [ConcurrentHashMap的红黑树实现分析](http://www.jianshu.com/p/23b84ba9a498)
5. [Java Collections Framework - HashMap](http://www.jianshu.com/p/be5ffa04a650)

#### HashMap

#### 工作原理

通过hash算法，通过put和get存储和获取对象。

1. 存储对象时，我们将K/V传给put方法时，它调用hashCode计算hash从而得到bucket位置，进一步存储，HashMap会根据当前bucket的占用情况自动调整容量(超过Load Factor则resize为原来的2倍)。如果发生碰撞的时候，Hashmap通过链表将产生碰撞冲突的元素组织起来。如果一个bucket中碰撞冲突的元素超过某个限制(默认是8)，则使用红黑树来替换链表，从而提高速度。
2. 获取对象时，我们将K传给get，它调用hashCode计算hash从而得到bucket位置，并进一步调用equals()方法确定键值对。

#### 数据结构

从结构实现来讲，HashMap是**数组+链表+红黑树**（JDK1.8增加了红黑树部分）实现的。

>类定义

```java
public class HashMap<K,V> 
    extends AbstractMap<K,V> 
    implements Map<K,V>, Cloneable, Serializable {
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //初始容量16
    static final int MAXIMUM_CAPACITY = 1 << 30; //最大容量
    static final float DEFAULT_LOAD_FACTOR = 0.75f; //默认负载因子
    transient Node<K,V>[] table; //哈希桶数组
    transient int size;
}
```

>使用Node<K,V>[]数组实现哈希桶数组。

```java
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;

}
```

>红黑树

```java
static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
    TreeNode<K,V> parent;  // red-black tree links
    TreeNode<K,V> left;
    TreeNode<K,V> right;
    TreeNode<K,V> prev;    // needed to unlink next upon deletion
    boolean red;
}
```

#### hash算法

>使用hash值的高位16位与低16进行XORs操作。

```java
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

1.7中的实现相对复杂的多，有兴趣的同学可以去看看1.7的源码。

如何确定key在数组的位置？
取模算法中的除法运算效率很低，在HashMap中通过h&（n-1）替代取模，得到所在数组位置，效率会高很多。（前提是保证数组的容量是2的整数倍）

#### put操作

1. 对key的hashCode()做hash，然后再计算index;
1. 如果没碰撞直接放到bucket里；
1. 如果碰撞了，以链表的形式存在buckets后；
1. 如果碰撞导致链表过长(大于等于TREEIFY_THRESHOLD)，就把链表转换成红黑树；
1. 如果节点已经存在就替换old value(保证key的唯一性)
1. 如果bucket满了(超过load factor*current capacity)，就要resize。

```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}

```

#### get操作

1. bucket里的第一个节点，直接命中；
1. 如果有冲突，则通过key.equals(k)去查找对应的entry
1. 若为树，则在树中通过key.equals(k)查找，O(logn)；
1. 若为链表，则在链表中通过key.equals(k)查找，O(n)

```java
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}
```

```java
    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

```