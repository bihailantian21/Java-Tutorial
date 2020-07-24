package com.zcr.g_huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 合并表记录
 * 题目描述
 * 数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 *
 * 输入描述:
 * 先输入键值对的个数
 * 然后输入成对的index和value值，以空格隔开
 *
 * 输出描述:
 * 输出合并后的键值对（多行）
 *
 * 示例1
 * 输入
 * 复制
 * 4
 * 0 1
 * 0 2
 * 1 2
 * 3 4
 * 输出
 * 复制
 * 0 3
 * 1 2
 * 3 4
 */
public class mergeTableRecord8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            TreeMap<Integer,Integer> map = new TreeMap();
            for (int i = 0; i < n; i++) {
                int index = scanner.nextInt();
                int number = scanner.nextInt();
                map.put(index, map.getOrDefault(index,0) + number);
            }
            for (Integer key : map.keySet()) {
                System.out.println(key + " " + map.get(key));
            }
        }
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                int s=sc.nextInt();
                int value=sc.nextInt();
                if (map.containsKey(s)) {
                    map.put(s, map.get(s) + value);
                } else
                    map.put(s, value);
            }
            for (Integer key : map.keySet()) {
                System.out.println(key + " " + map.get(key));
            }
        }
    }


    /**
     * 1.HashMap的方法
     * containsKey()
     * get()   getOrDefault()
     * put()   putIfAbsent()
     *
     *
     * 2.如何遍历HashMap Map<Integer, Integer> map = new HashMap<Integer, Integer>();
     *1、 通过ForEach循环进行遍历
     *      for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
     * 			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
     *      }
     *2、 ForEach迭代键值对方式
     *      // 迭代键
     * 		for (Integer key : map.keySet()) {
     * 			System.out.println("Key = " + key);
     *      }
     * 		// 迭代值
     * 		for (Integer value : map.values()) {
     * 			System.out.println("Value = " + value);
     *      }
     *3、使用带泛型的迭代器进行遍历
     *      Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
     * 		while (entries.hasNext()) {
     * 			Map.Entry<Integer, Integer> entry = entries.next();
     * 			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
     *       }
     *4、使用不带泛型的迭代器进行遍历
     *      Iterator<Map.Entry> entries = map.entrySet().iterator();
     * 		while (entries.hasNext()) {
     * 			Map.Entry entry = (Map.Entry) entries.next();
     * 			Integer key = (Integer) entry.getKey();
     * 			Integer value = (Integer) entry.getValue();
     * 			System.out.println("Key = " + key + ", Value = " + value);
     *       }
     *5、通过Java8 Lambda表达式遍历
     *      map.forEach((k, v) -> System.out.println("key: " + k + " value:" + v));
     *
     *
     *
     *
     *
     * 3.HashMap中的key保持有序----》TreeMap
     * @param number
     */
    private static void mergeTableRecord(double number) {
    }
}
