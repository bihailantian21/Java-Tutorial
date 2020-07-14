package com.zcr.b_leetcode.ali;

import java.util.Scanner;

public class getMinimumTimeCost {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        int n = Integer.parseInt(line);
        int[][] area = new int[n][n];

        for (int i = 0; i < n; i++) {
            line = scanner.nextLine();
            String[] split = line.split(",");
            if (split.length != n) {
                throw new IllegalArgumentException("错误输入");
            }
            int j = 0;
            for (String num : split) {
                area[i][j++] = Integer.parseInt(num);
            }
        }

        int minimumTimeCost = getMinimumTimeCost(n, area);
        System.out.println(minimumTimeCost);
    }

    private static int getMinimumTimeCost(int n, int[][] area) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int t = dfs(area, 0, i, n, 0);
            // System.out.println("res:" + t);
            if (t < res && t != -1)
                res = t;
        }
        return res;
    }

    static int dfs(int[][] area, int i, int j, int n, int t) {
        // System.out.println(i + " " + j);
        if (i > n || j >= n)
            return -1;
        if (i == n)
            return t;
        if (j == n - 1 || area[i + 1][j] <= area[i][j + 1])
            return dfs(area, i += 2, j, n, t += area[i - 1][j]);
        return dfs(area, i, j += 2, n, t += area[i][j - 1]);
    }
}
