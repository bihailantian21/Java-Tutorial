package com.zcr.c_datastructure.a_linkedlist;

import java.util.Stack;

/**
 * @author zcr
 * @date 2019/7/5-17:17
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
