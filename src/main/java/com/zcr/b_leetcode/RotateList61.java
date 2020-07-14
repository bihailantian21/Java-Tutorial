package com.zcr.b_leetcode;

/**
 * 61. Rotate List
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 *
 * Example 2:
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */

/**
 * 61、旋转列表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 *
 * 示例 2:
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 */
public class RotateList61 {

    /**
     * 给定一个LinkedList和一个整数，将List向右
     *
     * 1->2->3->4->5   向右移动2
     * |     |
     * s     f
     *       s      f
     * 4->5->1->2->3
     * 用快慢指针来做
     * 首先fast先走k步，然后我们要做的事情就是slow和fast同时移动一直到队尾。
     * 现在slow的位置就是结果种的最后一个位置。
     * 变形：fast.next指向head，head变成了slow->next，slow.next变成了null
     *
     * 注意：有一种情况就是，k大于了链表长度。那么就需要k%len 12%5=2
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateList(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow  = head;
        int len = 0;
        while (fast != null) {
            len++;
            fast = fast.next;
        }
        fast = head;
        for (int i = 0; i < (k % len); i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }
}
