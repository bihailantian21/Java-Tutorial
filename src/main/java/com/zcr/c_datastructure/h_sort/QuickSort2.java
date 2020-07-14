package com.zcr.c_datastructure.h_sort;

public class QuickSort2 {

    /**
     * 用荷兰国旗问题来改进快速排序
     * 把最后位置的数作为划分值，把小于x放左边，等于x的放中间，大于x放右边。小于等于区域的再这么干递归，大于区域的再这么干递归。等于x的就不用动了。
     * 经典快排其实是不划分的，其实是每次划分搞定一个数。然后再把小于等于区域递归，里面还有=x的数那要等到下一次的操作了。
     * 而我们这里是直接把=x的数全都找出来。要使用swap把最后一个x交换回来。
     *
     * 应用：
     * 给定一个数组arr，和一个数num，请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
     * 要求额外空间复杂度O(1)，时间复杂度O(N)。
     *
     * 小于、（待排序）、大于
     *
     * 规则：low:指的是从0到x都放在小于num的区域了，初始位置为-1
     * 然后遍历整个数组，找到小于num的就和x的下一个位置的数换一下，小于区域扩大一下；如果大于num的就不动继续遍历下一个
     * 分为两部分
     * cur<num，和less的下一个位置的数进行交换,less+1，小于区域扩大一下，继续遍历
     * cur>num，继续遍历下一个
     *
     * @param arr
     * @param num
     */
    public static void smallLeftBigRight(int[] arr,int num){
        //把小于等于num的数放在数组的左边，大于num的数放在数组的右边
        //思路：设置一个小于等于区cur=-1;遍历数组元素，当数组元素小于等于给定数时，
        // 将数组元素跟小于等于区x所指下一个数组元素进行交换。
        int low = -1;
        int cur = 0;
        int high = arr.length - 1;
        int temp;
        while (cur < high) {
            if (arr[cur] < num) {
                low++;
                temp=arr[low];
                arr[low]=arr[cur];
                arr[cur]=temp;
            }
            cur++;
        }
    }

}
