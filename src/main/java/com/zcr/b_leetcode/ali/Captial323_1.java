package com.zcr.b_leetcode.ali;


import java.util.Scanner;

/**
 *
 *    20    23   25   27
 *
 *
 *
 * 1、从n个人中选择任意数量的人员组成一支队伍，然后从一支队伍中选出一位队长，
 * 不同的队长算不同的组合，问这样的组合的数量对10^9+7取模 。
 *
 * 数据范围：1 <= n <= 1000000000;
 * 示例
 * 输入：n = 2
 * 输出：4
 * 解释,(1),(2)(1,2),(2,1)四种，括号第一个为队长
 *
 * 思路:
 * 首先一看数据范围，应该要O(logN)级别的方法才能AC,分析问题首先应该是个排列组合问题，得到通项公式为：
 * res = sum{i=1~n}   *   C{i=1~n}
 *
 *
 * 思路1：可以暴力算，当然不推荐，算了也是白算
 *
 * 思路2：动态规划，没写出来，而且也达不到O(logN)复杂度
 *
 * 思路3：数学知识告诉我们，res的通项公式为：
 * n*2^{n-1}
 * 要求2^n - 1，O(logN)复杂度，经典的快速幂算法。
 */
public class Captial323_1 {


    static int mod = 1000000007;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println((pow(n - 1) * n) % mod);
    }

    public static long pow(int n) {  //2^n
        if (n == 0)
            return 1;
        long half = pow(n / 2);
        if (n % 2 == 0)
            return (half * half) % mod;
        else
            return (half * half * 2) % mod;
    }


    //动态规划解法：已知n个人的取法为dp[n]。当求n+1人取法dp[n+1]时，
    // 新添加的第n+1人有可取可不取两种状态，但是不当队长，因此这时就有2*dp[n]种方法。
    // 然后令第n+1人为队长，剩下的人就有2^n种取法。可的转移方程状态dp[n+1]  = 2*dp[n] + 2^n。
    public int get_num(int n) {
        int k = 1;
        for (int i = 1; i < n; ++i) {
            //int temp = k*2 + Math.pow(2,i);
            //k = temp%(1000000007);
        }
        return k;

    }
}
