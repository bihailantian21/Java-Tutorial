package com.zcr.b_leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LetterCombinationOfaPhoneNumber17 {
    //使用递归
    //1、递归的变量:String curr int curIdx   String digits  List<String> result HashMap<Character,char[]> map
    //2、递归的终止条件：curIdx = len
    //3、递归的初始条件：curr = "" curIdx = 0

    //深度优先DFS
    public List<String> letterCombination(String digits) {
        List<String> res = new LinkedList<String>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        HashMap<Character,char[]> map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r','s'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y','z'});
        helper("",0,digits,res,map);
        return res;

    }

    public void helper(String curr,int currIdx,String digits,List<String> res,HashMap<Character,char[]> map) {
        if (currIdx == digits.length()) {
            res.add(curr);
        } else {
            char c = digits.charAt(currIdx);
            if (map.containsKey(c)) {
                for (char ch : map.get(c)) {
                    helper(curr + ch,currIdx + 1,digits,res,map);
                }
            } else {//如果输入无效字符，直接将index+1即可
                helper(curr,currIdx + 1,digits,res,map);
            }
        }
    }

    //广度优先BFS


    public static void main(String[] args) {
        String digits = "213";
        LetterCombinationOfaPhoneNumber17 letter = new LetterCombinationOfaPhoneNumber17();
        List<String> result = letter.letterCombination(digits);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
