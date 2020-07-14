package com.zcr.a_offer.a_linkedlist;

import com.zcr.b_leetcode.ListNode;

/**
 * 57.删除链表中重复的节点
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class deleteDuplication57 {

    //是删除‘存在重复的节点’的所有节点，而不是‘去重’
    public static ListNode deleteDuplication(ListNode head) {
        /**
         * 采用三个指针来进行遍历，同时删除重复的节点，因为是有序的链表，我们就可以确定，重复的元素肯定是在一块链接，所以我们就可以，用三指针，
         * 我们这里就叫pre、cur、nex 分别代表的是前中后三个指针，
         * 我们在考虑的情况中，如果头节点开始就重复，我们就处理很起来多了一种情况就需要额外处理，
         * 所以我们添加一个头节点，变成带头节点，保证了头节点开始不会重复，
         * 那么我们就可以开是让pre指向带头的节点，cur指向pre的next，nex指向cur的next。
         *
         * 接下来我们就可以看cur是否和nex相等，相等就让nex继续向下走，不相等然后再处理删除，
         * cur开始到nex中间节点都是要删除的（包含cur指向，不包含nex指向）删除，
         * 就用到了pre，删除完成让pre指向cur就可以了。
         *
         * 如果cur值与nex值不相等，那么就可以三个指针各自往前移动一个。
         */
        if (head == null) {//判断空
            return null;
        }
        if (head.next == null) {//判断是否只有一个节点
            return head;
        }
        //我们采用带头链表，自己添加一个头
        ListNode prehead = new ListNode(0);
        prehead.next = head;

        ListNode pre = prehead;
        ListNode cur = head;

        while (cur != null) {
            if (cur.next != null && cur.value == cur.next.value) {//有重复的
                //找到最后一个相同的节点
                while (cur.next != null && cur.value == cur.next.value) {
                    cur = cur.next;
                }
                pre.next = cur.next;
                cur = cur.next;
            } else {//没有重复的
                pre = pre.next;
                cur = cur.next;
            }
        }
        return prehead.next;
    }


    //例如，链表1->2->3->3->4->4->5 处理后为 1->2->3->4->5
    //       cur cur cur
    //是删除‘存在重复的节点’的节点，而‘去重’
    public static ListNode deleteDuplication2(ListNode head) {
        if (head == null) {//判断空
            return null;
        }
        if (head.next == null) {//判断是否只有一个节点
            return head;
        }
        //我们采用带头链表，自己添加一个头
        ListNode prehead = new ListNode(0);
        prehead.next = head;

        ListNode cur = head;
        while (cur != null) {
            if (cur.next != null && cur.value == cur.next.value) {//有重复的
                cur.next = cur.next.next;
            } else {//没有重复的
                cur = cur.next;
            }
        }
        return prehead.next;
    }

}
