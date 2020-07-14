package com.zcr.b_leetcode;

/**
 * 8. String to Integer (atoi)
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until the first non-whitespace
 * character is found. Then, starting from this character, takes an optional initial plus or minus sign
 * followed by as many numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed
 * integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values,
 * INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 *
 * Example 1:
 * Input: "42"
 * Output: 42
 *
 * Example 2:
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 *              Then take as many numerical digits as possible, which gets 42.
 *
 * Example 3:
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 *
 * Example 4:
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 *              digit or a +/- sign. Therefore no valid conversion could be performed.
 *
 * Example 5:
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 *              Thefore INT_MIN (−231) is returned.
 */

/**
 * 8.字符串转换整数(atoi)
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 *
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 *
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，
 * 则你的函数不需要进行转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，
 * 请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 * 示例 1:
 * 输入: "42"
 * 输出: 42
 *
 * 示例 2:
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 *
 * 示例 3:
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 *
 * 示例 4:
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 *
 * 示例 5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 */
public class String2Integer8 {

    /**
     * 看到字符串那就先转换为char[]。
     * 这道题,就从左往右开始遍历。寻找数据的起始值。情况如下。
     * 1.遇到空格则跳过。
     * 2. 非空格情况
     * 2.1
     * 符号'-'或者'+'则记录为正还是负任然后开始处理数据转换，将当前index++之后开始处理数据。
     * 2.2
     * 符号不是数字则返回0.是数字则开始记录当前index，开始处理数字。
     * 寻找到起始值之后开始向右遍历转换数字。
     * 数字获取可以通过
     * char转换为int 可以用 '1' - 48 = 1 来实现。并将计算值乘上1 或者 -1。
     * pop = (charList[i] -48) * zf; 其中zf为之前根据'+'或者'-'。默认为1，
     * 叠加结果为。
     * rev = rev * 10 + pop;
     *
     *
     * 但是执行这一步之前需要判断，rev是否移除才能进行相加。情况。
     * 1.叠加上去的结果不能大于Integer.MAX_VALUE，否则返回Integer.MAX_VALUE
     * 2.且不能小于Integer.MIN_VALUE，否则返回Integer.MIN_VALUE
     * 则进行处理。
     * 若大于。
     * if(rev>Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)){
     * return Integer.MAX_VALUE;
     * }
     *
     * if(rev<Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)){
     * return Integer.MIN_VALUE;
     * }
     * 循环结束或者遇到非数字情况。则直接return rev;即可。
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        int rev = 0;
        char[] charArray = str.toCharArray();
        int len = str.length();
        int sign = 1;
        int  i = 0;
        for (; i < len; i++) {
            if (charArray[i] == ' ') {
                continue;
            } else if (charArray[i] == '-') {
                sign = -1;
                i++;
                break;
            } else if (charArray[i] == '+') {
                i++;
                break;
            } else if (charArray[i] < '0' || charArray[i] > '9') {
                return 0;
            } else {
                break;
            }
        }

        if (i == len) {
            return 0;
        }

        for (; i < len; i++) {
            if (charArray[i] < '0' || charArray[i] > '9') {
                return rev;
            }
            int character = (charArray[i] - 48) * sign;
            if (rev > Integer.MIN_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && character > 7)) {
                return Integer.MAX_VALUE;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && character < -8)) {
                return Integer.MIN_VALUE;
            }
            rev = rev * 10 + character;
        }

        return rev;

    }

    public static void main(String[] args) {
        String nums = "2147483649";
        String2Integer8 string2Integer8 = new String2Integer8();
        int result = string2Integer8.myAtoi(nums);
        System.out.println(nums);
    }
}
