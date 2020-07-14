package com.zcr.a_offer.g_recursionandloop.recursion;

/**
 * @author zcr
 * @date 2019/7/6-18:01
 */
public class RecursionTest {
    public static void main(String[] args) {
        //通过打印问题，回顾递归调用机制
        //test(5);

        //阶乘问题
        int res = factorial(3);
        System.out.println(res);
    }

    //打印问题
    public static void test(int n) {
        if (n > 2) {
            test(n -1);
        }
        System.out.println("n=" + n);
    }

    //阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1)*n;//factorial(2-1)*2    //factorial(3-1)*3=factorial(2-1)*2*3
        }
    }
}
