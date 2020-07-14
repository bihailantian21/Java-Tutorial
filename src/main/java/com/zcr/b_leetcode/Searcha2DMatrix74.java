package com.zcr.b_leetcode;

/**
 * 74. Search a 2D Matrix
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 * Example 1:
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 *
 * Example 2:
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 */

/**
 * 74、搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 * 示例 1:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 */
public class Searcha2DMatrix74 {

    /**
     * 每一行中从左到右增大、下一行的第一个数比上一行的最后一个数大
     * 在矩阵中找到指定数字
     *
     * 从第一行的最后一个数开始找起，如果这个数大于它，那么第一行整行都被淘汰…第二行…
     * 否则，那么要找的数一定在这一行
     *
     * 用了两次二分法
     * 第一次:最后一列的数中找T，确定T是在哪行
     * 第二次：从某一行中找T
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int startrow = 0;
        int startcol = 0;
        int endrow = row - 1;
        int endcol = col - 1;
        int rownum = -1;
        while (startrow + 1 < endrow) {
            int midrow = startrow + (endrow - startrow) / 2;
            /*if (matrix[midrow][endcol] == target) {
                row = midrow;
            }*/
            if (matrix[midrow][endcol] < target) {
                startrow = midrow;
            } else {
                endrow = midrow;
            }
        }
        if (matrix[startrow][endcol] >= target) {
            rownum = startrow;
        } else if (matrix[endrow][endcol] >= target) {
            rownum = endrow;
        } else {
            return false;
        }

        while (startcol + 1 < endcol) {
            int mid = startcol + (endcol - startcol) / 2;
            if (matrix[rownum][mid] < target) {
                startcol = mid;
            } else {
                endcol = mid;
            }
        }
        if (matrix[rownum][startcol] == target || matrix[rownum][endcol] == target) {
            return true;
        }
        return false;
    }
}
