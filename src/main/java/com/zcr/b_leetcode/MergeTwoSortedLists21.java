package com.zcr.b_leetcode;

/**
 * 21. Merge Two Sorted Lists
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */

/**
 * 21、合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeTwoSortedLists21 {

    /**
     * 使用类似合并有序数组的方法，外排(归并排序中最后合并的方式)的方式(那个小就先加哪一个)；
     * 但是这里要注意我这里设置了一个虚拟的头结点，这样的话方便第一个结点的添加和判断；
     *
     * 时间：O(n)
     * 空间：O(m+n)
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoSortedLists(ListNode l1,ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            }else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return head.next;
    }
    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(7);
        l11.next = l12;
        l12.next = l13;
        ListNode l21 = new ListNode(2);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(9);
        l21.next = l22;
        l22.next = l23;
        System.out.println(l11);
        System.out.println(l21);
        ListNode result = mergeTwoSortedLists(l11,l21);
        System.out.println(result);
    }
}
