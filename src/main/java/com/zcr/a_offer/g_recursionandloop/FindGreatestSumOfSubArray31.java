package com.zcr.a_offer.g_recursionandloop;


/**
 * 31.连续子数组的最大和
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:
 * 在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
 * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
 *
 * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 */

/**
 * leetcode53
 */
public class FindGreatestSumOfSubArray31 {

    /**
     * 方法一：
     * 从头到尾逐个累加数组中的数字，
     * 初始化为0，第一步加上数字1，此时和为1。
     * 第二步加上数字-2，此时和为-1。
     * 第三步加上数字3，我们注意到由于此前累计的和为-1，小于0，那么用-1加上3，得到的和为2，比3本身还小。
     * 也就是说从第一个数字开始的子数组和比从第三个数字开始的子数组的和还要小，因此我们不用考虑从第一个数字开始的子数组，之前累计的和也被抛弃。
     * <p>
     * 我们从第三个数字重新开始累加，此时得到的和是3。
     * 第四步加上数字10，此时和为13。
     * 第五步加上数字-4，此时和为9。用-4加上13，得到的和为9，比原来的和还要小。
     * 因此我们把之前得到的和13保存下来，它有可能是最大的子数组和。
     * <p>
     * 第六步加上数字7，此时和为16。比之前保存的最大子数组和13还要大，把最大的子数组和从13更新为16。
     * 第七步加上数字2，此时和为18。比之前保存的最大子数组和16还要大，把最大的子数组和从16更新为18。
     * 第八步加上数字-5，此时和为13。比之前保存的最大子数组和18要小，最大的子数组和为18。
     * <p>
     * 总结：
     *
     * @param nums
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxCurr = nums[0];
        int max = nums[0];
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (maxCurr <= 0) {
                maxCurr = nums[i];
            } else {
                maxCurr += nums[i];
            }
            if (maxCurr > max) {
                max = maxCurr;
            }
        }
        return max;
    }

    /**
     * 方法二：
     * 给一组整数数组，找最大的和的子数组。
     * maxToCurr:遍历到某一个元素的时候，包含这个元素的子序列的最大的值是多少。目前的最大值，max(maxtocurr+num[i],num[i])
     * 为什么是等于这个呢？因为如果前面的是一个负数，加上num[i]肯定要比num[i]本身要小，那么就从num[i]开始重新计算最大和
     * max:整体的最大值，max(max, maxtocurr)
     * 1 -2 3 10 -4 7 2 -5
     * maxCurr 1 -1 3 13 9  16 18 13
     * max     1  1 3 13 13 16 18 18
     *
     * @param nums
     * @return
     */
    public int FindGreatestSumOfSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxCurr = nums[0];
        int max = nums[0];
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            maxCurr = Math.max(maxCurr + nums[i], nums[i]);
            max = Math.max(maxCurr, max);
        }
        return max;
    }
    /**
     * 这个不好理解
     *
     * 方法三：递归
     * 我们可以从数组的最后开始往前看，对于当前数nums[i]，
     * 以这个nums[i]结尾的最大值一定是你前面的所有数求出一个最大的子序和(但是由于是子数组，所以必须是判断前一个数)+我自己(nums[i])，
     * 所以这是一个递归的过程，边界条件就是i = 0时，最大子序和就是自己。
     *
     * @param nums
     * @return
     */

    /**
     * 方法四：动态规划   等同于   方法二
     * 只不过，
     * 1、滚动数组，只需要前一个位置。所以这个动态规划只需要存储前一个位置，用方法二中的写法就好。
     * 2、包含当前这个数字的最大子数组和的计算，要知道是分前面的和大于等于0和小于0的情况。
     * 方法二中没写，其实隐含的就是分了。
     *
     * 当以第i-1个数字结尾的子数组中所有数字的和小于0时，（以第i个数字结尾的子数组中所有数字的和就是第i个数字本身）；
     * 大于0时，（以第i个数字结尾的子数组中所有数字的和就是前面的累加和加上第i个数字本身）。
     *
     * 虽然我们通常使用递归的方式分析动态规划的问题，但最终都会基于循环去编码。
     *
     * @param nums
     * @return
     */
    public int FindGreatestSumOfSubArray3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] maxCurr = new int[nums.length];
        maxCurr[0] = nums[0];
        int max = nums[0];
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            maxCurr[i] = maxCurr[i - 1] > 0 ? maxCurr[i - 1] + nums[i] : nums[i];
            max = Math.max(maxCurr[i], max);
        }
        return max;
    }

}
