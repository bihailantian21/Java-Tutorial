package com.zcr.a_offer.b_stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用两个队列实现栈
 * 入栈：直接进入主队列中
 * 出栈：先将前几个出队列添加到帮助队列中，把主队列中剩下的最后一个元素进行出队列。最后交换主队列与帮助队列。
 *      如果只是取栈顶元素，还需要将主队列中剩下的最后的一个元素也添加到帮助队列中。
 *
 * 总结：队列先进先出，从这个队列倒腾到另一个队列顺序还是一样的
 *
 */
public  class TwoQueuesStack7_2 {//两个队列实现栈
    private Queue<Integer> queue;
    private Queue<Integer> help;

    public TwoQueuesStack7_2() {
        queue = new LinkedList<Integer>();//用双向链表实现队列的
        help = new LinkedList<Integer>();
    }

    public void push(int pushInt) {//用户让你压栈的时候，就只存到主队列中
        queue.add(pushInt);
    }

    public int pop() {//出的时候，先把前几个拿出来放到help队列里
        if (queue.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        while (queue.size() > 1) {//只要队列里的数超过1个。dada栈里只剩一个数的时候停止循环
            help.add(queue.poll());//就把data栈里的数全都弹出来，直接压到help栈里面
        }
        int res = queue.poll();//把剩的那一个数弹出来
        swap();//然后你把help栈改做data栈，把data栈改做help栈
        return res;
    }

    public int peek() {
        if (queue.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        while (queue.size() != 1) {
            help.add(queue.poll());
        }
        int res = queue.poll();
        help.add(res);//不能将剩的那个数抹掉，所以又重新压回去了
        swap();
        return res;
    }

    private void swap() {
        Queue<Integer> tmp = help;
        help = queue;
        queue = tmp;
    }

}
