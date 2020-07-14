package com.zcr.a_offer.c_array;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 33、把数组排成最小的数
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class PrintMinNumber33 {

    /**
     * 最直接的方法是求出数组中所有数字的全排列，然后把每个排列拼起来，然后求出拼起来的数字的最小值。
     * 根据排列组合的知识，n个数字有n!种排列。我们再来看下面更快的方法。
     *
     * 这题关键是找到一个排序规则。然后数组根据这个规则排序就能排成一个最小的数字。
     * 也就是是给出两个数字 a 和 b，我们需要确定一个规则判断 a 和 b 哪个应该排在前面，而不是仅仅比较这两个数字的值哪个更大。
     *
     * 如果只看长度不行，但是只看数字大小也不行。但是如果我们限定长度相同呢?
     *
     * 根据题目的要求，两个数字 a 和 b 能拼接成数字 ab 和 ba(此时ab和ba位数长度相同)。
     * 如果ab<ba，那么我们应该打印出 ab，也就是 a 应该排在 b 的前面，我们定义此时 a 小于b；(若ab < ba 则 a < b)
     * 反之，如果 ba<ab，我们定义b 小于a。 (若ab > ba 则 a > b)
     * 如果 ab=ba，a等于b。(若ab = ba 则 a = b)
     * 如果直接用数字去拼接ab，可能会溢出。但是我们可以直接用字符串拼接，然后定义字符串的比较规则即可。(隐形的大数问题)、
     *
     * 因为ab和ba位数相同，那么只要用字符串比较大小就好。
     *
     * 举个例子:
     * a = "3"、b = "31"，不能简单的将a放在b的前面，
     * 而是因为331 > 313( ab > ba)，应该将b放在a的前面；
     *
     *
     *
     *
     * Comparator是个接口，可重写compare()及equals()这两个方法
     * compare（a,b）方法:根据第一个参数小于、等于或大于第二个参数分别返回负整数、零或正整数。
     *                           -1       0       1
     * equals（obj）方法：仅当指定的对象也是一个 Comparator，并且强行实施与此 Comparator 相同的排序时才返回 true。
     *
     *
     * compareTo() 方法用于将 Number 对象与方法的参数进行比较。可用于比较 Byte, Long, Integer等。
     * 该方法用于两个相同数据类型的比较，两个不同类型的数据不能用此方法来比较。
     * 返回值
     * 如果指定的数与参数相等返回0。
     * 如果指定的数小于参数返回 -1。
     * 如果指定的数大于参数返回 1。
     *
     * @param numbers
     * @return
     */
    public String PrintMinNumber(int [] numbers) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i] + "");
        }
       /* Collections.sort(list, (o1, o2) -> {
            return (o1 + o2).compareTo(o2 + o1);  //Comparator按照降序排列(第一个大于第二个返回1-->升序排列)
                                                  //compareTo如果指定的数大于参数返回 1。
        });*/
       /* Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });*/
       Collections.sort(list, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
       StringBuilder res = new StringBuilder();
       for (String s : list) {
           res.append(s);
       }
       return res.toString();
    }
}
