package com.zcr.b_leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. Combination Sum
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * The same repeated number may be chosen from candidates unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 *
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * Example 2:
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */

/**
 * 39、组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 示例 1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class CombinationSum39 {

    /**
     *
     * 和Subset的区别：
     * 1.每个数可以重复选
     * 2.不是所有的组合我都要，必须filter出符合target的情况
     *
     *
     *
     *
     *
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

    /**
     * 去重复
     * 在搜索的时候，需要设置搜索起点的下标 begin ，由于一个数可以使用多次，下一层的结点从这个搜索起点开始搜索；
     * 在搜索起点 begin 之前的数因为以前的分支搜索过了，所以一定会产生重复。
     *
     * 剪枝提速
     * 如果一个数位搜索起点都不能搜索到结果，那么比它还大的数肯定搜索不到结果，基于这个想法，我们可以对输入数组进行排序，以减少搜索的分支；
     * 排序是为了提高搜索速度，非必要；
     * 搜索问题一般复杂度较高，能剪枝就尽量需要剪枝。把候选数组排个序，遇到一个较大的数，如果以这个数为起点都搜索不到结果，后面的数就更搜索不到结果了。
     * @param candidates
     * @param target
     * @param result
     * @param index
     * @param curSeq
     */
    public void helper(int[] candidates,int target,List<List<Integer>> result,int index,List<Integer> curSeq) {
        if (target == 0) {
            result.add(new ArrayList<>(curSeq));
        } else {
            for (int i = index; i < candidates.length; i++) {
                if (candidates[i] > target) {
                    break;
                }
                curSeq.add(candidates[i]);
                helper(candidates,target - candidates[i],result,i,curSeq);
                curSeq.remove(curSeq.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {1,2,2,3,4,5};
        int target = 7;
        CombinationSum39 combinationSum39 = new CombinationSum39();
        List<List<Integer>> result = combinationSum39.combinationSum(candidates,target);
        System.out.println(Arrays.toString(result.toArray()));
        System.out.println(result.size());

    }
}
