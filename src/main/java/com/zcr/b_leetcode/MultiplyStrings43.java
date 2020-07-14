package com.zcr.b_leetcode;

/**
 * 43. Multiply Strings
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Example 1:
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 *
 * Example 2:
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 * Note:
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

/**
 * 43、字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class MultiplyStrings43 {

    /**
     * 给了两个非负正数，是用string表示的而不是int，求乘积返回的也是string
     *
     * 基本的乘法规则：
     * 23*45=1035
     * 1、结果的最大长度=乘数1的长度+乘数2的长度
     * 先新建一个array记录结果
     * 2、一个一个数相乘
     * 取两个乘数的第二位相乘：3*5=15，有进位
     * 将15放在array中最后两位
     * 3、	取第一个乘数的第一位和第二个乘数的第二位相乘：2*5=10
     * 将10放在array中的倒数三位和倒数二位上
     * 4、	将这时候的两个array相加
     * 5、	第一个乘数的第二位和第二个乘数的第一位相乘：3*4=12
     * 将12放在array中的倒数三位和倒数二位上
     * 6、	将这时候的两个array相加
     * 7、第一个乘数的第一位和第二个乘数的第一位相乘：2*4=8
     * 将8放在array中的倒数三位上
     * 8、将这时候的两个array相加
     * 要注意的是：结果中的最高几位有可能不是一个有效的数，如是0的话
     * 要把前面的0去掉，只构造一个非0正数返回。
     *
     * 1、	两个for循环套起来，看两个乘数一个一个的数
     * 两个for都是从大到小进行循环，因为要从后面的位数看起，组合
     * 然后找一下相乘的结果放置的位置（相乘的结果一定是1位数或者两位数），相乘的结果的第一位放在i+j的位置，第二位放在i+j+1的位置
     * 还要加上之前结果的第一位。
     * 2、然后结果的第一位是余数、第二位是第二位之前的乘积加上商
     *
     * PowHigh PowLow
     *      01
     *      23
     *      01
     *      45
     *    1234
     *    0123
     *    ----
     *      15
     *     10（10+1=11 1+0=1 1）
     *     11
     *     12（12+1=13 1+1=2 3）
     *     23
     *    8（8+2=10 1+0=1 0）
     *    10
     *    1035
     * 注：如果结果是0035，要将前面两个0忽略掉，从第一个不是0的数字开始
     * 注：如果所有数都是0，那么就返回0。
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiplyStrings43(String num1,String num2) {
        if (num1.length() == 0 || num2.length() == 0) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int[] result = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int powHigh = i + j;
                int powLow = i + j + 1;
                mul += result[powLow];//是加上上一次计算的result[当前计算的powLow]
                result[powHigh] += mul / 10;//是加上上一次计算的result[当前计算的powHigh]
                result[powLow] = mul % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int res : result) {
            if (sb.length() == 0 && res == 0) {
                continue;
            } else {
                sb.append(res);
            }
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "23";
        String num2 = "45";
        String result;
        MultiplyStrings43 multiplyStrings43 = new MultiplyStrings43();
        result = multiplyStrings43.multiplyStrings43(num1,num2);
        System.out.println(result);

    }
}
