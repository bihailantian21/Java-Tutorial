package com.zcr.b_leetcode;

/**
 * 53. Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution using the divide
 * and conquer approach, which is more subtle.
 */

/**
 * 53、最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class MaximumSubarray53 {

    /**
     * 给一组整数数组，找最大的和的子数组。
     * maxToCurr:遍历到某一个元素的时候，包含这个元素的子序列的最大的值是多少。目前的最大值，max(maxtocurr+num[i],num[i])
     * 为什么是等于这个呢？因为如果前面的是一个负数，加上num[i]肯定要比num[i]本身要小，那么就从num[i]开始重新计算最大和
     * max:整体的最大值，max(max, maxtocurr)
     *
     * 例：
     * [-1,2,3,-2,1]
     *          初始值
     * maxtocurr:-1    2 5 3 4
     * max:      -1    2 5 5 5
     * 最大值是5。
     *
     * 初始值为什么是第一个数：而不是0？
     * 如果初始值为0：
     * 初始值-1 -1
     * maxtocurr:  0   -1  -2
     * max:        0   0    0
     * @param nums
     * @return
     */
    public int maximumSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxCurr = nums[0];
        int max = nums[0];
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            maxCurr = Math.max(maxCurr + nums[i],nums[i]);
            max = Math.max(maxCurr,max);
        }
        return max;

    }
}
