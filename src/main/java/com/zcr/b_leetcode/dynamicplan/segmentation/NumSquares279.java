package com.zcr.b_leetcode.dynamicplan.segmentation;

import java.util.ArrayList;
import java.util.List;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
public class NumSquares279 {

    /**
     * ?
     * dp[i]表示i最少可以由几个平方数构成。
     *
     * 初试化dp=[0,1,2,..,n]，长度为n+1，最多次数就是全由1构成。
     *
     * 遍历dp，对于i，遍历区间[2,n+1)：
     *
     * 遍历所有平方数小于i的数j，遍历区间[1,intsqrt{i}+1]：
     * dp[i]=min(dp[i],dp[i-j*j]+1)。始终保存所有可能情况中的最小值。
     * 返回dp[n]
     * @param n
     * @return
     */
    public int numSquares(int n) {
        List<Integer> squareList = generateSquareList(n);
        int[] dp = new int[n + 1];
        //Arrays.fill(dp,Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int square : squareList) {//找i之前所有的完全平方数，
                if (square > i) {
                    break;
                }
                min = Math.min(min, dp[i - square] + 1);//找到了之后，加上squre本次
            }
            dp[i] = min;
        }
        return dp[n];


        //找到一个很简便的写法
       /* int[] dp = new int[n + 1]; // 默认初始化值都为0
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏的情况就是每次+1
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
            }
        }
        return dp[n];*/
    }

    private List<Integer> generateSquareList(int n) {
        List<Integer> squareList = new ArrayList<>();
        int diff = 3;
        int square = 1;
        while (square <= n) {
            squareList.add(square);     //1,4,9,16   找规律得到完全平方数组    diff+2   间隔3 5 7
            square += diff;
            diff += 2;
        }
        return squareList;
    }
}
