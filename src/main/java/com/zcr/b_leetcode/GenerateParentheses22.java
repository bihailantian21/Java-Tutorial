package com.zcr.b_leetcode;

import java.util.LinkedList;
import java.util.List;

public class GenerateParentheses22 {

    public List<String> generateParentheses(int n){
        List<String> result = new LinkedList<>();
        helper("", result, n, 0, 0);
        return result;
    }

    public void helper(String cur, List<String> result, int n, int left, int right) {
        if (right == n) {
            result.add(cur);
            return;
        }
        if (left < n) {
            helper(cur + "(",result,n,left + 1,right);
        }
        if (right < left) {
            helper(cur + ")", result, n, left, right + 1);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses22 generateParentheses22 = new GenerateParentheses22();
        List<String> result  = generateParentheses22.generateParentheses(4);
        for (String re : result) {
            System.out.println(re);
        }
    }
}
