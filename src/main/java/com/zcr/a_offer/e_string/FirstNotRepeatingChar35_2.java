package com.zcr.a_offer.e_string;

/**
 * 如果需要判断多个字符是不是在某个字符串里出现过或者统计多个字符在某个字符串中出现的次数，我们可以考虑基于数组创建一个简单的哈希表，
 * 这样可以用很小的空间消耗来换取时间效率的提升。
 *
 * 35_2、从第一个字符串中删除在第二个字符串中出现的所有的字符。
 * 例：We are Students.     aeiou->W r Students.
 *
 * 用哈希表存储第二个字符串，
 * 从头到尾扫描第一个字符串，用O(1)就可以判断出该字符是否出现第二个字符串中
 *
 * 时间：O(n)
 */
public class FirstNotRepeatingChar35_2 {


    public String deleteSecondString(String str1,String str2) {
        if (str1 == null || str1.length() == 0) {
            return "";
        }
        if (str2 == null || str2.length() == 0) {
            return str1;
        }
        boolean[] hashmap = new boolean[256];
        for (int i = 0; i < str2.length(); i++) {
            hashmap[str2.charAt(i)] = true;
        }
        StringBuilder sb = new StringBuilder(str1);
        for (int i = 0; i < sb.length(); i++) {
            if (hashmap[sb.charAt(i)] == true) {
                str1 = sb.deleteCharAt(i).toString();
            }
        }
        return str1;
    }

    public static void main(String[] args) {
        FirstNotRepeatingChar35_2 firstNotRepeatingChar35_2 = new FirstNotRepeatingChar35_2();
        String result = firstNotRepeatingChar35_2.deleteSecondString("We are Students.","aeiou");
        System.out.println(result);
    }

}
