package com.zcr.a_offer.e_string;

/**
 * 49.把字符串转换成整数
 * 题目描述
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 * 输入描述:
 * 输入一个字符串,包括数字字母符号,可以为空
 * 输出描述:
 * 如果是合法的数值表达则返回该数字，否则返回0
 *
 * 示例1
 * 输入
 * +2147483647
 *     1a33
 * 输出
 * 2147483647
 *     0
 */
public class StrToInt49 {

    /**
     * 比较简单的模拟题。但是也要注意一些情况:
     * 前面的空字符要去掉；
     * 然后就是第一个如果是符号要判断；
     * 还有一个就是判断溢出(上溢出、下溢出)，这个问题可能有点麻烦，方式挺多，但是很多都有小问题；
     *
     *
     *
     *
     * 为什么int型最大的数是2147483647   -2147483648
     * 32位的电脑中，用二进制表示，最大的就是32个1，用十进制表示为2^32-1，大概40多亿(4294967295)
     * 对于有符号的，第一位用作表示正负(0，1)，最大的就是31个1，用十进制表示为2^31-1，大概20多个亿(2147483647)
     *
     *
     * @param str
     * @return
     */
    public int StrToInt(String str) {
        if (str == null || str.trim().equals(""))
            return 0;
        char[] chs = str.trim().toCharArray();//去除前面的空字符' '
        int res = 0;
        for (int i = (chs[0] == '-' || chs[0] == '+') ? 1 : 0; i < str.length(); i++) {
            if(chs[i] < '0' || chs[i] > '9') return 0; // < 48 || > 57
            int num = chs[i] - '0'; // chs[i] - 48
            int sum = res * 10 + num;
            if((sum - num)/10 != res) // 如果 sum超出范围了，这个表达式就回不到原来的res
                return 0;
            res = sum;
        }
        return chs[0] == '-' ? -res : res;
    }

    public static void main(String[] args) {
        String nums = "2147483650";
        StrToInt49 string2Integer8 = new StrToInt49();
        int result = string2Integer8.StrToInt(nums);
        System.out.println(nums);
    }
}
