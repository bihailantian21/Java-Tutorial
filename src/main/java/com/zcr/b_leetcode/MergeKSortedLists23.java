package com.zcr.b_leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists23 {

    //在ListNode类外部实现比较器，比较逻辑在compare方法中，不用修改原有的类
    class MyListNodeComparater implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.value - o2.value;
        }
    }

    /**
     * 1 因为有k个list，每个list平均长度是n，由于在merge的时候，每个值都会取一下，所以一共要取nk次。
     * 2 每次更新PriorityQueue需要logk，所以总的时间复杂度是O(nklogk); 空间复杂度为优先队列所占空间，为O(k)
     * 3 使用priorityQueue实现堆，初始时，将每个链表的第一个节点加入堆中，之后每次弹出堆顶元素，
     * 将这个节点指向的下一节点加入到堆中，直到所有节点添加完毕
     * @param lists
     * @return
     */
    public ListNode mergeKSortedList(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        if (lists == null || lists.length == 0) {
            return dummy.next;
        }
        int size = lists.length;
        ListNode cur = dummy;
        MyListNodeComparater myListNodeComparater = new MyListNodeComparater();
        Queue<ListNode> queue = new PriorityQueue<>(myListNodeComparater);
        for (int i = 0; i < size; i++) {
            if (lists[i] != null) {
                queue.add(lists[i]);
            }
        }
        while (queue.size() != 0) {
            ListNode node = queue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                queue.add(node.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l13 = new ListNode(5);
        l11.next = l12;
        l12.next = l13;
        ListNode l21 = new ListNode(2);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(6);
        l21.next = l22;
        l22.next = l23;
        ListNode l31 = new ListNode(4);
        ListNode l32 = new ListNode(5);
        ListNode l33 = new ListNode(7);
        l31.next = l32;
        l32.next = l33;
        MergeKSortedLists23 mergeKSortedLists23 = new MergeKSortedLists23();
        ListNode[] lists = {l11,l21,l31};
        ListNode result = mergeKSortedLists23.mergeKSortedList(lists);
        System.out.println(result);

    }
}
