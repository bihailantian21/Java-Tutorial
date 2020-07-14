package com.zcr.a_offer.g_recursionandloop;

/**
 * 计算1+2+...+100
 */
public class RecursionandLoop {

    //标准递归
    public int recursion(int n) {
        return n <= 0 ? 0 : n + recursion(n - 1);
    }

    //动态规划
    public int loop(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }
}
