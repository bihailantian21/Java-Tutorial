package com.zcr.b_leetcode;

/**
 * 2. Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */

/**
 * 2. 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AddTwoNumbers2 {

    /**
     * 分类讨论
     * case1:对应位置上都有数
     * case2:只有一个有数
     * case3:最后是否要进位
     * 时间O(max(m,n))
     * 空间O(max(m,n))新列表的长度
     *
     * 简化一下：这三种情况的代码大部分都是重复的,只是：
     * 初始时要判断是否位置上没有数、往下移动时是否已经是最后一位
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1,ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        /*while (l1 != null && l2 != null) {
            int dig = l1.value + l2.value + carry;
            int val = dig % 10;
            carry = dig / 10;
            current.next = new ListNode(val);
            current = current.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {//l2为空
            int dig = l1.value + carry;
            int val = dig % 10;
            carry = dig / 10;
            current.next = new ListNode(val);
            current = current.next;
            l1 = l1.next;
        }
        while (l2 != null) {//l1为空
            int dig = l2.value + carry;
            int val = dig % 10;
            carry = dig / 10;
            current.next = new ListNode(val);
            current = current.next;
            l2 = l2.next;
        }*/
        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.value : 0;
            int y = (l2 != null) ? l2.value : 0;
            int sum = x + y + carry;
            int val = sum % 10;
            carry = sum / 10;
            current.next = new ListNode(val);
            current = current.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            current.next = new ListNode(carry);
        }
        return dummy.next;
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
        AddTwoNumbers2 addTwoNumbers2 = new AddTwoNumbers2();
        ListNode result = addTwoNumbers2.addTwoNumbers(l11,l21);
        System.out.println(result);
    }
}
