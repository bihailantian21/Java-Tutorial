package com.zcr.a_offer.f_searchandsort;

import java.util.Arrays;

/**
 * 14、调整数组顺序，使得奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class PartitionArraybyOddandEven14 {

    /**
     * 第一反应：从头到尾扫描数组，如果遇到偶数，就拿出这个数字，使得后面的所有数字往前移动一位，把偶数放到最后面。
     * 时间：O(n^2)
     *
     * 这里我实现的方法有些问题，
     * {1,2,3,4,5,6,7}
     * {1,3,5,7,2,4,6}
     * 变成这个以后，for循环还是要继续...
     * 什么时候终止for循环呢？
     * @param array
     */
    public void reOrderArray(int [] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                int num = array[i];
                for (int j = i; j < array.length - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[array.length - 1] = num;
                i--;
            }
        }
    }

    public static void main(String[] args) {
        PartitionArraybyOddandEven14 partitionArraybyOddandEven14 = new PartitionArraybyOddandEven14();
        int[] array = {1,2,3,4,5,6,7};
        partitionArraybyOddandEven14.reOrderArray(array);
        System.out.println(Arrays.toString(array));


    }

    /**
     * 维护两个指针，一个指针向后，一个指针向前，如果第一个指针指的是偶数、第二个指针指的是奇数，那么就交换他们的位置。
     *
     * case通过率为0.00%
     * 用例:
     * [1,2,3,4,5,6,7]
     * 对应输出应该为:
     * [1,3,5,7,2,4,6]
     * 你的输出为:
     * [1,7,3,5,4,6,2]
     *
     * 类似与快排
     * 缺点：不能维持稳定性，不能保持相对位置
     * @param arr
     */
    public void reOrderArray2(int [] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            /*if (array[begin] % 2 == 0) {
                if (array[end] % 2 != 0) {

                } else {
                    end--;
                }
            } else {
                begin++;
            }*/

            //想到快速排序时候的思路：找到一个基准数，找到比它小的数，找到比它大的数，互换位置
            while (arr[l] % 2 != 0) {
                l++;
            }
            while (arr[r] % 2 == 0) {
                r--;
            }
            if (l >= r) {
                break;
            }
            //交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
    }

    /**
     * 首先这题出题的意思在于不使用额外空间实现。
     *
     * 其中LintCode373PartitionArraybyOddandEven不需要保证相对位置，可以使用下面的解法:
     * 第一个指针L从前面开始找，令一个R从后面开始找；
     * 第一个找到一个偶数，第二个找到一个奇数，就进行互换；
     * 终止条件就是L == R；
     * @param nums
     */
    // 不能保证原有的相对次序, 会改变原来的相对次序
    public void reOrderArray3(int[] nums) {
        int L = 0, R = nums.length - 1;
        while (L < R) {
            while (L < R && odd(nums[L])) L++;
            while (L < R && !odd(nums[R])) R--;
            if (L < R) {
                int t = nums[L];
                nums[L++] = nums[R];
                nums[R--] = t;
            }
        }
    }
    private boolean odd(int n) {
        return (n & 1) == 1 ? true : false;
    }


    /**
     * 这题必须要保证奇数和奇数、偶数和偶数的相对位置不变，所以不能使用双指针的方法，
     * 要想保持稳定性--相对顺序保持不变，就需要使用冒泡排序或者直接插入排序
     *
     * 先看类似冒泡排序解法
     * 冒泡排序是交换前面的一个数比后面的一个数大的情况，而这个题目是交换前面是偶数而后面是奇数的情况。
     * @param array
     */
    public void reOrderArray4(int[] array) {
        /*for (int end = array.length - 1; end > 0; end--) {//n-1次冒泡
            for (int i = 0; i < end; i++) {
                if (!odd(array[i]) && odd(array[i + 1])) {//把偶数往后面冒
                    int t = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = t;
                }
            }
        }*/
        for (int i = 0; i < array.length - 1; i++) {//n-1次冒泡
            for (int j = 0; j < array.length - 1 -i; j++) {
                if (!odd(array[j]) && odd(array[j + 1])) {//把偶数往后面冒
                    int t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
    }

    /**
     * 然后就是类似插入排序:
     * 步骤:
     * L从左向右遍历，找到第一个偶数；
     * 每次R从L+1开始向后找，直到找到第一个奇数；
     * 将[L,...,R-1]的元素整体后移一位，最后将找到的奇数放入L位置，然后L++；
     * 如果没有找到那样的arr[R]是奇数，那说明右边没有奇数了，可以break了；
     * @param array
     */
    public void reOrderArray5(int[] array) {
        int L = 0, R;
        while (L < array.length) {
            while (L < array.length && odd(array[L]))// 先找到第一个偶数
                L++;
            R = L + 1;
            while (R < array.length && !odd(array[R]))// 再在L 的后面开始找到第一个奇数
                R++;
            // 注意此时arr[L]是偶数　　arr[R]是奇数　-->将 [L,..R-1]中的数　向后移动一个位置
            if (R < array.length) {
                int t = array[R];
                for (int i = R - 1; i >= L; i--)
                    array[i + 1] = array[i];
                array[L] = t;
                L++;
            } else
                break;//查找失败 说明此时后面的都是偶数，可以退出了
        }
    }


}
