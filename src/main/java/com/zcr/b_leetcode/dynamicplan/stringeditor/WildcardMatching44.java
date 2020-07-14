package com.zcr.b_leetcode.dynamicplan.stringeditor;

/**
 * 44. Wildcard Matching
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 *
 * Example 1:
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 *
 * Example 3:
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 *
 * Example 4:
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 *
 * Example 5:
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 */

/**
 * 44、通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 *
 * 示例 3:
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 *
 * 示例 4:
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 *
 * 示例 5:
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输入: false
 */
public class WildcardMatching44 {

    /**
     * ?代表单个字符
     * *代表字符串、空字符串
     * 动态规划
     * boolean[(s)m+1][(p)n+1]，boolean[i][j]代表的是s从开始到i组成的字符串与p从开始到j组成的字符串是否匹配
     * 如：boolean[2][2]abc abcdef中的ab与ab是否匹配
     *
     * 情况一：
     *   s a b c
     * p T
     * 1*T
     * 2aF
     * 3*F
     * 初始化：第一个位置boolean[0][0]=true，s为空与p为空是匹配的
     * 初始化：如果s是空字符时，p中怎么样的情况能与之匹配。
     * 对于p中第一个位置：
     * 如果p中只要不是*的话，一定是false;
     * 如果是*的话，可能是true。它等于上面一个位置的值，
     * 为什么：
     * 对于p中的第二个位置：如果p中为a的话，它肯定与s不匹配；
     * 对于p中的第三个位置：如果p中为*的话，它等于上面一个位置a的值。也就是要看a*是否与空字符串匹配，就要看a是否与空字符串匹配。
     * s a b c
     * p T
     * 1*T
     * 2aF
     * 3*F
     * 所以：初始化的条件就是：
     * If([i] == “*”) [0][i]=[0][i-1]看它上面一行
     * 其他情况为false。
     *
     * P为空的情况下，都不匹配s中的字符串
     * s  a b c
     * p TF F F
     * 1*
     * 2a
     * 3*
     *
     * 情况二：
     * 如果p中的字符和s中的字符是一样的，或者p中是？，则等于左上一位的值。
     * 如：s:xxxb p:yyb 这时候b和b是匹配的，那么就要看前面的xxx与yy是否匹配。
     * s[j]==p[i]
     * s  a b c
     * p T
     * 1? T
     * 2b   T
     * 3?     T
     *
     * 情况三：*
     * s  a b c
     * p T
     * 1?
     * 2b
     * 3?     T
     * 4*     T
     * 如果p中是*
     * 第一种：*用于空字符，那么看p[i-1]与s[j]是否匹配
     * 等于它上面一行的值。
     * 如：s:ab p:ab* 这时候就要看ab与前面的ab是否匹配。前一行。
     *
     * 第二种：*作为任意字符的情况，这个值等于左边的值。
     * 如：s:abcd p:ab* 这时候就要看abc与前面的ab*是否匹配，也就是等于左边一位的值。
     * 然后求s:abc p:ab*的时候，就要看ab与ab是否匹配,也就是等于左边一位二值。
     * s  a b c
     * p T
     * 1?
     * 2b
     * 3?
     * 4*
     *
     * 1、	初始化s为空与p匹配的情况
     * 注意：字符串的下标值要减1
     * 2、	从s中第一个字符开始，看p中所有的（一列一列的填）
     * 如果s中当前字符和p中当前字符相等，…
     * 如果p中当前字符是*:*作为空字符、*匹配所有的字符串
     * 3、取最右下角的字符
     * @param s
     * @param p
     * @return
     */
    public boolean wildcardMatching(String s,String p) {
        if (s == null || p == null) {
            return false;
        }
        int len1 = s.length();
        int len2 = p.length();
        boolean[][] match = new boolean[len1 + 1][len2 + 1];
        match[0][0] = true;
        for (int pi = 1; pi <= len2; pi++) {
            if (p.charAt(pi - 1) == '*') {
                match[0][pi] = match[0][pi - 1];
            }
        }
        for (int si = 1; si <= len1; si++) {
            for (int pi = 1; pi <= len2; pi++) {
                if ((s.charAt(si - 1) == p.charAt(pi - 1)) || (p.charAt(pi - 1) == '?')) {
                    match[si][pi] = match[si - 1][pi - 1];
                }
                if (p.charAt(pi - 1) == '*') {
                    match[si][pi] = match[si][pi - 1] || match[si - 1][pi];
                }
            }

        }
        return match[len1][len2];
    }
}
