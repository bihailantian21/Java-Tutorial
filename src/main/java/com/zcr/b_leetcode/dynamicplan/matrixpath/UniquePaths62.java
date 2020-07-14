package com.zcr.b_leetcode.dynamicplan.matrixpath;

/**
 * 62. Unique Paths
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 * Above is a 7 x 3 grid. How many possible unique paths are there?
 * Note: m and n will be at most 100.
 *
 * Example 1:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 *
 * Example 2:
 * Input: m = 7, n = 3
 * Output: 28
 */

/**
 * 62、不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 *  000
 *  000
 *
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 */
public class UniquePaths62 {

    /**
     * 给了一个二维的m*n的地图，从左上角到右下角，只能向右向下移动。问：右多少条路线。
     * 动态规划
     *
     * 状态变量：二维数组  [i][j]从起点到[i][j]有几种不同的走法
     *
     *
     * 初始化：一般是先把边界的值给初始化了，其他位置都是通过边界的值进行一些运算得到的。
     * [0][0]=1    横向的都是1[i][0]=1   纵向的都是1[0][j]=1
     *
     *
     * 转移方程：对于任意一个位置，有几种不同的走法呢？两种：从上往下、从左往右。
     * 假设它上面一个位置的走法有m个，它左边一个位置的走法有n个。则能走到当前位置的走法有m+n个。[i][j]=[i-1][j]+[i][j-1]
     * 结果：[m-1][n-1]
     *
     *
     *
     * 1.确定状态：
     * 最后一步：最后的位置在[m-1][n-1]，那么前一步一定在在[m-2][n-1]或[m-1][n-2]
     * 子问题：有x种方式走到[m-2][n-1]、有y种方式走到[m-1][n-2]，所以子问题就是这两个。越老越缩小了范围
     * 状态：f[i][j]机器人有多少种方式从左上角走到[i][j]
     * 2.转移方程
     * f[i][j] = f[i-1][j] + f[i][j-1]
     * 3.初始条件和边界情况
     * 初始条件：f[0][0]=1
     * 边界情况：i=0 j=0 前一步只能有一个方向过来f[i][j]=0
     * 4.计算顺序
     * 一行一行的计算
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m,int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {//第一行
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {//第一列
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
