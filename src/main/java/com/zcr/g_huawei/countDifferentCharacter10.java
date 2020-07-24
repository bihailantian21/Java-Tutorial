package com.zcr.g_huawei;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/**
 * 字符个数统计
 * 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
 * 输入
 * abaca
 * 输出
 * 3
 * 输入描述:
 * 输入N个字符，字符在ACSII码范围内。
 *
 * 输出描述:
 * 输出范围在(0~127)字符的个数。
 *
 * 示例1
 * 输入
 * 复制
 * abc
 * 输出
 * 复制
 * 3
 */
public class countDifferentCharacter10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            int len = str.length();
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < len; i++) {
                set.add(str.charAt(i));
            }
            System.out.println(set.size());
        }
    }
}
