package com.zcr.c_datastructure.h_sort;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/7/7-10:35
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        shellSort2(arr);
    }

    //希尔排序
    public static void shellSort(int[] arr) {
        //使用逐步推导的方式编写

        //第一轮
        //因为第一轮排序是将10个数据分成了5组
        /*int temp = 0;
        for (int i = 5;i < arr.length;i++) {
            //遍历各组中所有的元素（共五组，每一组有2个元素）
            for (int j = i - 5; j >= 0 ; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第一轮：");
        System.out.println(Arrays.toString(arr));


        //第二轮
        //因为第二轮排序是将10个数据分成了5/2 = 2组
        for (int i = 2;i < arr.length;i++) {
            //遍历各组中所有的元素（共五组，每一组有2个元素）
            for (int j = i - 2; j >= 0 ; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第二轮：");
        System.out.println(Arrays.toString(arr));

        //第三轮
        //因为第三轮排序是将10个数据分成了2/2 = 1组
        for (int i = 1;i < arr.length;i++) {
            //遍历各组中所有的元素（共五组，每一组有2个元素）
            for (int j = i - 1; j >= 0 ; j -= 1) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第三轮：");
        System.out.println(Arrays.toString(arr));*/


        /**
         * 希尔排序    O(n^2)    O(nlogn)    O(1).   不稳定
         * 在要排序的一组数中，根据某一增量分为若干子序列，并对子序列分别进行直接插入排序。
         * 然后逐渐将增量减小,并重复上述过程。直至增量为1,此时数据序列基本有序,最后进行插入排序。
         * 规则：取一步长incre把表中全部数据分成incre个组，所有距离为incre的倍数的记录放在同一组中，
         * 在各组中进行直接插入排序、然后取第二个布长…、直到incre=1,所有记录已放在同一组中，再进行直接插入排序
         */
        //缩小增量排序，尽量把小的调到前面，大的调到后面，避免移动很多次的情况
        //希尔排序-对有序序列在插入时采用交换法
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2;gap > 0;gap /= 2) {
            for (int i = gap;i < arr.length;i++) {
                //遍历各组中所有的元素（共gap组，每一组有个元素）步长gap
                for (int j = i - gap; j >= 0 ; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明需要交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("第"+ (++count) +"轮：");
            System.out.println(Arrays.toString(arr));
        }
/*
        for(int gap = arr.length / 2;gap > 0;gap /= 2){
            for (int i = gap;i < arr.length - 1;i++) {
                if (arr[i] < arr[i - gap]) {
                    int temp = arr[i];
                    int j = i - gap;
                    for (;(j >= 0)&&(temp < arr[j]);j -= gap) {
                        arr[j + gap] = arr[j];
                    }
                    arr[j + gap] = temp;
                }
            }
        }
        return arr;*/

    }

    //希尔排序-对有序序列在插入时采用移动法
    public static void shellSort2(int[] arr) {

        /*//增量gap，并逐步缩小增量
        for (int gap = arr.length; gap > 0 ; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertVal = arr[insertIndex];
                if (arr[insertIndex] <arr[insertIndex - gap]) {
                    while (insertIndex - gap >= 0 && insertVal < arr[insertIndex - gap]) {
                        //移动
                        arr[insertIndex] = arr[insertIndex - gap];
                        insertIndex -= gap;
                    }
                    //退出while循环后，就给temp找到了插入的位置
                    arr[insertIndex] = insertVal;
                }
            }
            System.out.println("第"+ gap +"轮插入后：");
            System.out.println(Arrays.toString(arr));
        }*/


        //增量gap，并逐步缩小增量
        for (int gap = arr.length; gap > 0 ; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int insertVal = arr[i];
                int insertIndex = i - gap;
                ////给insertVal找到插入的位置
                if (insertVal <arr[insertIndex]) {
                    while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                        //移动
                        arr[insertIndex + gap] = arr[insertIndex];
                        insertIndex -= gap;
                    }
                    //退出while循环后，就给temp找到了插入的位置
                    arr[insertIndex + gap] = insertVal;
                }
            }
            System.out.println("第"+ gap +"轮插入后：");
            System.out.println(Arrays.toString(arr));
        }

            /*//第一趟
            for (int i = 5; i < arr.length; i++) {
                int insertVal = arr[i];
                int insertIndex = i - 5;
                while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                    //移动
                    arr[insertIndex + 5] = arr[insertIndex];
                    insertIndex -= 5;
                }
                //退出while循环后，就给temp找到了插入的位置
                arr[insertIndex + 5] = insertVal;
            }
            System.out.println("第"+ 1 +"轮插入后：");
            System.out.println(Arrays.toString(arr));

            //第二趟
            for (int i = 2; i < arr.length; i++) {
                int insertVal = arr[i];
                int insertIndex = i - 2;
                while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                    //移动
                    arr[insertIndex + 2] = arr[insertIndex];
                    insertIndex -= 2;
                }
                //退出while循环后，就给temp找到了插入的位置
                arr[insertIndex + 2] = insertVal;
            }
            System.out.println("第"+ 2 +"轮插入后：");
            System.out.println(Arrays.toString(arr));

        //第三趟
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                //移动
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex -= 1;
            }
            //退出while循环后，就给temp找到了插入的位置
            arr[insertIndex + 1] = insertVal;
        }
        System.out.println("第"+ 3 +"轮插入后：");
        System.out.println(Arrays.toString(arr));*/


        }

}





