package com.zcr.a_offer.e_string;

/**
 * 在英语中，如果两个单词出现的字母相同，并且每个字母出现的次数也相同，那么这两个单词互为变位词（Anagram）。
 * 如：silent listen
 * 判断输入的两个字符串是不是变位词
 */
public class FirstNotRepeatingChar35_4 {

    /**
     * 扫描第一个字符串：哈希表对应项加一
     * 扫描第二个字符串：哈希表对应项减一
     * 如果最后哈希表全部为0，则为变位词
     * @param str1
     * @param str2
     * @return
     */
    public boolean isAnagram(String str1,String str2) {
        if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
            return false;
        }
        int[] hashmap = new int[256];
        for (int i = 0; i < str1.length(); i++) {
            hashmap[str1.charAt(i)]++;
        }
        for (int i = 0; i < str2.length(); i++) {
            hashmap[str2.charAt(i)]--;
        }
        for (int i = 0; i < hashmap.length; i++) {
            if (hashmap[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        FirstNotRepeatingChar35_4 firstNotRepeatingChar35_4 = new FirstNotRepeatingChar35_4();
        boolean result = firstNotRepeatingChar35_4.isAnagram("listen","silentjj");
        System.out.println(result);
    }


}
