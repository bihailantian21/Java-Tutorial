package com.zcr.a_offer.b_stackandqueue;

/**
 * 21、包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 *
 * 【要求】1．pop、push、getMin操作的时间复杂度都是O(1)。
 * 2．设计的栈类型可以使用现成的栈结构。
 */

import java.util.Stack;

/**
 * 仅仅添加一个成员变量存储最小元素是不够的，当最小元素弹出栈时，我们希望能得到次小元素。
 * 把每次的最小元素用辅助栈保存。
 *
 * 准备两个栈：
 * 设置一个min栈，每次将最小的那个数也同样加入min栈中：
 * 当前数和min栈栈顶比较，把小的那个数压入栈顶，最后min栈的栈顶就是那个我们所需要的最小值。
 */
public class GetMinStack21 {

    Stack<Integer> stackData = new Stack<>(); // 数据栈
    Stack<Integer> stackMin = new Stack<>();  // 辅助栈

    public void push(int newNum) {
        stackData.push(newNum);//不管怎样，data栈都是把当前数压进去
        if (stackMin.isEmpty()) {
            stackMin.push(newNum);
        } else if (newNum <= this.min()) {//若当前数小，把当前数压入
            stackMin.push(newNum);
        } else {
            int newMin = stackMin.peek();
            stackMin.push(newMin);//否则重复压入栈顶的数
        }
    }

    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty.");
        }
        stackMin.pop();
        return stackData.pop();
    }

    public int min() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("Your stack is empty.");
        }
        return stackMin.peek();
    }


    public int top() {
        if (stackData.isEmpty())
            throw new RuntimeException("stack is empty!");
        return stackData.peek();
    }

}
