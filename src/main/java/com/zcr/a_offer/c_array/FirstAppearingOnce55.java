package com.zcr.a_offer.c_array;

/**
 * 55.字符流中第一个不重复的字符
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 *
 * 输出描述:
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 */
public class FirstAppearingOnce55 {

    /**
     * 解法一，O(N)。
     * 最容易想到的是: 先用一个容器或者字符串记录Insert的字符，
     * 并且使用一个哈希表count数组统计出现次数。
     * 然后查询的时候，就遍历容器，第一个c[str[i]] == 1的就是答案。
     * 但是这种方法需要遍历容器一遍，也就是时间复杂度是Insert的长度O(N)。
     * @param ch
     */
    private StringBuilder sb = new StringBuilder();
    private int[] c = new int[256];

    //Insert one char from stringstream
    public void Insert(char ch)
    {
        sb.append(ch);
        c[ch]++;
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        for (int i = 0; i < sb.length(); i++) {
            if (c[sb.charAt(i)] == 1) {
                return sb.charAt(i);
            }
        }
        return '#';
    }


    /**
     * 第二种优化的方法是使用一个队列，来记录c[ch] == 1的，然后每次查询的时候，
     * 从对头取，直到取到c[q.peek()] == 1的，就是我们要的，
     * 因为队列的先进先出，所以对头的一定是我们之前最早加入的。
     *
     * 这种方法将时间复杂度从O(N)降低到O(256)。
     *
     * 例如，加入googl的过程。
     * @param ch
     */
    /*//Insert one char from stringstream
    public void Insert2(char ch)
    {

    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce2()
    {

    }*/
}
