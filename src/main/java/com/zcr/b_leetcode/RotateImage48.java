package com.zcr.b_leetcode;

/**
 * 48. Rotate Image
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Note:
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * Example 2:
 * Given input matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 */

/**
 * 48、旋转图像
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1:
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * 示例 2:
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 */
public class RotateImage48 {

    /**
     * 1、首先旋转最外层的元素，然后再到里面，一层一层的往里面
     * 2、规律很明显：
     * 记录当前这一层的起始位置是什么？
     * 行：Top:0 bot:3 列：left:0 right:3
     * 3、看针对这特定的一层，位置是怎么交换的。
     * 先交换四个角的元素：
     * 定义一个temp：temp=[top][left]
     * 要把13移到1的位置：[top][left]=[bot][left]
     * 16->13[][]=[][]
     * 4->16[][]=[][]
     * Temp->4[][]=[][]
     * 4、再往里一位：9->2（1的位置往右移了一位[列+1]）[top][left+i]i[0,n-1)
     * n-1是不包括的，因为n-1的位置是角，已经换过了。
     * 其实我们对于4*4的矩阵，最外层是三个元素三个元素的换的。
     * 15->9
     * 8->15
     * 2->8[top+i][right]=[top][left+i](i=1)
     * 5、最外层换完之后，换内层：
     * Top++ left++ bot—right—
     * 剩下的交换规则一样
     *
     * N:每行/每列有多少个元素
     * 第一层循环：从最外层一层一层到最内层，通过n，n每次进行-2，四个值也要变化
     * 第二层循环：对于4*4矩阵，最外层就是三个数三个数进行交换，通过i
     * 进行四个数的交换从而得到旋转
     * @param matrix
     */
    public void rotateImage(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int top = 0;
        int bot = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int n = matrix.length;
        while (n > 1) {
            for (int i = 0; i < n - 1; i++) {
                int temp = matrix[top][left + i];
                matrix[top][left + i] = matrix[bot - i][left];
                matrix[bot - i][left] = matrix[bot][right - i];
                matrix[bot][right - i] = matrix[top + i][right];
                matrix[top + i][right] = temp;
            }
            top++;
            bot--;
            left++;
            right--;
            n -= 2;
        }
    }
}
