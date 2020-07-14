package com.zcr.b_leetcode;

/**
 * 6. ZigZag Conversion
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string s, int numRows);
 *
 * Example 1:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */


/**
 * 6、Z字形变换
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 *
 * 示例 1:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 *
 * 示例 2:
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */
public class ZigZagConversion6 {

    /**
     * 字符串的拼接
     * 1、需要一个字符串数组，注意StrigBuilder得初始化。结果就是将这个字符串数组所有的进行拼接。
     * 2、分为两个方向：vertically down和obliquely up
     * 垂直向下和倾斜向上
     *
     * 时间：O(n) 每个索引被访问一次
     * 空间：O(n) 字符串数组所占空间
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s,int numRows) {
        char[] sChar = s.toCharArray();
        int len = s.length();
        StringBuilder[] stringBuilders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            stringBuilders[i] = new StringBuilder();
        }
        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < numRows && i < len; idx++) {
                stringBuilders[idx].append(sChar[i]);
                i++;
            }
            for (int idx = numRows - 2; idx > 0 && i < len; idx--) {
                stringBuilders[idx].append(sChar[i]);
                i++;
            }
        }
        for (int j = 1; j < numRows; j++) {
            stringBuilders[0].append(stringBuilders[j]);
        }
        return stringBuilders[0].toString();
    }

    public static void main(String[] args) {
        ZigZagConversion6 zigZagConversion6 = new ZigZagConversion6();
        String s = "abcdefgh";
        int numsRos = 4;
        String result = zigZagConversion6.convert(s,numsRos);
        System.out.println(result);
    }
}
