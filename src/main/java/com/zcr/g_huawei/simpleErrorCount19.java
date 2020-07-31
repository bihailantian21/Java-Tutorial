package com.zcr.g_huawei;


import java.util.*;

/**
 * 简单错误统计
 * 题目描述
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 *
 *
 * 处理：
 *
 *
 * 1、 记录最多8条错误记录，循环记录（或者说最后只输出最后出现的八条错误记录），对相同的错误记录（净文件名（保留最后16位）称和行号完全匹配）只记录一条，错误计数增加；
 *
 *
 * 2、 超过16个字符的文件名称，只记录文件的最后有效16个字符；
 *
 *
 * 3、 输入的文件可能带路径，记录文件名称不能带路径。
 *
 *
 * 输入描述:
 * 一行或多行字符串。每行包括带路径文件名称，行号，以空格隔开。
 *
 * 输出描述:
 * 将所有的记录统计并将结果输出，格式：文件名 代码行数 数目，一个空格隔开，如：
 *
 * 示例1
 * 输入
 * 复制
 * E:\V1R2\product\fpgadrive.c   1325
 * 输出
 * 复制
 * fpgadrive.c 1325 1
 */
public class simpleErrorCount19 {
    private static final int MAX_SIZE = 8;

    /**
     * 错误原因一：不应该选择用HashMap嵌套HashMap的方式
     * 因为最后只需要输出8条记录
     * 而HashMap嵌套HashMap的方式，并不知道嵌套的那个子HashMap中有多少条记录，只统计外层HashMap的数量是没有用的
     * 我自己是联想到了redis的Map结构
     * 其实应该将文件名和行号作为Map的key
     *
     * 不过：复习了LinkedHashMap的用法、Map的遍历
     *
     * 错误原因二：
     * 从全路径中获取相对路径，所以前面的信息都没用，不需要用split("\\")，而是用lastIndexOf()
     *
     * 不过：复习了split("")中是正则表达式，所以对特殊字符需要转义
     * @param args
     */
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        LinkedHashMap<String,HashMap<Integer,Integer>> map = new LinkedHashMap<>() {
//            @Override
//            protected boolean removeEldestEntry(Map.Entry eldest) {
//                return size() > MAX_SIZE;
//            }
//        };
//        HashMap<Integer,Integer> lineCount = new HashMap<>();
//
//        while (scanner.hasNext()) {
//            String str = scanner.nextLine();
//            String[] strArr = str.split(" ");
//            String fullPath = strArr[0];
//            int lineNumber = Integer.parseInt(strArr[1]);
//            String[] fullPathArr = fullPath.split("\\\\");
//            String path = fullPathArr[fullPathArr.length - 1];
//
//
//            lineCount.putIfAbsent(lineNumber,lineCount.getOrDefault(lineNumber,0)+1);
//            map.put(path,lineCount);
//        }
//
//        Set<Map.Entry<Integer, Integer>> ma = map.get(0).entrySet();
//
//        for(Map.Entry entry : map.entrySet()) {
//            for (Map entry1 : entry.getValue())
//            System.out.println(entry.getKey() + " " +entry.getValue());
//        }
//    }

    /**
     * 输出最后8条数据，那么就是用LinkedHashMap按照时间顺序排序的特性进行
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String,Integer> map = new LinkedHashMap<>();
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            String[] strArr = str.split(" ");
            String fullPath = strArr[0];
            int lineNumber = Integer.parseInt(strArr[1]);
            int pathIndex = fullPath.lastIndexOf("\\");
            String path = fullPath.substring(pathIndex + 1);
            if (path.length() > 16) {
                path = path.substring(path.length() - 16);
            }

            String key = path + " " +lineNumber;
            map.put(key,map.getOrDefault(key,0)+1);
        }
        int count = 0;
        for (Map.Entry entry : map.entrySet()) {
            count++;
            if (count > map.entrySet().size() - 8) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }



}
