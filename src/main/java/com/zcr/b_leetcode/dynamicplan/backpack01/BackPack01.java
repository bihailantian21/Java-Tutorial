package com.zcr.b_leetcode.dynamicplan.backpack01;


/**
 *
 */
public class BackPack01 {

    /**
     * 递归方法
     * 首先我们用递归的方式来尝试解决这个问题
     * 我们用F(n,C)表示将前n个物品放进容量为C的背包里，得到的最大的价值。
     * 我们用自顶向下的角度来看，假如我们已经进行到了最后一步（即求解将nnn个物品放到背包里获得的最大价值），此时我们便有两种选择
     *
     * 不放第n个物品，此时总价值为F(n−1,C)
     * 放置第n个物品，此时总价值为vn+F(n−1,C−wn)
     * 两种选择中总价值最大的方案就是我们的最终方案，递推式（有时也称之为状态转移方程）
     * 如下：
     * F(i,C)=max(F(i−1,C),v(i)+F(i−1,C−w(i)))
     *
     * 解决背包问题的递归函数
     *
     * @param w        物品的重量数组
     * @param v        物品的价值数组
     * @param index    当前待选择的物品索引
     * @param capacity 当前背包有效容量
     * @return 最大价值
     */
    private static int solveKS(int[] w, int[] v, int index, int capacity) {
        //基准条件：如果索引无效或者容量不足，直接返回当前价值0
        if (index < 0 || capacity <= 0)
            return 0;

        //不放第index个物品所得价值
        int res = solveKS(w, v, index - 1, capacity);
        //放第index个物品所得价值（前提是：第index个物品可以放得下）
        if (w[index] <= capacity) {
            res = Math.max(res, v[index] + solveKS(w, v, index - 1, capacity - w[index]));
        }
        return res;
    }

    public static int knapSack(int[] w, int[] v, int C) {
        int size = w.length;
        return solveKS(w, v, size - 1, C);
    }

    public static void main(String[] args){
        int[] w = {2,1,3,2};
        int[] v = {12,10,20,15};
        System.out.println(knapSack(w,v,5));
    }


    /**
     * 记忆化搜索
     * 我们用递归方法可以很简单的实现以上代码，但是有个严重的问题就是，直接采用自顶向下的递归算法会导致要不止一次的解决公共子问题，因此效率是相当低下的。
     * 我们可以将已经求得的子问题的结果保存下来，这样对子问题只会求解一次，这便是记忆化搜索。
     * 下面在上述代码的基础上加上记忆化搜索
     */
    private static int[][] memo;

    /**
     * 解决背包问题的递归函数
     *
     * @param w        物品的重量数组
     * @param v        物品的价值数组
     * @param index    当前待选择的物品索引
     * @param capacity 当前背包有效容量
     * @return 最大价值
     */
    private static int solveKS2(int[] w, int[] v, int index, int capacity) {
        //基准条件：如果索引无效或者容量不足，直接返回当前价值0
        if (index < 0 || capacity <= 0)
            return 0;

        //如果此子问题已经求解过，则直接返回上次求解的结果
        if (memo[index][capacity] != 0) {
            return memo[index][capacity];
        }

        //不放第index个物品所得价值
        int res = solveKS(w, v, index - 1, capacity);
        //放第index个物品所得价值（前提是：第index个物品可以放得下）
        if (w[index] <= capacity) {
            res = Math.max(res, v[index] + solveKS(w, v, index - 1, capacity - w[index]));
        }
        //添加子问题的解，便于下次直接使用
        memo[index][capacity] = res;
        return res;
    }

    public static int knapSack2(int[] w, int[] v, int C) {
        int size = w.length;
        memo = new int[size][C + 1];
        return solveKS(w, v, size - 1, C);
    }


    /**
     * 有一个容量为 N 的背包，要用这个背包装下物品的价值最大，这些物品有两个属性：体积 w 和价值 v。
     *
     * 定义一个二维数组 dp 存储最大价值，其中 dp[i][j] 表示前 i 件物品体积不超过 j 的情况下能达到的最大价值。
     *
     * 设第 i 件物品体积为 w，价值为 v，根据第 i 件物品是否添加到背包中，可以分两种情况讨论：
     * 第 i 件物品没添加到背包，总体积不超过 j 的前 i 件物品的最大价值就是总体积不超过 j 的前 i-1 件物品的最大价值，dp[i][j] = dp[i-1][j]。
     * 第 i 件物品添加到背包中，dp[i][j] = dp[i-1][j-w] + v。
     * 第 i 件物品可添加也可以不添加，取决于哪种情况下最大价值更大。因此，0-1 背包的状态转移方程为：
     * dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
     * @param W
     * @param N
     * @param weights
     * @param values
     * @return
     */
    // W 为背包总体积
    // N 为物品数量
    // weights 数组存储 N 个物品的重量
    // values 数组存储 N 个物品的价值
    public int knapsack(int W, int N, int[] weights, int[] values) {
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {//物品数量
            int w = weights[i - 1], v = values[i - 1];
            for (int j = 1; j <= W; j++) {//背包重量
                if (j >= w) {//如果背包重量大于当前这个物品的数量
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][W];
    }


    /**
     * 空间优化
     * 在程序实现时可以对 0-1 背包做优化。观察状态转移方程可以知道，前 i 件物品的状态仅与前 i-1 件物品的状态有关，
     * 因此可以将 dp 定义为一维数组，其中 dp[j] 既可以表示 dp[i-1][j] 也可以表示 dp[i][j]。此时，
     * dp[j] = Math.max(dp[j], dp[j - w] + v);
     * 因为 dp[j-w] 表示 dp[i-1][j-w]，因此不能先求 dp[i][j-w]，防止将 dp[i-1][j-w] 覆盖。
     * 也就是说要先计算 dp[i][j] 再计算 dp[i][j-w]，在程序实现时需要按倒序来循环求解。
     *
     * 我们仍然假设背包空间为5，根据
     * F(i,C)=max(F(i−1,C),v(i)+F(i−1,C−w(i)))
     * 我们可以知道，当我们利用一维数组进行记忆化的时候，我们只需要使用到当前位置的值和该位置之前的值，举个例子
     * 假设我们要计算F(i,4),我们需要用到的值为F(i−1,4)和F(i−1,4−w(i)),因此为了防止结果被覆盖，
     * 我们需要从后向前依次计算结果
     *
     * @param W
     * @param N
     * @param weights
     * @param values
     * @return
     */
    public int knapsack2(int W, int N, int[] weights, int[] values) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= N; i++) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = W; j >= 1; j--) {
                if (j >= w) {
                    dp[j] = Math.max(dp[j], dp[j - w] + v);
                }
            }
        }
        return dp[W];
    }

    /**
     * 无法使用贪心算法的解释
     * 0-1 背包问题无法使用贪心算法来求解，也就是说不能按照先添加性价比最高的物品来达到最优，这是因为这种方式可能造成背包空间的浪费，
     * 从而无法达到最优。
     *
     * 考虑下面的物品和一个容量为 5 的背包，如果先添加物品 0 再添加物品 1，那么只能存放的价值为 16，浪费了大小为 2 的空间。
     * 最优的方式是存放物品 1 和物品 2，价值为 22.
     * id	w	v	v/w
     * 0	1	6	6
     * 1	2	10	5
     * 2	3	12	4
     *
     * 变种
     * 完全背包：物品数量为无限个
     * 多重背包：物品数量有限制
     * 多维费用背包：物品不仅有重量，还有体积，同时考虑这两种限制
     * 其它：物品之间相互约束或者依赖
     */
}
