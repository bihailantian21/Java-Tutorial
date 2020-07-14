package com.zcr.b_leetcode;

import java.util.Arrays;

public class ThreeSumClosest16 {

    public int threeSumClosest(int[] nums,int target) {
        if (nums == null || nums.length == 0) {
            return target;
        }
        Arrays.sort(nums);
        int len = nums.length;
        int delta = nums[0] + nums[1] +nums[2] - target;
        for (int i = 0; i < len - 2;i++) {
            int left  = i + 1;
            int right = len - 1;
            while (left < right) {
                int newdelta = nums[i] + nums[left] + nums[right] - target;
                if (newdelta == 0) {
                    return target;
                }
                if (Math.abs(delta) > Math.abs(newdelta)) {
                    delta = newdelta;
                }
                if (newdelta < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return target + delta;
    }

    public static void main(String[] args) {
        ThreeSumClosest16 threeSumClosest16 = new ThreeSumClosest16();
        int[] nums = {-1,2,1,-4};
        int target = 1;
        int result = threeSumClosest16.threeSumClosest(nums,target);
        System.out.println(result);
    }
}
