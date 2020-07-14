package com.zcr.b_leetcode;

/**
 * 75. Sort Colors
 * Given an array with n objects colored red, white or blue,
 * sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color
 * red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for
 * this problem.
 *
 * Example:
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Follow up:i
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 */

/**
 * 75、颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，
 * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 *
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class SortColors75 {

    /**
     * 用0、1、2三个数字代表红、白、蓝
     *
     * 例：
     * [1,0,1,2,0,1]->[0,0,1,1,1, 2]
     * 方法一：countering sort
     * 先计数：0->2 1->3 2->1=>0011122
     *
     * 方法二：
     * 例：
     * [0,0,1,2,1,0,2,2]
     *      |     |
     * 1、First:在这个位置左边都是0，从头开始找到第一个不是0的位置
     * Last：这个位置之后的元素都是2，从尾部开始找到第一个不是2的位置
     * 具体工作过程：
     * 2、First位置先找好了，然后从这个位置往后进行遍历：
     * 看到了1，本身这个1放的就正确，继续向后遍历
     *      |
     * [0,0,1,2,1,0,2,2]
     *      |     |
     * 3、看到了2，将2和last位置指的数进行交换，然后last—，index不变（因为此时index的值是后面交换过去的，还需要检查）
     *        |
     * [0,0,1,2,1,0,2,2]
     *      |     |
     *        |
     * [0,0,1,0,1,2,2,2]
     *      |   |
     * 4、看到了0，将0和first位置指的数进行交换，然后first++，index++（因为交换后，此时index的值就是原来first的值已经被检查过了，所以要++）
     *          |
     * [0,0,0,1,1,2,2,2]
     *      |   |
     * 5、Index=last，说明所有的顺序已经排序完了
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int len = nums.length;
        int first = 0;
        while (first < len && nums[first] == 0) {
            first++;
        }
        int last = len - 1;
        while (last >= 0 && nums[last] == 2) {
            last--;
        }
        int index = first;
        while (index <= last) {
            if (nums[index] == 1) {
                index++;
            } else if (nums[index] == 2) {
                switchColors(nums,index,last);
                last--;
            } else if (nums[index] == 0) {
                switchColors(nums,index,first);
                first++;
                index++;
            }
        }

    }

    public void switchColors(int nums[],int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
