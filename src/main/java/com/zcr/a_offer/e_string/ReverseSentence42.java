package com.zcr.a_offer.e_string;

/**
 * 42.翻转单词顺序列
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，
 * 有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。
 * 后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
 * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class ReverseSentence42 {

    /**
     * 方法一
     * 直接从后面开始构造结果字符串即可，中间加上" "即可。很简单。
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        if (str.trim().equals("")) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        String[] strs = str.split(" ");
        /*for (int i = strs.length - 1; i >= 0; i--) {
            result.append(strs[i]).append(" ");
        }*
        //注意每一个后面都加空格的话，加到最后一个就多余了！所以结束条件是i>=
        /
         */
        for (int i = strs.length - 1; i > 0; i--) {
            result.append(strs[i]).append(" ");
        }
        result.append(strs[0]);
        return result.toString();
    }


    /**
     * 方法二(剑指Offer书上的解法)
     * 也很简单:
     * 1、就是先把整个字符串先翻转一下，例如"I am a student. "翻转成".tneduts a ma I"；
     * 2、然后再翻转每个单词中字符的顺序即可。
     * 翻转某个字符的某个区间写成一个函数reverse()即可；
     *
     * 关键：实现一个函数实现反转字符串中的一段
     * 在英文句子中，单词被空格符号分隔，我们可以通过扫描空格来确定每个单词的起止位置。
     * 翻转单词的中start指向单词的开始字符，end指向单词的结束字符。
     *
     *
     * case通过率为20.00%
     * 用例:
     * "Wonderful"
     * 对应输出应该为:
     * "Wonderful"
     * 你的输出为:
     * "lufrednoW"
     *
     * len=15
     * 0123456789101111314
     * I   a m     a student.
     *14131211   109876543210
     *
     * len=9
     * 012345678
     * Wonderful
     *
     * @param str
     * @return
     */
    public String ReverseSentence2(String str) {
        if (str.trim().equals("")) {
            return str;
        }
        int len = str.length();
        char[] chars = str.toCharArray();
        reverse(chars,0,len - 1);
        int start = 0;
        int end = 0;
        while (start < len ) {
            if (chars[start] == ' ') {
                start++;
                end++;
            } else if (end == len || chars[end] == ' '){   //注意：写成chars[end] == ' ' || end == len 就会报错，会出现outofarray
                reverse(chars,start,--end);
                start = ++end;
            } else {
                end++;
            }
        }
        return new String(chars);
    }
    /*public String ReverseSentence(String str) {
        if(str.trim().equals("")) return str;
        int n = str.length();
        char[] chs = str.toCharArray();
        // 1、先翻转整个字符串
        reverse(chs, 0, n-1);
        // 2、然后翻转其中的每一个单词
        for(int i = 0; i < n; ){
            while(i < n && chs[i] == ' ')i++; // 跳过空格
            int L = i, R = i;
            for(; i < n && chs[i] != ' '; i++, R++); // chs[R] = ' '
            reverse(chs, L, R-1); // notice is R - 1
        }
        return new String(chs);
    }*/

    public void reverse(char[] chars,int start,int end) {
        //如何反转？就是第一个和最后一个交换位置、第二个和倒数第二个交换位置...
        for (; start < end; start++,end--) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
        }
    }

    public static void main(String[] args) {
        ReverseSentence42 reverseSentence42 = new ReverseSentence42();
        String str = "Wonderful";
        String result = reverseSentence42.ReverseSentence2(str);
        System.out.println(result);


    }

}
