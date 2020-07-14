package com.zcr.b_leetcode.dynamicplan.fibonaccinumbers;

/**
 * 45. Jump Game II
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Example:
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 * You can assume that you can always reach the last index.
 */

/**
 * 45、跳跃游戏2
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 */
public class JumpGame245 {

    /**
     * 返回最少几步就能到达队尾
     *  0 1 2 3 4 5 6
     * [2,1,3,1,1,1,1]
     * Step记录当前的步数
     * Curmax记录当前这一步能走到的最远的位置（如：初始值是在idx为0位置，当前第一步所能走到的最远的位置是idx为2；
     * 走两步第二步所能走到的最远的位置是idx为5）
     * Nextmax在我们做第一步的时候，有可能走到idx1，有可能走到idx3。
     * 如果走到idx1的话，最远能走到idx2。
     * 如果走动idx2的话，最远能走到idx5。
     * 在当前的这一步计算过程中，我们求得得下一步能走到得最远得位置。
     * 当前这一步走完之后，我们会把nextmax赋值给curmax作为下一步得curmax。
     *
     * 如：
     * Idx       0   1     2      3 4 5 6
     *           2   1     3      1 1 1 1
     * Idx     0     1/2   3/4/5
     * Step    0     1     2      3
     * Curmax  0     2     5      6
     * Nextmax0->2   2->5  5->6   6
     *
     * 外层循环：一步一步的大循环。什么情况会用到外层的条件？
     * 如：
     * Idx       0   1     2      3 4 5 6
     *           2   1     3      1 1 0 1
     * Idx     0     1/2   3/4/5
     * Step    0     1     2      3
     * Curmax  0     2     5      5
     * Nextmax0->2   2->5  5
     * 当内层循环的idx到6的时候，已经不满足idx<=curMax了，这时候内循环已经结束了，外循环也结束了，返回0。
     * 意思是说：永远不可能到达队尾。
     *
     * 内层循环：在每一步大循环中、小循环中计算nextmax的值，循环结束后，
     * 这一步我们已经走到了最远的位置。要把当前的最远位置要向后扩展。步数加加。如果新的最远位置能达到队尾了，就返回步数。
     *
     *
     *
     * @param nums
     * @return
     */
    public int jumpGame2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int curMax = 0;
        int nextMax = 0;
        int step = 0;
        int index = 0;
        while (index <= curMax) {
            for (int i = index; i <= curMax; i++) {
                nextMax = Math.max(nextMax,i + nums[i]);
            }
            step++;
            curMax = nextMax;
            if (curMax >= nums.length - 1) {
                return step;
            }
        }
        return 0;
    }
}
