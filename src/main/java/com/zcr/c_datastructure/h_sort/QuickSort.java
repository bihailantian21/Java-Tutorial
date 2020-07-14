package com.zcr.c_datastructure.h_sort;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/7/7-12:21
 *
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,70,-1,900,4561};
        quickSort2(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    //快速排序
    public static void quickSort(int[] arr,int left,int right) {
        int  l = left;
        int r = right;
        int temp = 0;//临时变量
        int pivot = arr[(left + right) / 2];//中轴
        while (l < r) {//循环的目的是让比pivot小的值放到左边，比它大的值放到右边
            while (arr[l] < pivot) {//循环的目的是在左边一直找，找到大于等于pivot的值才退出
                l++;
            }
            while (arr[r] > pivot) {//循环的目的是在右边一直找，找到小于等于pivot的值才退出
                r--;
            }
            if (l >= r) {//说明pivot的左右两边的值已经按照左边全部是小于它，右边全部是大于它的顺序排好了
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个值arr[l] == pivot，r--
            if (arr[l] == pivot){
                r--;
            }
            //如果交换完后，发现这个值arr[r] == pivot，l++
            if (arr[r] == pivot){
                l++;
            }
        }

        //如果l== r，必须让l++,r--，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr,left,r);
        }

        //向右递归
        if (right > l) {
            quickSort(arr,l,right);
        }


    }

    //快速排序2

    /**
     * 快速排序的关键是：先在数组中选择一个数字，然后比它小的数字放在数组左边，比它大的数字放到数组右边
     *
     * O(nlogn)    O(nlogn)   O(n^2)   O(nlogn)  不稳定（交换）
     *
     * (分治）(挖坑填数)
     * 1、先从数列中取出一个数作为key值；
     * 2、将比这个数小的数全部放在它的左边，大于或等于它的数全部放在它的右边；
     * 3、对左右两个小数列重复第二步，直至各区间只有1个数。
     *
     * 经典快排：
     * 1、把第一个位置的数作为划分值，把小于等于x放左边，大于x放右边。小于等于区域的再这么干，大于区域的再这么干。
     * 2、经典快排的问题是：划分出来的小于区域和大于区域若总是不平衡的，那么每次都只搞定了一个数，时间复杂度成了O(N^2)最坏情况下。
     *
     * 快速排序的缺点：当数组本身就是有序的，则每次都是以最后一个数字作为基准，时间复杂度O(n^2)。
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort2(int[] arr,int low,int high) {
        int pivot;
        if (low >= high) {//重要！等于了就是结束了结束条件！
            return;
        }
        pivot = partition(arr,low,high);
        quickSort2(arr,low,pivot - 1);
        quickSort2(arr,pivot + 1,high);
    }
    /**
     * 对数组a中下标从low到high的元素，选取基准元素pivotKey，
     * 根据与基准比较的大小，将各个元素排到基准元素的两端。
     * 返回值为最后基准元素的位置
     *
     * 时间：O(n)
     */
    public static int partition(int[] a, int low, int high) {
        int pivotKey = a[low];  //用第一个元素作为基准元素
        while (low < high) { //两侧交替向中间扫描
            while (low < high && a[high] >= pivotKey)
                high--;
            //swap(a, low, high);  //比基准小的元素放到低端
            a[low] = a[high];
            while (low < high && a[low] <= pivotKey)
                low++;
            //swap(a, low, high);  //比基准大的元素放到高端
            a[high] = a[low];
        }
        a[low] = pivotKey;
        return low;     //返回基准元素所在位置
    }

    public static void swap(int[] a, int i, int j) {
        int temp;
        temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
}
