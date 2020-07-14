package com.zcr.b_leetcode;

public class RemoveElement27 {
    
    public int removeElement(int[] nums, int val) {
        if (nums == null) {
            return 0;
        }
        int slow = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] != val) {
                nums[slow] = nums[i];
                slow++;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        RemoveElement27 removeElement27 = new RemoveElement27();
        int[] nums = {3,2,4,2,3};
        int result = removeElement27.removeElement(nums,3);
        System.out.println(result);
    }
}
