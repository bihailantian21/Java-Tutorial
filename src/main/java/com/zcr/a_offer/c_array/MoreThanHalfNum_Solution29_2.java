package com.zcr.a_offer.c_array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 29.数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 *
 * 29——2
 * LeetCode - 229. MajorityElementII
 * 求数组中>n/3的次数的数(最多两个)；
 * 求数组中>n/k的次数的数；
 */
public class MoreThanHalfNum_Solution29_2 {

    /**
     * 思路三－摩尔投票解法
     * 如果有符合条件的数字，则它出现的次数比其他所有数字出现的次数和还要多；
     * 在遍历数组时保存两个值：一个是数组中每次遍历的候选值candi，另一个当前候选值的次数times；
     * 遍历时，若当前值它与之前保存的候选值candi相同，则次数times加1，否则次数减1；
     * 若次数为0，则保存下一个新的数字candi，并将新的次数times置为1；
     * 由于我们要找的数字出现的次数比其他所有数字出现的次数之和还要多，那么要找的数字肯定是最后一次把次数设置为1时对应的数字。
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * 遍历结束后，所保存的数字(剩下的)即为所求。当然还需要判断它是否符合条件(因为有可能没有数字次数>N/2)；
     * <p>
     * 详细说法:
     * 我们把变量 candi 叫作候选，times 叫作次数，先看第一个for循环。
     * times==0 时，表示当前没有候选，则把当前数 arr[i]设成候选，同时把 times 设置成1。
     * times!=0 时，表示当前有候选，如果当前的数 arr[i]与候选一样，就把times 加 1；
     * 如果当前的数 arr[i]与候选不一样，就把 times 减 1, 减到0则表示又没有候选了。
     * <p>
     * 具体的意思是:
     * 当没有候选时，我们把当前的数作为候选，说明我们找到了两个不同的数中的第一个，当有候选且当前的数和候选一样时，
     * 说明目前没有找到两个不同的数中的另外一个, 反而是同一种数反复出现了, 那么就把 times++表示反复出现的数在累计自己的点数。
     * 当有候选且当前的数和候选不一样时，说明找全了两个不同的数，但是候选可能在之前多次出现，如果此时把候选完全换掉，
     * 候选的这个数相当于一下被删掉了多个，对吧? 所以这时候选“付出”一个自己的点数，即 times 减 1，然后当前数也被删掉。
     * 这样还是相当于一次删掉了两个不同的数。当然，如果 times 被减到为0，说明候选的点数完全被消耗完，那么又表示候选空缺，
     * arr 中的下一个数(arr[i+1])就又被作为候选。
     * <p>
     * 综上所述，第一个 for 循环的实质就是我们的核心解题思路，一次在数组中删掉两个不同的数，不停地删除，直到剩下的数只有一种，
     * 如果一个数出现次数大于一半，则这个数最后一定会被剩下来，也就是最后的 candi 值。
     * <p>
     * 检验:
     * 这里请注意，一个数出现次数虽然大于一半，它肯定会被剩下来，但那并不表示剩下来的数一定是符合条件的。
     * 例如，1，2，1。其中 1 符合出现次数超过了一半，所以1肯定会剩下来。再如 1，2，3，其中没有任何一个数出现的次数超过了一半，
     * 可 3 最后也剩下来了。所以 第二个 for 循环的工作就是检验最后剩下来的那个数(即 candi) 是否真的是出现次数大于一半的数。
     * 如果 candi 都不符合条件，那么其他的数也一定都不符合，说明 arr 中没有任何一个数出现了一半以上。
     *
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution2(int[] array) {
        int len = array.length;
        if (array == null || len == 0) {
            return 0;
        }
        if (len == 1) {
            return array[0];
        }
        int candi = 0;
        int times = 0;
        for (int i = 0; i < len; i++) {
            if (times == 0) {
                candi = array[i];
                times++;
            } else if (array[i] == candi) {
                times++;
            } else {
                times--;
            }
        }

        //最后一定要检验，不一定就是res
        times = 0;
        for (int i = 0; i < len; i++) {
            if (array[i] == candi) {
                times++;
            }
        }
        if (times > len / 2) {
            return candi;
        }
        return 0;
    }


    /**
     * LeetCode - 229. MajorityElementII
     * 第三种解法有两种变形题目，且都可以用摩尔投票问题解决:
     * 求数组中>n/3的次数的数(最多两个)；
     * 求数组中>n/k的次数的数；
     *
     * 先看第一个求数组中>n/3的次数的数：这个题目来自LeetCode229MajorityElement II，求出数组中>n/3次数的数。
     * 解析如下，和两个类似，只不过都多了一个变量:
     * 和>n/2次数的数解题方法很相似，>n/2的候选人candi只有一个，统计次数只有一个times；
     * 而>n/3次数的数解题是设置两个候选人candi1和candi2，并且设计两个统计次数count1和count2，按照类似的方法统计；
     *
     * 按照投票的说法，大于n/3次数的解题方法是:
     * 先选出两个候选人candi1、candi2，如果投candi1，则candi1的票数count1++，如果投candi2，则candi2的票数count2++；
     * 如果既不投candi1，也不投candi2，那么检查此时是否candi1和candi2候选人的票数是否已经为0，如果为0，则需要更换新的候选人；
     * 如果都不为0，则candi1和candi2的票数都要减一；当然最后也需要看看是否两个候选人的票数超过nums.length / 3;
     * LeetCode - 229. Majority Element II题解代码如下:
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len == 0) {
            return res;
        }
        if (len == 1) {
            res.add(nums[0]);
            return res;
        }
        int candi1 = 0;
        int candi2 = 0;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == candi1) {
                count1++;
            } else if (nums[i] == candi2) {
                count2++;
            } else if (count1 == 0) {
                candi1 = nums[i];
                count1++;
            } else if (count2 == 0) {
                candi2 = nums[i];
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        //此时选出了两个候选人，需要检查
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == candi1) {
                count1++;
            } else if (nums[i] == candi2) {
                count2++;
            }
        }
        if (count1 > nums.length / 3)
            res.add(candi1);
        if (count2 > nums.length / 3)
            res.add(candi2);
        return res;
    }


    /**
     * 然后看第二个问题求数组中>n/k的次数的数
     * 思路:
     * 一次在数组中删掉K个不同的数，不停地删除，直到剩下的数的种类不足 K，那么，如果某些数在数组中出现次数大于 n/k，则这些数最后一定会被剩下来。
     *
     * 在>n/2的问题中，解决的办法是立了 1个候选 candi，以及这个候选的 times 统计。
     * 在>n/3的问题中，解决的办法是立了 2个候选 candi1和candi2，以及这两个候选的count1、count2统计。
     * 这个问题要立 K-1 个候 选，然后有 K-1 个 times 统计。
     *
     * 具体过程如下。
     * 遍历到 arr[i]时，看 arr[i]是和否与已经被选出的某一个候选相同，
     * 如果与某一个候选相同，就把属于那个候选的点数统计加 1。
     * 如果与所有的候选都不相同，先看当前的候选是否选满了，K-1 就是满，否则就是不满；
     * 如果不满，把 arr[i]作为一个新的候选，属于它的点数初始化为 1。
     * 如果已满， 说明此时发现了天个不同的数，arr[i]就是第K个。
     * 此时把每一个候选各自的点数全部减 1，表示每个候选“付出”一个自己的点数。
     * 如果某些候选的点数在减 1之后等于0，则还需要把这些候选都删除，候选又变成不满的状态。
     * 在遍历过程结束后，再遍历一次 arr，验证被选出来的所有候选有哪些出现次数真的大于 n/k，符合条件的候选就存入结果；
     * 这里用LeetCode - 229. Majority Element II来测试我们的程序，可以发现是对的:
     * @param arr
     * @return
     */
    public List<Integer> printKMajority(int[] arr, int k) {
        List<Integer> res = new ArrayList<>();
        int len = arr.length;
        if (arr == null || len == 0) {
            return res;
        }
        if (len == 1) {
            res.add(arr[0]);
            return res;
        }

        HashMap<Integer,Integer> candis = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (candis.containsKey(arr[i])) { //在候选人的集合中有这个候选人了
                candis.put(arr[i], candis.get(arr[i]) + 1); //给他的票数+1
            } else {     //与所有的候选人都不同
                //候选人的集合已经满了(当前是第K个)，要把所有的候选人的票数减一，如果某些人的票数是1就要移除这个候选人
                if (candis.size() == k - 1) {
                    ArrayList<Integer> removeList = new ArrayList<>();
                    for (Map.Entry<Integer, Integer> entry : candis.entrySet()) {
                        Integer key = entry.getKey();
                        Integer value = entry.getValue();
                        if (value == 1) {
                            removeList.add(key);
                        } else {
                            candis.put(key, value - 1);
                        }
                    }
                    //删除那些value = 1的候选人
                    for (Integer removeKey : removeList) {
                        candis.remove(removeKey);
                    }
                } else {     //没有满,把这个加入候选人的集合
                    candis.put(arr[i], 1);
                }
            }
        }
        //检查候选人是否真的满足条件
        for (Map.Entry<Integer, Integer> entry : candis.entrySet()) {
            Integer key = entry.getKey();
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == key) {
                    sum++;
                }
            }
            if (sum > arr.length / k) {
                res.add(key);
            }
        }
        return res;
    }


}







