package com.zcr.b_leetcode;

import java.util.Stack;

public class ReverseNodesinkGroup25 {

    public ListNode reverseNodesinkGroup(ListNode l, int k) {
        if (l == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode(0);
        dummy.next = l;
        ListNode cur = dummy;
        ListNode next = cur.next;
        while (next != null) {
            for (int i = 0; i < k && next != null; i++) {
                stack.push(next);
                next = next.next;
            }
            if (stack.size() != k) {
                return dummy.next;
            }
            while (stack.size() != 0) {
                cur.next = stack.pop();
                cur = cur.next;
            }
            cur.next = next;//这句很重要，把前面已经换了位置的和后面没有换位置的连接起来
        }
        return dummy.next;
    }

    public ListNode reverseNoesinkGroup2(ListNode l, int k) {
        if (l == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = l;
        ListNode pre = dummy;
        while (pre != null) {
            //下一轮tail就是pre
            pre = reverse(pre,k);
        }
        return dummy.next;
    }

    public ListNode reverse(ListNode pre,int k){
        ListNode last = pre;
        for (int i = 0; i < k + 1; i++) {
            last = last.next;
            if (i != k && last == null) {
                return null;
            }
        }
        ListNode tail = pre.next;
        ListNode cur = pre.next.next;
        while (cur != last) {
            ListNode next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            tail.next = next;
            cur = next;
        }
        return tail;
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

        l13.next = l21;
        ReverseNodesinkGroup25 reverseNodesinkGroup25 = new ReverseNodesinkGroup25();
        ListNode result = reverseNodesinkGroup25.reverseNoesinkGroup2(l11,3);
        System.out.println(result);
    }
}
