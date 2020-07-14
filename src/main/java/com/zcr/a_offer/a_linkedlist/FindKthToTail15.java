package com.zcr.a_offer.a_linkedlist;

import com.zcr.b_leetcode.ListNode;

/**
 * 15、链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个结点。
 *
 * 注意：
 * 1、head为null
 * 2、k为0
 * 3、如果链表中的节点个数小于k，而不做任何处理，会报错
 * 4、如果k大于节点个数，也返回null
 *
 * 1->2->3->4->5 2
 * |     |
 *          |     |
 *
 * 设置两个指针一开始都指向head；
 * 然后先让第一个指针first走k-1步，然后两个指针再一起走，当第二个指针second走到末尾(second.next = null)时，第一个指针first就刚好指向倒数第k个结点；
 *                       k                                               second=null
 * 具体长度关系推一下就清楚了；
 *
 */
public class FindKthToTail15 {

    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) {
            return  null;
        }
        if (k == 0) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            if (fast == null) {//避免问题3、4出现
                return null;
            }
            fast = fast.next;
        }
        while (fast != null) {//这里一定要注意是fast而不是fast.next
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 常规思路
     * 先遍历一遍求出链表长度len；
     * 然后再从头开始走len - k 个就可以到倒数k个结点；
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail0(ListNode head, int k) {
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        if (k > len)
            return null;
        cur = head;
        for (int i = 0; i < len - k; i++)
            cur = cur.next;
        return cur;
    }
}
