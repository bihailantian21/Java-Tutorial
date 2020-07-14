package com.zcr.b_leetcode.dynamicplan.backpack01;


/**
 * 518. 零钱兑换 II
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 *
 *
 * 示例 1:
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * 示例 2:
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 *
 * 示例 3:
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 *
 * 注意:
 *
 * 你可以假设：
 *
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 */
public class CoinChange518 {

    public int change(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {//排列问题，不需要考虑元素顺序
            for (int i = coin; i <= amount; i++) {//完全背包问题
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }


    /**
     * 这道题，第一眼看过去和「力扣」 第 377 题：“组合总和 Ⅳ” 很像，不过仔细分析示例就可以看出区别：
     *
     * 「力扣」 第 377 题：一个组合的不同排列是一个新的组合。(1, 1, 2)、(1, 2, 2)、(2, 1, 2) 视为为不同的组合。
     *
     * 「力扣」 第 518 题：一个组合的不同排列在结果集中只出现一次，这一点是「背包问题」的特征，拿东西的顺序并不重要。
     * (2, 2, 1) 是一个组合，(1, 2, 2) 和 (2, 1, 2) 不是新的组合。这其实是「力扣」第 39 题：“组合总和”，
     * 于是可以尝试使用回溯搜索的写法计算出所有的组合数，参考代码在附录。
     *
     * 问的都是解法有多少种，而不是问具体的解。因此可以考虑使用“动态规划”。
     */

    /**
     * 方法：动态规划
     * * 这道题是典型的“完全背包问题”。“完全背包问题”的特点是：背包里的物品可以无限次选取。 
     * * 本题特殊的地方在于：从背包里选取的物品必须刚刚好装满需要考虑的容量，而不是小于等于就好，注意这点细微的区别。 
     * * 完全背包问题是基于 0-1 背包问题的扩展。它们的不同之处在于： 
     * 0-1 背包问题：当前考虑的物品用或者不用；
     * 完全背包问题：当前考虑的物品用或者不用，如果用，用几个。
     * * 思路依然是：一个一个物品考虑，容量一点一点扩大，整个过程是一个尝试和比较的过程。
     * 第 1 步：定义状态
     * dp[i][j]：硬币列表的前缀子区间 [0, i] 能够凑成总金额 j 的组合数。
     * 说明：背包问题有一个特点，顺序无关，在最开始，我们强调过这道问题的这个性质，因此可以一个一个硬币去看。
     * 第 2 步：思考状态转移方程
     * 对于遍历到的每一种面值的硬币，逐个考虑添加到 “总金额” 中。由于硬币的个数可以无限选取，因此对于一种新的面值的硬币 
     * coins[i - 1]（注意这里有一个位移偏差），依次考虑选取 0 枚、1 枚、2 枚，以此类推，
     * 直到选取这种面值的硬币的总金额超过需要的总金额 j，dp[i][j] 是它们的值的和。
     * 状态转移方程是：
     * * Java
     * dp[i][j] = dp[i - 1][j - 0 * coins[i]] +
     *            dp[i - 1][j - 1 * coins[i]] +
     *            dp[i - 1][j - 2 * coins[i]] +
     *            ... +
     *            dp[i - 1][j - k * coins[i]]
     * 这里状态转移要成立，需要满足：j - k * coins[i] >= 0。dp[i][j] 相对于 dp[i - 1][j] 而言，
     * 多考虑的一枚硬币，是“正在考虑的那枚硬币的面值”，coins[i]，而这枚硬币选取的个数（从 0 开始）就是 dp[i][j] 这个问题可以分解的各个子问题的分类标准。
     * 事实上，这个状态转移方程有优化的空间，因为做了很多重复的工作，读者可以试着自己优化一下状态转移方程。
     * 第 3 步：思考初始化
     * dp[0][0] 的值应该设置为 1，它虽然没有意义，但是是一个被参考的值，原因是：当 dp[i - 1][j - k * coins[i]] 的第 2 个坐标 j - k * coins[i] == 0 成立的时候，k 个硬币 coin[i] 就恰好成为了一种组合，因此，dp[0][0] = 1。
     * 填写第 1 行，也是初始化的时候需要考虑的内容，第 1 行即考虑第 1 个数，能够组合出的容量就只有 coins[0] 的整数倍数。
     * 事实上，可以考虑多设置一行，把第 1 行除了 dp[0][0] 全部设置为 0，这样可以避免这种复杂的初始化讨论，这一步留给读者完成。
     * 第 4 步：思考输出
     * 输出就是表格的最后一格的数值，即 dp[len - 1][amount]。
     * 第 5 步：考虑状态压缩
     * 当前状态行的值，只和上一行的状态值相关，因此可以考虑状态压缩，使用滚动数组技巧即可，这里暂不展示代码。
     */
        public int change2(int amount, int[] coins) {
            int len = coins.length;
            if (len == 0) {
                if (amount == 0) {
                    return 1;
                }
                return 0;
            }

            int[][] dp = new int[len][amount + 1];
            // 这个值语义不正确，但是是一个被其它状态参考的值，这样设置是正确的
            dp[0][0] = 1;

            // 填第 1 行
            for (int i = coins[0]; i <= amount; i += coins[0]) {
                dp[0][i] = 1;
            }

            for (int i = 1; i < len; i++) {
                for (int j = 0; j <= amount; j++) {
                    for (int k = 0; j - k * coins[i] >= 0; k++) {
                        dp[i][j] += dp[i - 1][j - k * coins[i]];
                    }
                }
            }
            return dp[len - 1][amount];
        }

    /**
     * 方法二：优化状态转移方程
     * 根据状态转移方程其实可以得到递推公式。状态转移方程的表达形式“看起来”像是一个“无穷级数”，可以通过如下方式得到一个 “递推公式”
     * * Java
     *
     * dp[i][j] = dp[i - 1][j - 0 * coins[i - 1]] +
     *            dp[i - 1][j - 1 * coins[i - 1]] +
     *            dp[i - 1][j - 2 * coins[i - 1]] +
     *            ... +
     *            dp[i - 1][j - k * coins[i - 1]]
     * 这里 j - k * coins[i] >= 0。我们将这个等式记为“等式（1）。
     * 将 j 用 coins[i] 替换，得：
     * * Java
     * dp[i][j - coins[i]] = dp[i - 1][j - coins[i] - 0 * coins[i]] +
     *                       dp[i - 1][j - coins[i] - 1 * coins[i]] +
     *                       dp[i - 1][j - coins[i] - 2 * coins[i]] +
     *                       ... +
     *                       dp[i - 1][j - coins[i] - k * coins[i]]
     * 这里 j - coins[i] - k * coins[i] >= 0。
     * 整理一下：
     * * Java
     *
     * dp[i][j - coins[i]] = dp[i - 1][j - 1 * coins[i]] +
     *                       dp[i - 1][j - 2 * coins[i]] +
     *                       dp[i - 1][j - 3 * coins[i]] +
     *                       ... +
     *                       dp[i - 1][j - k * coins[i]]
     * 这里 j - k * coins[i] >= 0。我们将这个等式记为“等式（2）”。
     * 将 等式（1）- 等式（2），得：
     * * Java
     *
     * dp[i][j] - dp[i][j - coins[i]] = dp[i - 1][j]
     * 整理得：
     * * Java
     *
     * dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]]
     * 所以其实每一行单元的值的填写我们只要看它的左边就好了，如果没有左边，它至少是上一行单元格的值。
     * 请读者比较这里状态压缩和 0-1 背包问题的不同之处。
     */
        public int change3(int amount, int[] coins) {
            int len = coins.length;
            if (len == 0) {
                if (amount == 0) {
                    return 1;
                }
                return 0;
            }

            int[][] dp = new int[len][amount + 1];
            dp[0][0] = 1;

            for (int i = coins[0]; i <= amount; i += coins[0]) {
                dp[0][i] = 1;
            }

            for (int i = 1; i < len; i++) {
                for (int j = 0; j <= amount; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j - coins[i] >= 0) {
                        dp[i][j] += dp[i][j - coins[i]];
                    }
                }
            }
            return dp[len - 1][amount];
        }



        public int change4(int amount, int[] coins) {
            int len = coins.length;
            if (len == 0) {
                if (amount == 0) {
                    return 1;
                }
                return 0;
            }

            int[] dp = new int[amount + 1];
            dp[0] = 1;

            for (int i = coins[0]; i <= amount; i += coins[0]) {
                dp[i] = 1;
            }

            for (int i = 1; i < len; i++) {

                // 从 coins[i] 开始即可
                for (int j = coins[i] ; j <= amount; j++) {
                    dp[j] += dp[j - coins[i]];
                }
            }
            return dp[amount];
        }



}
