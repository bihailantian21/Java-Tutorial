package com.zcr.b_leetcode;

public class ContainerWithMostWater12 {

    public int maxArea(int[] height) {
        int area = 0;
        int left = 0;
        int right = height.length - 1;
        // 移动值比较小的那一边，移动有瓶颈的那一边
        while (left < right) {
            area = Math.max(area,(right - left) * Math.min(height[left],height[right]));
            if (height[left] > height[right]) {
                right--;
            } else if(height[left] < height[right]) {
                left++;
            } else {
                left++;
                right--;
            }
        }
        return area;
    }

    public static void main(String[] args) {
        ContainerWithMostWater12 container = new ContainerWithMostWater12();
        int[] height = {2,1,3,4};
        int result = container.maxArea(height);
        System.out.println(result);
    }
}
