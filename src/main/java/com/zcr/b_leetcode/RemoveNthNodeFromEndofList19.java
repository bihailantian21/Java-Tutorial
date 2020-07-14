package com.zcr.b_leetcode;

public class RemoveNthNodeFromEndofList19 {

    //快慢指针
    public ListNode removeNthNode(ListNode l,int n) {
        if (l == null) {
            return l;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = l;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
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
        l13.next = l21;
        l21.next = l22;
        l22.next = l23;
        System.out.println(l11);
        RemoveNthNodeFromEndofList19 removeNthNodeFromEndofList19 = new RemoveNthNodeFromEndofList19();
        ListNode result = removeNthNodeFromEndofList19.removeNthNode(l11,3);
        System.out.println(result);
    }
}
