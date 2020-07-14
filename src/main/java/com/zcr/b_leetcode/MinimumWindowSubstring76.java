package com.zcr.b_leetcode;

/**
 * 76. Minimum Window Substring
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 *
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

import java.util.Arrays;

/**
 * 76、最小覆盖子串
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 *
 * 说明：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class MinimumWindowSubstring76 {

    /**
     * 找到最短长度的子字符串，它包含下面所有的字符。
     * S:xxaxxbaacxxxbxx
     *     123456789
     *     |||||||||
     *     123456789
     * T:aabc
     * 要找最短的
     *
     * 0、起始字符一定是T中的某一个字符，终止字符也一定时T中的某一个字符。
     * 1、因为T中可能包含重复的字符，所以每一个字符的数量也是我们需要考虑的。
     * Hashmap处理T，记录每个字符对应的数量。或者使用int array，int[256]，直接使用字符作为Index。
     * Tarray
     * 2、	sarray记录t在s中匹配到的情况，一直在变化。
     * 3、	从当前坐标开始，去遍历s的每一个字符，如果字符是在t中包含着的，就返回当前的坐标。封装成一个函数。返回每一个有效的字符，跳过无效字符。
     * 4、	Left和right进行初始化，从第一个有效字符开始
     * 5、	Right:右指针到达队尾循环结束
     * Right代表了什么含义？s中的子字符串右边的边界是什么。
     * Right是主循环，遍历每一个有效字符。
     * 当s中这个字符数量<t中这个字符数量时，count++；当s中这个字符数量>=t中这个字符数量时，count就不变了。
     * s中这个字符数量++。
     * 每次将right移动到下一个有效字符后，判断如果count数已经达到了t中的字符长度，说明在s中找到了符合条件的子字符串。
     * 6、	Left:s中的子字符串最左边的边界是什么。
     * While循环：如果count数已经达到了t中的字符长度，说明在s中找到了符合条件的子字符串。
     * 如果结果为空，或者之前找的子字符串的长度比现在找到的这个子字符串(left,right)的长度要长，就更新结果。
     * 然后继续进行往后的操作，整体向右滑动一个位置。
     * 先看看当前left指的位置的字符是什么，然后向右滑动把它移出去：如果这个字符在s中的数量<=这个字符在t中的数量，count--。
     * S中这个字符数量--。
     * 将left移动到下一个有效字符。
     * 7、count—后肯定不满足while的条件了，就继续移动右指针。
     * 将right移动到下一个有效字符。
     * （但是也有可能count不—，即还满足while的条件，这时候，就是说向前滑动一个窗口后（移动到下一个有效字符），比上一个子字符串长度小了，是最有可能得出结果的时候）。
     * 例：
     * Tarray：
     * [a]2
     * [b]1
     * [c]1
     * Sarray：
     * left 1 . . 4 5 6 7 8 9
     * right1 . . 4 5 6 7 7
     * [a]0 1       2 3   2
     * [b]0       1
     * [c]0             1
     * Count1     2 3   4 4 3
     * Len              7 4
     * Count记录已经匹配到的字符的数量，当count变为4时候，T中所有的字符都被找到了。
     * 当s中这个字符数量<t中这个字符数量时，count++；当s中这个字符数量>=t中这个字符数量时，count就不变了。
     *
     *
     *
     * 总结：
     * 1、初始，left指针和right指针都指向s的第一个元素
     * 2、将right指针右移，扩张一个窗口，直到得到一个可行窗口，即包含t的全部字母的窗口
     * 3、得到可行窗口后，将left指针矩阵右移，若得到的窗口依然可行，则更新最小窗口大小
     * 4、若窗口不再可行，转到2
     * @param s
     * @param t
     * @return
     */
    public String minWindow1(String s, String t) {
        String result = "";
        if (s == null || t == null || s.length() ==0 || t.length() == 0) {
            return result;
        }
        int[] tarray = new int[256];
        int[] sarray = new int[256];
        int count = 0;
        int len1 = s.length();
        int len2 = t.length();
        /*for (int i = 0; i < len2; i++) {
            tarray[t.charAt(i)]++;
        }*/
        for (char c : t.toCharArray()) {
            tarray[c]++;
        }
        int left = getNextValidChar1(0,s,tarray);
        if (left == len1) {
            return result;
        }
        int right = left;
        while (right < len1) {
            int rightchar = s.charAt(right);
            if (sarray[rightchar] < tarray[rightchar]) {
                count++;
            }
            sarray[rightchar]++;
            while (left < len1 && count == len2) {
                if (result.isEmpty() || result.length() > right - left + 1) {
                    result = s.substring(left,right + 1);
                }
                int leftchar = s.charAt(left);
                if (sarray[leftchar] <= tarray[leftchar]) {
                    count--;
                }
                sarray[leftchar]--;
                left = getNextValidChar1(left + 1,s,tarray);
            }
            right = getNextValidChar1(right + 1,s,tarray);
        }
        return result;
    }

    public int getNextValidChar1(int start,String s,int[] tarray) {
        while (start < s.length()) {
            char c = s.charAt(start);
            if (tarray[c] != 0) {
                return start;
            }
            start++;
        }
        return start;
    }


    public String minWindow(String s,String t) {
        if (s == null || t == null || s.length() ==0 || t.length() == 0) {
            return "";
        }
        int matchCount = 0;
        String res = "";
        int[] tArr = new int[256];
        for (char c : t.toCharArray()) {
            tArr[c]++;
        }
        int[] sArr = new int[256];
        int left = findNextStrIdx(0,s,tArr);
        if (left == s.length()) {
            return "";
        }
        int right = left;
        while (right < s.length()) {
            int rightChar = s.charAt(right);
            if (sArr[rightChar] < tArr[rightChar]) {
                matchCount++;
            }
            sArr[rightChar]++;
            while (left < s.length() && matchCount == t.length()) {
                if (res.isEmpty() || res.length() > right - left + 1) {
                    res = s.substring(left,right + 1);
                }
                int leftChar = s.charAt(left);
                if (sArr[leftChar] <= tArr[leftChar]) {
                    matchCount--;
                }
                sArr[leftChar]--;
                left = findNextStrIdx(left + 1,s,tArr);
            }
            right = findNextStrIdx(right + 1,s,tArr);
        }
        return res;
    }
    public int findNextStrIdx(int start,String s,int[] tArr) {
        while (start < s.length()) {
            char c = s.charAt(start);
            if (tArr[c] != 0) {
                return start;
            }
            start++;
        }
        return start;
    }

    public static void main(String[] args) {
        /*String s = "ADOBECODEBANC";
        String t = "ABC";*/
        /*String s= "xxaxxbaacxxxbxx";
        String t = "aabc";*/
        String s = "ABAACBAB";
        String t = "ABC";
        MinimumWindowSubstring76 minimumWindowSubstring76 = new MinimumWindowSubstring76();
        String result = minimumWindowSubstring76.minWindow(s,t);
        System.out.println(Arrays.toString(result.toCharArray()));
    }
}
