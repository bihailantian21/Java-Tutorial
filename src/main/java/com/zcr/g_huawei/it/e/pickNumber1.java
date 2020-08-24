package com.zcr.g_huawei.it.e;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 第一题
 * 给你N行M列的人，他们是一个方阵。左上角是(0,0)，右下角是(n-1, m-1)，最外圈的人顺时针报数，当他们报的数个位是7并且十位是奇数，
 * 就选出来。外圈报完了内圈接着报，一直到所有人都报完，问最后选出来的是哪些。[[x1,y1], [x2,y2]...]。
 *
 * 要求是10<=n,m<=1000，如果输入不合法，那么就是返回空数组
3  4
1  2  3  4
10 11 12 5
9  8  7  6

6  4
1  2  3  4
10 11 12 5
9  8  7  6
1  2  3  4
10 11 12 5
9  8  7  6

 *
 * 输入
 * 10 10
 *
 * 输出
 * [[7,9],[1,1],[8,2],[7,5],[4,4]]
 */
public class pickNumber1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<List<Integer>> result = new ArrayList<>();
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        if (n > 10 || m > 1000) {
            System.out.println(result.toArray());
        }
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        for (int k = 0; k < Math.min(m,n); k++) {
            for (int j = 0 + k; j < n - 0 - k; j++) {//列增加（列在初始和末尾都受k的限制）
                //if (isPick(matrix[0 + k][j])) {//行固定，与k有关
                    List li = new ArrayList();
                    li.add(0 + k);
                    li.add(j);
                    result.add(li);
                //}
            }
            for (int i = 1 + k; i < m - 1 - k ; i++) {//行增加（行在初始和末尾都受k的限制）
                //if (isPick(matrix[i][m - k])) {//列固定，与k有关
                    List li = new ArrayList();
                    li.add(i);
                    li.add(m - k);
                    result.add(li);
                //}
            }
            for (int j = n - 1 - k; j >= 0 + k; j--) {//列递减（列在初始和末尾都受k的限制）  这里一定要注意，从小到大0~<n(n个)，从大到小n-1~>-1(才是n个，或者是>=0，如果到>0的话，就少一个)
                //if (isPick(matrix[m  - 1　- k][j])) {//行固定，与k有关
                    List li = new ArrayList();
                    li.add(m - 1- k);
                    li.add(j);
                    result.add(li);

                //}
            }
            for (int i = m - 1 - k - 1; i > k; i--) {//行递减（行在初始和末尾都受k的限制）
                //if (isPick(matrix[i][0 +k])) {//列固定，与k有关
                    List li = new ArrayList();
                    li.add(i);
                    li.add(0 + k);
                    result.add(li);
                //}
            }
        }
        System.out.println(Arrays.toString(result.toArray()));
    }

    public static Boolean isPick(int number) {
        int ge = number % 10;
        int shi = number / 10;
        if((ge == 7) && (shi % 2 == 1)) {
            return true;
        }
        return false;
    }
}
