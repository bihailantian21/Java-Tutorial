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


    /**
     * double d=4.22;
     *
     * 小数转为整数分为：
     * 1.直接去除小数：Integer.parseInt(d);
     * 直接舍掉小数bai 比如float是4.7 转换du成int 后是4 而不是5 要四舍五入的zhi话转换前先加dao上0.5
     * 比如 int i ; double j = 4.7; i = (int)(j+0.5);
     * 2.4舍5入到整数：Math.round(d);
     * 3.取小数的最小整数(向下取整)：Math.floor(d);
     * 4.取小数的最大整数（向上取整）：Math.ceil(d);
     *
     * @param number
     * @return
     */
    private static int getApproximation(double number) {
        int re = (int)number;
        return  (number - re) >= 0.5 ? re + 1 : re;
    }

    private static int getApproximation2(double number) {
        return  (int)(number + 0.5);
    }
}
