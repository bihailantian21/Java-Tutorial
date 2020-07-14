package com.zcr.b_leetcode;

public class SearchInsertPosition35 {

    public int searchInsertPosition(int[] nums,int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target){
                left = mid;
            } else{
                return mid;
            }
        }
        if (nums[right] < target) {
            return right + 1;
        }
        if (target < nums[left]) {
            return left;
        }
        return right;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,3,4,5};
        int target = 6;
        SearchInsertPosition35 searchInsertPosition35 = new SearchInsertPosition35();
        int result = searchInsertPosition35.searchInsertPosition(nums,target);
        System.out.println(result);
    }
}
