package com.zcr.b_leetcode.dynamicplan.stringeditor;

/**
 * 5. Longest Palindromic Substring
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: "cbbd"
 * Output: "bb"
 */

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LongestPalindromicSubstring5 {//区间动态规划

    /**
     * 思路一：回文子串有奇数个偶数个
     * 从任意一个字符开始，向两边扩展
     */


    /**
     * 一个串是不是回文串：1.头尾是否相等  2.中间是不是回文串
     *
     *
     * 呈轴对称的，动态规划的方法
     * 1、用start和length记录最后的结果，从哪个位置开始，长度为多少
     * 2、需要一个二维的boolean数组，记录当前的字符串是否是回文
     * Boolean[i][j]，下标从i到j表示的子字符串是否是回文
     * j0 1 2 3
     * i  a b b a
     * 0a T F F T
     * 1b   T T F
     * 2b     T F
     * 3a       T
     * 初始化
     * 长度为1的子字符串：[i][i]=true
     * 长度为2的子字符串：[i][i+1]相邻的字符，如果相同为true，不同为false
     * 长度为n的子字符串：[i][j]，如果i不等于j为false，否则(i等于j)就要看它中间的字符串，等于[i+1][j-1]的值(也就是它的下一行、前一列的那个位置上的值）
     * j  0 1 2 3 4
     * i  a b c b d
     * 0a T F F F F
     * 1b   T F T F
     * 2c     T F F
     * 3b       T F
     * 4d         T
     * 3、长度为1、2的情况特殊处理直接就能赋值
     * 4、最后一个循环中：
     * 对于boolean数组而言，取值为：
     * i的含义是当前子字符串的长度，从长度为3的开始4，5…一直到字符串的总长度为止即i<len+1,表示最多取到len，开始进入循环，因为找到了他们的共同规律
     * j起点是从0开始,表示的是子字符串的起始位置,一直到len-1为止即j<len。末尾位置j+i-1一直到len位置，即j+i-1<len+1
     * 但是还是得从字符串中实际取值：
     * 0 1 2 3 4 5
     * 起始位置j(长度为i)则末位位置为j+i-1
     * 如：0 3 0+3-1=2  [0,2]
     * 如：如果字符串的长度为5
     * i=3时，0到2、1到3、2到4
     * i=4时，0到3、1到4
     *
     * 5、数组生成了
     * 如何找到最长的呢？每次循环都长度变大，所以不断的更新。
     *
     * 时间：O(n^2) 嵌套for循环
     * 空间：O(n^2) boolean数组
     *
     * 启示1：循环的时候，i=0 i< len其实就是循环len次，因为0,1,2...len-1一共就是len个数
     * 启示2：这种模型很常见，还有的题：不是让你找到最长的子字符串，而是所有的
     * @param s
     * @return
     */
    public String longestPalindromicSubstring(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int len = s.length();
        int start = 0;
        int length = 1;
        boolean[][] isPalindromic = new boolean[len][len];
        char[] sChar = s.toCharArray();//将字符串变成数组，才容易取得每一个位置上的值。也就是前面讲的，循环遍历字符串的两种方式。
        //定义数组的两种方式int[] array = {};或int[] array1 = new int[];
        for (int i = 0; i < len; i++) {
            isPalindromic[i][i] = true;
        }
        for (int i = 0; i < len - 1; i++) {
            if (sChar[i] == sChar[i + 1]) {
                isPalindromic[i][i + 1] = true;
                start = i;
                length = 2;
            }
        }
        for (int i = 3; i < len + 1; i++) {// length of the current sustring
            for (int j = 0; j + i - 1 < len; j++) {
                if (sChar[j] == sChar[j + i - 1] && isPalindromic[j + 1][j + i - 2]) {
                    //长度为n的子字符串：[i][j]，如果i不等于j为false，否则(i等于j)就要看它中间的字符串，
                    // 等于[i+1][j-1]的值(也就是它的下一行、前一列的那个位置上的值）
                    //第一个条件：位置上的数是否等于j位置上的数    [j,j+i-1]
                    //第二个条件：boolean数组中它的下一行、前一列的那个位置上的值是什么[j+1,j+i-2],是true的话，表明这才是一个回文串
                    //更新boolean数组、子字符串起始位置、子字符串长度
                    isPalindromic[j][j + i - 1] = true;
                    start = j;
                    length = i;
                }
            }
        }
        return s.substring(start,start + length);
    }

    /*//如果题目要求找到所有的回文字符串，那么就记录一个列表
    public String allPalindromicSubstring(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int len = s.length();
        int start = 0;
        int length = 1;
        boolean[][] isPalindromic = new boolean[len][len];
        char[] sChar = s.toCharArray();
        int reslut_len = (int) Math.pow(2,len);
        String[] result = new String[reslut_len];
        //int[] array = {};
        //int[] array1 = new int[];
        String result_string = "";
        char[] result_char = new char[len];
        for (int i = 0; i < len; i++) {
            isPalindromic[i][i] = true;
            result_char.sChar[i];
            result_string = Arrays.toString();
        }
        for (int i = 0; i < len - 1; i++) {
            if (sChar[i] == sChar[i + 1]) {
                isPalindromic[i][i + 1] = true;
                start = i;
                length = 2;
            }
        }
        for (int i = 3; i < len + 1; i++) {// length of the current sustring
            for (int j = 0; j + i - 1 < len; j++) {
                if (sChar[j] == sChar[j + i - 1] && isPalindromic[j + 1][j + i - 2]) {
                    isPalindromic[j][j + i - 1] = true;
                    start = j;
                    length = i;
                }
            }
        }
        return s.substring(start,start + length);
    }*/


    public static void main(String[] args) {
        LongestPalindromicSubstring5 zong = new LongestPalindromicSubstring5();
        String s = "abbafffff";
        String result = zong.longestPalindromicSubstring(s);
        System.out.println(result);
    }
}
