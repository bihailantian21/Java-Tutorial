package com.zcr.b_leetcode.dynamicplan.stringeditor;


/**
 * 583. 两个字符串的删除操作
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 *
 *
 *
 * 示例：
 *
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 *
 *
 * 提示：
 *
 * 给定单词的长度不超过500。
 * 给定单词中的字符只含有小写字母。
 */
public class MinDistance583 {

    /**
     * 可以转换为求两个字符串的最长公共子序列问题。
     *
     * 只需要求出最公共长子序列（1143题），然后算出总字符串长度，减去二倍的子序列长度即可。
     * 例如:”sea”, “eat”
     *
     * 最长公共子序列 为 ea.
     *
     * 那么,步骤数 = 3 + 3 – 2*2 = 2.
     *
     * 上述等价关系成立的原因是如果两个字符串完全不匹配（也就是两个字符串没有任何一个字符相同），那么总删除次数是 m + n。
     * 如果两个字符串存在一个公共子序列，长度为 lcs，两个字符串我们都可以减少 lcs 次删除操作，也就是总共减少 2lcs 次删除操作，
     * 也就得到了上述等式。
     *
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return m + n - 2 * dp[m][n];
    }

}
