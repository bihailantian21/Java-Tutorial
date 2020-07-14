package com.zcr.b_leetcode.dynamicplan.maximumincrementalsequence;

import java.util.Arrays;

/**
 * 646. 最长数对链
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 *
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 *
 * 给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 *
 * 示例 :
 *
 * 输入:
 * [
 *      [1,2],
 *      [2,3],
 *      [3,4]
 * ]
 * 输出: 2
 * 解释: 最长的数对链是 [1,2] -> [3,4]
 * 注意：
 *
 * 给出数对的个数在 [1, 1000] 范围内。
 */
public class FindLongestChain646 {

    /**
     * 思路
     * 在一个长度为 k，以 pairs[i] 结尾的数对链中，如果 pairs[i][1] < pairs[j][0]，则将该数对加入链中，数对链长度变为 k+1。
     *
     * 算法
     * 根据数对的第一个数排序所有的数对，dp[i] 存储以 pairs[i] 结尾的最长链的长度。
     * 当 i < j 且 pairs[i][1] < pairs[j][0] 时，
     * 扩展数对链，更新 dp[j] = max(dp[j], dp[i] + 1)。
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return 0;
        }
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));//递增排序
        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);//终于知道为啥初始要填1了！！因为断开后，又要从连续的数字开始，那么这个连续数字的前一个需要置为1
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().orElse(0);
        /**
         *  int ans = 0;
         *         for (int x: dp) if (x > ans) ans = x;
         *         return ans;
         */
    }
}
