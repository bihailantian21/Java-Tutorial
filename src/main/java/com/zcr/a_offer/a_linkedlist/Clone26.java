package com.zcr.a_offer.a_linkedlist;

import java.util.HashMap;

/**
 * 26、复杂链表的复制
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class Clone26 {

    /**
     * 原始方法：
     * 1、复制原始链表上每一个节点，把next连接起来
     * 2、设置每个节点的random节点
     * 定位每个节点的random节点要经过O(n)
     * 时间：O(n^2)
     *
     *
     *
     *
     * 从第二步着手优化时间
     * <p>
     * 思路一－Use HashMap
     * 思路:
     * 1、从左到右遍历链表，对每个结点都复制生成相应的副本结点，然后将对应的关系(之前的结点和新的副本结点)放入哈希表中；
     * 2、然后从左到右设置每一个副本结点的next和random指针，即找到原先cur的next和random的拷贝(从Map中获取)；
     * 有了Hash表，则能在O(1)时间内找到。
     * 3、最后返回副本结点的头结点(map.get(head))即可；
     * 注意：不是对原来的链表做处理了，而是对拷贝后的副本做出来，因为副本里面每一个节点O(1)就能找到。
     * <p>
     * 时间：O(n)
     * 空间：O(n)
     * <p>
     * 看一个例子:
     * 例如: 原链表 1->2->3->null，假设 1 的 rand 指针指向 3，2 的 rand 指针指向 null，3的rand指针指向 1。
     * 遍历到节点 1 时，可以从 map 中得到节点 1 的副本节点1，节点 1 的next 指向节点 2，
     * 所以从 map 中得到节点 2的副本节点 2，然后令 1’.next=2'，副本节点了的 next 指针就设置好了。
     * 同时节点 1的 rand 指向节点 3，所以从map 中得到节点 3 的副本节点 3，然后令 1‘.rand=3'，副本节点1的 rand 指针也设置好了。
     *
     * map
     * key value
     * 1    1'
     * 2    2'
     * 3    3'
     *
     * 1'.next=2'
     * 1'.random=3'
     *
     * 2'.next=3'
     * 2'.random=null
     *
     * 3'next=null
     * 3'.random=1'
     * 到最后输出所有的副本的指向就行了~
     *
     * 副本的指向等于副本的方式~
     *
     * @param pHead
     * @return
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = pHead;
        while (cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        cur = pHead;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(pHead);
    }

    /**
     * 思路二－O(1)空间
     * 本题最优解法是只用O(1)的空间来解决。
     * 1、第一个步骤，先从左到右遍历一遍链表，对每个结点cur都复制生成相应的副本结点copy，然后把副本结点copy放在cur和下一个要遍历结点的中间；
     * 2、再从左到右遍历一遍链表，在遍历时设置每一个结点的副本结点的random指针；
     * 3、设置完random指针之后，将链表拆成两个链表，返回第二个链表的头部；
     *
     * 第一遍：
     * 1->1'->2->2'->3->3'->4->4'
     * cur    next
     *        cur    next
     *
     * 第二遍：
     * cur copycur next
     *              cur copycur next
     *
     *
     * 第三遍：
     * cur copycur next
     *              cur
     *
     *
     * @param pHead
     * @return
     */
    public RandomListNode Clone2(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode cur = pHead;
        RandomListNode next;
        //1、第一个步骤，先从左到右遍历一遍链表，对每个结点cur都复制生成相应的副本结点copy，然后把副本结点copy放在cur和下一个要遍历结点的中间；
        while (cur != null) {
            next = cur.next;//先存着之前的next
            cur.next = new RandomListNode(cur.label);
            cur.next.next = next;
            cur = next;
        }
        //2.再从左到右遍历一遍链表，在遍历时设置每一个结点的副本结点的random指针；
        //为什么这么搞呢？因为如果A的random节点指向N，则A'指向N'。（因为有了A指向N，一下子很容易找到N'）
        cur = pHead;
        RandomListNode copyCur = null;
        while (cur != null) {
            next = cur.next.next;//保存原来链表中的下一个
            copyCur = cur.next;
            copyCur.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        //3、将链表拆成两个链表，返回第二个链表的头部；
        cur = pHead;
        RandomListNode copyHead = pHead.next;
        while (cur != null) {
            next = cur.next.next;//保存原来链表中的下一个
            copyCur = cur.next;
            cur.next = next;
            copyCur.next = next != null ? next.next : null;
            cur = next;
        }
        return copyHead;
    }


}
