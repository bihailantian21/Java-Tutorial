package com.zcr.b_leetcode;

/**
 * 85. Maximal Rectangle
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 * Example:
 * Input:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * Output: 6
 */

import java.util.Stack;

/**
 * 85、最大矩阵
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例:
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */
public class MaxmimuRectangles85 {

    /**
     * 给一个矩阵，由0和1组成，求由1组成的矩形的最大的面积
     * 一行一行的看，将当前行作为底，上面的作为高
     * 如果数字是0，则当前高度就是0，如果是1的话就往上看看最高高度能是多少
     * 第一行：得到的直方图是00100
     * 2：01211
     * 3：00322
     * 4：00030
     *
     *
     * 唯一不同的是：要从数字转换成直方图
     * Heights[][]:size=5
     * Heights[0][i]…height[1][i]
     * 对于每一行怎么确定呢？
     * [i]==0
     * [i]=1:=[i-1]+1
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[] heights = new int[col];
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            int area = largestRectangleArea(heights);
            max = Math.max(area,max);
        }
        return max;
    }

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

    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        int len = heights.length;
        for (int cur = 0; cur < len; cur++) {
            if (stack.isEmpty() || heights[cur] >= stack.peek()) {
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
