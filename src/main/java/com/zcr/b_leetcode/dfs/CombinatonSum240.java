package com.zcr.b_leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 *
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */

/**
 * 40、组合总和2
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class CombinatonSum240 {

    /**
     * 给一组没有重复数字的数组和一个目标值，找出所有的符合条件的组合。
     * 如：{1,2,3,4,5} 7
     * 0     1     2     3     4     5     6
     * 1(7)->1(6)->1(5)->1(4)->1(3)->1(2)->1(1)->(0) [1,1,1,1,1,1,1]
     *                                   ->2(1)->break
     *                             ->2(2)->(0)       [1,1,1,1,1,2]
     *                             ->3(2)->break
     *                       ->2(3)->2(1)->break
     *                       ->3(3)->3(0)             [1,1,1,1,3]
     *                       ->4(3)->break
     *                 ->2(4)->2(2)->(0)            [1,1,1,2,2]
     *                       ->3(2)->break
     *                 ->3(4)->3(1)->break
     *                 ->4(4)->(0)                   [1,1,1,4]
     *                 ->5(4)->break
     *           ->2(5)->2(3)->2(1)->break
     *                 ->3(3)->(0)                   [1,1,2,3]
     *                 ->4(3)->break
     *           ->3(5)->3(2)->break
     *           ->4(5)->4(1)->break
     *           ->5(5)->(0)                       [1,1,5]
     *     ->2(6)->2(4)->2(2)->(0)                   [1,2,2,2]
     *                 ->3(2)->break
     *           ->3(4)->3(1)->break
     *           ->4(4)->5(0)                        【1,2,4】
     *           ->5(4)->break
     *     ->3(6)->3(3)->3(0)                         [1,3,3]
     *           ->4(3)->break
     *     ->4(6)->4(2)->break
     *     ->5(6)->5(1)->break
     * 2(7)->2(5)->2(3)->2(1)->break
     *           ->3(3)->(0)                         [2,2,3]
     *           ->4(3)->break
     *     ->3(5)->3(2)->break
     *     ->4(5)->4(1)->break
     *     ->5(5)->(0)                                  [2,5]
     * 3(7)->3(4)->3(1)->break
     *     ->4(4)->(0)                                  [3,4]
     * 4(7)->4(3)->break
     * 5(7)->5(2)->break
     * 总结：
     * 1、break只是跳到了上一个for循环，也就是说当前for循环中此时已经不满足了（当前的数已经超过了当前目标数），所以剩下的也肯定不满足。
     * 所以就回溯到上一个for循环中。将上一个for循环最后的数移除替换为++的数。
     * 2、当每次找到满足条件的列表后，就在当前for循环中一直往下，将当前for循环最后的数移除替换为++的数。
     * 时间复杂度：2^5=32
     *
     * 回溯法、也就是递归
     * 1、排序。然后找到合适组合。
     * 2、递归的思路：
     * 例：
     * 第一个数取1，目标是7；
     * 第二个数取2，目标是6；第二个数取3，目标是6；第二个数取6，目标是6；第二个数取5，目标是6；
     * 第三个数取3，目标是4；第三个数取4，目标是4，…
     * 1(7)->2(6)->3(4)->4(1)->break
     * 1(7)->2(6)->4(4)->(0) Target为0时，取到了正确的结果，但是不包括当前的数。找到了一组有效解。
     * 1(7)->2(6)->5(4)->break
     * 因为此时当前数已经大于了目标值，所以后面肯定取不到了（因为后面的数肯定都比当前数大），所以要break
     * 然后回溯回去，看
     * 1(7)->3(6)…
     * 1(7)->4(6)…
     * 1(7)->5(6)…
     * 3、重点：看递归函数怎么写
     * 初始值：数组、当前目标值、当前要处理的数的下标、结果、当前已经有的conbination的组合是什么
     * 结束条件：如果当前目标值为0，就把当前已经有的conbination的组合放到结果列表中（之前的数组，是不包括当前下标所指的数字的）
     * 因为curSeq在之后回溯的过程中是会变的，所以我们这里要新建一个列表
     * 循环条件：如果当前目标值大于0，那么进入循环，从当前的下标值开始，一直到数组的最后。
     * 如：取了数1之后，后面可能取2、3、4、5，依次进行判断。
     * 如果当前的数大于目标值了，后面肯定取不到了（因为后面的数肯定都比当前数大），所以要break。提高效率。
     * 如果当前的数可以取的话（当前的数不大于目标值），将当前数加入到已有的组合中。
     * 然后递归取调用helper函数：目标值是目标值减去已经取了的数字；下标值变成了i，也就是我们当前取的这个数。
     * 最后从已有的组合中删掉最后的一个数，为什么要删呢？这是回溯常见的套路：如：1->2->3做完了；就把3拿掉，开始做1->2->4，针对4以后再做各种处理；然后把4拿掉，开始做1->2->5，针对5以后再做各种处理。
     * 4、break只是跳到了上一个for循环，也就是说当前for循环中此时已经不满足了（当前的数已经超过了当前目标数），所以剩下的也肯定不满足。
     * 所以就回溯到上一个for循环中。将上一个for循环最后的数移除替换为++的数。
     * 5、当每次找到满足条件的列表后，就在当前for循环中一直往下，将当前for循环最后的数移除替换为++的数。
     *
     *
     * 1、区别：和前一题的区别在于：给定的数组中可能会有重复的数字出现。
     * 这时候需要我们做一个去重的操作。
     * 如：{1,2,2',3...} 7
     * 1(7)->2(6)->2'(4)->3(1)
     *           ->3(4)
     *     ->2'(6)->3(4)
     * 1->2->3
     * 1->2’->3
     * 这两组姐是重复的，需要进行去除。
     * 什么时候需要去重：当array[idx]==array[idx-1]的时候，后面这一条分支是可以不用做的。
     * 2、还有一个区别：上一道题中，相同的数可以取多次，所以对于helper函数中下一个数就是index，而这道题中改为index+1
     *
     * 时间：O(2^n)
     * 空间：O()
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates,int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target <= 0) {
            return result;
        }
        Arrays.sort(candidates);
        List<Integer> curSeq = new ArrayList<>();
        helper(candidates,target,result,0,curSeq);
        return result;
    }

    public void helper(int[] candidates,int target,List<List<Integer>> result,int index,List<Integer> curSeq) {
        if (target == 0) {
            result.add(new ArrayList<>(curSeq));
        } else {
            for (int i = index; i < candidates.length; i++) {
                if (i != index && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                if (candidates[i] > target) {
                    break;
                }
                curSeq.add(candidates[i]);
                helper(candidates,target - candidates[i],result,i+1,curSeq);
                curSeq.remove(curSeq.size() - 1);
            }
        }
    }


//    private void process(int start, int[] candidates, int target, List<Integer> list) {
//        if (target < 0) {
//            return;
//        }
//        if (target == 0) {
//            lists.add(new ArrayList<>(list));
//        } else {
//            for (int i = start; i < candidates.length; i++) {
//                //因为这个数和上个数相同，所以从这个数开始的所以情况，在上个数里面都考虑过了，需要跳过
//                if (i > start && candidates[i] == candidates[i - 1]) {
//                    continue;
//                }
//
//                list.add(candidates[i]);
//                process(i + 1, candidates, target - candidates[i], list);
//                list.remove(list.size() - 1);
//            }
//        }
//
//    }
        /*public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> res = new ArrayList<>();
            dfs(res, candidates, new ArrayList<>(), target, 0);
            return res;
        }
        public void dfs(List<List<Integer>> res, int[] candidates, List<Integer> temp, int target, int nextIndex){
            if (target == 0){
                if (!res.contains(temp)) {
                    res.add(temp);
                }
                return;
            }
            if (nextIndex >= candidates.length || target < 0 || candidates[nextIndex] > target){
                return;
            }
            for (int i = nextIndex; i < candidates.length; i++) {
                if (candidates[i] > target){
                    return;
                }
                List<Integer> list = new ArrayList<>(temp);
                list.add(candidates[i]);
                dfs(res, candidates, list, target - candidates[i], ++nextIndex);
            }
        }*/



    public static void main(String[] args) {
        int[] candidates = {1,2,2,3,4,5};
        int target = 7;
        CombinatonSum240 combinationSum240 = new CombinatonSum240();
        List<List<Integer>> result = combinationSum240.combinationSum(candidates,target);
        System.out.println(Arrays.toString(result.toArray()));
        System.out.println(result.size());

    }
}
