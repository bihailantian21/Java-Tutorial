package com.zcr.b_leetcode.dynamicplan.backpack01;


import java.util.Arrays;

/**
 * 377. 组合总和 Ⅳ
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 *
 * 示例:
 *
 * nums = [1, 2, 3]
 * target = 4
 *
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * 请注意，顺序不同的序列被视作不同的组合。
 *
 * 因此输出为 7。
 * 进阶：
 * 如果给定的数组中含有负数会怎么样？
 * 问题会产生什么变化？
 * 我们需要在题目中添加什么限制来允许负数的出现？
 */
public class CombinationSum377_4 {

    /**
     * 涉及顺序的完全背包。
     *
     * 方法：动态规划
     * “动态规划”的两个步骤是思考“状态”以及“状态转移方程”。
     * 1、状态
     * 对于“状态”，我们首先思考能不能就用问题当中问的方式定义状态，上面递归树都画出来了。当然就用问题问的方式。
     * dp[i] ：对于给定的由正整数组成且不存在重复数字的数组，和为 i 的组合的个数。
     * 思考输出什么？因为状态就是问题当中问的方式而定义的，因此输出就是最后一个状态 dp[n]。
     * 2、状态转移方程
     * 由上面的树形图，可以很容易地写出状态转移方程：
     * dp[i] = sum{dp[i - num] for num in nums and if i >= num}
     * 注意：在 
     * 0
     * 0 这一点，我们定义 dp[0] = 1 的，它表示如果 nums 里有一个数恰好等于 target，它单独成为 
     * 1
     * 1 种可能。
     * @param nums
     * @param target
     * @return
     */
    /**
     * 这里状态定义就是题目要求的，并不难，状态转移方程要动点脑子，也不难：
     * 状态转移方程：dp[i]= dp[i - nums[0]] + dp[i - nums[1]] + dp[i - nums[2]] + ... （当 [] 里面的数 >= 0）
     * 特别注意：dp[0] = 1，表示，如果那个硬币的面值刚刚好等于需要凑出的价值，这个就成为 1 种组合方案
     * 再举一个具体的例子：nums=[1, 3, 4], target=7;
     * dp[7] = dp[6] + dp[4] + dp[3]
     * 即：7 的组合数可以由三部分组成，1 和 dp[6]，3 和 dp[4], 4 和dp[3];
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] maximum = new int[target + 1];
        maximum[0] = 1;
        Arrays.sort(nums);
        for (int i = 1; i <= target; i++) {//需要考虑元素顺序
            for (int j = 0; j < nums.length && nums[j] <= i; j++) {//完全背包问题
                maximum[i] += maximum[i - nums[j]];
            }
        }
        return maximum[target];
    }


    /**
     * 解题思路
     * 常见的背包问题有1、组合问题。2、True、False问题。3、最大最小问题。
     * 以下题目整理来自大神CyC，github地址：
     * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92.md#0-1-%E8%83%8C%E5%8C%85
     * 我在大神整理的基础上，又做了细分的整理。分为三类。
     * 1、组合问题：
     * 377. 组合总和 Ⅳ
     * 494. 目标和
     * 518. 零钱兑换 II
     * 2、True、False问题：
     * 139. 单词拆分
     * 416. 分割等和子集
     * 3、最大最小问题：
     * 474. 一和零
     * 322. 零钱兑换
     *
     * 组合问题公式
     * dp[i] += dp[i-num]
     * True、False问题公式
     * dp[i] = dp[i] or dp[i-num]
     * 最大最小问题公式
     * dp[i] = min(dp[i], dp[i-num]+1)或者dp[i] = max(dp[i], dp[i-num]+1)
     * 以上三组公式是解决对应问题的核心公式。
     *
     * 当然拿到问题后，需要做到以下几个步骤：
     * 1.分析是否为背包问题。
     * 2.是以上三种背包问题中的哪一种。
     * 3.是0-1背包问题还是完全背包问题。也就是题目给的nums数组中的元素是否可以重复使用。
     * 4.如果是组合问题，是否需要考虑元素之间的顺序。需要考虑顺序由顺序的解法，不需要考虑顺序又有对应的解法。
     *
     * 接下来讲一下背包问题的判定
     * 背包问题具备的特征：给定一个target，target可以是数字也可以是字符串，再给定一个数组nums，nums中装的可能是数字，也可能是字符串，问：能否使用nums中的元素做各种排列组合得到target。
     *
     * 背包问题技巧：
     * 1.如果是0-1背包，即数组中的元素不可重复使用，nums放在外循环，target在内循环，且内循环倒序；
     *
     * for num in nums:
     *     for i in range(target, nums-1, -1):
     * 2.如果是完全背包，即数组中的元素可重复使用，nums放在外循环，target在内循环。且内循环正序。
     *
     * for num in nums:
     *     for i in range(nums, target+1):
     * 3.如果组合问题需考虑元素之间的顺序，需将target放在外循环，将nums放在内循环。
     *
     * for i in range(1, target+1):
     *     for num in nums:
     */
}
