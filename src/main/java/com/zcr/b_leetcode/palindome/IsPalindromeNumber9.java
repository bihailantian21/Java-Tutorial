package com.zcr.b_leetcode.palindome;

/**
 * 9. Palindrome Number
 * Determine whether an integer is a palindrome. An integer is a palindrome
 * when it reads the same backward as forward.
 *
 * Example 1:
 * Input: 121
 * Output: true
 *
 * Example 2:
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-.
 * Therefore it is not a palindrome.
 *
 * Example 3:
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 *
 * Follow up:Coud you solve it without converting the integer to a string?
 *
 */

/**
 * 9. 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 进阶:你能不将整数转为字符串来解决这个问题吗？
 */
public class IsPalindromeNumber9 {

    /**
     * 如：1 3 2 3 1
     *    1 0 0 0 0
     *    1        1
     *      1 0 0
     *      3   3
     *        1
     * 回文数：第一个数和最后一个数相等、第二个数和倒数第二个数相等...
     * 我们使用数学解法：通过取整和取余操作获取整数中对应的数字进行比较。
     * 1、初始化被除数divs：因为要取得这个数的第一个数
     * 2、然后第一个数怎么取：/divs
     * 最后一个数怎么取：%10
     * 比较这两个数是否相等，不想等直接返回false，否则继续循环
     * 3、改变循环条件：num(13231-1*1000)/10 divs(/100) 继续循环
     *
     * 时间：O(1)
     * 空间：O(1)
     * @param num
     * @return
     */
    public boolean isPalindrome(int num) {
        if (num < 0) {
            return false;
        }
        int divs = 1;
        int numcopy = num;
        while (numcopy / divs >= 10) {
            divs *= 10;
        }
        while (num != 0 ){
            int left = num / divs;
            int right = num % 10;
            if (left != right) {
                return false;
            }
            num = (num - left * divs) / 10;
            divs /= 100;
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalindromeNumber9 isPalindromeNumber8 = new IsPalindromeNumber9();
        int num = 1232102321;
        boolean result = isPalindromeNumber8.isPalindrome(num);
        System.out.println(result);
    }
}
