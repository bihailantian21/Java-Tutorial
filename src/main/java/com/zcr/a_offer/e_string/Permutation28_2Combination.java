package com.zcr.a_offer.e_string;


/**
 * 27、字符串的排列
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 *
 * 27_2、字符串的组合
 * 输入一个字符串,按字典序打印出该字符串中字符的所有组合。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串a,ab,bc,ac,abc
 * ab和ba是同一种组合
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * leetcode
 * 使用回溯法
 */
public class Permutation28_2Combination {


    /**
     * 如果不是求字符的所有排列，而是求字符的所有组合。比如，输入三个字符a,b,c，则它们的组合有a,b,c,ab,ac,bc,abc。
     * 当交换字符串中的两个字符时，虽然能得到两个不同的排列，但却是同一个组合，比如ab和ba是不同的排列，但只算一个组合。
     *
     * 分析：我们可以将字符串中的每个字符看成二叉树的一个节点，根节点为空，每个节点都会有两种选择：要 和 不要 两种选择。那么我们就可以利用递归实现。
     * 如：abc
     *i            ""
     *0       a          ""
     *1  ab      a      b       ""
     *2 abc ab  ac a   bc b    c ""
     * @param s
     * @return
     */

    /**
     * 思路分析：
     * 如果输入n个字符，则这n个字符能构成长度为1的组合，长度为2的组合，……..，长度为n的组合。
     * 在求n个字符长度的为m（1<=m<=n）的时候，我们把这n个字符分为两部分，第一个字符和其余的所有字符。
     * 如果组合里包括第一个字符，那么就从其余的字符里选取m-1个字符；如果组合里不包括第一个字符，那么就从其余字符里选取m个字符。
     * 也就是说，我们可以把求n个字符组成长度为m的组合的问题分解成两个子问题：
     * 1.求n-1个字符串中长度为m-1的组合
     * 2.求n-1个字符串中长度为m的组合
     * 递归结束的条件就是，当m为0，即从字符串中不再选出字符的时候，这个时候已经找到了m个字符的组合，输出即可。还有一个条件是，当输入的字符串是串，自然是不能从中选出任何字符的。
     * 这两个子问题都可以用递归来解决。
     *
     * 初始遍历那个：待排列组合的数组str、待取的下一个字符的在数组的起始位置索引begin、表示还需要取的字符的个数num、当前结果数组、保存每一种结果数组的集合
     *
     * 例：abc
     * 长度为1：begin:a
     * 长度为2：
     * 长度为3:
     *
     * @param
     * @return
     */
    public static void main(String[] args){
        char[] str = {'a','b','c'};
        Permutation28_2Combination permutation27_2Combination = new Permutation28_2Combination();
        ArrayList<ArrayList<Character>> result= permutation27_2Combination.Combination(str);
        System.out.println(Arrays.toString(result.toArray()));
    }

    public ArrayList<ArrayList<Character>> Combination(char[] str) {
        ArrayList<ArrayList<Character>> result = new ArrayList<>();
        if (str == null || str.length == 0){
            return result;
        }
        ArrayList<Character> cur = new ArrayList<>();
        for (int num = 1;num <= str.length;num++) {
            helper(str, 0, num,cur, result);  //从数组中第一个字符开始，依次取num个，num >=1 && num <= 数组长度
        }
        return result;
    }
    /**
     * @param str    待排列组合的数组
     * @param begin  待取的下一个字符的在数组的起始位置索引
     * @param num    表示还需要取的字符的个数
     * @param result 保存每一种组合的字符
     */
    public static void helper(char[] str, int begin, int num, ArrayList<Character> cur,ArrayList<ArrayList<Character>> result){
            //注意：begin > str.length - 1这个条件不能放在这里一起判断
            // 因为有时候当num为0的时候，begin也满足begin>str.len-1，但是这时候我们已经在result中的字符是一种组合，应该保证先输出再返回
        if (num == 0){   //如果num为0,说明已经凑够了num个字符，直接输出并返回
            result.add(new ArrayList<>(cur));
            return;
        }
        if (begin > str.length-1)
            return;
        cur.add(str[begin]);    //当前的字符被选中
        helper(str,begin+1,num-1,cur,result);   //则从索引位置为begin+1的位置开始选择剩下的num-1个字符
        cur.remove(cur.size()-1);    //当前的字符未被选中
        helper(str,begin+1,num,cur,result);   //则从索引位置为begin+1的位置继续选择num个字符
    }
}

