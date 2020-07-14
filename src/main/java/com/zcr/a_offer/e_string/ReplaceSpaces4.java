package com.zcr.a_offer.e_string;

/**
 * 4、替换空格
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.
 * 则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceSpaces4 {

    /**
     * 这个题目如果只是简单的插入的话，插入之后导致后面的元素的移动导致，需要O(n2)的复杂度；
     * <p>
     * 这个的解决方法使用两个指针，可以达到O(n)复杂度；
     * 首先计算出空格的个数，这样求的新的字符串的长度；
     * 然后使用两个指针,新的指针second指向新的字符串的末尾，老指针first指向原来字符串的末尾，
     * 每次检查字符串的末尾如果是空格的话，就添加%20进去，否则把原来的字符串复制到后面；
     *
     *
     * We Are Happy.
     *             f   s
     *            f   s
     *           f   s
     *          f   s
     *         f   s
     *        f   s
     *       f sss
     *      f s
     *     f s
     *    f s
     *  fsss
     * We%20Are%20Happy.
     *
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int len = str.length();
        int spaceNum = 0;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == ' ') {
                spaceNum++;
            }
        }
        int newlen = len + 2 * spaceNum;
        str.setLength(newlen);
        int slow = newlen - 1;
        int fast = len - 1;
        for (; fast >= 0; fast--) {
            if (str.charAt(fast) == ' ') {
                str.setCharAt(slow--, '0');
                str.setCharAt(slow--, '2');
                str.setCharAt(slow--, '%');
            } else {
                str.setCharAt(slow--, str.charAt(fast));
            }
        }
        return str.toString();
    }
}

    /**
     * O(n^2)
     * @param str
     * @return
     */
//    public String replaceSpace2(StringBuffer str) {
//        if (str == null || str.length() == 0) {
//            return "";
//        }
//        for (int i = 0; i < str.length() - 1; i++) {
//            if (str.charAt(i) == ' ') {
//                str.setCharAt(i,'%');
//                for (int j = i; j < str.length() - 1; j++) {
//                    str.setCharAt(j,str.setCharAt(););
//                }
//            }
//        }
//    }
