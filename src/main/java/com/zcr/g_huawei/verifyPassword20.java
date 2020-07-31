package com.zcr.g_huawei;

import java.util.Scanner;

/**
 * 密码验证合格程序
 * 题目描述
 * 密码要求:
 *
 *
 *
 *
 * 1.长度超过8位
 *
 *
 *
 *
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 *
 *
 *
 *
 * 3.不能有相同长度大于等于2的子串重复
 *
 *
 *
 *
 *
 * 输入描述:
 * 一组或多组长度超过2的子符串。每组占一行
 *
 * 输出描述:
 * 如果符合要求输出：OK，否则输出NG
 *
 * 示例1
 * 输入
 * 复制
 * 021Abc9000
 * 021Abc9Abc1
 * 021ABC9000
 * 021$bc9000
 * 输出
 * 复制
 * OK
 * NG
 * NG
 * OK
 */
public class verifyPassword20 {

    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            int length = str.length();
            if (length <= 8) {
                System.out.println("NG");
                continue;
            }
            int capitalLetter = 0;
            int lowercase = 0;
            int number = 0;
            int otherchar = 0;

            for (int i = 0; i < length; i++) {
                char c = str.charAt(i);
                if (c >= 48 && c <= 57 ) {
                    number = 1;
                } else if (c >= 65 && c <= 90 ) {
                    capitalLetter = 1;
                } else if (c >= 97 && c <= 122 ) {
                    lowercase = 1;
                } else {
                    otherchar = 1;
                }
            }

            if (capitalLetter + lowercase + number + otherchar < 3) {
                System.out.println("NG");
                continue;
            }
            
            if (hasSameSubstring(str)) {
                System.out.println("NG");
                continue;
            }

            System.out.println("OK");
        }
    }

    private static boolean hasSameSubstring(String str) {//021Abc9Abc1
        for (int i = 0; i < str.length() - 1; i++) {
            String str1 = str.substring(i,i+2);
            for (int j = i + 1; j < str.length() - 1; j++) {
                String str2 = str.substring(j,j+2);
                if (str1.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasSameSubstring2("021Ab9Ab91"));
    }


    /**
     * 这个才是对的
     * 看来重点是3
     * @param str
     * @return
     */
    private static boolean hasSameSubstring2(String str) {//021Abc9Abc1
        for (int i = 0; i < str.length() - 3; i++) {
            String str1 = str.substring(i,i+3);
            String str2 = str.substring(i+3,str.length());
            if (str2.contains(str1)) {
                return true;
            }
        }
        return false;
    }

}


