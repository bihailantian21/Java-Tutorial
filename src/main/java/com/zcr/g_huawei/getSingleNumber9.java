package com.zcr.g_huawei;

import java.util.*;

public class getSingleNumber9 {


    /**
     * 只出现一次的，那么就是要去除重复，可以使用Map ------------ 但是这样不好，因为并不需要统计每个字符出现的次数，只需要找出出现一次的字符即可。
     * Map的有序和无序实现类，与Map的排序
     * 1.HashMap、Hashtable不是有序的；
     * 2.TreeMap和LinkedHashMap是有序的（TreeMap默认 Key 升序，LinkedHashMap则记录了插入顺序）。
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            String str = String.valueOf(n);
            int len = str.length();
            LinkedHashMap<Character,Integer> map = new LinkedHashMap<>();
            for (int i = len - 1; i >= 0; i--) {
                map.put(str.charAt(i),map.getOrDefault(str.charAt(i),0) + 1);
            }
            StringBuilder result = new StringBuilder();
            for (Character entry : map.keySet()) {
                result.append(entry);
            }
            System.out.println(Integer.parseInt(result.toString()));
        }
    }

    /**
     * 可以用set去重
     * Set无序指的是HashSet，它不能保证元素的添加顺序，更不能保证自然顺序，而Set的其他实现类是可以实现这两种顺序的。
     *
     * 1，LinkedHashset : 保证元素添加的自然顺序
     *
     * 2，TreeSet : 保证元素的自然顺序
     *
     * @param args
     */
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            String str = String.valueOf(n);
            int len = str.length();
            Set set = new LinkedHashSet();
            for (int i = len - 1; i >= 0; i--) {
                set.add(str.charAt(i));
            }
            StringBuilder result = new StringBuilder();
            for (Iterator it = set.iterator(); it.hasNext(); ) {
                Character entry = (Character) it.next();
                result.append(entry);
            }
            System.out.println(Integer.parseInt(result.toString()));
        }
    }

    /**
     * 可以用String的contains方法去重/或者List的contains
     * @param args
     */
    public static void main3(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            String str = String.valueOf(n);
            int len = str.length();
            String result = "";
            for (int i = len - 1; i >= 0; i--) {
               if (!result.contains(str.charAt(i) + "")) {
                   result += str.charAt(i);
               }
            }
            System.out.println(Integer.parseInt(result));
        }
    }

    /**
     * 总结：
     * 1.如何遍历一个整数int
     * （1）转换为一个字符串String
     * String str = String.valueOf(n);
     * int len = str.length();
     * for (int i = len - 1; i >= 0; i--) {
     *    str.charAt(i);
     * }
     * （2）转换为一个字符数组char[]
     * char[] chars = (n+"").toCharArray();
     * for(int i= chars.length-1; i>= 0;i--){
     *            chars[i];
     * }
     *
     * 2.如何去重
     * （1）Map.put()
     * （2）Set.add()
     * （3）List  contains()
     * （4）String contains()
     */


}
