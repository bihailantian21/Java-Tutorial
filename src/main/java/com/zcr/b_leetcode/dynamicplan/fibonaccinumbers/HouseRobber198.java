package com.zcr.b_leetcode.dynamicplan.fibonaccinumbers;

/**
 * 198. House Robber
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
 * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security
 * system connected and it will automatically contact the police if two adjacent houses were broken into on
 * the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum
 * amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 *
 * Example 2:
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *              Total amount you can rob = 2 + 9 + 1 = 12.
 */

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class HouseRobber198 {

    /**
     * 题目描述：抢劫一排住户，但是不能抢邻近的住户，求最大抢劫量。
     *
     * 定义 dp 数组用来存储最大的抢劫量，其中 dp[i] 表示抢到第 i 个住户时的最大抢劫量。
     * 由于不能抢劫邻近住户，如果抢劫了第 i -1 个住户，那么就不能再抢劫第 i 个住户，所以
     * dp[i]=max{dp[i-2]+nums[i],dp[i-1]}
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);//注意dp[i]------nums[i-1]
        }
        return dp[nums.length];
    }

    public int rob2(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int preforword2 = 0;//前两步
        int preforword1 = nums[0];//前一步
        for (int i = 2; i <= nums.length; i++) {
            int cur = Math.max(preforword2 + nums[i - 1], preforword1);//注意dp[i]------nums[i-1]
            preforword2 = preforword1;
            preforword1 = cur;
        }
        return preforword1;
    }

    public int rob3(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int preforword2 = 0;//前两步
        int preforword1 = 0;//前一步
        for (int i = 1; i <= nums.length; i++) {
            int cur = Math.max(preforword2 + nums[i - 1], preforword1);//注意dp[i]------nums[i-1]
            preforword2 = preforword1;
            preforword1 = cur;
        }
        return preforword1;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        HouseRobber198 houseRobber198 = new HouseRobber198();
        System.out.println(houseRobber198.rob3(nums));
    }
}
