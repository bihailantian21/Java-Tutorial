package com.zcr.b_leetcode.dfs;

import java.util.LinkedList;
import java.util.List;

/**
 * 78. Subsets
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */

/**
 * 78、子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Subsets78 {

    /**
     * 给一组不相同的数字，返回所有可能的子集
     * 典型的递归
     * 方法一：
     * [1,2,3]
     * Cur:’’->1->1,2->1,2,3
     *          ->1,3
     *         2->2,3
     *         3
     *
     * 选出子集第一位可能的数字
     * 选出第二位可能的数字
     * 只能继续向前
     * ‘’,1,2,3,[1,2],[1,3],[2,3],[1,2,3]
     * 我们这个递归，每次调用函数做的事情是什么呢？
     * 变量：数组、当前下标、结果、当前子数组
     * 1、	将当前子数组cur加到结果res中
     * 2、	记录cur、看这个位置有什么选择、然后把这个选择去掉换成另一个别的选择
     * Cur.add(num) helper cur.remove
     * 注意这次的Helpr中应改变当前下标
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> cur = new LinkedList<>();
        helper(nums,0,result,cur);
        return result;
    }

    public void helper(int[] nums,int curIdx,List<List<Integer>> result,List<Integer> cur) {
        result.add(new LinkedList<Integer>(cur));
        for (int i = curIdx; i < nums.length; i++) {
            cur.add(nums[i]);
            helper(nums,i + 1,result,cur);
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * 方法二：
     * [1,2,3]
     * 遍历，一个一个的看，对于每个数字都有两个选择：选或不选。
     *         1     2     3
     * ‘’    ->’’   ->’’  ->’’
     *                    ->3
     *              ->2   ->2
     *                    ->23
     *       ->1   ->1   ->1
     *             ->13
     *             ->12  ->12
     *                   ->123
     * 最后一步得到的结果，就是我们所有的要求的子集
     * 1、	记录有效结果（有效结果：就是每一个数字都做过处理了，Index指向了array外面了）
     * 2、	不选当前数字、helper就是cur不加进去，但是curidx向前移动了一位
     * 3、	选择当前数字、helper
     * @param nums
     * @param curIdx
     * @param result
     * @param cur
     */
    public void helper2(int[] nums,int curIdx,List<List<Integer>> result,List<Integer> cur) {
        if (curIdx == nums.length) {
            result.add(new LinkedList<Integer>(cur));
        } else {
            helper(nums,curIdx + 1,result,cur);
            cur.add(nums[curIdx]);
            helper(nums,curIdx + 1,result,cur);
            cur.remove(cur.size() - 1);
        }
    }


    /**
     *
     */
}
