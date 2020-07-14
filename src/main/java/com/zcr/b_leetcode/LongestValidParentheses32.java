package com.zcr.b_leetcode;

import java.util.Stack;

public class LongestValidParentheses32 {
    public int longestValidParentheses(String s) {
        int length = s.length();
        if (s == null || length < 2) {
            return 0;
        }
        int leftmost = -1;
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) { // until here,not a valid parenthesis
                    leftmost = i; // change leftmost
                } else {
                    int j = stack.pop();
                    if (stack.isEmpty()) {
                        max = Math.max(max,i - leftmost);
                    } else {
                        max = Math.max(max,i - stack.peek());
                    }
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        String s = "(()())())()()()()()";
        LongestValidParentheses32 longestValidParentheses32 = new LongestValidParentheses32();
        int result = longestValidParentheses32.longestValidParentheses(s);
        System.out.println(result);
    }
}
