package com.zcr.b_leetcode.dynamicplan.stockdeal;


/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 *
 * 该题为马尔可夫过程，分为A观望，B持股，C冷却三个状态
 * 状态转移图：A-(观望)->A, A-(买入｜-price)->B, B-(观望)->B, B-(卖出|+price)->C, C-(冷却)->A 可用维特比算法求解
 */
public class MaxProfit309 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        A[0] = 0;
        B[0] = C[0] = -prices[0];
        for (int i = 1; i < N; i++) {
            A[i] = Math.max(A[i - 1], C[i - 1]);
            B[i] = Math.max(B[i - 1], A[i - 1] - prices[i]);
            C[i] = B[i - 1] + prices[i];
        }
        return Math.max(A[N - 1], C[N - 1]);
    }
    /**
     * 每次 sell 之后要等一天才能继续交易。只要把这个特点融入上一题的状态转移方程即可：
     *
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
     * 解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
     */
    int maxProfit_with_cool(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; // 代表 dp[i-2][0]
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }

}
