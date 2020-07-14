package com.zcr.b_leetcode.ali;


/**
 *
 * 输入n,m两个整数代表n行m列
 * 下面输入n行字符串，每个字符串都包含m个字符（只含有'.','#','E','S'）
 * 其中S代表起点，E代表终点，#代表无法通过
 * 从起点出发，可向左，向右，向上，向下移动一步
 * 也可按如下中心对称移动，也只算移动一步
 * X（i,j）→ X‘（n-1-i,m-1-j）     这叫做移动到中心的对称的位置
 * 求从起点到终点最少需要移动几步
 *
 * 示例输入
 * 1
 * 2
 * 3
 * 4
 * 5
 * 输入
 * 4 4
 * #S..
 * E#..
 * #...
 * ....
 * 输出：4
 * 解释:S(0,1)先瞬移到(3, 2)，然后往上一步，往右一步，瞬移到E，一共4步
 *
 * (0,1)  (4-1-0=3,4-1-1=2)
 *
 * 先中心对称到达（3，3），然后向上一步，向右一步，中心对称到达终点
 *
 *
 *
 *
 * 给你一个迷宫，包括一个起点‘S’和一个终点‘E’，‘#’表示障碍，不可到达的位置，‘.'表示可以到达的位置，
 * 另外你可以跳跃，跳跃的规则是从一个点跳到他中心对称的那个点上，最多跳跃5次，求从起点到达终点的最短路径长度。
 */
public class MinimumPathWithEnergy323_2 {


    /**
     * 动态规划---求最小路径数量
     * @param grid
     * @return
     */
    public int minimumPathSum(char[][] grid) {
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


    /**
     * 解法: 搜索题，定义状态 vis[x][y][z] 代表小明到达 (x,y)位置共使用了z次瞬移，对五种状态进行转移即可。
     * @param grid
     * @return
     */
    public int minimumPathSumWithEnergy(char[][] grid) {
        return -1;
    }



        public static void main(String[] args){
        char[][] grid = {   {'#', 'S', '.', '.','.'},
                            {'E', '#', '#', '.','.'},
                            {'#', '#', '#', '.','.'},
                            {'#', '#', '#', '.','.'}   };
        //int result = shortestPath(grid,5);
        //System.out.println(result);
    }
}
