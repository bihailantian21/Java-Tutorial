package com.zcr.a_offer.c_array;

import java.util.ArrayList;

/**
 * 41.和为S的两个数字
 * 题目描述
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 *
 * 输出描述:
 * 对应每个测试案例，输出两个数，小的先输出。
 */
public class FindNumbersWithSum41_2 {

    /**
     * 直观反应，遍历每个数，遍历每个数的时候，再在其余数字中遍历找到和为S的数字。
     * 时间：O(n^2)
     *
     * 比较简单的双指针题目。肯定不是两重循环找。
     * 思路
     * 设两个指针L、R，分别是排序数组的开头和结尾；
     * 然后下面就是两个指针L、R向中间靠拢的过程。
     * ① 如果arr[L] + arr[R] > sum，说明右边那个arr[R]大了，需要向左移动，看能不能找到更小的arr[R]来和arr[L]一起组成sum。
     * ② 同理，如果arr[L] + arr[R] < sum，说明左边那个arr[L]小了，需要向右移动，看能不能找到更大的arr[L]来和arr[R]一起组成sum。
     * ③否则等于就返回即可；
     * 题目说要找到乘积最小的，可以发现，L、R隔的越远，arr[L] * arr[R]乘积越小，所以我们的做法没问题。
     *
     * 时间O(n)
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        if (array == null || array.length == 0) {
            return result;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            if (array[left] + array[right] > sum) {
                right--;
            } else if (array[left] + array[right] < sum) {
                left++;
            } else {
                result.add(array[left]);
                result.add(array[right]);
                return result;//这个特别重要！要不找到了，还要一直变化
            }
        }
        return result;
    }
}
