package com.zcr.a_offer.b_stackandqueue;

import java.util.Stack;

/**
 * 7、用两个栈实现队列
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class TwoStacksQueue7 {
    /**
     * 入队列：用户压得栈永远进push栈
     * 出队列：将push栈中的数倒到pop栈中（只要满足1：如果决定要倒一次要倒完、 2：如果要倒时pop栈中有东西则不行），
     * 用户拿的栈永远从pop栈中
     *
     * 总结：
     * 1、当pop栈不为空时，pop栈中的栈顶元素是最先进入栈的元素，可以弹出
     * 2、当pop栈为空时，要把push栈中的元素依次弹出放入到pop栈中。
     * （由于先进入push栈中的元素被压到了栈底，经过弹出和压入之后，就又处于pop栈的栈顶，又可以弹出了）
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        while (stack1.isEmpty() && stack2.isEmpty()) {
            return -1;
        }
        /*if (!stack2.isEmpty()) {
            return stack2.pop();
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }*/
        //因为不管怎么样都要return stack2.pop();那么把它写到最后面统一执行
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
