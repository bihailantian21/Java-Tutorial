package com.zcr.b_leetcode;

import java.util.Arrays;

/**
 * 41. First Missing Positive
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 * Input: [1,2,0]
 * Output: 3
 *
 * Example 2:
 * Input: [3,4,-1,1]
 * Output: 2
 *
 * Example 3:
 * Input: [7,8,9,11,12]
 * Output: 1
 *
 * Note:
 * Your algorithm should run in O(n) time and uses constant extra space.
 */

/**
 * 41、缺失的第一个正数
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 *
 * 示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 * 说明:
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 */
public class FirstMissingPositive41 {

    /**
     * 找到第一个缺少的正数。要求时间复杂度是O(n)和空间复杂度是O(1)。
     * 通用的思想：
     * 1、	boolean类型的array，下标index就代表遇到的正数，最大能到Integer.Max。
     * 下标为0的位置代表正数1、下标为1的位置代表正数2…下标为MAX-1的位置代表MAX。
     * 2、	把input数组for循环一遍[1,-1,2,-5,0,…]
     * 从array中找第一个为false的index。那么index+1就是我们要找的数。
     * 这种方法，用的memery比较大。或者使用num.length作为boolean的长度。
     *
     * 提交通不过leetcode，内存超过限制。
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int len = nums.length;
        boolean[] array = new boolean[Integer.MAX_VALUE];
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                array[nums[i] - 1] = true;
            }
        }
        int i = 0;
        for (; i < Integer.MAX_VALUE; i++) {
            if (array[i] == false) {
                break;
            }
        }
        return i + 1;
    }

    /**
     * 还是超出内存限制
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int len = nums.length;
        Arrays.sort(nums);
        int max = nums[len - 1];
        boolean[] array = new boolean[max];
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                array[nums[i] - 1] = true;
            }
        }
        int i = 0;
        for (; i < max; i++) {
            if (array[i] == false) {
                break;
            }
        }
        return i + 1;
    }

    /**
     * 方法二：
     * 如：
     *  0    1   2  3   4    5 6
     * [2,-1,3,1,0,-5,5]
     * [ 2, MAX, 3,1,MAX,MAX,5]
     *  -2,-MAX,-3,MAX,-MAX,MAX
     * 找出来MAX下标为3，则结果为4。
     *
     * 如：
     * 1 2 3 4
     * 则结果是len+1=5
     *
     * Conner case：当数字长度非常长，要返回的就是Integer.max。
     *
     * 1、	第一次遍历每个数，先把负数和0都变成正数的最大值，等于是一个标记，标记这些数我们是不用考虑的
     * 2、	第二遍循环，取出array中当前已有的值
     * 为什么要取正数：因为可能当前的数已经被invert了，但是我们只需要考虑invert之前的正数是什么。
     * 当这个正数小于数组长度时，就把这个数对应的位置（下标值）的值做一个invert变成负数，等于是一个标记，标记这个位置上对应的正数存在。【先做绝对值，再做负，因为担心它以前已经被标记过了。】
     * 3、第三遍循环：找出数组中第一个大于0的位置，将下标值+1就代表了要找的数。
     * 4、如果没有的话，就返回nums.length+1.
     *
     * 注：为什么检查每一个数的时候要取绝对值
     * 如3,1,4,2
     *      -4
     *  -3
     *         -2
     * 注：为什么是num<=len
     * 数组长度是4，如果有一个number是6，我们是不需要invert的，因为我们缺少的正整数最大有可能的是5。
     * @param nums
     * @return
     */
    public int firstMissingPositive3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0) {
                nums[i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < len; i++) {
            int num = Math.abs(nums[i]);
            if (num <= len) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return len + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1,-1,2,-5,0};
        int result;
        FirstMissingPositive41 firstMissingPositive41 = new FirstMissingPositive41();
        result = firstMissingPositive41.firstMissingPositive(nums);
        System.out.println(result);
    }
}
