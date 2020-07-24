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


    /**
     * Integer类的常用方法：
     *
     * (1)
     * java中Integer.toBinaryString()用于将十进制转成二进制输出。
     * 定义
     * String toBinaryString(int i)   //以2为基返回整数参数的字符串表示形式，表示为无符号整数
     * (2)
     * Java Integer.toHexString()十进制转成十六进制
     * (3)
     * Java Integer.toOctalString()十进制转成八进制
     * (4)
     * Java Integer.valueOf()返回Integer整型对象
     * java中Integer.valueOf()用于返回一个Integer（整型）对象，当被处理的字符串在-128和127（包含边界）之间时，返回的对象是预先缓存的。
     * 定义
     * Integer valueOf(int i) 返回一个表示指定的int值的 Integer 实例。
     * Integer valueOf(String s) 返回保存指定的String的值的 Integer 对象。
     * Integer valueOf(String s, int radix)
     * (5)
     * Java Integer.parseInt()字符串转换int
     * java中Integer.parseInt()一般用于将字符串转成基本数据类型int类型，在进行转换的时候parseInt每次都会构造一个常量值。
     * 定义
     * int parseInt(String s) //将字符串转换成int
     * parseInt(String s, int radix) //以第二个参数指定的基数将字符串参数转换成int
     * (6)
     * Java Integer转换double，float，int，long，string
     * java中Integer类可以很方便的转换成double，float，int，long，string等类型，都有固定的方法进行转换。
     * 方法
     * double doubleValue() //以 double 类型返回该 Integer 的值。
     * float floatValue() //以 float 类型返回该 Integer 的值。
     * int intValue() //以 int 类型返回该 Integer 的值。
     * long longValue() //以 long 类型返回该 Integer 的值。
     * String toString() //返回一个表示该Integer值的String对象。
     * (7)
     * Java Integer.equals()判断相等
     * Java中判断Integer是否相等可以用equals()或者“==”,“==”是进行地址及值比较，equals方法是数值比较，
     * 当Integer的值不在-128到127的时候，会新new一个对象，因此这个时候如果用“==”进行判断就会报错。
     * 定义
     * boolean equals(Object obj)  //比较此对象与指定对象
     * Integer.equals()传入Integer对象，只进行值是否相等判断。
     *
     *
     *
     *
     * @param args
     */
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
