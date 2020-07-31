package com.zcr.g_huawei;


import java.util.Scanner;

/**
 * 取近似值
 * 题目描述
 * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
 *
 * 输入描述:
 * 输入一个正浮点数值
 *
 * 输出描述:
 * 输出该数值的近似整数值
 *
 * 示例1
 * 输入
 * 复制
 * 5.5
 * 输出
 * 复制
 * 6
 */
public class getApproximation7 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            double number = scanner.nextDouble();
            int result = getApproximation(number);
            System.out.println(result);
        }
    }



    private static int getApproximation(double number) {
        int re = (int)number;
        return  (number - re) >= 0.5 ? re + 1 : re;
    }

    private static int getApproximation2(double number) {
        return  (int)(number + 0.5);
    }
}
