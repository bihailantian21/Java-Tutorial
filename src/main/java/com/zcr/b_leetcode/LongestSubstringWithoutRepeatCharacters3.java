package com.zcr.b_leetcode;

/**
 * 3. Longest Substring Without Repeating Characters
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LongestSubstringWithoutRepeatCharacters3 {

    /**
     * 滑动窗口 什么是滑动窗口？ 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，
     * 当再进入 a，队列变成了 abca，这时候不满足要求。所以，我们要移动这个队列！ 如何移动？
     * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！ 一直维持这样的。
     *
     * Java（假设字符集为 ASCII 128）
     * 以前的我们都没有对字符串 s 所使用的字符集进行假设。
     * 当我们知道该字符集比较小的时侯，我们可以用一个整数数组作为直接访问表来替换 Map。
     * 常用的表如下所示：
     * int [26] 用于字母 ‘a’ - ‘z’ 或 ‘A’ - ‘Z’
     * int [128] 用于ASCII码
     *
     * s.charAt(int)表示获取某个下标位置上的元素。所以一般遍历字符串（便于处理每一个字符）：
     * 法一：while(i < len) {s.charAt(rigt)XXX;i++}
     * 法二：将字符串转换成字符数组，遍历字符数组就是遍历字符串。for(char c : s.toCharArray)
     *
     * 1、使用一个布尔类型的数组表示此字符是否在字符串中已经存在 boolean[] used; userd('a')
     * 2、left表示结果的最左边，right表示结果的最右边
     * 3、遍历字符串，如果这个字符不存在，将此字符置为存在，然后right继续前移；注意：此时计算一个maxNoreapt，以防止根本没有重复字符的字符串出现
     * 否则（这个字符存在）比较现有最大值和已有最大值，然后定位重复字符，如果当前不是重复字符，前移left，每次将left值前移都将当前值置为不存在；
     * 否则（当前是那个重复字符），从重复的那一个字符下一个开始重新开始循环left++，然后right因为找到了和它重复的，right++。
     *
     * 时间O(2n)=O(n)
     * 空间O(128)
     */
    public int longestSbstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int len = s.length();
        int max = 0;
        int maxNoreapt = 0;
        boolean[] used = new boolean[128];
        while (right < len) {
            if (used[s.charAt(right)] == false) {
                used[s.charAt(right)] = true;
                right++;
                maxNoreapt = right - left;//很有必要存在，针对根本没有重复字符的字符串而言"abcde"
            } else {
                max = Math.max(max,maxNoreapt);
                while (left < right && s.charAt(right) != s.charAt(left)) {
                    used[s.charAt(left)] = false;
                    left++;
                }
                left++;//左迁移，是因为重新开始的子字符串的位置
                right++;//右也前移，是因为确定了当前的right已经不和前面的重复了
            }
        }
        return Math.max(max,maxNoreapt);
    }

    /**
     * 不需要移动了，直接得到值。
     *
     * 时间O(n)
     * 空间O(128)
     * @param s
     * @return
     */
    public int longestSbstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int len = s.length();
        int max = 0;
        Map<Character,Integer> map = new HashMap<>();
        while (right < len) {


        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatCharacters3 longest = new LongestSubstringWithoutRepeatCharacters3();
        //String s = "nfpdmpi";
        String s = "abcde";
        int result = longest.longestSbstring(s);
        System.out.println(result);
    }
}
