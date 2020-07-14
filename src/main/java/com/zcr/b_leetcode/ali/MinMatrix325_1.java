package com.zcr.b_leetcode.ali;


/**
 *
 *
 * 小强有一个3*n的矩阵，从每一列中选一个数字组成一个新的一行n列的矩阵bi(i=0,1,2,...,n-1)，
 * 计算使sum (1=0,n-2) b[i+1]-b[i]最小
 *
 *
 * 3 * n的数组，每列选出一个数字，输出他们的差值的绝对值的最小值。
 * 如：
 * nums
 *   0 1 2 3 4
 * 0 5 9 5 4 4
 * 1 4 7 4 10 3
 * 2 2 10 9 2 3
 * 最小值为5。 【5，7，5，4，4】 ，所以输出等于|7-5|+|5-7|+|4-5|+|4-4|=5。所以输出就是5。
 *
 *
 * dp[i][j]
 *   i 0 1 2 3 4
 * j
 * 0   0 4 2       a
 * 1   0 2 3    a
 * 2   0 5 1   a
 *
 * dp[i][j] = min(for  abs(nums[i][j] - nums[k][j-1])   +   dp[k][j-1]  )
 *
 *
 */
public class MinMatrix325_1 {

    /**
     * 第一题，dp，三个状态转移
     *
     * 第一题：存3*n数据到二维数组nums，做一个3*n的辅助数组help，
     * help[i][j]代表从第一列到第j列以nums[i][j]为终点的计算最小值。
     * 初始状态：第一列help全部为0；整列整列的规划。
     * help[i][j]=min{
     * (help[0][j-1] + abs(nums[i][j]-nums[0][j-1])),
     * (help[1][j-1] + abs(nums[i][j]-nums[1][j-1])),
     * (help[2][j-1] + abs(nums[i][j]-nums[2][j-1]));
     *
     * 最后取最后一列的最小值输出
     *
     * 一到考试有点慌，知道要用动态规划，然后就想怎么用。这个问题可以看成是在矩阵中寻找一个路径，
     * 要求整个路径的前向之差绝对值最小。可以想如何把问题规模缩小，显然下一个数的选择，
     * 可以有三条路径，如果从第一行过来，那么就需要用到选了第一行的路径的和的最小值，
     * 同样也需要求出用到第二行和第三行过来的路径的最小值。
     *  
     * 可见这是一个动态规划问题。我们定义一个动态规划数组，dp[i][j]
     * 表示选择了矩阵中(i,j)位置的元素，最小的路径绝对值之和。
     *
     * 显然最终的结果就是最后一列三个路径最小值。
     * dp[i][j]=   min(    abs(nums[i][j]−nums[i−1][k])   +     dp[i−1][k]    )    k∈0,1,2
     *
     * 好了，有公式可以写代码了。我直接给出我的AC代码，尴尬的就是直接想把时间复杂度和空间复杂度写到最小。
     * （完美主义害死人，在这里多花了几分钟的时间。）
     */

    public static int minminus(int[][] nums) {
        if (nums == null || nums.length == 0 || nums[0].length == 0) {
            return -1;
        }
        int n1 = nums.length;
        int n2 = nums[0].length;
        int[][] dp = new int[n1][n2];

        //第一列的差值都为0
        for (int i = 0; i < n1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j < n2; j++) {//注意这里要先计算列、再计算行！！！因为否则的话，前面的数还没有计算出来默认为0
         for (int i = 0; i < n1; i++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.abs(nums[i][j] - nums[k][j-1]) + dp[k][j-1]);
                }
//                dp[i][j]=Math.min(dp[0][j-1] + Math.abs(nums[i][j]-nums[0][j-1]), dp[1][j-1] + Math.abs(nums[i][j]-nums[1][j-1]));
//                dp[i][j]=Math.min(dp[i][j], dp[2][j-1] + Math.abs(nums[i][j]-nums[2][j-1]));
             /**
              * 59544
              * 474103
              * 210923
              */



                /*先计算行再计算列会出错！
                04200
                02511
                05451
                */

//                dp[i][j]=Math.min(Math.abs(nums[i][j]-nums[0][j-1]), Math.abs(nums[i][j]-nums[1][j-1]));
//                dp[i][j]=Math.min(dp[i][j],  Math.abs(nums[i][j]-nums[2][j-1]));
//                /*04200
//                02311
//                05021*/
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n1; i++) {
            result = Math.min(result, dp[i][n2-1]);
        }
        return result;
    }

    public static void main(String[] args) {
        /*Scanner input = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < ; j++) {

            }
        }*/


        int[][] nums = {{5, 9, 5, 4, 4},{4, 7, 4, 10, 3},{2, 10, 9, 2, 3}};
        int n1 = nums.length;
        int n2 = nums[0].length;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                System.out.print(nums[i][j]);
            }
            System.out.println();
        }
        int result = minminus(nums);
        System.out.println(result);
    }
}
