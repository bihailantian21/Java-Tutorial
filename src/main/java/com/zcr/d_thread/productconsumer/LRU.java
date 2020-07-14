package com.zcr.d_thread.productconsumer;

import java.util.HashMap;

/**
 * Q：设计一个LRU淘汰策略缓存系统？key不超过1000.
 * void put(String key,Objcet value);
 * Objcet get(String key);
 */


/**
 * LinkedHashMap：使用双向链表来维护元素的顺序，顺序为插入顺序或者最近最少使用（LRU）顺序。可用于实现缓存。
 */


/**
 * 采用双向链表把HashMap给连接起来
 */

/**
 * 访问节点时，若哈希链表中没有对应的数据,从数据库中读取，插入到链表的末尾。如果已经达到上线，就将链表的头部节点删掉。
 *           若有对应的数据，先更新：将节点移动到链表的末尾，然后返回数据
 *
 * 其实就是维护了两套机制：
 * （1）缓存hashMap---为了在O(1)内找到数据、为了放置所有的缓存数据
 * （2）链表顺序结构---将hashMap中的所有节点连接起来。
 *               ---为了维护节点的插入顺序、将最近访问过的放在尾部、每次缓存满了的化淘汰头部的
 * 所以每次要维护这两套机制
 * */
public class LRU {
    private int capacity;//链表容量
    private HashMap<String, Node> nodeMap;//这个HashMap代表了缓存的所有元素---为什么需要它，因为还是需要在O(1)内查找元素
    private Node head;//链表头节点
    private Node tail;//链表尾节点


    public LRU(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
    }


    public String get(String key) {
        //找的话还是按照缓存hashmap的方式O(1)时间找到
        Node node = nodeMap.get(key);
        //没有找到的话，说明这个节点在缓存hashmap中就不存在，就返回空
        //如果做进一步处理的化，就是将这个节点对应的元素从数据库移到这个缓存的尾部
        if (node == null) {
            return null;
        }
        //获取就是用到了，将这个值移到尾部
        refreshNode(node);
        return node.value;
    }

    public void put(String key, String value) {
        //放的话，先找
        Node node = nodeMap.get(key);
        if (node == null) {
            //如果key不存在,插入到尾部
            if (nodeMap.size() > capacity) {
                //如果缓存hashMap满了,先将头部节点从缓存hashMap中移除
                //然后删除链表头部的节点
                String oldkey = removeNode(head);
                nodeMap.remove(oldkey);
            }
            //没有满的话或者满了已经删了头部的节点后，直接将节点添加到链表尾部
            node = new Node(key, value);
            addNode(node);
            //然后将节点加入缓存hashMap
            nodeMap.put(key,node);
        } else {
            //如果key存在,修改就是用到了，将这个值移到尾部
            node.value = value;
            refreshNode(node);
        }
    }

    public void remove(String key) {
        Node node = nodeMap.get(key);
        removeNode(node);
        nodeMap.remove(key);
    }

    /**
     * 刷新被访问的节点
     * @param node
     */
    private void refreshNode(Node node) {
        //如果访问的是尾节点，无需移动节点
        if (node == tail) {
            return;
        }
        //移除节点
        removeNode(node);
        //重新插入节点
        addNode(node);
    }

    /**
     * 删除链表中的某一个节点
     * 用途：
     * （1）新访问到某个节点时需要改变某个节点位置，先删、再加到尾部
     * （2）缓存满了需要删除头部节点，需要获取头部节点的值
     * @param node
     * @return
     */
    private String removeNode(Node node) {
        if (node == tail) {
            //移除尾节点
            tail = tail.pre;
        } else if (node == head) {
            //移除头节点
            head = head.next;
        } else {
            //移除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /**
     * 将节点添加到链表的尾部
     * @param node
     */
    private void addNode(Node node) {
        if (tail != null) {
            tail.next = node;
            node.pre = tail;
            node.next = null;
        }
        tail = node;
    }

    public static void main(String[] args) {
        LRU lru = new LRU(5);
        lru.put("001","1");
        lru.put("002","2");
        lru.put("003","3");
        lru.put("004","4");
        lru.put("005","5");
        lru.get("002");
        lru.put("004","4-4");
        lru.put("006","6");
        System.out.println(lru.get("001"));
        System.out.println(lru.get("006"));

    }



}

class Node {
    Node next;
    Node pre;
    String key;
    String value;

    public Node() {
    }

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}