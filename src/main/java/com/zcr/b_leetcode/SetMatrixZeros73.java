package com.zcr.b_leetcode;
/**
 * 73. Set Matrix Zeroes
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 *
 * Example 1:
 * Input:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * Output:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 *
 * Example 2:
 * Input:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * Output:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * Follow up:
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */

/**
 * 73、矩阵置0
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例 1:
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 *
 * 示例 2:
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 *
 * 进阶:
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 */
public class SetMatrixZeros73 {

    /**
     * 如果一个位置为0，那么把它所在行所在列也都变成0。
     * 如果我们实时的去做，就会把后面也都变成0，那么遍历到后面的时候就会造成运算错误。
     * 我们用第一行第一列作为我们存储结果的临时位置。
     *
     * 当我们遇到0时，我们把这个位置在第一行对应的位置、在第一列对应的位置变成0。
     * 第二遍遍历的时候，只需要遍历第一行第一列，把相应的行数列数变为0。
     *
     * 特殊情况处理：
     * 处理左上角这个特殊位置：本身的第一行第一列该怎么处理呢?我们记录Firstrowzero、Firstcolzero
     * 看本身的第一行第一列是否需要变为0。
     * 然后就可以从1、1位置开始遍历。
     *
     * @param matrix
     */
    public void setMatrixZero(int[][] matrix) {
        int len1 = matrix.length;
        int len2 = matrix[0].length;
        if (matrix == null || len1 == 0 || len2 == 0) {
            return;
        }
        boolean firstRowZero = false;
        boolean firstColZero = false;
        //检查第一列，firstColZero第一列应该全部为0
        for (int i = 0; i < len1; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }
        //检查第一行，firstRowZero第一行应该全部为0
        for (int i = 0; i < len2; i++) {
            if (matrix[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < len1; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < len2; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < len2; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < len1; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        //第一行应该全部为0
        if (firstRowZero) {
            for (int i = 0; i < len2; i++) {
                matrix[0][i] = 0;
            }
        }
        //第一列应该全部为0
        if (firstColZero) {
            for (int i = 0; i < len1; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
