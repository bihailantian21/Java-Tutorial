package com.zcr.a_offer.e_string;


/**
 * 27、字符串的排列
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * leetcode
 * 使用回溯法
 */
public class Permutation28 {

    /**
     * 输入中的字符不包含重复元素
     *
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] schar = str.toCharArray();
        StringBuilder cur = new StringBuilder();
        HashSet<Character> set = new HashSet<>();
        helper(schar, res, cur, set);
        return res;
    }

    public void helper(char[] schar, ArrayList<String> res, StringBuilder cur, HashSet<Character> set) {
        if (cur.length() == schar.length) {
            res.add(cur.toString());
        } else {
            for (int i = 0; i < schar.length; i++) {
                if (!set.contains(schar[i])) {
                    set.add(schar[i]);
                    cur.append(schar[i]);
                    helper(schar, res, cur, set);
                    set.remove(schar[i]);
                    cur.deleteCharAt(cur.length() - 1);
                }
            }
        }
    }


    public static void main(String[] args) {
        Permutation28 permutation28 = new Permutation28();
        ArrayList<String> result = new ArrayList<>();
        String str = "01234567";
        result = permutation28.Permutation2(str);
        System.out.println(Arrays.toString(result.toArray()));
    }

    /**
     *
     * 题目：输入一个字符串，打印出该字符串中字符的所有排列。
     * 举例：输入字符串 abc，则输出由字符 a、b、c 所能排列出来的所有字符串 abc、acb、bac、bca、cab 和 cba。
     *
     * 输入中的字符包含重复元素，需要我们去重
     * @param str
     * @return
     */
    public ArrayList<String> Permutation2(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] schar = str.toCharArray();
        Arrays.sort(schar);
        StringBuilder cur = new StringBuilder();
        boolean[] used = new boolean[str.length()];
        helper2(schar, res, cur, used);
        return res;
    }

    public void helper2(char[] schar, ArrayList<String> res, StringBuilder cur, boolean[] used) {
        if (cur.length() == schar.length) {
            res.add(cur.toString());
        } else {
            for (int i = 0; i < schar.length; i++) {
                if (used[i] || (i > 0 && !used[i - 1] && schar[i] == schar[i - 1])) {//这个已经用过了   或者    前一个没有用过，这一个等于前一个
                    continue;
                }
                used[i] = true;
                cur.append(schar[i]);
                helper2(schar, res, cur, used);
                used[i] = false;
                cur.deleteCharAt(cur.length() - 1);
            }
        }
    }
}

