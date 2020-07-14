package com.zcr.a_offer.c_array;

import java.util.HashMap;

/**
 * 29.数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class MoreThanHalfNum_Solution29 {


    /**
     * 方法一：
     * 使用map来保存每个元素出现的次数，只要某个元素次数超过array.length/2就返回，很简单。
     *
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        int len = array.length;
        if (array == null || len == 0) {
            return 0;
        }
        if (len == 1) {
            return array[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(array[i], map.getOrDefault(array[i], 0) + 1);
            if (map.get(array[i]) > len / 2) {
                return array[i];
            }
        }
        return 0;
    }

    /***
     * 思路二
     * 把数组排序，排序之后位于数组中间的数字一定是出现次数超过数组长度一半的数字。
     * 我们有成熟的算法能够在O(n)时间内得到数组中第k大的算法。
     *
     * 随机排序排序算法中：我们现在数组中随机选择一个数字，然后调整数组中数字的顺序，比它小的排在左边，比它大的排在右边。
     * 如果选中的数字下标刚好是n/2，那么这个数字就是数组的中位数。
     * 如果它的下标大于n/2，那么中位数位于它的左边，我们可以接着在它左边部分继续查找。
     * 如果它的下标小于n/2，那么中位数位于它的右边，我们可以接着在它右边部分继续查找。
     * 这是典型的递归过程。
     *
     * 使用类似快速排序partition的思想:
     * 对于次数超过一半的数字，则数组中的中位数一定是该数字(如果数组中真的存在次数超过一半的数字)；
     * 所以我们可以利用partition()然后将找到那个数，这个数可以将数组划分成左右两边的数的个数相同的两部分。
     * 时间复杂度为O(n)。
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution1(int[] array) {
        int len = array.length;
        if (array == null || len == 0) {
            return 0;
        }
        if (len == 1) {
            return array[0];
        }
        int low = 0;
        int high = array.length - 1;
        int pivot = partition(array, low, high);
        int mid = len / 2;
        System.out.println(pivot);
        while (pivot != mid) {
            if (pivot > mid) {
                high = pivot - 1;
                pivot = partition(array, low, high);
            } else {
                low = pivot + 1;
                pivot = partition(array, low, high);
            }
        }
        int res = array[mid];
        int times = 0;
        for (int i = 0; i < len; i++) {
            if (array[i] == res) {
                times++;
            }
        }
        if (times > len / 2) {
            return res;
        }
        return 0;
    }

    public static int partition(int[] a, int low, int high) {
        int pivotKey = a[low];  //用第一个元素作为基准元素
        while (low < high) { //两侧交替向中间扫描
            while (low < high && a[high] >= pivotKey)
                high--;
            a[low] = a[high];
            while (low < high && a[low] <= pivotKey)
                low++;
            a[high] = a[low];
        }
        a[low] = pivotKey;
        return low;     //返回基准元素所在位置
    }


    public static void main(String[] args) {
        MoreThanHalfNum_Solution29 moreThanHalfNum_solution29 = new MoreThanHalfNum_Solution29();
        int[] nums = {5, 1, 2, 3, 2, 2, 2, 4, 2};
        int tar = 2;
        //int re = moreThanHalfNum_solution29.MoreThanHalfNum_Solution1(nums);
        //System.out.println(re);
        int test = moreThanHalfNum_solution29.partition(nums, 0, nums.length - 1);
        System.out.println(test);

    }

    /**
     * 思路三－摩尔投票解法
     * 如果有符合条件的数字，则它出现的次数比其他所有数字出现的次数和还要多；
     * 在遍历数组时保存两个值：一个是数组中每次遍历的候选值candi，另一个当前候选值的次数times；
     * 遍历时，若当前值它与之前保存的候选值candi相同，则次数times加1，否则次数减1；
     * 若次数为0，则保存下一个新的数字candi，并将新的次数times置为1；
     * 由于我们要找的数字出现的次数比其他所有数字出现的次数之和还要多，那么要找的数字肯定是最后一次把次数设置为1时对应的数字。
     *
     *
     *
     * 遍历结束后，所保存的数字(剩下的)即为所求。当然还需要判断它是否符合条件(因为有可能没有数字次数>N/2)；
     *
     * 详细说法:
     * 我们把变量 candi 叫作候选，times 叫作次数，先看第一个for循环。
     * times==0 时，表示当前没有候选，则把当前数 arr[i]设成候选，同时把 times 设置成1。
     * times!=0 时，表示当前有候选，如果当前的数 arr[i]与候选一样，就把times 加 1；
     * 如果当前的数 arr[i]与候选不一样，就把 times 减 1, 减到0则表示又没有候选了。
     *
     * 具体的意思是:
     * 当没有候选时，我们把当前的数作为候选，说明我们找到了两个不同的数中的第一个，当有候选且当前的数和候选一样时，
     * 说明目前没有找到两个不同的数中的另外一个, 反而是同一种数反复出现了, 那么就把 times++表示反复出现的数在累计自己的点数。
     * 当有候选且当前的数和候选不一样时，说明找全了两个不同的数，但是候选可能在之前多次出现，如果此时把候选完全换掉，
     * 候选的这个数相当于一下被删掉了多个，对吧? 所以这时候选“付出”一个自己的点数，即 times 减 1，然后当前数也被删掉。
     * 这样还是相当于一次删掉了两个不同的数。当然，如果 times 被减到为0，说明候选的点数完全被消耗完，那么又表示候选空缺，
     * arr 中的下一个数(arr[i+1])就又被作为候选。
     *
     * 综上所述，第一个 for 循环的实质就是我们的核心解题思路，一次在数组中删掉两个不同的数，不停地删除，直到剩下的数只有一种，
     * 如果一个数出现次数大于一半，则这个数最后一定会被剩下来，也就是最后的 candi 值。
     *
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
}
