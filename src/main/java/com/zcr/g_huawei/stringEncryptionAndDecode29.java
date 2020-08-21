package com.zcr.g_huawei;


import java.util.Scanner;

public class stringEncryptionAndDecode29 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str1 = scanner.nextLine();
            System.out.println(String.valueOf(Encrypt(str1.toCharArray())));
            String str2 = scanner.nextLine();
            System.out.println(String.valueOf(unEncrypt(str2.toCharArray())));

        }
    }

    public static char[] Encrypt(char aucPassword[]) {
        char aucResult[] = new char[aucPassword.length];
        for (int i = 0; i < aucPassword.length; i++) {
            if (aucPassword[i] >= '0' && aucPassword[i] <= '9') {
                if (aucPassword[i] == '9') {
                    aucResult[i] = '0';
                } else {
                    aucResult[i] = (char) (aucPassword[i] + 1);
                }
            } else if (aucPassword[i] >= 'a' && aucPassword[i] <= 'z') {
                aucResult[i] = (char) (aucPassword[i]  - 'a' + 'A');
                if (aucResult[i] == 'Z') {
                    aucResult[i] = 'A';
                } else {
                    aucResult[i] = (char) (aucResult[i] + 1);
                }
            } else if (aucPassword[i] >= 'A' && aucPassword[i] <= 'Z') {
                aucResult[i] = (char) (aucPassword[i] - 'A' + 'a');
                if (aucResult[i] == 'z') {
                    aucResult[i] = 'a';
                } else {
                    aucResult[i] = (char) (aucResult[i] + 1);
                }
            } else {
                aucResult[i] = aucPassword[i];
            }
        }
        return aucResult;
    }

    public static char[] unEncrypt (char result[]) {
        char password[] = new char[result.length];
        for (int i = 0; i < result.length; i++) {
            if (result[i] >= '0' && result[i] <= '9') {
                if (result[i] == '0') {
                    password[i] = '9';
                } else {
                    password[i] = (char) (result[i] - 1);
                }
            } else if (result[i] >= 'a' && result[i] <= 'z') {
                password[i] = (char) (result[i]  - 'a' + 'A');
                if (password[i] == 'A') {
                    password[i] = 'Z';
                } else {
                    password[i] = (char) (password[i] - 1);
                }
            } else if (result[i] >= 'A' && result[i] <= 'Z') {
                password[i] = (char) (result[i] - 'A' + 'a');
                if (password[i] == 'a') {
                    password[i] = 'z';
                } else {
                    password[i] = (char) (password[i] - 1);
                }
            } else {
                password[i] = result[i];
            }
        }
        return password;
    }
}
