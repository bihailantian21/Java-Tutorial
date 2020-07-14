package com.zcr.a_offer.c_array;

import java.util.ArrayList;

/**
 * 41.和为S的连续正数序列
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 *
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *
 * 输出描述:
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class FindContinuousSequence41 {

    /**
     * 滑动窗口！双指针！
     *
     * 双指针思路:
     * 两个指针 small 和 big 分别表示序列(窗口)的最小值和最大值。
     * 首先把 small 初始化为 1，big 初始化为 2。
     * 如果从small到 big 的序列的和大于 s，我们可以从序列中去掉较小的值，也就是增大 small 的值。
     * 如果从 small 到 big 的序列的和小于 s，我们可以增大big，让这个序列包含更多的数字。
     * 因为这个序列至少要有两个数字，我们一直增加 small 到 (1+s)/2 为止。
     *
     * 看一个例子，S = 9, mid = 5。
     *
     * 步骤small big	序列	  序列和	与S相比	下一步操作
     * 1	1	2	1, 2	3	小于	增大big
     * 2	1	3	1, 2, 3	6	小于	增大big
     * 3	1	4	1, 2, 3, 4	10	大于	增大small
     * 4	2	4	2, 3, 4	9	等于	打印序列，增大big
     * 5	2	5	2, 3, 4, 5	14	大于	增加small
     * 6	3	5	3, 4, 5	12	大于	增加small
     * 7	4	5	4, 5	9	等于	打印序列
     * 过程:
     * 先把 small 初始化为 1，big 初始化为2。
     * 此时介于 small 和 big 之间的序列是{1， 2}，序列的和为 3，小于9，记以我们下一步要让序列包含更多的数字。
     * 我们把 big 增加 1 变成 3，此时序列为{1，2,， 3}。由于序列的和是 6，仍然小于 9，我们接下来再增加big 变成4，
     * 介于 small 和 big 之间的序列也随之变成{1, 2, 3, 4}。由于序列的和 10 大于 9，我们要删去去序列中的一些数字，
     * 于是我们增加 small 变成2，此时得到的序列是{2, 3, 4}，序列的和正好是 9。
     * 我们找到了第一个和为 9 的连续序列，把它打印出来。
     * 接下来我们再增加 big，重复前面的过程， 可以找到第二个和为 9 的连续序列{4, 5}。
     *
     *
     *
     * 求连续序列的和用了一个小技巧：
     * 通常我们可以用循环求连续序列的和，但考虑到每一次操作之后的序列和操作之前的序列相比大部分数字都是一样的，
     * 只是增加或减少一个数字，因此我们可以在前一个序列和的基础上求操作之后的序列的和。
     * 这样可以减少很多不必要的计算，提高代码效率。
     * @param sum
     * @return
     */
    private ArrayList<ArrayList<Integer>> result;

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        result = new ArrayList<>();
        int left = 1;
        int right = 2;
        int mid = (sum + 1) / 2;
        int curSum = left + right;
        while (left < mid) {
            if (curSum == sum) {
                packing(left,right);
            }
            while (curSum > sum && left < mid) {//还要去找别的可能的连续序列
                curSum -= left;
                left++;
                if (curSum == sum) {
                    packing(left,right);
                }
            }
            right++;
            curSum += right;
        }
        return result;
    }

    public void packing(int left,int right) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            temp.add(i);
        }
        result.add(temp);
    }
}
