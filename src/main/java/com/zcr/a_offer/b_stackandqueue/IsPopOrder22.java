package com.zcr.a_offer.b_stackandqueue;

import java.util.Stack;

/**
 * 22、栈的压入弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class IsPopOrder22 {

    /**
     * 建立一个辅助栈，把输入的第一个序列中的数字依次压入该辅助栈，并按照第二个序列的顺序依次从该栈中弹出数字。
     * 例1：
     * 1,2,3,4,5,对于4,5,3,2,1
     * 第一个希望被弹出的数字是4，因此4需要先压入辅助栈里面。压入栈的顺序由压栈顺序已经确定好了，要想把4压入，先得压入1，2，3。4位于栈顶，弹出4。
     * 第二个希望被弹出的数字是5，因此5需要先压入辅助栈里面，把5压入栈。5位于栈顶，弹出5。
     * 第三个希望被弹出的数字是3。3位于栈顶，弹出3。
     * 第四个希望被弹出的数字是2。2位于栈顶，弹出2。
     * 第五个希望被弹出的数字是1。1位于栈顶，弹出1。
     *
     * 例2：
     * 1,2,3,4,5,对于4,3,5,1,2
     * 第一个希望被弹出的数字是4，因此4需要先压入辅助栈里面。压入栈的顺序由压栈顺序已经确定好了，要想把4压入，先得压入1，2，3。4位于栈顶，弹出4。
     * 第二个希望被弹出的数字是3。3位于栈顶，弹出3。
     * 第三个希望被弹出的数字是5，因此5需要先压入辅助栈里面，把5压入栈。5位于栈顶，弹出5。
     * 第四个希望被弹出的数字是1。因此1需要先压入辅助栈里面，但是此时压栈序列中所有的数字已经都压入栈了，结束。
     *
     * 总结：
     * 1、如果下一个弹出的数字刚好是栈顶数字，那么直接弹出。
     * 2、如果下一个弹出的数字不在栈顶，我们把压栈序列中还没有入栈的数字压入辅助栈中，直到把下一个弹出的数字压入栈顶为止。
     * 如果所有的数字都压入栈了，还是没有找到下一个弹出的数字，那么该序列不可能是一个弹出序列。
     *
     * 举例：
     * 入栈1,2,3,4,
     * 出栈4,5,3,2,1
     * 首先1入辅助栈，此时栈顶1≠4，继续入栈2
     * 此时栈顶2≠4，继续入栈3
     * 此时栈顶3≠4，继续入栈4
     * 此时栈顶4＝4，出栈4，弹出序列向后一位，此时为5，,辅助栈里面是1,2,3
     * 此时栈顶3≠5，继续入栈5
     * 此时栈顶5=5，出栈5,弹出序列向后一位，此时为3，,辅助栈里面是1,2,3
     *
     * 我本来的思路：
     * 1、对于popA循环
     * 如果栈顶元素等于弹出元素，将栈顶元素弹出，继续popA循环循环
     * 2、如果栈顶元素不等于弹出元素，那么将pushA按照顺序压入栈，直到找到弹出元素。
     * 我这个思路不灵活！
     *
     * 正确的思路：
     * 1、对于pushA循环
     * 每次如果栈顶元素不等于弹出元素，继续pushA循环（将pushA中元素按顺序加入辅助栈）
     * 2、如果栈顶元素等于弹出元素，那么就将栈顶元素弹出、弹出元素后移（有可能连续好几个栈顶元素都等于弹出元素，所以用while）
     *
     *
     * 还是使用一个栈，首先不管，遍历pushA[i] 的时候先将pushA[i]入栈；
     * 然后判断栈stack中元素的栈顶(stack.peek())和pop[popIndex]是否相等，如果一直相等就一直弹栈(while)，且popIndex++；
     * 最后看栈是否为空即可；
     *
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA == null ||pushA.length == 0 || popA == null || popA.length == 0) {
            return false;
        }
        boolean res = false;
        Stack<Integer> help = new Stack<>();
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {//对于pushA循环,每次如果栈顶元素不等于弹出元素，继续pushA循环（将pushA中元素按顺序加入辅助栈）
            help.push(pushA[i]);
            while (!help.isEmpty() && help.peek() == popA[popIndex]) {//如果栈顶元素等于弹出元素，那么就将栈顶元素弹出、弹出元素后移（有可能连续好几个栈顶元素都等于弹出元素，所以用while）
                help.pop();
                popIndex++;
            }
        }
        //java.util.EmptyStackException
       /* for (int i = 0; i < popA.length; i++) {
            if (popA[i] != help.peek()) {
                while (pushA[index] != popA[i]) {
                    help.push(pushA[index]);
                    index++;
                }
                if (index > pushA.length - 1) {
                    return false;
                }
                help.push(pushA[index]);
                help.pop();
            } else {
                help.pop();
            }
        }*/
        return help.isEmpty();
    }


    /**
     * 遍历pushA，使用一个索引popIndex下标记录popA走到的位置，如果pushA[i] = popA[popIndex]，就popIndex++(不处理)；
     * 否则(不相等)，就入栈pushA[i]；
     * 最后全部弹栈，每弹一个，就看stack.pop() == popA[popIndex]，如果不等，就返回false，否则返回true；
     *
     *      例1：
     *      * 1,2,3,4,5,对于4,5,3,2,1
     *      1 2 3
     *
     *      例2：
     *      * 1,2,3,4,5,对于4,3,5,1,2
     *      1 2 3
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder2(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<>();
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            if (pushA[i] == popA[popIndex])
                popIndex++;
            else
                stack.push(pushA[i]);
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != popA[popIndex++])
                return false;
        }
        return true;
    }
}
