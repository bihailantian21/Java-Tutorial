package com.zcr.b_leetcode;

/**
 * 82. Remove Duplicates from Sorted List II
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 *
 * Example 1:
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 *
 * Example 2:
 * Input: 1->1->1->2->3
 * Output: 2->3
 */

/**
 * 82、删除排序链表中的重复元素2
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 *
 * 示例 2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class RemoveDuplicatesfromSortedLists282 {

    /**
     * 一旦一个数有重复，它就不在结果中出现
     * 1-2-2-2-3-3-4
     * 一个数，必须跟它前面一个数进行比较和跟它后面一个数进行比较，都不相同，才能说明它是一个唯一的数。
     * 1、Dummy、Real：最终形成List中包括的最后一个Node 、pre：指向当前节点的前一个节点
     * Cur：指向当前的节点
     * 2、如果之前是dummy或者当前节点不等于之前的节点、后一个节点为null或者当前节点不等于后面的一个节点：将realNode.next=cur、realNode向后移动一位
     * （初始时，先不把realNode指向dummy，等curNode是一个有效节点的时候，再将realNode指向cur）
     * 3、如果遇到1->2->2这种，对于这种real.next和real只是在初次指向1了，之后再也没有变化，那么有效节点和之后重复的节点就从来没有断开
     * 所以每次向后移动pre、cur指针的时候，必须都将pre.next指向null
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode real = dummy;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if ((pre == dummy || pre.value != cur.value) && (cur.next == null || cur.next.value != cur.value)) {
                real.next = cur;
                real = cur;
            }
            pre = cur;
            cur = cur.next;
            pre.next = null;
        }
        return dummy.next;
    }
}
