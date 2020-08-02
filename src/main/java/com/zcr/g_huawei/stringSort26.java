package com.zcr.g_huawei;


import java.util.Scanner;

/**
 * 字符串排序
 * 题目描述
 * 编写一个程序，将输入字符串中的字符按如下规则排序。
 *
 * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
 *
 * 如，输入： Type 输出： epTy
 *
 * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 *
 * 如，输入： BabA 输出： aABb
 *
 * 规则 3 ：非英文字母的其它字符保持原来的位置。
 *
 *
 * 如，输入： By?e 输出： Be?y
 *
 *
 * 注意有多组测试数据，即输入有多行，每一行单独处理（换行符隔开的表示不同行）
 *
 *
 * 输入描述:
 * 输入字符串
 * 输出描述:
 * 输出字符串
 * 示例1
 * 输入
 * 复制
 * A Famous Saying: Much Ado About Nothing (2012/8).
 * 输出
 * 复制
 * A aaAAbc dFgghh: iimM nNn oooos Sttuuuy (2012/8).
 */
public class stringSort26 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            char[] chars = str.toCharArray();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                char c = (char)('A' + i);//A:65   a:97
                for (int j = 0; j < chars.length; j++) {
                    if (chars[j] == c || chars[j] == c - 'a' + 'A') {
                        result.append(chars[j]);
                    }
                }
            }

            for (int i = 0; i < chars.length; i++) {
                if ( !((chars[i] > 'A' && chars[i] < 'Z') || (chars[i] > 'a' && chars[i] < 'z'))) {
                    result.insert(i,chars[i]);
                }
            }
            System.out.println(result.toString());
            
        }
    }
}
