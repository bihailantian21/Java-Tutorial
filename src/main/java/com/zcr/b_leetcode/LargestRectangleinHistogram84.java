package com.zcr.b_leetcode;

/**
 * 84. Largest Rectangle in Histogram
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 * Example:
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */

import java.util.Stack;

/**
 * 84、柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class LargestRectangleinHistogram84 {

    /**
     * 方法一：O(n^2)
     * [2,4,6,5,3]
     * 一个指针从左向右遍历:
     * 如果下一个比当前高度大，
     * 如果下一个比当前高度小，从当前位置开始往前移动，计算：
     * 6*1=6 4*2=8 2*3=6取他们之中最大的一个
     * 然后继续往后移动，计算：
     * 5*1=5 5*2=10 4*3=12 2*4=8
     * 然后继续先后移动，为空了，计算：
     * 3*1=3 3*2=6 3*3=9 3*4=12 2*5=10
     * 总结：
     * 1、	什么时候开始计算呢？当当前是最后一个了或者当前的高度比下一个大（下一个的高度比当前的高度小）
     * 为了减少一些运算量：如果后面的比前一个大，那么前一个一定不是最大的结果。
     * 因为至少4是可以乘2的。
     * 2、如果向前移动的时候，当前位置比后一个低：按照当前高度计算
     * 当前位置比后一个高：按照后一个的高度计算
     * 反正就是取比较低的一个高度
     *
     * 时间:O(n^2)
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = 0;
        int len = heights.length;
        for (int cur = 0; cur < len; cur++) {
            if (cur == len - 1 || heights[cur] > heights[cur + 1]) {
                int minHeight = heights[cur];
                for (int idx = cur; idx >= 0; idx--) {
                    minHeight = Math.min(minHeight,heights[idx]);
                    max = Math.max(max,minHeight * (cur - idx + 1));
                }
            }
        }
        return max;
    }

    /**
     * 方法二：用stack优化时间复杂度
     * 对一个特定位置的高度，找到左边比它小的位置、找到右边比它小的位置。那么以这个高度为矩形来计算的面积我们呢就已经确定了。
     * 那么我们就可以计算每一个高度，以它为高度最大的矩形面积是多少。
     *
     *         0 1 2 3 4 5
     *        [2,4,6,5,7,3]
     * Cur:    0 1 2 3 3 4 5 5 5 5   6
     * Stack:  0 0 0 0 0 0 0 0 0 0   (0)
     *           1 1 1 1 1 1 1(1)(5)
     *             2(2)3 3 3(3)
     *                   4(4)
     * index:        2     4 3 1 5    0
     * Right:        3     5 5 5 5    6
     * Leftmost:     1     3 1 0 0    -1
     * Area          6    7 15 16 15   12
     * 1、如果当前比前一个（stack.peek）高，把当前放入栈中
     * 2、如果当前比前一个（stack.peek）低，就找到了前一个的需要计算的高度的值（stack中pop）、
     * 左边界（stack中peak没有值了给-1）和右边界（当前）
     * 3、计算左边界的时候还要注意如果前一个和前一个的前面几个的高度相同，那么我们不需要等到下一次进行重复计算了，统一设置高度。
     * 4、每当计算了一遍的时候，然后保持cur不变（所以要先—再++才能保持不变），继续比较将cur与再前面一个进行比较，确定再前面的几个值的情况进行计算。
     * 5、所有的计算完毕以后，还需要针对第一个
     *
     * 如：
     * 5比6低，找到了6的高度（pop[2]6）、6的左边界（cur[3]）和右边界（peek[1]）
     * Area=6*((3-1)-1)=6  为什么-1：因为左边界、右边界，多了一个1
     *
     * 3比7低，找到了7的高度（pop[4]7）、7的左边界（cur[5]）和右边界（peek[3]）
     * Area=7*((5-3)-1)=7
     *
     * 3比5低，找到了5的高度（pop[3]5）、5的左边界（cur[5]）和右边界（peek[1]）
     * Area=5*((5-1)-1)=15
     *
     * 3比4低，找到了4的高度（pop[1]4）、4的左边界（cur[5]）和右边界（peek[0]）
     * Area=4*((5-0)-1)=16
     *
     * 对于最后一个3, 找到了3的高度（pop[5]3）、3的左边界（cur[6]）和右边界（peek[0]）
     * 3*((6-0)-1)=15
     *
     * 对于第一个2, 找到了2的高度（pop[0]2）、2的左边界（cur[6]）和右边界（peek[-1]）
     * 2*((6-(-1))-1)=12
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        int len = heights.length;
        for (int cur = 0; cur < len; cur++) {
            if (stack.isEmpty() || heights[cur] >= heights[stack.peek()]) {
                stack.push(cur);
            } else {
                int right = cur;
                int index = stack.pop();
                while (!stack.isEmpty() && heights[index] == heights[stack.peek()]) {
                    index = stack.pop();
                }
                int leftmost = (stack.isEmpty()) ? -1 : stack.peek();
                max = Math.max(max,heights[index] * (right - leftmost - 1));
                cur--;
            }
        }
        int rightmost = stack.peek() + 1;
        while (!stack.isEmpty()) {
            int index = stack.pop();
            int left = (stack.isEmpty()) ? -1 : stack.peek();
            max = Math.max(max,heights[index] * (rightmost - left - 1));
        }
        return max;
    }
}
