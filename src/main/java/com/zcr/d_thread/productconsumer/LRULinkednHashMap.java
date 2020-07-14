package com.zcr.d_thread.productconsumer;


/**
 * 18.	LinkedHashMap：LinkedHashMap是HashMap的子类，二者区别在于LinkedHashMap在HashMap的基础上
 * 采用双向链表将冲突链表的entry联系起来，这样保证了元素的遍历顺序跟插入顺序相同。可用于实现缓存。
 *
 * 冲突链表加入了双向链表的元素（before、after、next，其中next用于保证entry的链表结构，before、after用于完成双向链表的定义），
 * 同时引入了header指向双向链表的头部（哑元）。
 *
 * 这样LinkedHashMap在遍历的时候不同于HashMap需要先遍历整个table，LinkedHashMap只需要遍历header指向的双向链表即可，
 * 因此LinkedHashMap的迭代时间只和entry数量相关。其他的包括初始容量、负载因子以及hashCode、equals方法基本和HashMap一致。
 *
 * put()
 * put(K key，V value)方法插入过程类似HashMap，不同的是这里的插入有两个含义：
 * 对于table而言，新的entry插入到指定的bucket时如果产生冲突，使用头插法将entry插入冲突链表头部
 * 对于header而言，新的entry需要插入双向链表的尾部（保证迭代顺序）
 *
 * remove()
 * remove(Object key)删除过程类似HashMap的remove，不同的是这里的删除也有两个含义：
 * 对于table来说，删除对应bucket中的entry，然后修改冲突链表引用
 * 对于header来说，将entry从双向链表删除，然后修改冲突链表该位置前后元素的引用
 *
 * 1.存储结构
 * 继承自 HashMap，因此具有和 HashMap 一样的快速查找特性。
 * public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V>
 * 内部维护了一个双向链表，用来维护插入顺序或者 LRU 顺序。
 * /**





 /**
 * The head (eldest) of the doubly linked list.
 */
//transient LinkedHashMap.Entry<K,V> head;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The tail (youngest) of the doubly linked list.
 */
/*
transient LinkedHashMap.Entry<K,V> tail;

accessOrder 决定了顺序，默认为 false，此时维护的是插入顺序。
final boolean accessOrder;

        LinkedHashMap 最重要的是以下用于维护顺序的函数，它们会在 put、get 等方法中调用。
        void afterNodeAccess(Node<K,V> p) { }
        void afterNodeInsertion(boolean evict) { }

2.afterNodeAccess()
        当一个节点被访问时，如果 accessOrder 为 true，则会将该节点移到链表尾部。
        也就是说指定为 LRU 顺序之后，在每次访问一个节点时，会将这个节点移到链表尾部，保证链表尾部是最近访问的节点，
        那么链表首部就是最近最久未使用的节点。
        void afterNodeAccess(Node<K,V> e) { // move node to last
        LinkedHashMap.Entry<K,V> last;
        if (accessOrder && (last = tail) != e) {
        LinkedHashMap.Entry<K,V> p =
        (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
        p.after = null;
        if (b == null)
        head = a;
        else
        b.after = a;
        if (a != null)
        a.before = b;
        else
        last = b;
        if (last == null)
        head = p;
        else {
        p.before = last;
        last.after = p;
        }
        tail = p;
        ++modCount;
        }
        }
        3.afterNodeInsertion()
        在 put 等操作之后执行，当 removeEldestEntry() 方法返回 true 时会移除最晚的节点，也就是链表首部节点 first。
        evict 只有在构建 Map 的时候才为 false，在这里为 true。
        void afterNodeInsertion(boolean evict) { // possibly remove eldest
        LinkedHashMap.Entry<K,V> first;
        if (evict && (first = head) != null && removeEldestEntry(first)) {
        K key = first.key;
        removeNode(hash(key), key, null, false, true);
        }
        }
        removeEldestEntry() 默认为 false，如果需要让它为 true，需要继承 LinkedHashMap 并且覆盖这个方法的实现，这在实现 LRU 的缓存中特别有用，通过移除最近最久未使用的节点，从而保证缓存空间足够，并且缓存的数据都是热点数据。
protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return false;
        }
        4.LRU 缓存
        以下是使用 LinkedHashMap 实现的一个 LRU 缓存：
        1.设定最大缓存空间 MAX_ENTRIES 为 3；
        2.使用 LinkedHashMap 的构造函数将 accessOrder 设置为 true，开启 LRU 顺序；
        3.覆盖 removeEldestEntry() 方法实现，在节点多于 MAX_ENTRIES 就会将最近最久未使用的数据移除。
*/

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final int MAX_ENTRIES = 3;

    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }

    LRUCache() {
        super(MAX_ENTRIES, 0.75f, true);
    }
}

public class LRULinkednHashMap {

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>();
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");
        cache.get(1);
        cache.put(4, "d");
        System.out.println(cache.keySet());
    }
//[3, 1, 4]


}
