package com.zcr.c_datastructure.h_sort;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/7/7-17:24
 */
public class RadixSort {

    public static void main(String[] args) {
        int arr[] = {53,3,542,748,14,214};
        radixSort(arr);
    }

    //基数排序
    public static void radixSort(int[] arr) {
        //第一轮（针对每个元素的个位进行排序处理）

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //为了防止在放数的时候数据溢出，每个一维数组的大小为arrr.length
        int[][] bucket = new int[10][arr.length];//经典的用空间换时间的算法

        //为了记录每个桶中实际存放了多少个数据，我们定义一个一维数据来记录各个桶每次放入的数据个数
        //可以这样理解，bucketElementCount[0]，记录的就是bucket[0]桶的放入数据的个数
        int[] bucketElementCounts = new int[10];
        int index = 0;

        //第一轮
        /*for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位
            int digitOfElement = arr[j] % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            //digitOfElement 哪个桶 ？
            //bucketElementCounts[digitOfElement] 桶中的哪个下标？
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入到原来的数组）
        index = 0;
        //遍历每一桶，并将桶中的数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {//或者是bucket.length
            //如果桶中有数据，我们才放入到原数组中
            if (bucketElementCounts[k] != 0){
                //循环该桶即第k个桶（即第k个一维数组），放入 
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++] = bucket[k][l];
                }
            }
            //第一轮处理后要将每个bucketElementCounts[k]置0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮对个数的排序处理arr = "+ Arrays.toString(arr));

        //第二轮
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位
            int digitOfElement = arr[j] /10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            //digitOfElement 哪个桶 ？
            //bucketElementCounts[digitOfElement] 桶中的哪个下标？
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入到原来的数组）
        index = 0;
        //遍历每一桶，并将桶中的数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {//或者是bucket.length
            //如果桶中有数据，我们才放入到原数组中
            if (bucketElementCounts[k] != 0){
                //循环该桶即第k个桶（即第k个一维数组），放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++] = bucket[k][l];
                }
            }
            //第二轮处理后要将每个bucketElementCounts[k]置0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第二轮对个数的排序处理arr = "+ Arrays.toString(arr));

        //第三轮
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位
            int digitOfElement = arr[j] / 100;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            //digitOfElement 哪个桶 ？
            //bucketElementCounts[digitOfElement] 桶中的哪个下标？
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入到原来的数组）
        index = 0;
        //遍历每一桶，并将桶中的数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {//或者是bucket.length
            //如果桶中有数据，我们才放入到原数组中
            if (bucketElementCounts[k] != 0){
                //循环该桶即第k个桶（即第k个一维数组），放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++] = bucket[k][l];
                }
            }
            //第三轮处理后要将每个bucketElementCounts[k]置0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第三轮对个数的排序处理arr = "+ Arrays.toString(arr));

*/
        //一共进行多少轮？有多少位进行多少轮
        //先得到数组中最大的数的位数
        int max = arr[0];//假设第一个数就是最大数
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        //这里我们使用循环
        for (int i = 0 , n = 1; i < maxLength; i++,n *= 10) {
            //针对每一轮的对应的位数进行排序处理，个位、十位、百位、千位、...
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位的数值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                //digitOfElement 哪个桶 ？
                //bucketElementCounts[digitOfElement] 桶中的哪个下标？
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入到原来的数组）
            index = 0;
            //遍历每一桶，并将桶中的数据放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {//或者是bucket.length
                //如果桶中有数据，我们才放入到原数组中
                if (bucketElementCounts[k] != 0){
                    //循环该桶即第k个桶（即第k个一维数组），放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr中
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后要将每个bucketElementCounts[k]置0
                bucketElementCounts[k] = 0;
            }
            System.out.println("第"+(i + 1)+"轮数的排序处理arr = "+ Arrays.toString(arr));

        }


    }
}
