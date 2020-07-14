package com.zcr.a_offer.g_recursionandloop;

/***
 * 9、矩形覆盖
 * 我们可以用 2*1 的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形，总共有多少种方法？
 */
public class RectCover9_4 {

    /**
     * 我们先把2*8的覆盖方法记为f(8)，用第一个1*2的小矩形去覆盖大矩形的最左边时有两种方法：横着放或竖着放。
     * 当竖着放的时候，右边还剩下2*7个区域，可以记为f(7)。
     * 当横着放的时候，则左下角必须再横着放一个1*2的矩形，此时右边还剩下2*6个区域，记为f(6)。
     * 此时f(8) = f(7) + f(6)
     * 其实又是一道斐波那契数列的题。
     *
     * 使用递归
     * @param target
     * @return
     */
    public int RectCover1(int target) {
        if (target < 1)
            return 0;
        if (target == 1 || target == 2)
            return target;
        return RectCover1(target - 1) + RectCover1(target - 2);
    }

    /**
     * 使用动态规划
     * @param target
     * @return
     */
    public int RectCover2(int target) {
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
     * 使用滚动优化
     * @param n
     * @return
     */
    public int RectCover3(int n) {
        if (n <= 2)
            return n;
        int pre2 = 1, pre1 = 2;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = pre2 + pre1;
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }
}
