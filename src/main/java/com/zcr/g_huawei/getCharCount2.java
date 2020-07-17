package com.zcr.g_huawei;

import java.util.Scanner;

public class getCharCount2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String  c = scanner.next();
        char cc = c.charAt(0);
        System.out.println(getCharCount(str, cc));

    }

    public static int getCharCount(String str, char c) {
        int count = 0;
        if (str == null || str.length() == 0 ) {
            return count;
        }
        String strA = str.toLowerCase();
        String cA = String.valueOf(c).toLowerCase();
        char ccA = cA.charAt(0);
        for (int i = 0; i < str.length(); i++) {
            if (strA.charAt(i) == ccA) {
                count++;
            }
        }
        return count;
    }
}
