package com.zcr.c_datastructure.h_sort;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/7/7-9:53
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {101,34,119,1,-1,89};
        insertSort(arr);
        /*int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 80000);//会生成一个0~80000的随机数
        }

        System.out.println("排序前：");
        //System.out.println(Arrays.toString(arr));
        long l1 = System.currentTimeMillis();
        insertSort(arr);
        long l2  = System.currentTimeMillis();
        System.out.println(l2 - l1);
        System.out.println("排序后：");*/
        System.out.println(Arrays.toString(arr));
    }

    //插入排序
    public static void insertSort(int[] arr) {
        //使用逐步推导的方式讲解

        //第一轮 [34 101] 119 1
        //定义待插入的数
        /*int insertVal = arr[1];
        int insertIndex = 0;//1-1，即arr[1]的前面这个数的下标
        //给insertVal找到插入的位置
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {//保证在找插入位置时不越界，保证待插入的数还没有找到插入位置
            //当前的arr[insertIndex]后移
            arr[insertIndex + 1] = arr[insertIndex];//101 101 119 1
            insertIndex--;//与前面那个数比较
        }
        //当退出while循环时，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第一轮插入后：");
        System.out.println(Arrays.toString(arr));

        //第二轮 [34 101 119 ]  1
        //定义待插入的数
        insertVal = arr[2];
        insertIndex = 1;//2-1，即arr[1]的前面这个数的下标
        //给insertVal找到插入的位置
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {//保证在找插入位置时不越界，保证待插入的数还没有找到插入位置
            //当前的arr[insertIndex]后移
            arr[insertIndex + 1] = arr[insertIndex];//
            insertIndex--;//与前面那个数比较
        }
        //当退出while循环时，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第二轮插入后：");
        System.out.println(Arrays.toString(arr));

        //第二轮 [1 34 101 119]
        //定义待插入的数
        insertVal = arr[3];
        insertIndex = 2;//3-1，即arr[1]的前面这个数的下标
        //给insertVal找到插入的位置
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {//保证在找插入位置时不越界，保证待插入的数还没有找到插入位置
            //当前的arr[insertIndex]后移
            arr[insertIndex + 1] = arr[insertIndex];//
            insertIndex--;//与前面那个数比较
        }
        //当退出while循环时，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第三轮插入后：");
        System.out.println(Arrays.toString(arr));*/

        /**
         * 冒泡排序和选择排序在工程上基本见不到了，只是教学什么是复杂度。插入排序很有用。
         * 比如说数组一上来就有序，对于冒泡排序和选择排序，是严格的O(N^2)，是因为即便是数组有序，因为这两种排序流程已经定好了，所以还是要走一遍，只是不交换了而已，所以说算法复杂度与数据状况没关系。
         * 对于插入排序，如果刚开始就是有序的，整个插入的代价是O（N）,因为只有i往后移动，j根本不需要往前移动。
         * 如果是逆序的，i往后到底，j往前到顶，O（N^2）。
         * 即插入排序的算法复杂度与数据状况有关系。
         * 最好、最差、平均,所以说算法复杂度一律按最差的估计。
         *
         * 直接插入排序O(n^2)     O(n)  O(n^2)   O(1)    稳定（遇到一个数小于前一个数才插进去。等于大于的时候不往前插）
         * 在要排序的一组数中，假定前i-1个数已经排好序，现在将第i个数插到前面的有序数列中，使得这i个数也是排好顺序的。如此反复循环，直到全部排好顺序。
         * 1位置上的数和0位置上数比较，如果1位置小就交换，此时0~1范围上排好了。
         * 2位置上的数和1位置上数比较，如果2位置小就交换，此时再拿现在1位置上的数和0位置上的数比较，如果1位置上的数小就交换，此时0~2范围上排好了。
         * 依次比较交换3和2 2和1 1和0，0~3范围上排好了。
         * 就像你手里攥着很多牌，新抓了一张牌，看能滑到哪个位置，插进去。在数组里面就是看能插到哪个位置，不能往前了，就停。
         * 规则：一共进行n-1趟、如果遇到一个数小于前一个数，则将该数插入到左边所有比自己大的数之前，也就是说，将该数前面的所有更大的数字都后移一位，空出来的位置放入该数。
         */
        //进行数组长度-1次（因为第一个为基准，给后面的几个数找位置）
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;//i-1，即arr[1]的前面这个数的下标
            //给insertVal找到插入的位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {//保证在找插入位置时不越界，保证待插入的数还没有找到插入位置
                //当前的arr[insertIndex]后移
                arr[insertIndex + 1] = arr[insertIndex];//101 101 119 1
                insertIndex--;//与前面那个数比较
            }
            //当退出while循环时，说明插入的位置找到，insertIndex + 1
            //判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
            //System.out.println("第"+ i +"轮插入后：");
            //System.out.println(Arrays.toString(arr));

            /*for (int i = 0;i < arr.length - 1;i++) {
                if (arr[i + 1] < arr[i]) {
                    int temp = arr[i + 1];
                    int j = i;
                    for (;(j >= 0)&&(temp < arr[j]);j--) {
                        arr[j + 1] = arr[j];
                    }
                    arr[j + 1] = temp;
                }
            }
            return arr;*/

        }


    }
}
