package com.zcr.a_offer.e_string;

import java.util.HashMap;

/**
 * 35.第一个只出现一次的字符
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
 * 如果没有则返回 -1（需要区分大小写）.
 */
public class FirstNotRepeatingChar35 {

    /**
     * 最直观的方式：从头开始扫描字符串中每一个字符，当访问到某个字符时，和后面的每个字符挨个比较，如果在后面
     * 没有发现重复的字符，就说明他就是第一个只出现一次的字符。
     * 时间：O(n^2)
     *
     * 与字符出现的次数有关，统计每个字符在这个字符串中出现的次数。--使用哈希表。
     * 第一遍扫描：建立哈希表
     * 第二遍扫描：找出第一个次数为1的数
     *
     * 如何实现哈希表？
     * 字符char是一个长度为8的数据类型，有256种可能。于是我们创建一个长度为256的数组，
     * 每个字符根据其ASII码值作为数组的下标对应数组的一个数字，而数组中存储每个字符出现的次数。
     * 大小256，以ASII码值为键值的哈希表。
     *
     * 因为字母ascii在65 ~ 122，所以开一个58的数组就可以了。
     *
     * 时间：O(n)
     * 空间：O(1)因为256是常数个。
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        int[] hashmap = new int[256];
        for (int i = 0; i < str.length(); i++) {
            hashmap[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (hashmap[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 使用map
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar2(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        HashMap<Character, Integer> hashmap = new HashMap();
        for (int i = 0; i < str.length(); i++) {
            hashmap.put(str.charAt(i), hashmap.getOrDefault(str.charAt(i), 0) + 1);
        }
        for (int i = 0; i < str.length(); i++) {
            if (hashmap.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
