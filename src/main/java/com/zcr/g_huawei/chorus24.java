package com.zcr.g_huawei;


import java.util.Scanner;

/**
 * 合唱队
 * 题目描述
 * 计算最少出列多少位同学，使得剩下的同学排成合唱队形
 *
 * 说明：
 *
 * N位同学站成一排，音乐老师要请其中的(N-K)位同学出列，使得剩下的K位同学排成合唱队形。
 * 合唱队形是指这样的一种队形：设K位同学从左到右依次编号为1，2…，K，他们的身高分别为T1，T2，…，TK，   则他们的身高满足存在i（1<=i<=K）使得T1<T2<......<Ti-1<Ti>Ti+1>......>TK。
 *
 * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 *
 *
 * 注意不允许改变队列元素的先后顺序
 * 请注意处理多组输入输出！
 *
 * 输入描述:
 * 整数N
 *
 * 输出描述:
 * 最少需要几位同学出列
 *
 * 示例1
 * 输入
 * 复制
 * 8
 * 186 186 150 200 160 130 197 200
 * 输出
 * 复制
 * 4
 */
public class chorus24 {

    /**
     * 0   1   2   3   4   5   6   7
     * 186 186 150 200 160 130 197 200
     * 0    0   0   1   0   0   1   2
     * 0    1   0   2   1   0   0   0
     * 0    1   0   3   1   0   1   2
     * 不对，这样不对，不是说相邻的才算数，间隔着上升也算
     * 而且应该从1开始计数
     * 1    1   1    2   2   1   3   4
     * 3    3   2    3   2   1   1   1
     * 4    4   2    5   4   2   4   5
     * 3    3   1    4   3   1   3   4
     *
     *
     * left[i]表示以i作为中间节点的话，留下了多少人       n-left[i]表示去除的人
     * l[i]表示从左到右，i在最大递增子串中的位置  这里用到了动态规划的思想
     * r[i]表示从右到左，i在最大递增子串中的位置  这里用到了动态规划的思想
     *
     *
     * 教训1：要注意数组越界问题
     * -1~n
     * 0~n-1
     * 1~n-2
     *
     * 教训2：不能同时nextInt()和nextLine()
     *
     * 教训3：n++和 n+1不一样的！
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            if(n<=2){
                System.out.println(0);
            }
            //String height = scanner.nextLine();
            //String[] heightArr = height.split(" ");
            int[] heightArr = new int[n];
            int[] left = new int[n];

            int[] l = new int[n];
            int[] r = new int[n];
            for (int i = 0; i < n; i++) {
                heightArr[i] = scanner.nextInt();
                l[i] = 1;
                r[i] = 1;
            }
            for (int i = 1; i < n; i++) {
                int max = 1;
                for (int j = 0; j < i; j++) {
                    if (heightArr[i] > heightArr[j]){
                        max = Math.max(max,l[j]+1);
                    }
                }
                l[i] = max;
            }

            for (int i = n - 2; i > -1; i--) {
                int max = 1;
                for (int j = n - 1; j > i; j--) {
                    if (heightArr[i] > heightArr[j]){
                        max = Math.max(max,r[j]+1);
                    }
                }
                r[i] = max;
            }

            int result = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                left[i] = l[i] + r[i] - 1;
                result = Math.min(result,n - left[i]);
            }
            System.out.println(result);
        }
    }


}
