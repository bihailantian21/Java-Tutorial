package com.zcr.a_offer.c_array;

/**
 * 67.机器人的运动范围
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
 *
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class movingCount67 {

    /**
     *
     * 　与【Java】矩阵中的路径类似，也采用回溯法，先判断机器人能否进入(i,j)，再判断周围4个格子。这题返回的是int值。
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        if (rows <= 0 || cols <= 0 || threshold < 0)
            return 0;

        boolean[] isVisited = new boolean[rows * cols];
        int count = movingCountCore(threshold, rows, cols, 0, 0, isVisited);// 用两种方法试一下
        return count;
    }

    private int movingCountCore(int threshold, int rows, int cols, int row, int col, boolean[] isVisited) {
        if (row < 0 || col < 0 || row >= rows || col >= cols || isVisited[row * cols + col]
                || cal(row) + cal(col) > threshold)
            return 0;
        isVisited[row * cols + col] = true;
        return 1 + movingCountCore(threshold, rows, cols, row - 1, col, isVisited)
                + movingCountCore(threshold, rows, cols, row + 1, col, isVisited)
                + movingCountCore(threshold, rows, cols, row, col - 1, isVisited)
                + movingCountCore(threshold, rows, cols, row, col + 1, isVisited);
    }

    private int cal(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
