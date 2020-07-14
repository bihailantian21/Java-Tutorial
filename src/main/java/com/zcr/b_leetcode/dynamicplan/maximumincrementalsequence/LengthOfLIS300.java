package com.zcr.b_leetcode.dynamicplan.maximumincrementalsequence;


import java.util.Arrays;

/**
 * 300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
public class LengthOfLIS300 {

    /**
     * 解题思路：
     * 状态定义：dp[i]的值代表 nums 前 i 个数字的最长子序列长度。
     * 转移方程： 设 j∈[0,i)，考虑每轮计算新 dp[i]时，遍历 [0,i) 列表区间，做以下判断：
     * 当 nums[i] > nums[j] 时： nums[i] 可以接在 nums[j]之后（此题要求严格递增），此情况下最长上升子序列长度为 dp[j] + 1 ；
     * 当 nums[i] <= nums[j] 时： nums[i]nums[i] 无法接在 nums[j]nums[j] 之后，此情况上升子序列不成立，跳过。
     * 上述所有 1. 情况 下计算出的 dp[j] + 1的最大值，为直到 i的最长上升子序列长度（即 dp[i] ）。实现方式为遍历 j 时，每轮执行 dp[i] = max(dp[i], dp[j] + 1)。
     * 转移方程： dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)。
     * 初始状态：dp[i]dp[i] 所有元素置 11，含义是每个元素都至少可以单独成为子序列，此时长度都为 11。
     * 返回值：返回 dp 列表最大值，即可得到全局最长上升子序列长度。
     *
     * 复杂度分析：时间复杂度 O(N^2)： 遍历计算 dp 列表需 O(N)，计算每个 dp[i] 需 O(N)。
     * 空间复杂度 O(N) ： dp 列表占用线性大小额外空间。
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }
        return Arrays.stream(dp).max().orElse(0);
        /**
         * 使用 Stream 求最大值会导致运行时间过长，可以改成以下形式：
         *
         * int ret = 0;
         * for (int i = 0; i < n; i++) {
         *     ret = Math.max(ret, dp[i]);
         * }
         * return ret;
         */

        /**
         * //可以想这样，每次for循环的时候，就把Res求出来了！
         *         if(nums.length == 0) return 0;
         *         int[] dp = new int[nums.length];
         *         int res = 0;
         *         Arrays.fill(dp, 1);//
         *         for(int i = 0; i < nums.length; i++) {
         *             for(int j = 0; j < i; j++) {
         *                 if(nums[j] < nums[i]) {
         *                      dp[i] = Math.max(dp[i], dp[j] + 1);
         *                 }
         *             }
         *             res = Math.max(res, dp[i]);
         *         }
         *         return res;
         */
    }


    /**
     * ？
     * 以上解法的时间复杂度为 O(N2)，可以使用二分查找将时间复杂度降低为 O(NlogN)。
     *
     * 定义一个 tails 数组，其中 tails[i] 存储长度为 i + 1 的最长递增子序列的最后一个元素。对于一个元素 x，
     * 如果它大于 tails 数组所有的值，那么把它添加到 tails 后面，表示最长递增子序列长度加 1；
     * 如果 tails[i-1] < x <= tails[i]，那么更新 tails[i] = x。
     * 例如对于数组 [4,3,6,5]，有：
     * tails      len      num
     * []         0        4
     * [4]        1        3
     * [3]        1        6
     * [3,6]      2        5
     * [3,5]      2        null
     * 可以看出 tails 数组保持有序，因此在查找 Si 位于 tails 数组的位置时就可以使用二分查找。
     */
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int len = 0;
        for (int num : nums) {
            int index = binarySearch(tails, len, num);
            tails[index] = num;
            if (index == len) {
                len++;
            }
        }
        return len;
    }

    private int binarySearch(int[] tails, int len, int key) {
        int l = 0, h = len;
        while (l < h) {
            int mid = l + (h - l) / 2;
            if (tails[mid] == key) {
                return mid;
            } else if (tails[mid] > key) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
