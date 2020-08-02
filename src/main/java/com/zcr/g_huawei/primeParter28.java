package com.zcr.g_huawei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 素数伴侣
 * 题目描述
 * 题目描述
 * 若两个正整数的和为素数，则这两个正整数称之为“素数伴侣”，如2和5、6和13，它们能应用于通信加密。现在密码学会请你设计一个程序，从已有的N（N为偶数）个正整数中挑选出若干对组成“素数伴侣”，挑选方案多种多样，例如有4个正整数：2，5，6，13，如果将5和6分为一组中只能得到一组“素数伴侣”，而将2和5、6和13编组将得到两组“素数伴侣”，能组成“素数伴侣”最多的方案称为“最佳方案”，当然密码学会希望你寻找出“最佳方案”。
 *
 * 输入:
 *
 * 有一个正偶数N（N≤100），表示待挑选的自然数的个数。后面给出具体的数字，范围为[2,30000]。
 *
 * 输出:
 *
 * 输出一个整数K，表示你求得的“最佳方案”组成“素数伴侣”的对数。
 *
 *
 *
 * 输入描述:
 * 输入说明
 * 1 输入一个正偶数n
 * 2 输入n个整数
 *
 * 输出描述:
 * 求得的“最佳方案”组成“素数伴侣”的对数。
 *
 * 示例1
 * 输入
 * 复制
 * 4
 * 2 5 6 13
 * 输出
 * 复制
 * 2
 */
public class primeParter28 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            if (num > 100) {
                break;
            }
            int[] dict = new int[num];
            for (int i = 0; i < num; i++) {
                dict[i] = scanner.nextInt();
            }
            int result = 0;
            for (int i = 0; i < num; i++) {
                for (int j = i + 1; j < num; j++) {
                    if (isPrime(dict[i] + dict[j])) {
                        result++;
                    }
                }
            }

        }
    }

    private static boolean isPrime(int i) {
        for (int j = 2; j < i / 2; j++) {
            if (i % j != 0) {
                return false;
            }
        }
        return true;
    }
}
