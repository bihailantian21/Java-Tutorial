package com.zcr.b_leetcode;

import java.util.Arrays;

public class SearchForARange34 {
    public int[] searchForARange(int[] nums, int target) {
        int[] result = {-1, -1};
        if(nums == null || nums.length == 0) {
            return result;
        }
        int left = 0;
        int right = nums.length - 1;
        int left_point = -1;
        int right_point = -1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) { //与下面唯一的不同就在于这里加了个=
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            left_point = left;
        } else {
            left_point = right;
        }
        left = 0;
        right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            right_point = left;
        } else {
            right_point = right;
        }
        result[0] = left_point;
        result[1] = right_point;
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 5, 5, 5, 5, 6,7,8,9};
        int target = 5;
        SearchForARange34 searchForARange34 = new SearchForARange34();
        int[] result = searchForARange34.searchForARange(nums,target);
        System.out.println(Arrays.toString(result));
    }
}
