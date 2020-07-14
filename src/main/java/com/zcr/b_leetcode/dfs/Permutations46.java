package com.zcr.b_leetcode.dfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 46. Permutations
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */

/**
 * 46、全排列
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations46 {

    /**
     * 给一组不包含重复数字的数组，给出所有的排列组合
     *
     * 递归：
     * 初始变量：结果、当前列表的状态、数组、用hashset记录数组中的一个数是否在之前用到过了（或者用boolean array记录数组中某一位是否用过了）
     * 结束条件：退出条件，当前的list的size为3时，说明所有的数已经到放了List中了。这一轮递归结束！
     * 循环：遍历数组中所有的数字，看是否以前已经用过
     * 如果没有用过的话，那么它就是一个有效的数字，就把它加到当前数组中，在也加到set中说明我们用过了，还要记录当前数组中最后一个数字的位置，以便在回溯的时候将这个当前元素进行删除。
     * 例：
     * [1,2,3]
     * 一共三位，第一位在这三个数中任选一位、第二位在这三个数中任选一位、第三位在这三个数中任选一位（取当前第x位数的时候，我们需要直到上一位取了什么，所以说要用hashset记录这三个数中哪个已经用过了）
     *
     *
     * cur                  res                  set                                                  used[i]         preNum
     * []                    []                   []                                                     [f,f,f]      0
     * [1]                   []                  [1]      第一层for i=0                                   [t,f,f]       1
     * [1]->[1,2]           []                  [1,2]           第二层for i=0/i=1                         [t,t,f]       1
     * [1,2]->[1,2,3]       []                 [1,2,3]               第三层过for i=0/i=1/i=2              [t,t,t]       2
     * [1,2,3]          [[1,2,3]]               [1,2,3]                     第四层加入res。第四层结束。
     * ->[1,2]             [[1,2,3]]            [1,2]                第三层for循环结束。                   [t,t,f]
     * ->[1]              [[1,2,3]]              [1]            返回到第二层for循环                        [t,f,f]
     * [1]->[1,3]        [[1,2,3]]              [1,3]           第二层for i=2
     * [1,3]->[1,3,2]    [[1,2,3]]             [1,3,2]
     * ->[1,3]       [[1,2,3], [1,3,2]]         [1,3]          第二层for循环结束。
     *
     *
     * [2]        [[1,2,3], [1,3,2]]          [2]         返回到第一层for循环i=1
     * [2] ->[2,1]    [[1,2,3], [1,3,2]]        [2,1]
     *
     *
     *
     *                                                    返回到第一层for循环i=2
     * @param nums
     * @return
     */
    public List<List<Integer>> permutations(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> cur = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        helper(result,cur,nums,set);
        return result;
    }
    
    public void helper(List<List<Integer>> result,List<Integer> cur,int[] nums,Set<Integer> set) {
        int len = nums.length;
        if (cur.size() == len) {
            result.add(new LinkedList<>(cur));
        } else {
            for (int i = 0; i < len; i++) {
                if (set.contains(nums[i])) {
                    continue;
                }
                cur.add(nums[i]);
                set.add(nums[i]);
                int last = cur.size() - 1;
                helper(result,cur,nums,set);
                cur.remove(last);
                set.remove(nums[i]);
            }
        }
    }
}
