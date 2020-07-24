package com.zcr.g_huawei;

import java.util.Scanner;


/**
 * 数字颠倒
 * 描述：
 *
 * 输入一个整数，将这个整数以字符串的形式逆序输出
 *
 * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
 *
 *
 * 输入描述:
 * 输入一个int整数
 *
 * 输出描述:
 * 将这个整数以字符串的形式逆序输出
 *
 * 示例1
 * 输入
 * 复制
 * 1516000
 * 输出
 * 复制
 * 0006151
 */
public class reverseNumber11 {

    /**
     * int转换为String
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            String str = String.valueOf(n);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = str.length() - 1; i >= 0; i--) {
                stringBuilder.append(str.charAt(i));
            }
            System.out.println(stringBuilder.toString());
        }
    }

    /**
     * 字符串反转：只有StringBuilder和StringBuffer才能进行字符串的反转
     * @param args
     */
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            String str = String.valueOf(n);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.reverse();
            System.out.println(stringBuilder.toString());
        }
    }

    /**
     * 12.字符串反转：
     * 写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
     *
     * 输入描述:
     * 输入N个字符
     *
     * 输出描述:
     * 输出该字符串反转后的字符串
     *
     * 示例1
     * 输入
     * 复制
     * abcd
     * 输出
     * 复制
     * dcba
     * @param args
     */
    public static void main3(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String n = scanner.nextLine();
            StringBuilder stringBuilder = new StringBuilder(n);
            System.out.println(stringBuilder.reverse().toString());
        }
    }
}
