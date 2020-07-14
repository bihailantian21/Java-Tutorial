package com.zcr.b_leetcode;

import java.util.HashMap;

/**
 *1. Two Sum
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum1 {

    /**
     * 暴力法
     * 时间O(n^2)
     * 空间O(1)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums,int target) {
        int[] result = new int[2];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * 为了对运行时间复杂度进行优化，我们需要一种更有效的方法来检查数组中是否存在目标元素。
     * 如果存在，我们需要找出它的索引。保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
     *
     * 1、首先得到差值
     * map:key:值 value:下标（作为返回结果）
     * 2、只用遍历一遍数组中的值，如果map中有差值，返回；否则将值和下标放入map。
     * 时间O(n)
     * 空间O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums,int target) {
        int[] result = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            int val = target - num;
            if (map.containsKey(val)) {
                result[0] = i;
                result[1] = map.get(val);
            } else {
                map.put(num,i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TwoSum1 twoSum1 = new TwoSum1();
        int[] nums = {1,2,3,4,5,6,7};
        int target = 9;
        int[] result;//这里作为结果的话不需要初始化
        result = twoSum1.twoSum1(nums,target);
        System.out.println(result[0] + "," + result[1]);
    }
}
