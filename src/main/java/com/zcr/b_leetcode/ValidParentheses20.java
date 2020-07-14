package com.zcr.b_leetcode;

import java.util.Stack;

public class ValidParentheses20 {

    public static boolean isValid(String s) {
        Stack<Character> mark = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                mark.push(s.charAt(i));
            } else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
                //如果栈为空，说明前面没有与之相匹配的正括号了
                if (mark.isEmpty()) return false;
                char cur =  mark.pop();
                if (cur == '(' && s.charAt(i) != ')') return false;
                if (cur == '(' && s.charAt(i) != ')') return false;
                if (cur == '(' && s.charAt(i) != ')') return false;
            }
        }
        if (mark.isEmpty()) return true;
        return false;
    }
    public static void main(String[] args) {
        Boolean result = isValid("{(())]}");
        System.out.println(result);
    }
}
