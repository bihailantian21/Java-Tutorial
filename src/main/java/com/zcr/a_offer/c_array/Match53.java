package com.zcr.a_offer.c_array;

/**
 * 53.正则表达式匹配
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 *
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */


/**
 *
 */
public class Match53 {

    /**
     * 解析
     * 这种题目一般都会用动态规划。
     *
     * 递归的思路如下图，大致分三种情况
     * 我们的sl和pl分别表示当前判断的两个串的长度。而对应的索引是s[sl - 1]和p[pl-1]。
     * s[sl - 1] = p[pl-1]；
     * p[pl - 1] = '.'；
     *
     * p[pl - 1] = '*'；这种情况具体又可以分几种情况，具体看下图。
     *
     *
     * 最后还要注意边界，当s == "" && p == ""的时候返回true，当p=="" & s!= ""的时候返回false。
     * 当s == "" && p != ""的时候就要注意，如果p[i-1] == '*'则dp[0][i] = dp[0][i-2]，因为可以用*可以消去前一个字符。
     * 虽然第三种情况，可以合起来考虑，代码会更简洁一些，但是这里个人认为还是写的清楚一点较好。
     *
     *
     * @param str
     * @param pattern
     * @return
     */
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        int len1 = str.length;
        int len2 = pattern.length;
        boolean[][] match= new boolean[len1 + 1][len2 + 1];
        match[0][0] = true;
        for (int i = 1; i <= len2; i++) {
            if (pattern[i - 1] == '*') {
                match[0][i] = match[0][i - 2];
            }
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '.') {
                    match[i][j] = match[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    if (pattern[j - 2] != str[i - 1] && pattern[j - 2] != '.' ) {
                        match[i][j] = match[i][j - 2];
                    } else {
                        match[i][j] = match[i][j - 2] || match[i][j - 1] || match[i - 1][j];

                    }
                }
            }
        }
        return match[len1][len2];
    }
}
