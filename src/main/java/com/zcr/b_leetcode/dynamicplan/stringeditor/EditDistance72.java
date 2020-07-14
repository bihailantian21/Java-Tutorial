package com.zcr.b_leetcode.dynamicplan.stringeditor;


/**
 * 72. Edit Distance
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * You have the following 3 operations permitted on a word:
 * Insert a character
 * Delete a character
 * Replace a character
 *
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 *
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */

/**
 * 72、编辑距离
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 示例 1:
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例 2:
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class EditDistance72 {

    /**
     * 插入一个字符、删除一个字符、替换一个字符
     * State:[i][j]表示word1从0开始到i，与word2从0开始到j要相等的话，需要几步操作。
     * Init:
     * [0][0]=0
     * 初始化Word1为空的情况：word2要和word1相等，则删掉word2中的字符[0][1]=1…   [0][i]=i
     * 初始化word2为空的情况：word2要和word1相等，则在word2中加上一个字符[1][0]=1…   [i][0]=i
     * Func:
     *
     *
     * 两种情况
     *
     * 大情况一：
     * If(word1[i]==word2[j])
     * 如：12b与ab是否相等，
     * 情况一：12b与a相等的步数+1（为什么要加1呢，要在a后面加一个b）
     * （左边的值+1）
     * 情况二：12与ab相等的步数+1（要把b删掉）
     * （上面的值+1）
     * 情况三：12与a相等的步数（则后面都加一个b的话是自动匹配的）
     * （等于左上角的值）
     * 取上面三个值中最小的
     *
     * 大情况二：
     * 这两个字符是不相同的：
     * 其实和上面的分三个小情况是一样的，唯一的区别是从左上角得到的值也需要+1。
     * 因为这两个字符并不匹配，我们需要将一个字符变成另一个字符replace。
     * Res:
     * 1 0 A b c d e
     * 2
     * 0 0 1 2 3 4 5
     * 1 1
     * 2 2
     * b 3
     * 4 4
     * 5 5
     *
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dis = new int[m + 1][n + 1];
        dis[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            dis[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            dis[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dis[i][j] = Integer.MAX_VALUE;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dis[i][j] = Math.min(dis[i - 1][j - 1],dis[i - 1][j] + 1);
                    dis[i][j] = Math.min(dis[i - 1][j - 1],dis[i][j - 1] + 1);
                } else {
                    dis[i][j] = Math.min(dis[i - 1][j - 1] + 1,dis[i - 1][j] + 1);
                    dis[i][j] = Math.min(dis[i][j],dis[i][j - 1] + 1);
                }
            }
        }
        return dis[m][n];
    }
}
