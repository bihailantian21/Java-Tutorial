package com.zcr.c_datastructure.h_sort;

public class NetherlandsFlag {

    /**
     * 荷兰国旗问题（给定一个数组arr，和一个数num，请把小于num的数放在数组的 左边，等于num的数放在数组的中间，大于num的数放在数组的右边。）
     * 要求额外空间复杂度O(1)，时间复杂度O(N
     *
     * 分成三部分，划分一个小于区域less、一个大于区域more
     *
     * 小于、等于、（待排序）、大于
     *
     * cur=num，直接遍历下一个
     * cur<num，和less的下一个位置的数进行交换,less+1，小于区域扩大一下，继续遍历cur++
     * cur>num，和more的上一个位置的数进行交换，more-1;大于区域扩大一下，继续考察换过来的cur
     *
     * 什么时候停止呢，当cur和more撞上了
     * 为什么小于cur要++，大于时cur不用++？
     * 小于区域在推着等于区域跑，小于区域换回来是等于区域的数，或者如果等于区域没有那就是自己和自己交换，不管是哪种情况，你都不需要再考虑要考察cur上的数了。
     * 但是大于区域拿回来的数是待定区域的数，所以还需要再考察一下。
     * @param arr
     * @param num
     */
    public static void netherlandsFlag(int[] arr,int num){
        int low = -1;
        int cur = 0;
        int high = arr.length;
        int temp;
        while (cur < high) {//结束条件
            if (arr[cur] < num) {//与小于区域的下一个数交换++less,当前的数换完之后继续往下遍历cur++
                low++;
                temp=arr[low];
                arr[low]=arr[cur];
                arr[cur]=temp;
                cur++;
            } else if (arr[cur] > num) {//大于位置的前一个数，--more,当前的数你别变，还要继续考察你
                high--;
                temp=arr[high];
                arr[high]=arr[cur];
                arr[cur]=temp;
            } else {
                cur++;
            }
        }
    }

}
