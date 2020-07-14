package com.zcr.b_leetcode;

/**
 * 42. Trapping Rain Water
 * Given n non-negative integers representing an elevation map
 * where the width of each bar is 1, compute how much water it is able to trap after raining.
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case,
 * 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */

/**
 * 42、接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
 *
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class TrappingRainWater42 {

    /**
     * 给定一个整数数组，表示对应下标位置上的墙壁高度，求下雨后所有低洼处的面积总和。
     * 好理解的解法：要求的剪头所指下标的体积，就要求剪头左边最高的墙和剪头右边最高的墙的高度是多少，这两个里面的最小值决定了面积的高度。
     *
     * 例：
     * cur=[1 0 2 1 0 1 3 2 1 2]
     * 对于0来说，他左边的最大值是0
     * 对于1来说，他左边的最大值是1
     * 2 1
     * 3 2
     * 4 2
     * 5 2
     * 6 2
     * 7 3
     * 8 3
     * 9 3
     * 0 1 2 3 4 5 6 7 8 9
     * left[0,1,1,2,2,2,2,3,3,3]
     * 从左往右统计
     *
     * 对于9来说，它右边的最大值是0
     * 8 2
     * 7 2
     * 6 2
     * 5 3
     * 4 3
     * 3 3
     * 2 3
     * 1 3
     * 0 3
     * 0 1 2 3 4 5 6 7 8 9
     * right[3,3,3,3,3,3,2,2,2,0]
     * 从右往左统计
     *
     * 就可以计算出，对于任何一个位置它能存储的水的量是多少
     * 在第0位置，最小高度为0，能存储的水的量是0；
     * 在第1位置，最小高度为1，当前高度为0，能存储水量为(1-0)=1；
     * 在第2位置，最小高度为1，但是当前高度为2，当前高度大于最小高度，不能存储水量；
     * 3 2 1 （2-1）=1
     * 4 2 0 （2-0）=2
     * 5 2 1 （2-1）=1
     * 6 2 3  3>2=0
     * 7 2 2  2=2=0
     * 8 2 1  (2-1)=1
     * 9 0 2  2>0=0
     * Sum=6
     *
     * 总结：
     *       0 1 2 3 4 5 6 7 8 9
     * Left [0,1,1,2,2,2,2,3,3,3]
     * right[3,3,3,3,3,3,2,2,2,0]
     * cur   1 0 2 1 0 1 3 2 1 2
     * max   0 1 0 1 2 1 0 0 1 0
     * max=min(left,right) -cur
     * 所以如果cur大于最小高度的话，max为0
     * 时间：O(n)
     * 空间：O(n)
     *
     *
     *
     * @param nums
     * @return
     */
    public int trappingRainWater(int[] nums) {
        int len = nums.length;
        int[] leftmax = new int[len];
        int[] rightmax = new int[len];
        int[] max = new int[len];
        int leftm = 0;
        int rightm = 0;
        int minheight = 0;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            leftmax[i] = leftm;
            if (nums[i] > leftm) {
                leftm = nums[i];
            }
        }
        //System.out.println(Arrays.toString(leftmax));
        for (int j = len - 1; j >= 0; j--) {
            rightmax[j] = rightm;
            if (nums[j] > rightm) {
                rightm = nums[j];
            }
        }
        //System.out.println(Arrays.toString(rightmax));
        for (int i = 0; i < len; i++) {
            minheight = Math.min(leftmax[i],rightmax[i]);
            if (nums[i] < minheight) {
               max[i] = minheight - nums[i];
            } else {
                max[i] = 0;
            }
        }
        //System.out.println(Arrays.toString(max));
        for (int i = 0; i < len; i++) {
            sum += max[i];
        }
        return sum;
    }

    /**
     * 优化空间复杂度的思想：
     * 我们用两个指针，一个从左往右，一个从右往左
     * 确定哪个是确定的水量的，然后再往中间，直到两个指针相遇。
     *
     * 原理：
     * 我们左边有一个高的墙，右边有一个更高的墙，分别为leftmost，rightmost。这两个墙是我们找出来的。
     * 现在是leftmost比rightmost小，这时候要关注的是left指针，这个位置的水量就已经确定了：
     * 因为left低，所以left位置的水量最高只能达到letmost，不可能比这个更高了，那么有可能比这个更低吗？
     * 我们现在不知道left和right中间的数字。如果中间有比leftmost低的，有没有影响？没有，因为rightmost这个墙是非常高的，
     * 水量还是会变成和leftmost一样的高度。如果中间有比leftmost高的，有没有影响？没有，水量还是会变成和leftmost一样的高度。
     *
     * 为什么right这一点水量不是确定的？right的水量最高能达到和leftmost水量持平，有没有可能水量比它低？
     * 不可能。有没有可能水量比它高？有可能，如果left和right中间有比leftmost高的，那么right水量就会和这个中间的高的一样。
     *
     * 总结：右指针所能存储的水量是不确定的，左指针是确定的。
     * 所以我们要比较leftmost和rightmost中最小的那个值，那么left指针和right指针对应的位置的水量就已经确定了。这种方法就不需要额外的复杂度。
     * 1、	leftmost rightmost
     * 2、	left right
     * 3、	leftmost：为leftmost和当前left中较大的那一个
     * rightmost：为rightmost和当前right中较大的那一个
     * 4、	如果左边most的小，左边的水量就会确定下来（左边most-左边高度）
     * 如果右边most的小，右边的水量就会确定下来（右边most-右边高度）
     *
     * 例：
     *          0 11’ 2  3  |2’ 3’ 4’ 4  5  6  7
     * left     0  1  0  2  |      2  1  0  1
     * right    0  2        |1  2  3
     * leftmost 0  1  1  2  |2  2  2  2  2  2
     * rightmost0  2  2  2  |2  2  3  3  3  3
     * max         0  1  0  |1  0  0. 1  2  1  *
     * .（这时leftmost<rightmost，所以要看左边的）
     * *（这时候左指针和右指针重合了，停止循环）
     * max=leftmost-left
     * max=rightmost-right
     * 1<2所以这时候1这个位置的水量就已经确定了，max就等于leftmost-这个位置的高度。
     * 相等的话移动任何一个指针都可以，我们代码中写的是移动右指针。
     * 最后max=6
     * @param nums
     * @return
     */
    public int trappingRainWater2(int[] nums) {
        int len = nums.length;
        int max = 0;
        int leftmost = 0;
        int rightmost = 0;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            leftmost = Math.max(leftmost,nums[left]);
            rightmost = Math.max(rightmost,nums[right]);
            if (leftmost < rightmost) {
                max += leftmost - nums[left];
                left++;
            } else {
                max += rightmost - nums[right];
                right--;
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        int result;
        TrappingRainWater42 trappingRainWater42 = new TrappingRainWater42();
        result = trappingRainWater42.trappingRainWater2(nums);
        System.out.println(result);
    }
}
