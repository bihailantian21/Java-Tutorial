package com.zcr.a_offer.a_linkedlist;

import com.zcr.b_leetcode.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 5、从尾到头打印链表
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
public class PrintListFromTailToHead5 {

    /**
     * 方法一：使用栈
     * 栈具有后进先出的特点，在遍历链表时将值按顺序放入栈中，最后出栈的顺序即为逆序。
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<ListNode> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop().value);
        }
        return result;
    }

    /**
     * 方法二：使用递归
     * 我们每访问到一个节点的时候，先递归输出它后面的节点，再输出该节点自身。
     * 要逆序打印链表 1->2->3（3,2,1)，可以先逆序打印链表 2->3(3,2)，最后再打印第一个节点 1。而链表 2->3 可以看成一个新的链表，
     * 要逆序打印该链表可以继续使用求解函数，也就是在求解函数中调用自己，这就是递归函数。
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<>();
        ListNode cur = listNode;
        helper(listNode,result,cur);
        return result;
    }

    public void helper(ListNode listNode,ArrayList<Integer> result,ListNode cur){
        if (cur == null) {
            return;
        }
        helper(listNode,result,cur.next);
        result.add(cur.value);
    }
}
