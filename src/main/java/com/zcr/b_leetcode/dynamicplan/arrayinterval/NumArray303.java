package com.zcr.b_leetcode.dynamicplan.arrayinterval;

/**
 * 303. 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，
 * 包含 i,  j 两点。
 *
 * 示例：
 *              0  1  2   3  4   5
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 *      dp
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 *
 * /**
 *  * Your NumArray object will be instantiated and called as such:
 *  * NumArray obj = new NumArray(nums);
 *  * int param_1 = obj.sumRange(i,j);
 *
 *
 *
 *  求区间 i ~ j 的和，可以转换为 sum[j + 1] - sum[i]，其中 sum[i] 为 0 ~ i - 1 的和。
 *  */

public class NumArray303 {
    private int[] dp ;

    public NumArray303(int[] nums) {
        dp = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            dp[i] = dp[i - 1] +nums[i-1];
        }
    }

    /**
     * dp[i]:代表了0~i-1位置上的和
     * @param i
     * @param j
     * @return
     */
    public int sumRange(int i, int j) {
        return dp[j + 1] - dp[i];
    }
}
