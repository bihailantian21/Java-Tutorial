package com.zcr.a_offer.g_recursionandloop;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class JumpFloorII9_3 {

    /**
     * 这题不同于跳台阶问题的地方在于，n层可以由前面的任意一层跳过来:
     * 第n个台阶可以由前面所有的台阶跳过来即f[n] = f[n-1] + f[n-2] + ... f[1]；
     * 然后加上直接跳到自己这个台阶(+1)；
     *
     * 标准递归：
     *
     * @param target
     * @return
     */
    public int JumpFloorII(int target) {
        if (target < 1)
            return 0;
        if (target == 1 || target == 2)
            return target;
        int sum = 1; //加上自己一步直接跳到自己的台阶
        for (int i = 1; i < target; i++)
            sum += JumpFloorII(i);
        return sum;
    }

    /**
     * 使用动态规划
     *
     * dp[n] = dp[1]+dp[2]+...+dp[n-1]+1
     * ...
     * dp[3] = dp[1]+dp[2]+1
     * dp[2] = dp[1]+1
     * @param target
     * @return
     */
    public int JumpFloorII2(int target) {
        if (target < 1)
            return 0;
        if (target == 1 || target == 2)
            return target;
        int[] dp = new int[target + 1];
        dp[1] = 1;
        for (int i = 2; i <= target; i++) {
            dp[i] = 0;
            for (int j = 1; j < i; j++) //前面的和
                dp[i] += dp[j];
            dp[i] += 1;//加上自己的
        }
        return dp[target];
    }

    /**
     * 使用滚动优化
     * @param target
     * @return
     */
    public int JumpFloorII3(int target) {
        if (target < 1)
            return 0;
        if (target == 1 || target == 2)
            return target;
        int preSum = 3, res = 0;//一开始  preSum = f1 + f2的值
        for (int i = 3; i <= target; i++) {
            res = preSum + 1;  //之前的和　加上自己的
            preSum += res;
        }
        return res;
    }

    /**
     * 推出前面几项也可以看出其实就是一个等比数列求和 ，也就是2^n-1。
     * 跳上 n-1 级台阶，可以从 n-2 级跳 1 级上去，也可以从 n-3 级跳 2 级上去...，那么
     * f(n-1) = f(n-2) + f(n-3) + ... + f(0)
     * 同样，跳上 n 级台阶，可以从 n-1 级跳 1 级上去，也可以从 n-2 级跳 2 级上去... ，那么
     * f(n) = f(n-1) + f(n-2) + ... + f(0)
     * 综上可得
     * f(n) - f(n-1) = f(n-1)
     * 即
     * f(n) = 2*f(n-1)
     * 所以 f(n) 是一个等比数列
     * @param target
     * @return
     */
    public int JumpFloorII4(int target) {
        //return 1 << (target - 1);
        return (int) Math.pow(2, target - 1);
    }
}
