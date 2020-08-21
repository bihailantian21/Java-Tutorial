package com.zcr.g_huawei.it.d;


import java.util.Scanner;

/**

 *
 * 小明步长 l, 有一个 m x n 的矩阵， 1 表示能走 0 表示不能走，小明可以横着走竖着走，方向不限制，问小明能否从左上角走到右下角。
 *
 * 输入：
 *
 * 复制代码
 * 1
 * 2
 * 3
 * 4
 * 5
 *
2
3 5
1 0 1 0 0
0 1 1 0 1
0 0 1 0 1
 * 输出：1
 *
 *
 1 0 1 0 0 1
 0 1 1 0 0 0
 0 1 0 1 0 1
 * 第二题类似于青蛙跳菏叶，固定步长，用0-1矩阵来记录可以落脚的位置，BFS就能全部通过，记得加个约束不让跳回已经走过的位置就行。
 */
public class Walk2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int l = scanner.nextInt();
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] maxtrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxtrix[i][j] = scanner.nextInt();
            }
        }

        //约定：当map[i][j]为1时，表示该点没有走过，为0时代表墙，为2时表示通路可以走，为3表示该位置已经走过但是走不通
        System.out.println(setWay(maxtrix, l, m, n, 0, 0));
    }

    private static int setWay(int[][] maxtrix, int l, int m , int n, int i, int j) {
        if (maxtrix[m - 1][n - 1] == 2) {
            return 1;
        } else {
            if (maxtrix[i][j] == 1) {
                maxtrix[i][j] = 2;
                if (setWay(maxtrix, l, m, n, i, j + l) == 1) {
                    return 1;
                } else if (setWay(maxtrix, l, m, n, i, j - l) == 1) {
                    return 1;
                } else if (setWay(maxtrix, l, m, n, i + l, j) == 1) {
                    return 1;
                } else if (setWay(maxtrix, l, m, n, i - l, j) == 1) {
                    return 1;
                } else {
                    maxtrix[i][j] = 3;
                    return 0;
                }
            } else {
                return 0;
            }
        }


    }

}
