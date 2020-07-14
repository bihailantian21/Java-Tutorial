package com.zcr.b_leetcode.dynamicplan.maximumincrementalsequence;


import java.util.Arrays;

/**
 * 最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class FindContinutiousNumber {

    // dp[i]  以i结尾的数字的最长
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        Arrays.sort(nums);//递增排序
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] == nums[j] + 1) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().orElse(0);
    }
}
