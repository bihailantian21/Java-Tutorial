package com.zcr.a_offer.e_string;

/**
 * 42.左旋转字符串
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
 * 是不是很简单？OK，搞定它！
 */
public class LeftRotateString42_2 {

    /**
     * 思路一
     * 简单的做法:
     * 直接将[n, str.length()]先加到res字符串；
     * 然后将[0, n]之间的字符串加入到res即可；
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str,int n) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        result.append(str.substring(n));
        result.append(str, 0, n);
        return result.toString();
    }

    /**
     * 思路二
     * 剑指Offer的解法:
     * 将字符串分成两部分，第一部分记为前n个字符部分记为A，后面的部分记为B；
     * 其实这个题目就是要你从AB转换到BA；
     * 做法就是 (1)、先将A部分字符串翻转；(2)、然后将B字符串翻转；(3)、最后将整个字符串翻转；
     * 也就是(ATBT)T = BA；
     *
     * 如：abcXYZdef n=3
     * 分为A=abc   和B=XYZdef
     * 先反转A：cba 再反转B：fedZYX
     * 最后反转整个字符串：XYZdefabc
     *
     * 或者：
     * 先反转整个字符串：fedZYXcba
     * 然后再反转后面n个，再反转前面len-n个
     *         XYZdefabc
     *
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString2(String str,int n) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (n == str.length() || n == 0) {
            return str;
        }
        char[] chars = str.toCharArray();
        reverse(chars,0,n - 1);
        reverse(chars,n,str.length() - 1);
        reverse(chars,0,str.length() - 1);
        return new String(chars);//不能写成chars.toString()，因为toString 是输出没有经过重写的tosheng也就是十六进制啊的形势
    }

    public void reverse(char[] chars,int start,int end) {
        //如何反转？就是第一个和最后一个交换位置、第二个和倒数第二个交换位置...
        for (; start < end; start++,end--) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
        }
    }
}
