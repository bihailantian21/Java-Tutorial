package com.zcr.a_offer.c_array;

import java.util.ArrayList;

/**
 * 20、顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，
 * 如果输入如下4 X 4矩阵：
 *  0 1 2 3
 *0 1 2 3 4
 *1 5 6 7 8
 *2 9 10 11 12
 *3 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class PrintMatrixClockwise20 {

    /**
     * 包含多个循环、判断多个边界条件。所以需要把复杂问题分解成多个简单问题
     * 每次循环打印一个圈
     *
     * 题目本身很容易，但是代码容易写的很乱，所以这里考虑一种宏观思考问题的思想。
     * 使用矩阵分圈处理的方式，在矩阵中使用(ar,ac)表示左上角，(br,bc)表示矩阵的右下角；
     * 每次只需要通过这四个变量打印一个矩阵，然后用一个宏观的函数来调用打印的局部的函数，这样调理更加清晰；
     *
     *     0 1 2 3
     *  *0 1 2 3 4
     *  *1 5 6 7 8
     *  *2 9 10 11 12
     *  *3 13 14 15 16
     *
     *  1 2 3 4 5
     *  6 7 8 9 10
     *  11 12 13 14 15
     *  16 17 18 19 20
     *  21 22 23 24 25
     *
     *  01
     *0 12
     *1 34
     *
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        ArrayList<Integer> res = new ArrayList<>();
        int ar = 0;
        int ac = 0;
        int br = matrix.length - 1;
        int bc = matrix[0].length - 1;
        while (ar <= br && ac <= bc) {
            print(matrix,ar,ac,br,bc,res);
            ar++;
            ac++;
            br--;
            bc--;
        }
        return res;
    }

    public void print(int [][] matrix,int ar,int ac,int br,int bc,ArrayList<Integer> res) {
        if (ar == br) {//在同一行
            for (int i = ac; i <= bc; i++) {
                res.add(matrix[ar][i]);
            }
        } else if (ac == bc) {//在同一列
            for (int i = ar; i <= br; i++) {
                res.add(matrix[i][ac]);
            }
        } else {
            for (int i = ac; i < bc; i++) {
                res.add(matrix[ar][i]);
            }
            for (int i = ar; i < br; i++) {
                res.add(matrix[i][bc]);
            }
            for (int i = bc; i > ac; i--) {
                res.add(matrix[br][i]);
            }
            for (int j = br; j > ar; j--) {
                res.add(matrix[j][ac]);
            }
        }
    }
}
