package com.zcr.b_leetcode.dynamicplan.backpack01;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 */
public class CoinChange322 {

    /**
     *使用递归的关键是知道递归函数是用来干什么的，从宏观的角度去理解递归。
     * 直接使用递归超出时间限制
     */
    int res = Integer.MAX_VALUE;
    public int coinChange0(int[] coins, int amount) {
        if(coins.length == 0){
            return -1;
        }

        findWay(coins,amount,0);
        // 如果没有任何一种硬币组合能组成总金额，返回 -1。
        if(res == Integer.MAX_VALUE){
            return -1;
        }
        return res;
    }

    public void findWay(int[] coins,int amount,int count){
        if(amount < 0){
            return;
        }
        if(amount == 0){
            res = Math.min(res,count);
        }

        for(int i = 0;i < coins.length;i++){
            findWay(coins,amount-coins[i],count+1);
        }
    }



    /**
     * 记忆化搜索
     * 可以看出在进行递归的时候，有很多重复的节点要进行操作，这样会浪费很多的时间。
     * 使用数组 memo[ ] 来保存节点的值
     * memo[n] 表示钱币 n 可以被换取的最少的硬币数，不能换取就为 -1
     * findWay 函数的目的是为了找到 amount 数量的零钱可以兑换的最少硬币数量，返回其值 int
     *
     * 在进行递归的时候，memo[n]被复制了，就不用继续递归了，可以直接的调用
     */
        int[] memo;
        public int coinChange1(int[] coins, int amount) {
            if(coins.length == 0){
                return -1;
            }
            memo = new int[amount];

            return findWay(coins,amount);
        }
        // memo[n] 表示钱币n可以被换取的最少的硬币数，不能换取就为-1
        // findWay函数的目的是为了找到 amount数量的零钱可以兑换的最少硬币数量，返回其值int
        public int findWay(int[] coins,int amount){
            if(amount < 0){
                return -1;
            }
            if(amount == 0){
                return 0;
            }
            // 记忆化的处理，memo[n]用赋予了值，就不用继续下面的循环
            // 直接的返回memo[n] 的最优值
            if(memo[amount-1] != 0){
                return memo[amount-1];
            }
            int min = Integer.MAX_VALUE;
            for(int i = 0;i < coins.length;i++){
                int res = findWay(coins,amount-coins[i]);
                if(res >= 0 && res < min){
                    min = res + 1; // 加1，是为了加上得到res结果的那个步骤中，兑换的一个硬币
                }
            }
            memo[amount-1] = (min == Integer.MAX_VALUE ? -1 : min);
            return memo[amount-1];
        }


    /**
     * 动态规划
     * 上面的记忆化搜索是先从 memo[amonut-1] 开始，从上到下
     * 动态规划从 memo[0]memo[0] 开始，从下到上
     */
    /**
     * 题目描述：给一些面额的硬币，要求用这些硬币来组成给定面额的钱数，并且使得硬币数量最少。硬币可以重复使用。
     *
     * 物品：硬币
     * 物品大小：面额
     * 物品价值：数量
     * 因为硬币可以重复使用，因此这是一个完全背包问题。完全背包只需要将 0-1 背包的逆序遍历 dp 数组改为正序遍历即可。
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) { //将逆序遍历改为正序遍历
                if (i == coin) {
                    dp[i] = 1;
                } else if (dp[i] == 0 && dp[i - coin] != 0) {
                    dp[i] = dp[i - coin] + 1;
                } else if (dp[i - coin] != 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }


    /**
     * 九章算法
     * @param A
     * @param M
     * @return
     */
    public int coinChange9(int[] A,int M) {
        int[] f = new int[M + 1];
        int n = A.length;
        f[0] = 0;
        int i,j;
        for (i = 0; i < M; i++) {
            f[i] = Integer.MAX_VALUE;
            for (j = 0; j < n; j++) {
                if (i >= A[j] && f[i - A[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i - A[j]] + 1, f[i]);
                }
            }
        }
        if (f[M] == Integer.MAX_VALUE) {
            f[M] = -1;
        }
        return f[M];
    }




    /**
     * 动态规划的核心思想是。。把Question划归成Subquestion，再把Subquestion划归成Subsubquestion。。直到可以求解
     * 对于这道题，以coins = [1, 2, 5], amount = 11为例 我们要求组成11的最少硬币数，可以考虑组合中的最后一个硬币分别是1，2，5的情况，比如
     * * 最后一个硬币是1的话，最少硬币数应该为【组成10的最少硬币数】+ 1枚（1块硬币）
     * * 最后一个硬币是2的话，最少硬币数应该为【组成9的最少硬币数】+ 1枚（2块硬币）
     * * 最后一个硬币是5的话，最少硬币数应该为【组成6的最少硬币数】+ 1枚（5块硬币）
     * 在这3种情况中硬币数最少的那个就是结果 按同样的道理，我们也可以分别再求出组成10的最少硬币数，组成9的最少硬币数，组成6的最少硬币数。。。 
     * 发现了吗，这种当前状态的问题可以转化成之前状态问题的，一般就是动态规划的套路 所以我们自底向上依次求组成1，2...一直到11的最少硬币数 对每一个数，
     * 依次比较最后一个硬币是不同面额的情况，从中选出最小值 ⚠️注意：这里有两个小技巧：
     * 1. 预设一个0位方便后续计算，组成0的最少硬币数是0，所以dp[0] = 0
     * 2. 给每一个数预设一个最小值amount+1，因为硬币面额最小为整数1，所以只要有解，最小硬币数必然小于amount+1
     */
    public int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        for(int i=0; i<amount+1; i++) {//完全背包问题
            dp[i] = i == 0 ? 0 : amount+1;
            for(int coin : coins) {//组合问题，需要考虑元素顺序
                if(i >= coin) {
                    dp[i] = Math.min(dp[i-coin] + 1, dp[i]);
                }
            }
        }
        return dp[amount] == amount+1 ? -1 : dp[amount];
    }


    /**
     * 二、凑零钱问题
     * 先看下题目：给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，
     * 问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1 。算法的函数签名如下：
     * // coins 中是可选硬币面值，amount 是目标金额
     * int coinChange(int[] coins, int amount);
     * 比如说 k = 3，面值分别为 1，2，5，总金额 amount = 11。那么最少需要 3 枚硬币凑出，即 11 = 5 + 5 + 1。
     *
     * 你认为计算机应该如何解决这个问题？显然，就是把所有肯能的凑硬币方法都穷举出来，然后找找看最少需要多少枚硬币。
     * 1、暴力递归
     * 首先，这个问题是动态规划问题，因为它具有「最优子结构」的。要符合「最优子结构」，子问题间必须互相独立。啥叫相互独立？你肯定不想看数学证明，我用一个直观的例子来讲解。
     *
     * 比如说，你的原问题是考出最高的总成绩，那么你的子问题就是要把语文考到最高，数学考到最高…… 为了每门课考到最高，
     * 你要把每门课相应的选择题分数拿到最高，填空题分数拿到最高…… 当然，最终就是你每门课都是满分，这就是最高的总成绩。
     *
     * 得到了正确的结果：最高的总成绩就是总分。因为这个过程符合最优子结构，“每门科目考到最高”这些子问题是互相独立，互不干扰的。
     *
     * 但是，如果加一个条件：你的语文成绩和数学成绩会互相制约，此消彼长。这样的话，显然你能考到的最高总成绩就达不到总分了，
     * 按刚才那个思路就会得到错误的结果。因为子问题并不独立，语文数学成绩无法同时最优，所以最优子结构被破坏。
     *
     * 回到凑零钱问题，为什么说它符合最优子结构呢？比如你想求 amount = 11 时的最少硬币数（原问题），
     * 如果你知道凑出 amount = 10 的最少硬币数（子问题），你只需要把子问题的答案加一（再选一枚面值为 1 的硬币）就是原问题的答案，
     * 因为硬币的数量是没有限制的，子问题之间没有相互制，是互相独立的。
     *
     * 那么，既然知道了这是个动态规划问题，就要思考如何列出正确的状态转移方程？
     *
     * 先确定「状态」，也就是原问题和子问题中变化的变量。由于硬币数量无限，所以唯一的状态就是目标金额 amount。
     *
     * 然后确定 dp 函数的定义：当前的目标金额是 n，至少需要 dp(n) 个硬币凑出该金额。
     *
     * 然后确定「选择」并择优，也就是对于每个状态，可以做出什么选择改变当前状态。具体到这个问题，无论当的目标金额是多少，
     * 选择就是从面额列表 coins 中选择一个硬币，然后目标金额就会减少：
     * # 伪码框架
     * def coinChange(coins: List[int], amount: int):
     *     # 定义：要凑出金额 n，至少要 dp(n) 个硬币
     *     def dp(n):
     *         # 做选择，选择需要硬币最少的那个结果
     *         for coin in coins:
     *             res = min(res, 1 + dp(n - coin))
     *         return res
     *     # 我们要求的问题是 dp(amount)
     *     return dp(amount)
     * 最后明确 base case，显然目标金额为 0 时，所需硬币数量为 0；当目标金额小于 0 时，无解，返回 -1：
     *
     * def coinChange(coins: List[int], amount: int):
     *     def dp(n):
     *         # base case
     *         if n == 0: return 0
     *         if n < 0: return -1
     *         # 求最小值，所以初始化为正无穷
     *         res = float('INF')
     *         for coin in coins:
     *             subproblem = dp(n - coin)
     *             # 子问题无解，跳过
     *             if subproblem == -1: continue
     *             res = min(res, 1 + subproblem)
     *         return res if res != float('INF') else -1
     *     return dp(amount)
     * 至此，状态转移方程其实已经完成了，以上算法已经是暴力解法了，以上代码的数学形式就是状态转移方程：
     * dp(n)=
     * 0,n=0
     * −1,n<0
     * min{dp(n−coin)+1∣coin∈coins},n>0
     *
     * 至此，这个问题其实就解决了，只不过需要消除一下重叠子问题，比如 amount = 11, coins = {1,2,5} 时画出递归树看看：
     * 时间复杂度分析：子问题总数 x 每个子问题的时间。
     * 子问题总数为递归树节点个数，这个比较难看出来，是 O(n^k)，总之是指数级别的。每个子问题中含有一个 for 循环，复杂度为 O(k)。所以总时间复杂度为 O(k * n^k)，指数级别。
     *
     * 2、带备忘录的递归
     * 只需要稍加修改，就可以通过备忘录消除子问题：
     * def coinChange(coins: List[int], amount: int):
     *     # 备忘录
     *     memo = dict()
     *     def dp(n):
     *         # 查备忘录，避免重复计算
     *         if n in memo: return memo[n]
     *
     *         if n == 0: return 0
     *         if n < 0: return -1
     *         res = float('INF')
     *         for coin in coins:
     *             subproblem = dp(n - coin)
     *             if subproblem == -1: continue
     *             res = min(res, 1 + subproblem)
     *         # 记入备忘录
     *         memo[n] = res if res != float('INF') else -1
     *         return memo[n]
     *
     *     return dp(amount)
     * 不画图了，很显然「备忘录」大大减小了子问题数目，完全消除了子问题的冗余，所以子问题总数不会超过金额数 n，即子问题数目为 O(n)。处理一个子问题的时间不变，仍是 O(k)，所以总的时间复杂度是 O(kn)。
     *
     * 3、dp 数组的迭代解法
     * 当然，我们也可以自底向上使用 dp table 来消除重叠子问题，dp 数组的定义和刚才 dp 函数类似，定义也是一样的：
     * dp[i] = x 表示，当目标金额为 i 时，至少需要 x 枚硬币。
     *
     * int coinChange(vector<int>& coins, int amount) {
     *     // 数组大小为 amount + 1，初始值也为 amount + 1
     *     vector<int> dp(amount + 1, amount + 1);
     *     // base case
     *     dp[0] = 0;
     *     for (int i = 0; i < dp.size(); i++) {
     *         // 内层 for 在求所有子问题 + 1 的最小值
     *         for (int coin : coins) {
     *             // 子问题无解，跳过
     *             if (i - coin < 0) continue;
     *             dp[i] = min(dp[i], 1 + dp[i - coin]);
     *         }
     *     }
     *     return (dp[amount] == amount + 1) ? -1 : dp[amount];
     * }
     * PS：为啥 dp 数组初始化为 amount + 1 呢，因为凑成 amount 金额的硬币数最多只可能等于 amount（全用 1 元面值的硬币），所以初始化为 amount + 1 就相当于初始化为正无穷，便于后续取最小值。
     *
     * 三、最后总结
     * 第一个斐波那契数列的问题，解释了如何通过「备忘录」或者「dp table」的方法来优化递归树，并且明确了这两种方法本质上是一样的，只是自顶向下和自底向上的不同而已。
     * 第二个凑零钱的问题，展示了如何流程化确定「状态转移方程」，只要通过状态转移方程写出暴力递归解，剩下的也就是优化递归树，消除重叠子问题而已。
     * 如果你不太了解动态规划，还能看到这里，真得给你鼓掌，相信你已经掌握了这个算法的设计技巧。
     * 计算机解决问题其实没有任何奇技淫巧，它唯一的解决办法就是穷举，穷举所有可能性。算法设计无非就是先思考“如何穷举”，然后再追求“如何聪明地穷举”。
     *
     * 列出动态转移方程，就是在解决“如何穷举”的问题。之所以说它难，一是因为很多穷举需要递归实现，二是因为有的问题本身的解空间复杂，不那么容易穷举完整。
     *
     * 备忘录、DP table 就是在追求“如何聪明地穷举”。用空间换时间的思路，是降低时间复杂度的不二法门，除此之外，试问，还能玩出啥花活？
     *
     * 最后，点击我的头像可以查看更多详细题解，希望读者多多点赞，让我感受到你的认可～
     *
     * PS：我的所有算法文章都已经上传到了 Github 仓库：fucking-algorithm，共 60 多篇，绝对精品，肯定让你收获满满，求个 star 不过分吧～
     *
     * PPS：我最近精心制作了一份电子书《labuladong的算法小抄》，分为「动态规划」「数据结构」「算法思维」「高频面试」四个章节，目录如下，限时开放下载，如有需要可扫码到我的公众号 labuladong 后台回复关键词「pdf」下载：
     */
}
