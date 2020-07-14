package com.zcr.b_leetcode;

/**
 * 38. Count and Say
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 * You can do so recursively, in other words from the previous member read off the digits,
 * counting the number of digits in groups of the same digit.
 * Note: Each term of the sequence of integers will be represented as a string.
 *
 * Example 1:
 * Input: 1
 * Output: "1"
 * Explanation: This is the base case.
 *
 * Example 2:
 * Input: 4
 * Output: "1211"
 * Explanation: For n = 3 the term was "21" in which we have two groups "2" and "1", "2" can be read as "12" which means frequency = 1 and value = 2, the same way "1" is read as "11", so the answer is the concatenation of "12" and "11" which is "1211".
 */

/**
 * 38、外观数列
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 * 注意：整数序列中的每一项将表示为一个字符串。
 *
 * 示例 1:
 * 输入: 1
 * 输出: "1"
 * 解释：这是一个基本样例。
 *
 * 示例 2:
 * 输入: 4
 * 输出: "1211"
 * 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；
 * 类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
 */
public class CountandSay38 {

    /**
     * 1
     * 11
     * 21
     * 1211
     * 111221
     * 312211
     * 个数+数字
     *
     * 如：
     * 给定n，要进行n次循环，拿其中的一次，展示一下中间的过程。
     * Initial 1  						  2                              1  1
     * count:0 1(count是.这里+1)     1(当前数字2！=前一个数1，count置1)      1  2
     * prev:. 1                          2                               1  1
     * str:  11                         12                                  21
     * 总结：每当当前数字不等于前一个数字时，count置为1，pre置为当前数，然后append前一个的字符串str=count+prev。
     * 而当当前数字等于前一个数字时，count++，pre不变，不append。
     * 最后的一个字符串还没有加进来，所以当所有的循环结束后，把最后的字符串str=count+prev加上。
     * （因为在for循环中，sb.append的时机是在进行后一个的时候发现与前面不同时，而最后一个字符后面没有字符了，需要我们人为的添加）
     * 如：
     * Initial   1  1  1  2  2  1
     * count:0   1  2  3  1  2  1
     *  prev:.   1  1  1  2  2  1
     * str:          31    22 11
     *
     * 0、第一层for循环：给定n，要进行n次循环
     * 然后第二层for循环：从第一个字符一个一个的看：
     * 1、当当前数字等于前一个数字时，count++，pre不变（也可以置为当前数），不append。
     * 2、每当当前数字不等于前一个数字时，count置为1，pre置为当前数，然后append前一个的字符串str=count+prev。
     * 3、最后的一个字符串还没有加进来，所以当所有的循环结束后（所有的字符都被检查过之后），把最后的字符串str=count+prev加上。
     * @param n
     * @return
     */
    public String countandSay(int n) {
        if (n == 0) {
            return "";
        }
        String str = "1";//如果n=1的时候，直接return 1
        for (int i = 0; i < n - 1; i++) {
            int count = 0;
            Character prev = '.';
            StringBuilder sb = new StringBuilder();
            for (int idx = 0; idx < str.length(); idx++) {
                if (str.charAt(idx) == prev || prev == '.') {
                    count++;
                } else {
                    sb.append(count + Character.toString(prev));
                    count = 1;
                }
                prev = str.charAt(idx);
            }
            sb.append(count + Character.toString(prev));
            str = sb.toString();
        }
        return str;
    }

    /**
     * 动态规划的题：
     * 先建一个数组
     * 求上一个字符串的长度
     * 需要统计每一个字符出现的个数
     *
     *
     * 第0个循环：给定n，那么就需要做n次循环
     * 初始值：count为1
     * 第一个循环：从第一位到最后一位
     * 第二个循环：每一次发现数字不一样的时候都要将count变为1
     * 跳出第二个循环的时候打印个数+数字
     * 然后继续将第一个循环++
     * @param n
     */
    public void countandSay2(int n) {

    }

    public static void main(String[] args) {
        int n = 6;
        CountandSay38 countandSay38 = new CountandSay38();
        String result = countandSay38.countandSay(n);
        System.out.println(result);
    }
}
