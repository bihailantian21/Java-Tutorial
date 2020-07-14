package com.zcr.c_datastructure.g_search;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/7/7-21:35
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        System.out.println(fibSearch(arr,89));
    }

    //必须是有序的
    //因为后面的mid = low + F(k - 1) - 1，需要使用到斐波那契数列，所以我们要先获取一个斐波那契数列
    //用非递归的方式得到（）也可以使用递归的方式
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契查找算法
    //使用非递归的方式
    public static int fibSearch(int[] a,int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid值
        int f[] = fib();//获取到斐波那契数列
        //获取到斐波那契数值的下标
        while (high > f[k] - 1) {
            k++;
        }

        //因为f[k]的值可能大于a数组的长度，因此需要使用Arrays类构造一个新的数组，并指向a
        int[] temp = Arrays.copyOf(a,f[k]);//不足的部分会使用0填充
        //实际上需要使用a数组的最后的数填充temp
        //temp = {1,8,10,89,1000,1234,0,0,0}->{1,8,10,89,1000,1234,1234,1234,1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        //使用循环，找到key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {//向数组的左边查找递归
                high = mid - 1;
                //为什么是k--？
                //1.全部元素=前面的元素+后边的元素
                //2.f[k] = f[k - 1] + f[k -2]
                //因为前面有f[k - 1]个元素，所以可以继续拆分f[k-1] = f[k - 2] + f[k -3]
                //即在f[k - 1]的前面继续查找，k--
                //即下次循环时，mid = f[k-1-1] - 1
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                //为什么是k-2?
                //1.全部元素=前面的元素+后边的元素
                //2.f[k] = f[k - 1] + f[k -2]
                //因为后面有f[k - 2]个元素，所以可以继续拆分f[k-2] = f[k - 3] + f[k -4]
                //即在f[k - 2]的前面面继续查找，k-2
                //即下次循环时，mid = f[k-1-2] - 1
                k -= 2;
            } else {
                //需要确定返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }

        }
        return -1;



    }
}
