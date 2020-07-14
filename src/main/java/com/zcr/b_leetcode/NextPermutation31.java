package com.zcr.b_leetcode;

import java.util.Arrays;

public class NextPermutation31 {

    public void nextPermutation31(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int len = nums.length;
        int replace = len - 2;
        while (replace >= 0) {
            if (nums[replace] < nums[replace + 1]) {
                break;
            }
            replace--;
        }
        if (replace < 0) {
            Arrays.sort(nums);
            return;
        }
        int lgrIdx = replace + 1;
        while (lgrIdx < len && nums[lgrIdx] < nums[replace]) {
            lgrIdx++;
        }
        int temp = nums[replace];
        nums[replace] = nums[lgrIdx - 1];
        nums[lgrIdx - 1] = temp;
        Arrays.sort(nums, replace + 1, len);
    }

    public static void main(String[] args) {
        NextPermutation31 next = new NextPermutation31();
        int[] array = {1,2,6,4,2};
        next.nextPermutation31(array);
        System.out.println(Arrays.toString(array));
    }
}
