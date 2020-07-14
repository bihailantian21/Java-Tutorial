package com.zcr.c_datastructure.g_search;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/7/7-21:16
 */
public class InsertValueSearch {

    public static void main(String[] args) {

        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));

        int resIndex = insertValueSearch(arr,0,arr.length - 1,1);
        System.out.println(resIndex);

    }

    //编写插值查找算法
    //也要求数组是有序的
    public static int insertValueSearch(int[] arr,int left, int right, int findVal) {

        System.out.println("hello");//查找次数
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        //求出mid
        //自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {//向右查找递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归查找
            return insertValueSearch(arr, right, mid - 1, findVal);
        } else {
            return mid;
        }



    }
}
