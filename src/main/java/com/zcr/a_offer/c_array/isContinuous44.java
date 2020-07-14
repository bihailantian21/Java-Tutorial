package com.zcr.a_offer.c_array;


import java.util.Arrays;

/**
 * 43.扑克牌顺子
 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
 * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
 * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
 * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
 *
 * LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。
 * 为了方便起见,你可以认为大小王是0。
 *
 * 题目：
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2~10为数字本身，A为1，J为11，Q为12，K为13，而大、小王可以看成任意数字。
 * 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
 */
public class isContinuous44 {

    /**
     * 思路一
     * 先把数组排序。
     * 由于 0 可以当成任意数字，我们可以用 0 去补满数组中的空缺。如果排序之后的数组不是连续的，即相邻的两个数字相隔若干个数字，
     * 但只要我们有足够的 0 可以补满这两个数字的空缺，这个数组实际上还是连续的。
     * 举个例子，数组排序之后为{0，1，3，4，5}，在1和3之间空缺了一个 2，刚好我们有一个 0，也就是我们可以把它当成 2去填补这个空缺 。
     *
     * 分析了上面的问题之后，我们就可以整理出解题步骤:
     * （1）首先把数组排序，再统计数组中 0 的个数，最后统计排序之后的数组中相邻数字之间的空缺总数；
     * （2）如果空缺的总数小于或者等于 0 的个数，那么这个数组就是连续的，反之则不连续；
     * 还需要注意: 如果数组中的非 0 数字重复出现，则该数组不是连续的。即如果一副牌里含有对子，则不可能是顺子；
     *
     * 抽象建模能力：把扑克牌转换成数组、把找顺子通过排序、计数的过程实现
     *
     * @param numbers
     * @return
     */
    public boolean isContinuous(int [] numbers) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        Arrays.sort(numbers);
        int numberofZero = 0;
        int numberofGap = 0;
        for (int i = 0; i < numbers.length && numbers[i] == 0; i++) {
            numberofZero++;
        }
        int small = numberofZero;
        int big = small + 1;
        while (big < numbers.length) {
            if (numbers[small] == numbers[big]) {
                return false;
            }
            numberofGap += numbers[big] - numbers[small] - 1;
            small = big;
            big++;
        }
        return numberofZero >= numberofGap ? true : false;
    }

    public static void main(String[] args) {
        isContinuous44 isContinuous44 = new isContinuous44();
        boolean result = isContinuous44.isContinuous(new int[]{1, 3, 2, 6, 4});
        System.out.println(result);
    }
}
