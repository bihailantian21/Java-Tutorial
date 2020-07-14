package com.zcr.b_leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum15 {

    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return res;
        }
        int len = nums.length;
        Arrays.sort(nums);
        int i = 0;
        //先取一个做基数，从后面开始用双指针的方式找到这个基数的负数
        while (i < len - 2) {
            int base = nums[i];
            int left = i + 1;
            int right = len - 1;

            while (left < right) {
                int sum = base + nums[left] + nums[right];
                if (sum == 0) {
                    LinkedList<Integer> list = new LinkedList<Integer>();
                    list.add(base);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    left = moveright(nums,left + 1);
                    right = moveleft(nums,right - 1);
                } else if(sum < 0) {
                    //移动左，向右移动
                    left = moveright(nums,left + 1);
                } else {
                    //移动右，向左移动
                    right = moveleft(nums,right - 1);
                }
            }
            //i/base向右移动
            i = moveright(nums,i + 1);
        }
        return res;
    }
    //剔除重复的
    //基数也要剔除相同的
    //left和right也要剔除重复的
    public int moveleft(int[] nums,int right) {
        while (right == nums.length - 1 || (right >= 0 && nums[right] == nums[right + 1])) {
            right--;
        }
        return right;
    }
    public int moveright(int[] nums,int left) {
        while (left == 0 || (left < nums.length && nums[left] == nums[left - 1])) {
            left++;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,0,1,2,-1,-4};
        ThreeSum15 threeSum15 = new ThreeSum15();
        List<List<Integer>> result;
        result = threeSum15.threeSum(nums);
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            System.out.println(Arrays.toString(result.get(i).toArray()));
        }
    }

}
