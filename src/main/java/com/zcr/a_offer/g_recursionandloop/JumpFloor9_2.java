package com.zcr.a_offer.g_recursionandloop;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */

/**
 * 总结：做这类题的三种方法：
 * 1、标准递归
 * 2、动态规划
 * 3、滚动优化
 */

public class JumpFloor9_2 {


    /**
     * 标准递归：
     * @param target
     * @return
     */
    public int JumpFloor0(int target) {
        if (target < 1)
            return 0;
        if (target == 1 || target == 2)
            return target;
        return JumpFloor(target - 1) + JumpFloor(target - 2);
    }
    /**
     * 采用递归：跳 n 阶台阶，可以先跳 1 阶台阶，此时跳法数目等于再跳 n-1 阶台阶；或者先跳 2 阶台阶，此时跳法数目等于再跳 n-2 阶台阶。
     * 即不同的跳法数等于它们相加起来。
     * 而 n-1 和 n-2 阶台阶的跳法可以看成子问题
     *
     * 动态规划
     * 时间：O(n)
     * 空间：O(n)
     *
     * @param target
     * @return
     */
    public int JumpFloor(int target) {
        if (target <= 1) {
            return target;
        }
        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= target; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[target];
    }

    /**
     * 每次只需要前面的两个值，为了节省空间
     *
     * 时间：O(n)
     * 空间：O(1)
     * @param target
     * @return
     */
    public int JumpFloor2(int target) {
        if (target <= 1) {
            return 1;
        }
        int result = 0;
        int pre1 = 1;
        int pre2 = 1;
        for (int i = 2; i <= target; i++) {
            result = pre1 + pre2;
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }

}
