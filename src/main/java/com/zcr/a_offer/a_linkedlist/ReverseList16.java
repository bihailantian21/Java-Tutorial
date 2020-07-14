package com.zcr.a_offer.a_linkedlist;

import com.zcr.b_leetcode.ListNode;

/**
 * 16、反转单链表
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class ReverseList16 {
    /**
     * 很经典的翻转链表的题目，使用pre、next指针，pre指向当前cur的前一个，next是当前cur的下一个指针；
     * 然后每次都改变cur的next为pre，循环递推，直到cur = null，最后返回pre；
     *    1->2->3->4->5
     *    1<-2->3->4->5
     * pre head next
     *     pre head next
     * 1<-2<-3->4->5
     *     2   1
     *      pre cur next
     *
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        // 判断链表为空或长度为1的情况
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = null; // 当前节点的前一个节点
        ListNode next = null; // 当前节点的下一个节点
        //这道题中不需要再设置cur了，因为head留着没什么用
        while( head != null){
            next = head.next; // 记录当前节点的下一个节点位置；
            head.next = pre; // 让当前节点指向前一个节点位置，完成反转
            pre = head; // pre 往右走
            head = next;// 当前节点往右继续走
        }
        return pre;//当前节点为空了，说明新节点中pre就是头节点
    }

    /**
     * 思路和上面还是一样的，就是pre = cur，cur = next这两行替换成去递归了，没什么特殊的。
     * @param head
     * @return
     */
    public ListNode ReverseList2(ListNode head) {
        return reverse(head, null);
    }

    private ListNode reverse(ListNode cur, ListNode pre) {
        if (cur == null)
            return pre;
        ListNode next = cur.next;
        cur.next = pre;
        return reverse(next, cur);
    }
}
