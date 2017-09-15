#### 参考文章

1. [并发队列-无界非阻塞队列ConcurrentLinkedQueue原理探究](http://www.jianshu.com/p/9e73b9216322)

### 一、ConcurrentLinkedQueue

基于链接结点的无界线程安全队列。

- 两个volatile变量修饰了head节点和tail节点；
- 非阻塞队列：通过使用循环CAS方式来实现。

通过设计：CAS+hops来减少head、tail的更新（写操作），因为：volatile写还是比较耗性能的（需要插入内存屏障）。

#### 1.1 ConcurrentLinkedQueue的结构

head节点和tail节点，每个节点（Node）由节点元素（item）和指向下一个元素（next）的引用组成（Node节点类中集成了大量的CAS操作）。

```java
private transient volatile Node<E> head;
private transient volatile Node<E> tail;

```

```java
private static class Node<E> {
    volatile E item;
    volatile Node<E> next;
}

```

#### 1.2 入队列

特点：tail节点并不是时刻更新为队尾节点的！

```java
public boolean offer(E e) {
    checkNotNull(e);
    final Node<E> newNode = new Node<E>(e);

    for (Node<E> t = tail, p = t;;) {
        Node<E> q = p.next;
        if (q == null) {
            // p is last node
            if (p.casNext(null, newNode)) {
                if (p != t) // hop two nodes at a time
                    casTail(t, newNode);  // Failure is OK.
                return true;
            }
            // Lost CAS race to another thread; re-read next
        }
        else if (p == q)
            p = (t != (t = tail)) ? t : head;
        else
            // Check for tail updates after two hops.
            p = (p != t && t != (t = tail)) ? t : q;
    }
}
```

1. 定位尾节点
    - tail节点
    - tail节点的next节点
2. HOPS的设计意图
    - tail节点和尾节点的距离>=HOPS时，才更新tail节点
    - 本质：增加volatile变量的读操作来减少volatile变量的写操作。


#### 2.2 出队列

只有当head节点里没有元素时，出队操作才会更新head节点。（通过hops变量来减少使用CAS更新head节点的消耗）