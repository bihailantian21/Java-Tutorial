package com.zcr.b_leetcode;

/**
 * 80. Remove Duplicates from Sorted Array II
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example 1:
 * Given nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It doesn't matter what you leave beyond the returned length.
 *
 * Example 2:
 * Given nums = [0,0,1,1,1,1,2,3,3],
 * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It doesn't matter what values are set beyond the returned length.
 *
 * Clarification:
 * Confused why the returned value is an integer but your answer is an array?
 * Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
 * Internally you can think of this:
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeDuplicates(nums);
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 */

/**
 * 80、删除排序数组中的重复项
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 * 给定 nums = [1,1,1,2,2,3],
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 */
public class RemoveDuplicatesFromSortedArray280 {

    /**
     * 从有序数组中移除重复的字符
     *
     * 快慢指针
     * 快指针：遍历所有的数字
     * 慢指针：发现一个不同的数字，我把它放在什么位置
     * 如果当前数字和后一个相同：慢指针不变
     * 不同：说明一个新的数字出现了，把新的数字放在慢指针的位置，慢指针前移
     *
     *
     * 使得每个字符最多出现两次，去重之后返回的数组有多长
     * f1 f2f3
     * | | |
     * 1 2 2 2 3 3 4
     * | |
     * S1 s2
     *
     * 1、fast：遍历所有的数字
     * Low：下一个遇到的数要放到什么位置
     * 2、	fast和low初始位置都是在下标为2的位置，因为允许每个字符最多出现两次，所以前两个字符无论是什么都行
     * 3、	当前数与后一个相同，且当前数与前一个相同，fast不变，low++
     *  如果skip呢？其实就是将slow不变
     * 4、	如果当前数字fast有效的话，两个指针同时移动
     * @param array
     * @return
     */
    public int removeDuplicates(int[] array) {
        /*int slow = 1;
        for (int fast = 1; fast < array.length; fast++) {
            if (array[fast] != array[fast - 1]) {
                array[slow++] = array[fast];
            }
        }
        return slow;*/
        if (array == null || array.length <= 2) {
            return array.length;
        }
        int slow = 2;
        for (int fast = 2; fast < array.length; fast++) {
            if (!(array[slow - 1] == array[slow - 2] && array[slow - 1] == array[fast])) {
                //前面两个相同且当前与前一个相同，说明有超过两个重复的数了，
                // 则表明我们要将fast下一位放到当前的slow中，所以只需要fast++，slow不变
                //除此之外，都是同时移动两个指针
                array[slow++] = array[fast];
            }
        }
        return slow;
    }

}
