package com.zcr.b_leetcode;

/**
 * 60. Permutation Sequence
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * Note:
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 *
 * Example 1:
 * Input: n = 3, k = 3
 * Output: "213"
 *
 * Example 2:
 * Input: n = 4, k = 9
 * Output: "2314"
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 60、第k个排列
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 *
 * 示例 1:
 * 输入: n = 3, k = 3
 * 输出: "213"
 *
 * 示例 2:
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class PermutationSequence60 {

    /**
     * 给定一个整数数组，可以有多个由数组中数字不同顺序组成的数字，给出第k个。
     *
     * n=3 3！ 1 2 3
     * 0 123 | 0
     * 1 132 |
     * 2 213 |   1
     * 3 231 |
     * 4 312 |     2
     * 5 321 |
     * 1、我们将它分成n份，每一组都有(n-1)!个数字
     * 我们要找第k个数，就去找k在哪个区间
     * K分法的概念。
     * 2、我们通过找其中一个区间，就可以确定最高位的数是多少。
     * 比如它是2，再把这个区间分为n-1份，每一组都有(n-2)!个数字。
     * 原来要找的是第k个数，现在在小子集中要找的，是第k-x个数，x是之前的区间的数。
     * 3、...
     *
     * 一共有n的阶乘个数
     * 怎么分组，每一组有多少个数？
     * 我们有一个n位的数，
     * 1()(n-1)!
     * 12()(n-2)!
     * f[0]=1 f[i]=f[i-1]*i i是[1,n-1]i代表的意思是：确定了首位之后，剩下的i位能有多少种不同数字的组合
     * 如：3位数，我确定了最高位1，剩下两位不同的组合有f(2)=2种。
     *
     * 我们怎么确定第k个数在哪个组里呢？
     * 先把k-1，然后(k-1)/每一组有多少个数=那个组的最高位在原序列中的下标。
     * 然后更新k，k%每一组有多少个数=更新后的k
     * 如：我们呢要找第4个数，4-1=3，组数：3/2=1，0(1)1(2)2(3)，所以所在组的最高位就是2。
     * 然后更新k， 3%2=1。
     *
     * 完整的例子：
     * n=3 3！ 1 2 3   k=4
     * 0 123 | 0
     * 1 132 |
     * 2 213 |   1
     * 3 231 |
     * 4 312 |     2
     * 5 321 |
     * 1、
     * i=0
     * f[0]=1
     * i[1,2]
     * f[1]=1*f[0]=1
     * f[2]=2*f[1]=2
     * 2、
     * i[1,3]
     * nums=[1,2,3]
     *       0 1 2
     * 3、
     * k=4-1=3
     * 4、
     * i[0,2]
     * 0 1 2
     * _ _ _
     * i=0
     * n-1-i=3-1-0=2 f[2]=2当我们确定最前面这位数的时候，剩下两个数是不确定的，这两位有多少种组合。
     * 3/2=1代表了组数的下标
     * result[0]=nums[1]=2 nums=[1,3]
     * k=3%2=1
     *
     * i=1
     * n-1-i=3-1-1=1 f[1]=1当我们呢确定了最前面的两位数的时候，剩下的一个数有多少种组合。
     * 1/1=1
     * result[1]=nums[1]=3 nums=[1]
     * k=1%1=0
     *
     * i=2
     * n-1-i=3-1-2=0 f[0]=1
     * 0/1=0
     * result[2]=nums[0]=1 nums=[]
     * k=0%1=0
     *
     * 则要找的数就是：231
     *
     * 总结：
     * 1、	求阶乘的结果：阶乘的意义：求每一组对应的有多少个数字
     * 2、	构建可用的数字的List
     * 3、	K—是把第k个数字变成从0开始的坐标的表示
     * 4、	从最高位开始，一位一位的来确定数字。
     *
     * 总结：
     * 解题思路：将 n! 种排列分为：n 组，每组有 (n - 1)!个排列，根据k值可以确定是第几组的第几个排列，
     * 选取该排列的第1个数字，然后递归从剩余的数字里面选取下一个数字，直到n=1为止。
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @param k
     * @return
     */
    public String permutationSequence(int n,int k) {
        char[] result = new char[n];
        List<Integer> nums = new ArrayList<>();
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = i * factorial[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        k--;
        for (int i = 0; i < n; i++) {
            result[i] = Character.forDigit(nums.remove( k / factorial[n - i - 1]),10);
            k %= factorial[n - i - 1];
        }
        return new String(result);
    }
}
