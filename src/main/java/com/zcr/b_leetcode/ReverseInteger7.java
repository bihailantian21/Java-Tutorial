package com.zcr.b_leetcode;

/**
 * 7. Reverse Integer
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 * Input: 123
 * Output: 321
 *
 * Example 2:
 * Input: -123
 * Output: -321
 *
 * Example 3:
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within
 * the 32-bit signed integer range: [−2^31,  2^31 − 1].
 * For the purpose of this problem, assume that your function returns 0
 * when the reversed integer overflows.
 */

/**
 * 7. 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *
 *  示例 2:
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 * 输入: 120
 * 输出: 21
 *
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 */
public class ReverseInteger7 {

    /**
     * 1、正数和负数是一样的
     * 2、处理overflow的情况：对于最大数2^31-1如果反转肯定会Overflow
     * 那么我们就反着来，看反过来是否结果一样
     * 3、反转数据比较简单：把个位数提取出来（%10）、把上一位的数字进行进位数(*10)
     * 依次循环，每次将数据除以10再次进行循环
     * 时间：O(1)
     * 空间：O(1)
     * @param num
     * @return
     */
    public int reverse(int num) {
        int rev = 0;
        while (num != 0) {
            int newrev = rev * 10 + num % 10;
            //处理overflow的情况
            if ((newrev - num % 10) / 10 != rev) {
                return 0;
            }
            rev = newrev;
            num = num / 10;
        }
        return rev;
    }

    public static void main(String[] args) {
        ReverseInteger7 reverseInteger7 = new ReverseInteger7();
        int num = (int) Math.pow(2,31) - 1;
        System.out.println(num);
        int result = reverseInteger7.reverse(num);
        System.out.println(result);
    }
}
