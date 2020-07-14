package com.zcr.c_datastructure.g_search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcr
 * @date 2019/7/7-20:39
 */
public class BinarySearch {

    public static void main(String[] args) {
        /*int arr[] = {1,8,10,89,1000,1234};//使用二分查找算法的前提是该数组是有序的
        int resIndex = binarySerach(arr,0,arr.length - 1,-1);//如果写一个没有的数，会出现死循环，死递归
        System.out.println(resIndex);*/
        int arr[] = {1,8,10,89,1000,1000,1000,1234};//使用二分查找算法的前提是该数组是有序的
        List<Integer> resIndex = binarySerach2(arr,0,arr.length - 1,1000);//如果写一个没有的数，会出现死循环，死递归
        System.out.println(resIndex);
    }

    //二分查找

    /**
     *
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return 如果找到返回下标，找不到返回-1
     */
    public static int binarySerach(int[] arr,int left,int right,int findVal) {

        /*//当left > right时，说明递归整个数组就是没有找到，就返回-1
        if (left > right) {
            return -1;
        }*/

        while (left <= right) {
            int mid = (left + right) / 2;
            int midVal = arr[mid];

            if (findVal > midVal) {//向右递归
                return binarySerach(arr,mid + 1,right,findVal);
            } else if (findVal < midVal) {//向左递归
                return binarySerach(arr,left,mid,findVal);
            } else {
                return mid;
            }
        }
        return -1;

    }

    //当存在多个相同的数值时，把所有的值都找到
    //思路分析：在找到mid值时，不要马上返回；
    // 向mid索引值的左边扫描，将所有满足1000的元素的下标都加入到一个集合中ArrayList
    //向mid索引值的右边扫描，将所有满足1000的元素的下标都加入到一个集合中ArrayList
    public static List<Integer> binarySerach2(int[] arr, int left, int right, int findVal) {

        /*//当left > right时，说明递归整个数组就是没有找到，就返回-1
        if (left > right) {
            return -1;
        }*/
        System.out.println("hello");


        while (left <= right) {
            int mid = (left + right) / 2;
            int midVal = arr[mid];

            if (findVal > midVal) {//向右递归
                return binarySerach2(arr,mid + 1,right,findVal);
            } else if (findVal < midVal) {//向左递归
                return binarySerach2(arr,left,mid,findVal);
            } else {
                List<Integer>  resIndexlist = new ArrayList<Integer>();
                int temp = mid - 1;
                while (true) {
                    if (temp < 0 || arr[temp] != findVal) {//退出
                        break;
                    }
                    //否则，就把temp放入到集合中
                    resIndexlist.add(temp);
                    temp--;//temp左移
                }

                resIndexlist.add(mid);

                temp = mid + 1;
                while (true) {
                    if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
                        break;
                    }
                    //否则，就把temp放入到集合中
                    resIndexlist.add(temp);
                    temp++;//temp左移
                }
                return resIndexlist;
            }
        }
        return new ArrayList<Integer>();

    }
}
