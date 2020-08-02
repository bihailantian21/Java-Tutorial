package com.zcr.g_huawei;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * 查找兄弟单词
 *
 *输入描述:
 * 先输入字典中单词的个数n，再输入n个单词作为字典单词。
 * 再输入一个单词，查找其在字典中兄弟单词的个数m
 * 再输入数字k
 *
 * 输出描述:
 * 根据输入，输出查找到的兄弟单词的个数m
 * 然后输出查找到的兄弟单词的第k个单词。
 *
 *
 */
public class findWord27 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int wordsNum = scanner.nextInt();
            if (wordsNum > 1000) {
                break;
            }
            String[] dict = new String[wordsNum];
            for (int i = 0; i < wordsNum; i++) {
                dict[i] = scanner.next();
            }
            String target = scanner.next();
            int brotherIndex = scanner.nextInt();

            ArrayList<String> brotherlist = new ArrayList<>();
            for (int i = 0; i < wordsNum; i++) {
                if (isBrotherStr(dict[i], target)) {
                    brotherlist.add(dict[i]);
                }
            }
            System.out.println(brotherlist.size());
            Collections.sort(brotherlist);
            //要注意数组越界问题
            if (brotherlist.size() >= brotherIndex) {
                System.out.println(brotherlist.get(brotherIndex - 1));
            }
        }
    }

    private static boolean isBrotherStr(String s, String target) {
        if (s.equals(target) || s.length() != target.length()) {
            return false;
        }
        char[] sArr = s.toCharArray();
        char[] tArr = target.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        /*for (int i = 0; i < sArr.length; i++) {
                if (sArr[i] != tArr[i]) {
                    return false;
                }
        }*/
        return Arrays.equals(sArr,tArr);
    }
    //判断是不是兄弟字符还有一种方法：数每个字符出现的个数是否相同
}
