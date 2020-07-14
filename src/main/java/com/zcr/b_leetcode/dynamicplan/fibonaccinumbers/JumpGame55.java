package com.zcr.b_leetcode.dynamicplan.fibonaccinumbers;

/**
 * 55. Jump Game
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 */

/**
 * 55、跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 *
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class JumpGame55 {

    /**
     * 给了一个数组，每一个数组代表的数表示从这个位置能向后跳几步。求：是否能从第一个点跳到最后一个点。
     * 起始位置：index=0
     *  0 1 2 3 4
     * [2,3,1,4,2]
     * 循环，不断更新maxreach
     *
     * For循环的条件：是for那个到达的话、最远能到达那个范围内的数
     * @param nums
     * @return
     */
    public boolean jumpGame(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        if (nums.length == 1 && nums[0] == 0) {
            return true;
        }
        int len = nums.length;
        int maxreach = 0;
        for (int i = 0; i < len && i <= maxreach; i++) {
            maxreach = Math.max(maxreach,i + nums[i]);
            if (maxreach >= len - 1) {
                return true;
            }
        }
        return false;
    }


    /**
     * 1.确定状态：
     * 最后一步：能跳到n-1   是从石头i调过来的       （1）青蛙能够跳到石头i（2）最后一步不超过跳跃的最大距离 i    n-1       n-1-i<ai
     * 子问题：能不能跳到石头i
     * f[i]青蛙能不能跳到石头i
     * 2.转移方程
     * f[j] = OR0<=i<j(f[i] && a[i]>=j)
     * 3.初始条件和边界情况
     * 初始条件：f[0]=true
     * 边界条件：
     * 4.计算顺序：从0到n-1
     *
     * f[n-1]
     *
     * @param nums
     * @return
     */
    public boolean jumpGame9(int[] nums) {
        int n = nums.length;
        boolean[] f = new boolean[n];
        f[0] = true;
        for (int j = 0; j < n; j++) {
            f[j] = false;
            for (int i = 0; i < j; i++) {
                if (f[i] && i + nums[i] >= j) {
                    f[j] = true;
                    break;
                }
            }
        }
        return f[n-1];
    }
}
