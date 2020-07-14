package com.zcr.b_leetcode;

/**
 * 33. Search in Rotated Sorted Array
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */

/**
 * 33、搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class SearchInRotatedSortedArray33 {

    /**
     * 0,1,2,3,4,5,6,7
     *               7
     *             6
     *           5
     *         4
     *       3
     *    2
     *  1
     * 0
     *
     * 0 1 2 3 4 5 6
     * 4,5,6,7,0,1,2
     *       7
     *     6
     *   5
     * 4
     * ____________
     *            2
     *          1
     *        0
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
     * @param num
     * @param target
     * @return
     */
    public int sarchInRotatedSortedArray(int[] num,int target) {
        if (num == null || num.length == 0) {
            return -1;
        }
        int left = 0;
        int right = num.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (num[mid] == target) {
                return mid;
            }
            if (num[mid] > num[left]) {
                if (num[left] <= target && target <= num[mid]) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else if (num[mid] < num[right]){
                if (num[right] >= target && target >= num[mid]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        if (num[left] == target) {
            return left;
        }
        if (num[right] == target) {
            return right;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] num = {3,2,1};
        int target = 3;
        SearchInRotatedSortedArray33 searchInRotatedSortedArray33 = new SearchInRotatedSortedArray33();
        int result = searchInRotatedSortedArray33.sarchInRotatedSortedArray(num,target);
        System.out.println(result);

    }

}
