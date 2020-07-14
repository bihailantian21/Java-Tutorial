package com.zcr.b_leetcode.dynamicplan.stringeditor;

/**
 * 10. Regular Expression Matching
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 *
 * Example 1:
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 *
 * Example 3:
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 * Example 4:
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 *
 * Example 5:
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class RegularExpressionMatching10 {

    public boolean isMatchDFS(String a,String b) {
        if (a == null || b == null) {
            return false;
        }
        int alen = a.length();
        int blen = b.length();
        return true;
    }

    /**
     * 字符串匹配问题：动态规划
     * 难度在*，*和它前面的字符表示一个整体，可能有一个二个多个；只有.的话，一个个对比就好，而有*的话需要分情况讨论。
     * 1、布尔类型的数组boolean[][]，基底match[0][0]为true，动态规划必须有基底。注意：boolean[]默认值就是false，所以对false的情况不用管。
     * match[i][j]代表的是A[1~i]与B[1~j]是否匹配，而且i,j是字符数，不是下标数
     * 2、case0:就是说A为""空时，看b中含有*的能否与空匹配 ""与"a"  ""与"a*"  ""与"ab*"  ""与"a*b*"
     * 先要把*代表0次的初始化一下 ""  "a*"是匹配的
     * A 0 a b c d e
     * B
     * 0 T
     * a F
     * * T
     * c F
     * * T
     * e F
     *
     * A 0 a b c d e
     * B
     * 0 T
     * a F
     * b T
     * c F
     * * T
     * e F
     *
     *
     * （0）""与""      T               ""与""      T
     * （1）""与"a"     F               ""与"a"     F
     * （2）""与"a*"    要看"" ""T       ""与"ab"    F
     * （3）""与"a*c"   F               ""与"abc"   F
     * （4）""与"a*c*"  要看"" "a*"T     ""与"abc*"  要看""
     * （5）""与"a*c*e" F                ""与"abc*e"
     * 总结：先处理一遍第一列的数据以应对匹配被匹配串为空串的情况
     * 如果在匹配串中遇到*，那么要看第0列往上数两行的数是否匹配match[0][bi] = match[0][bi - 2]
     * 否则的话（匹配串中没有遇到*），都是false
     * 因为：a*可以为0次，这时候就要看被匹配串与匹配串中除去a*的子匹配串是否匹配
     *
     * 3、case1:一一匹配的情况
     * A 0  a b c d e
     * B
     * 0
     * a   T
     * .     T
     * c       T
     * f         F
     * e           F
     * （1）"a"与"a"         T
     * （2）"ab"与"a."       b=.要看"a"与"a"        T
     * （3）"abc"与"a.c"     c=c要看"ab"与"a."      T
     * （4）"abcd"与"a.cf"   d!=f                  F
     * （5）"abcde"与"a.cfe" e=e要看"abcd"与"a.cf"  F
     * 总结:这种一一匹配的情况，先要看是否A中和B中处于同一个位置上的字符相同或有.。不相同的话：false，不需要处理。相同的话：match[ai][bi] = match[ai - 1][bi - 1];
     *
     * 4、case2:有*出现的情况-----不等于（*前面一位和A中对应位置不相等）
     * 注意：这次的写法中match[i][j]代表的是A[1~i]与B[1~j],i代表的是列数、j代表的是行数
     * 一列一列的排除(针对这一列中的所有行)
     * 然后到了
     * A 0 a b c d e
     * B
     * 0
     * a   T F F F F
     * b
     * * F T T F F F
     * c
     * d
     * e
     * （1）"a" "ab*"  b和a不相等，这种情况下，只有b*代表0个b才有可能匹配。 "a" "a" T
     * 总结：所以现在要看被匹配字符串和匹配字符串中去掉b*的子字符串是否相等。
     * *是以它前面的字符的存在而存在的，所以要看它前面的字符。    所以此时就是看A的全部和B中往前移动两位是否匹配。match[ai][bi] = match[ai][bi - 2]
     * 这种情况下，只有它代表0个字符串时有可能匹配，所以就要看它前面的   "sa"  "sb*"只有b*代表0个字符时匹配，要看"sa" "s"是否匹配
     * （2）"ab" "ab*"
     * （3）"abc" "ab*"
     * （4）"abcd" "ab*"
     * （5）"abcde" "ab*"
     * （6）"abcde" "ab*"
     *
     * 5、case3:有*出现的情况-----等于（*前面一位和A中对应位置相等）
     *（2）"ab" "ab*" b和b相等，这种情况下，是否匹配和*代表的数量有关。就是说：不一定匹配，也不一定不匹配。所以说是或者的关系
     * 若*代表零个：（看被匹配字符串和匹配字符串中去掉b*的子字符串是否相等。）ab  a    F  match[ai][bi] = match[ai][bi - 2]
     * 若*代表一个：（看被匹配字符串和匹配字符串中去掉*的子字符串是否相等。）ab  ab    T  match[ai][bi - 1]
     * 若*代表多个：（看被匹配字符串去掉*代表的子字符和匹配字符串是否相等。如果相等，那么A中有几个b都能和B匹配。）a   ab*  T  match[ai - 1][bi]
     * match[ai][bi] = (match[ai][bi - 2]        //a*有0个a（case2）
     *               || match[ai - 1][bi]        //a*有多个a(case4)
     *               || match[ai][bi - 1]);      //a*有单个a(case3)
     * 注意：||的用法：或者，两个都为false结果才为false，否则为true。意思是只要有一个为true，结果就是true。
     *
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isMatch(String a,String b) {
        if (a == null || b == null) {
            return false;
        }
        int alen = a.length();
        int blen = b.length();
        boolean[][] match = new boolean[alen + 1][blen + 1];
        match[0][0] = true;//基底
        //case0:就是说a为""空时，看b中含有*的能否与空匹配 ""-"a"  ""-"a*"  ""-"ab*"  ""-"a*b*"
        for (int bi = 1; bi <= blen; bi++) {//先要把*代表0次的初始化一下 ""  "a*"是匹配的
            if (b.charAt(bi - 1) == '*') {
                match[0][bi] = match[0][bi - 2];
            }
        }
        for (int ai = 1; ai <= alen; ai++) {//列数
            for (int bi = 1; bi <= blen; bi++) {//行数
                //case1:一一匹配的情况
                if (b.charAt(bi - 1) == '.' || b.charAt(bi - 1) == a.charAt(ai - 1)) {
                    match[ai][bi] = match[ai - 1][bi - 1];
                } else if (b.charAt(bi - 1) == '*') {//是*的话也有可能匹配，所以要分情况讨论
                    // case2:有*出现的情况---不等于（）
                    //*是以它前面的字符的存在而存在的，所以要看它前面的字符
                    if (b.charAt(bi - 2) != a.charAt(ai - 1) && b.charAt(bi - 2) != '.') {
                        //这种情况下，只有它代表0个字符串时有可能匹配，所以就要看它前面的   "sa"  "sb*"只有b*代表0个字符时匹配，要看"sa" "s"是否匹配
                        match[ai][bi] = match[ai][bi - 2];
                    } else {
                        //case3:有*出现的情况---等于（）
                        match[ai][bi] = (match[ai][bi - 2]  //a*有0个a
                                || match[ai - 1][bi]        //a*有多个a
                                || match[ai][bi - 1]);      //a*有单个a
                    }
                }
            }
        }
        return match[alen][blen];
    }

    public static void main(String[] args) {
        RegularExpressionMatching10 regularExpressionMatching10 = new RegularExpressionMatching10();
        String a = "";
        String b = "ab*";
        boolean result = regularExpressionMatching10.isMatch(a,b);
        System.out.println(result);
    }
}
