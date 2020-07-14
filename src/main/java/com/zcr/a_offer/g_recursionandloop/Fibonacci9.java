package com.zcr.a_offer.g_recursionandloop;

/**
 * 9、斐波那契数列
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 */
public class Fibonacci9 {

    /**
     * 如果使用递归求解，会重复计算一些子问题。
     * 例如，计算 f(4) 需要计算 f(3) 和 f(2)，计算 f(3) 需要计算 f(2) 和 f(1)，可以看到 f(2) 被重复计算了。
     *
     *
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        if (n <= 0){
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return Fibonacci(n - 2) + Fibonacci(n - 1);
    }

    /**
     * 想办法避免重复计算，可以把已经得到的中间数列保存起来，下次需要计算的时候我们先查找一下，如果已经计算过了就不需要再重复计算。
     *
     * 更简单的方法是从下往上计算
     * 例：
     * f(0)和f(1)计算出f(2)
     * f(1)和f(2)计算出f(3)...
     * 时间复杂度：O(n)
     * 空间：O(n)
     *
     * 递归是将一个问题划分成多个子问题求解，动态规划也是如此，但是动态规划会把子问题的解缓存起来，从而避免重复求解子问题。
     *
     * @param n
     * @return
     */
    public int Fibonacci2(int n) {
        if (n < 1)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    /**
     * 考虑到第 i 项只与第 i-1 和第 i-2 项有关，
     * 因此只需要存储前两项的值就能求解第 i 项，从而将空间复杂度由 O(N) 降低为 O(1)。
     *
     * 时间：O(n)
     * 空间：O(1)
     * @param n
     * @return
     */
    public int Fibonacci3(int n) {
        if (n <= 1)
            return n;
        int pre2 = 0, pre1 = 1;
        int fib = 0;
        for (int i = 2; i <= n; i++) {
            fib = pre2 + pre1;
            pre2 = pre1;
            pre1 = fib;
        }
        return fib;
    }
}
