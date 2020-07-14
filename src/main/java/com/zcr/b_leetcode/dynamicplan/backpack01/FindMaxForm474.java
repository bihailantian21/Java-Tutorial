package com.zcr.b_leetcode.dynamicplan.backpack01;

/**
 *  474. 一和零
 * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 *
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 *
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 *
 * 注意:
 * 给定 0 和 1 的数量都不会超过 100。
 * 给定字符串数组的长度不会超过 600。
 *
 * 示例 1:
 * 输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * 输出: 4
 * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 *
 * 示例 2:
 * 输入: Array = {"10", "0", "1"}, m = 1, n = 1
 * 输出: 2
 * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 */
public class FindMaxForm474 {


    /**
     * 思路：把总共的 0 个 1 的个数视为背包的容量，每一个字符串视为装进背包的物品。这道题就可以使用 0-1 背包问题的思路完成。这里的目标值是能放进背包的字符串的数量。
     * 思路依然是“一个一个尝试，容量一点一点尝试”，每个物品分类讨论：选与不选。
     * 第 1 步：定义状态
     * dp[i][j][k] 表示子区间 [0, i] 能够使用 j 个 0 和 k 个 1 的字符串的最大数量。
     * 第 2 步：状态转移方程
     * dp[i][j][k]={
     * 1.dp[i−1][j][k],
     * 2.dp[i−1][j−当前字符串使用0的个数][k−当前字符串使用1的个数]
     * 1.不选择当前考虑的字符串，至少是这个数值
     * 2.选择当前考虑的字符串
     *
     * 第 3 步：初始化
     * 为了避免分类讨论，通常多设置一行。这里可以认为，第 0 个字符串是空串。第 1 行默认初始化为 0。
     * 第 4 步：输出
     * 输出是最后一个状态，即：dp[len][m][n]。
     */
    private int[] countZeroAndOne(String str) {
        int[] cnt = new int[2];
        for (char c : str.toCharArray()) {
            cnt[c - '0']++;
        }
        return cnt;
    }

    public int findMaxForm1(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];

        for (int i = 1; i <= len; i++) {
            // 注意：有一位偏移
            int[] cnt = countZeroAndOne(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // 先把上一行抄下来
                    dp[i][j][k] = dp[i - 1][j][k];

                    int zeros = cnt[0];
                    int ones = cnt[1];

                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    /**
     * 第 5 步：思考状态压缩
     * 因为当前行只参考了上一行的值，因此可以使用滚动数组，也可以从后向前赋值。
     * 这是一个多维费用的 0-1 背包问题，有两个背包大小，0 的数量和 1 的数量。
     *
     * 这是一道裸的“二维费用的01背包问题”
     * 背包模型
     * 物品：数组元素
     * 体积：数组元素的长度
     * 价值：二维费用，消耗的0的个数，1的个数
     * 然后就是套01背包问题的模板
     *
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {    // 每个字符串只能用一次
            int ones = 0, zeros = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

}
