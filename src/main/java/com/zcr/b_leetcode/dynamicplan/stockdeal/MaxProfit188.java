package com.zcr.b_leetcode.dynamicplan.stockdeal;


/**
 * 188. 买卖股票的最佳时机 IV
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *
 * 示例 2:
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 */
public class MaxProfit188 {

    /**
     * 本文参考自英文版 LeetCode：https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
     * 很多读者抱怨股票系列问题奇技淫巧太多，如果面试真的遇到这类问题，基本不会想到那些巧妙的办法，怎么办？所以本文拒绝奇技淫巧，而是稳扎稳打，只用一种通用方法解决所用问题，以不变应万变。
     *
     * 这篇文章用状态机的技巧来解决，可以全部提交通过。不要觉得这个名词高大上，文学词汇而已，实际上就是 DP table，看一眼就明白了。
     * 先随便抽出一道题，看看别人的解法：
     * int maxProfit(vector<int>& prices) {
     *     if(prices.empty()) return 0;
     *     int s1=-prices[0],s2=INT_MIN,s3=INT_MIN,s4=INT_MIN;
     *     for(int i=1;i<prices.size();++i) {
     *         s1 = max(s1, -prices[i]);
     *         s2 = max(s2, s1+prices[i]);
     *         s3 = max(s3, s2-prices[i]);
     *         s4 = max(s4, s3+prices[i]);
     *     }
     *     return max(0,s4);
     * }
     * 能看懂吧？会做了吗？不可能的，你看不懂，这才正常。就算你勉强看懂了，下一个问题你还是做不出来。为什么别人能写出这么诡异却又高效的解法呢？
     * 因为这类问题是有框架的，但是人家不会告诉你的，因为一旦告诉你，你五分钟就学会了，该算法题就不再神秘，变得不堪一击了。
     *
     * 本文就来告诉你这个框架，然后带着你一道一道秒杀。
     * 这 6 道股票买卖问题是有共性的，我们通过对第四题（限制最大交易次数为 k）的分析一道一道解决。因为第四题是一个最泛化的形式，其他的问题都是这个形式的简化。
     * 第一题是只进行一次交易，相当于 k = 1；第二题是不限交易次数，相当于 k = +infinity（正无穷）；
     * 第三题是只进行 2 次交易，相当于 k = 2；剩下两道也是不限次数，但是加了交易「冷冻期」和「手续费」的额外条件，其实就是第二题的变种，都很容易处理。
     *
     * 一、穷举框架
     * 首先，还是一样的思路：如何穷举？这里的穷举思路和上篇文章递归的思想不太一样。
     * 递归其实是符合我们思考的逻辑的，一步步推进，遇到无法解决的就丢给递归，一不小心就做出来了，可读性还很好。缺点就是一旦出错，你也不容易找到错误出现的原因。
     * 比如上篇文章的递归解法，肯定还有计算冗余，但确实不容易找到。
     *
     * 而这里，我们不用递归思想进行穷举，而是利用「状态」进行穷举。我们具体到每一天，看看总共有几种可能的「状态」，再找出每个「状态」对应的「选择」。
     * 我们要穷举所有「状态」，穷举的目的是根据对应的「选择」更新状态。听起来抽象，你只要记住「状态」和「选择」两个词就行，下面实操一下就很容易明白了。
     *
     * for 状态1 in 状态1的所有取值：
     *     for 状态2 in 状态2的所有取值：
     *         for ...
     *             dp[状态1][状态2][...] = 择优(选择1，选择2...)
     * 比如说这个问题，每天都有三种「选择」：买入、卖出、无操作，我们用 buy, sell, rest 表示这三种选择。
     * 但问题是，并不是每天都可以任意选择这三种选择的，因为 sell 必须在 buy 之后，buy 必须在 sell 之后。
     * 那么 rest 操作还应该分两种状态，一种是 buy 之后的 rest（持有了股票），一种是 sell 之后的 rest（没有持有股票）。
     * 而且别忘了，我们还有交易次数 k 的限制，就是说你 buy 还只能在 k > 0 的前提下操作。
     *
     * 很复杂对吧，不要怕，我们现在的目的只是穷举，你有再多的状态，老夫要做的就是一把梭全部列举出来。这个问题的「状态」有三个，
     * 第一个是天数，第二个是允许交易的最大次数，第三个是当前的持有状态（即之前说的 rest 的状态，我们不妨用 1 表示持有，0 表示没有持有）。
     * 然后我们用一个三维数组就可以装下这几种状态的全部组合：
     * dp[i][k][0 or 1]
     * 0 <= i <= n-1, 1 <= k <= K
     * n 为天数，大 K 为最多交易数
     * 此问题共 n × K × 2 种状态，全部穷举就能搞定。
     *
     * for 0 <= i < n:
     *     for 1 <= k <= K:
     *         for s in {0, 1}:
     *             dp[i][k][s] = max(buy, sell, rest)
     * 而且我们可以用自然语言描述出每一个状态的含义，比如说 dp[3][2][1] 的含义就是：今天是第三天，我现在手上持有着股票，至今最多进行 2 次交易。
     * 再比如 dp[2][3][0] 的含义：今天是第二天，我现在手上没有持有股票，至今最多进行 3 次交易。很容易理解，对吧？
     *
     * 我们想求的最终答案是 dp[n - 1][K][0]，即最后一天，最多允许 K 次交易，最多获得多少利润。
     * 读者可能问为什么不是 dp[n - 1][K][1]？因为 [1] 代表手上还持有股票，[0] 表示手上的股票已经卖出去了，很显然后者得到的利润一定大于前者。
     *
     * 记住如何解释「状态」，一旦你觉得哪里不好理解，把它翻译成自然语言就容易理解了。
     *
     * 二、状态转移框架
     * 现在，我们完成了「状态」的穷举，我们开始思考每种「状态」有哪些「选择」，应该如何更新「状态」。只看「持有状态」，可以画个状态转移图。
     *             buy
     *  0rest                1rest
     *            sell
     * 通过这个图可以很清楚地看到，每种状态（0 和 1）是如何转移而来的。根据这个图，我们来写一下状态转移方程：
     *
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *               max(   选择 rest  ,           选择 sell      )
     * 解释：今天我没有持有股票，有两种可能：
     * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
     * 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
     *
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *               max(   选择 rest  ,           选择 buy         )
     * 解释：今天我持有着股票，有两种可能：
     * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
     * 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
     * 这个解释应该很清楚了，如果 buy，就要从利润中减去 prices[i]，如果 sell，就要给利润增加 prices[i]。
     * 今天的最大利润就是这两种可能选择中较大的那个。而且注意 k 的限制，我们在选择 buy 的时候，把 k 减小了 1，很好理解吧，
     * 当然你也可以在 sell 的时候减 1，一样的。
     *
     * 现在，我们已经完成了动态规划中最困难的一步：状态转移方程。如果之前的内容你都可以理解，那么你已经可以秒杀所有问题了，
     * 只要套这个框架就行了。不过还差最后一点点，就是定义 base case，即最简单的情况。
     *
     * dp[-1][k][0] = 0
     * 解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
     * dp[-1][k][1] = -infinity
     * 解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
     * dp[i][0][0] = 0
     * 解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
     * dp[i][0][1] = -infinity
     * 解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
     * 把上面的状态转移方程总结一下：
     *
     * base case：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = dp[i][0][1] = -infinity
     *
     * 状态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * 读者可能会问，这个数组索引是 -1 怎么编程表示出来呢，负无穷怎么表示呢？这都是细节问题，有很多方法实现。现在完整的框架已经完成，下面开始具体化。
     */


    /**
     * k = any integer
     *
     * 有了上一题 k = 2 的铺垫，这题应该和上一题的第一个解法没啥区别。
     * 但是出现了一个超内存的错误，原来是传入的 k 值会非常大，dp 数组太大了。现在想想，交易次数 k 最多有多大呢？
     *
     * 一次交易由买入和卖出构成，至少需要两天。所以说有效的限制 k 应该不超过 n/2，如果超过，就没有约束作用了，相当于 k = +infinity。
     * 这种情况是之前解决过的。
     *
     */
    public int maxProfit_k_any(int max_k, int[] prices) {
        int n = prices.length;
        if (max_k > n / 2)
            return maxProfit_k_inf(prices);

        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++)
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) { /* 处理 base case */ }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        return dp[n - 1][max_k][0];
    }

    public int maxProfit_k_inf(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }

}
