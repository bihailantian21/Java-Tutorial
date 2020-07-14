package com.zcr.b_leetcode.dynamicplan.matrixpath;

/**
 * 64. Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */

/**
 * 64、最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class MinimumPathSum64 {

    /**
     * 使左上到右下路径上的数字和最小
     * 动态规划
     * 状态：[m][m]    [i][j]从左上到某一点这一路径上最小的值
     * 初始化：第一行：加过来   第一列：加过来
     * 转义方程：Min(up,left)+[i][j] 左边和上面哪个最小选择哪个
     * 结果：右下角的值
     *
     * [
     *     [1,3,1],
     *     [1,5,1],
     *     [4,2,1]
     * ]
     *    0 1 2
     * 0  1 4 5
     * 1  2
     * 2  6
     *
     *
     * [
     *      [1,2,5],
     *      [3,2,1]
     * ]
     *
     *    0 1 2
     * 0  1
     * 2
     *
     * @param grid
     * @return
     */
    public int minimumPathSum(int[][] grid) {
        int m = grid.length;//m行
        int n = grid[0].length;//n列
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {//第一行
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {//第一列
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < m; i++) {//行
            for (int j = 1; j < n; j++) {//列
                dp[i][j] = Math.min(dp[i - 1][j],dp[i][j - 1]) + grid[i][j];
                                   //上面         //左边
            }
        }
        return dp[m - 1][n - 1];
    }
}
