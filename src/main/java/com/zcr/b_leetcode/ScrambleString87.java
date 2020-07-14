package com.zcr.b_leetcode;

/**
 * 87. Scramble String
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * Below is one possible representation of s1 = "great":
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * We say that "rgeat" is a scrambled string of "great".
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * We say that "rgtae" is a scrambled string of "great".
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 *
 * Example 1:
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 *
 * Example 2:
 * Input: s1 = "abcde", s2 = "caebd"
 * Output: false
 */

import java.util.Arrays;

/**
 * 87、扰乱字符串
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
 * 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
 *
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
 *
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 * 示例 1:
 * 输入: s1 = "great", s2 = "rgeat"
 * 输出: true
 *
 * 示例 2:
 * 输入: s1 = "abcde", s2 = "caebd"
 * 输出: false
 */
public class ScrambleString87 {

    /**
     * 一个字符串可以分为两个，可以由任意个数字进行分割
     * 打乱：任选一个节点、交换两个节点的位置
     * 分治法
     * 对于一个字符串来说，
     * 规律
     *
     * 1、	左macth左、右match右
     * 2、	左match右、右match左
     * Match不是说完全一样的：是一样或者互换得到的
     *
     * 递归
     * 1、长度为1
     * 2、排序后如果得到的字符不是相同的—直接返回false
     * 3、如何分割左节点和右节点
     * for循环：从各个位置分割string
     * （左macth左、右match右
     * 或者左match右、右match左）
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        if (s1.length() == 1 && s1.equals(s2)) {
            return true;
        }
        char[] s1char = s1.toCharArray();
        char[] s2char = s2.toCharArray();
        Arrays.sort(s1char);
        Arrays.sort(s2char);
        String s1str = new String(s1char);
        String s2str = new String(s2char);
        if (!s1str.equals(s2str)) {
            return false;
        }
        for (int i = 1; i < s1.length(); i++) {
            String s1left = s1.substring(0,i);
            String s1right = s1.substring(i,s1.length());
            if ((isScramble(s1left,s2.substring(0,i)) && isScramble(s1right,s2.substring(i,s2.length())))
            || (isScramble(s1left,s2.substring(s2.length() - i,s2.length())) && isScramble(s1right,s2.substring(0,s2.length() - i)))) {
                return true;
            }
        }
        return false;
    }
}
