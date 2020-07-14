package com.zcr.b_leetcode;

/**
 * 81. Search in Rotated Sorted Array II
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 *
 * You are given a target value to search. If found in the array return true, otherwise return false.
 *
 * Example 1:
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 *
 * Example 2:
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 *
 * Follow up:
 * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 * Would this affect the run-time complexity? How and why?
 */

/**
 * 81、搜索旋转排序数组2
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *
 * 示例 1:
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 *
 * 示例 2:
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 *
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */
public class SearchInRotatedSortedArray281 {

    /**
     *
     * RotatedSortedArray:
     * 01234 -> 34012
     * Binary search二分法，二分法有一个固定的模板
     * 对于常规的序列：left mid right
     *
     * 对于Rotated Sorted Array：
     * Mid点在分隔线的左侧：nums[mid]>nums[left]
     * 又分为当nums[left]<target<nums[mid]或不
     *
     * Mid点在分割线的右侧：nums[mid]<nums[right]
     * 又分为当nums[mid]<target<nums[right]或不
     *
     * 当左右指针相邻的时候，已经是结束条件了
     *
     * 否则会造成死循环
     * 避免overflow
     *
     * 如果说有相同的数后，那么nums[start]可能等于nums[mid]
     * 只要把start++，那么mid也会随之移动
     * @param num
     * @param target
     * @return
     */
    public boolean sarchInRotatedSortedArray(int[] num,int target) {
        if (num == null || num.length == 0) {
            return false;
        }
        int left = 0;
        int right = num.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (num[mid] == target) {
                return true;
            }
            if (num[mid] > num[left]) {
                if (num[left] <= target && target <= num[mid]) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else if (num[mid] < num[left]){//这里把right改为了left
                if (num[right] >= target && target >= num[mid]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        if (num[left] == target) {
            return true;
        }
        if (num[right] == target) {
            return true;
        }
        return false;
    }

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[start] == nums[mid]) {
                start++;
                continue;
            }
            //前半部分有序
            if (nums[start] < nums[mid]) {
                //target在前半部分
                if (nums[mid] > target && nums[start] <= target) {
                    end = mid - 1;
                } else {  //否则，去后半部分找
                    start = mid + 1;
                }
            } else {
                //后半部分有序
                //target在后半部分
                if (nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {  //否则，去后半部分找
                    end = mid - 1;

                }
            }
        }
        //一直没找到，返回false
        return false;

    }


}
