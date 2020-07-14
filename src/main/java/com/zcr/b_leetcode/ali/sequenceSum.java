package com.zcr.b_leetcode.ali;

import java.util.Scanner;

public class sequenceSum {




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*int N = scanner.nextInt();
        int L = scanner.nextInt();*/
        while (scanner.hasNext()) {
            int N = scanner.nextInt();
            int L = scanner.nextInt();

        boolean flag = false;
        for (int i = L; i <= 100; i++) {//n是项数
            if ((2*N+i-i*i)%(2*i) == 0) {
                flag = true;
                int start = (2*N+i-i*i)/(2*i);
                for (int j = 0; j < i-1; j++) {
                    int a = start+j;
                    System.out.println(a+" ");
                }
                System.out.println(start+i-1);
                break;
            }
        }
        if (flag == false) {
            System.out.println("No");
        }
        }
    }
}
