package com.zcr.g_huawei;

import java.util.*;

/**
 * 删除字符串中出现次数最少的字符
 * 题目描述
 * 实现删除字符串中出现次数最少的字符，若多个字符出现次数一样，则都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 * 注意每个输入文件有多组输入，即多个字符串用回车隔开
 * 输入描述:
 * 字符串只包含小写英文字母, 不考虑非法输入，输入的字符串长度小于等于20个字节。
 *
 * 输出描述:
 * 删除字符串中出现次数最少的字符后的字符串。
 *
 * 示例1
 * 输入
 * 复制
 * abcdd
 * 输出
 * 复制
 * dd
 */
public class deleteLeastAppearChar23 {

    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            Map<Character,Integer> map = new HashMap<>();
            String str = scanner.nextLine();
            for (int i = 0; i < str.length(); i++) {
                map.put(str.charAt(i),map.getOrDefault(str.charAt(i),0)+1);
            }
            int minCount = Integer.MAX_VALUE;
            Set<Character> set = new HashSet<>();
           *//*这里发生了严重的逻辑错误，这里的意思是只要小于minCount就把它进入到set中，那么每一个都会加到set中呀
           for (Map.Entry<Character,Integer> entry : map.entrySet()) {
                if (entry.getValue() <= minCount) {
                    minCount = entry.getValue();
                    set.add(entry.getKey());
                }
            }*//*
            for (Iterator<Character> it = set.iterator(); it.hasNext(); ) {
                Character character = it.next();
                System.out.println(character);
                str = str.replace(character.charValue(),'\0');
            }
            System.out.println(str);
        }
    }*/

    public static void main(String[] args) {//swumneiiii
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if(str.length()>20){
                continue;
            }
            Map<Character,Integer> map = new HashMap<>();

            for (int i = 0; i < str.length(); i++) {
                map.put(str.charAt(i),map.getOrDefault(str.charAt(i),0)+1);
            }
            int minCount = Integer.MAX_VALUE;
            for (Map.Entry<Character,Integer> entry : map.entrySet()) {
                if (entry.getValue() < minCount) {
                    minCount = entry.getValue();
                }
             }
             for (Map.Entry<Character,Integer> entry : map.entrySet()) {
                if (entry.getValue() == minCount) {
                    str = str.replaceAll(String.valueOf(entry.getKey().charValue()), "");
                    //str = str.replace(entry.getKey().charValue(), '\0');错误
                }
             }
            System.out.println(str);
        }
    }

    public static void main2(String[]args){
        Scanner scan=new Scanner(System.in);
        while(scan.hasNextLine()){
            String str=scan.nextLine();
            if(str.length()>20){
                continue;
            }
            int []max=new int[26];
            char[]ch=str.toCharArray();
            int min=Integer.MAX_VALUE;
            for(int i=0;i<ch.length;i++){
                max[ch[i]-'a']++;
                min=min>max[ch[i]-'a']?max[ch[i]-'a']:min;
            }
            for(int i=0;i<max.length;i++){
                if(max[i]==min){
                    str=str.replaceAll(String.valueOf((char)(i+97)), "");
                }
            }
            System.out.println(str);
        }
    }
}
