package com.zcr.b_leetcode;

public class SwapNodesinPairs24 {

    public ListNode swapNodesinPairs(ListNode l) {
        ListNode dummy = new ListNode(0);
        dummy.next = l;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            swap2(cur);
            cur = cur.next.next;
        }
        return dummy.next;
    }

    public void swap2(ListNode cur) {
        ListNode temp = cur.next;
        cur.next = temp.next;
        temp.next = temp.next.next;
        cur.next.next = temp;
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
        SwapNodesinPairs24 swapNodesinPairs24 = new SwapNodesinPairs24();
        ListNode result = swapNodesinPairs24.swapNodesinPairs(l11);
        System.out.println(result);

    }
}
