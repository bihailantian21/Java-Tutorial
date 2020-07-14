package com.zcr.a_offer.e_string;

/**
 * 35_3、删除字符串中所有重复出现的字符
 * 如：google  gole
 */
public class FirstNotRepeatingChar35_3 {

    /**
     * deleteCharAt(j)方法会删除字符串中下标为 j 的字符，并将其后的字符下标都向前移了一位。
     * 例如，当删除下标为3的 a 后，本应该下标为4的 a 此时向前移了一位而下标变成3，
     * 而此时负责字符串检索的 j 值经过一次循环变成4，就这样跳过了原本下标为4的 a。
     *
     * 所以说每次删除完下标值要--
     * @param str
     * @return
     */
    public String deleteRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        boolean[] hashmap = new boolean[256];
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            if (hashmap[sb.charAt(i)] == true) {
                str = sb.deleteCharAt(i).toString();
                i--;
            } else {
                hashmap[sb.charAt(i)] = true;
            }
        }
        return str;
    }

    public static void main(String[] args) {
        FirstNotRepeatingChar35_3 firstNotRepeatingChar35_3 = new FirstNotRepeatingChar35_3();
        String result = firstNotRepeatingChar35_3.deleteRepeatingChar("googleg");
        System.out.println(result);
    }


}
