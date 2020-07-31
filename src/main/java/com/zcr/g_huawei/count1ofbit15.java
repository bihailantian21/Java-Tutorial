package com.zcr.g_huawei;


import java.util.Scanner;

/**
 * 求int型正整数在内存中存储时1的个数
 * 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
 *
 * 输入描述:
 *  输入一个整数（int类型）
 *
 * 输出描述:
 *  这个数转换成2进制后，输出1的个数
 *
 * 示例1
 * 输入
 * 复制
 * 5
 * 输出
 * 复制
 * 2
 */
public class count1ofbit15 {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String s = Integer.toBinaryString(n);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                result++;
            }
        }
        System.out.println(result);
    }

    /**
     * 用位运算的方法
     * @param args
     */
    public static void main2(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int count = 0;
        while(n>0){
            if((n&1)>0){
                count++;
            }
            n=n>>1;
        }
        System.out.println(count);
    }
}
