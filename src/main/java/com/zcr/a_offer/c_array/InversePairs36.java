package com.zcr.a_offer.c_array;

/**
 * 36.数组中的逆序对
 * 题目描述
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 *
 * 输入描述:
 * 题目保证输入的数组中没有的相同的数字
 *
 * 数据范围：
 * 	对于%50的数据,size<=10^4
 * 	对于%75的数据,size<=10^5
 * 	对于%100的数据,size<=2*10^5
 *
 * 示例1
 * 输入
 * 1,2,3,4,5,6,7,0
 * 输出
 * 7
 *
 * 例：
 * 7,5,6,4
 * 逆序对有7,6  7,5 7,4 5,4 6,4
 * 5对
 */
public class InversePairs36 {

    /**
     * 直观反应是顺序扫描整个数组，每扫描到一个数字的时候，逐个比较数字和它后面数字的大小。
     * 如果后面的数字比它小，则这两个数字组成了一个逆序对。
     * 假设数组有n个数字，每个数字都要和后面的O(n)个数字进行比较。
     *
     * 时间：O(n^2)
     *
     * 每扫描到一个数字的时候，我们不能逐个比较数字和它后面数字的大小。我们可以先考虑比较两个相邻的数字。
     * 例：
     *     7564
     *  75      64
     * 7  5    6  4
     * 5  7    4  6
     *    4567
     * （1）先把数组分解成两个长度为2的子数组
     * （2）再把这两个子数组分别拆分成两个长度为1的子数组
     * 接下来一边合并子数组，一边统计逆序对的数目。
     * 逆序对75 逆序对64
     * 0  1
     * 7  5     7  5
     * |  |        |
     *    7     5  7
     *    |     |
     *
     * 0  1
     * 6  4     6   4
     * |  |         |
     *    6      4  6
     *    |      |
     * （3）由于我们已经统计了这两对子数组内部的逆序对，因此需要把这两对子数组排序，以免在以后的统计过程中再重复统计。
     * 0  1    2  3
     * 5  7    4  6      5  7    4  6      5  7    4  6
     *    |       |      |          |      |       |
     *            7              6  7          5   6   7
     *            |              |             |
     * 74  76             54
     *
     * （4）接下来我们统计两个长度为2的子数组之间的逆序对：
     * [1]我们先用两个指针分别指向两个子数组的末尾，并每次比较两个指针指向的数字。
     * [2]如果第一个子数组中的数字大于第二个子数组中的数字，则构成逆序对。并且逆序对的数目等于第二个子数组中剩余数字的个数。
     * [3]如果第一个子数组中的数字小于第二个子数组中的数字，则不构成逆序对。
     * [4]每一次比较的时候，我们都把较大的数字从后往前复制到一个辅助数组中去，确保辅助数组中的数字是递增排序。
     * [5]在把较大的数字复制到辅助数组中后，把对应的指针往前移动一位，接下来进行下一轮的比较。
     *
     * 总结：先把数组分隔成子数组，先统计出子数组内部的逆序对的数目，然后再统计出两个相邻子数组之间的逆序对的数目。
     * 在统计逆序对的过程中，还需要对数组进行排序。
     *
     *
     * 经典的利用归并排序解决的问题。
     * 利用归并排序的每次merge()过程：
     * 因为merge()过程，处理的两个范围都是有序的，即[L, mid]有序， [mid+1, R]有序；
     * 我们可以在这里做手脚，当两部分出现arr[p1] > arr[p2]的时候，结果直接可以累加mid - p1 + 1个，这样就可以利用归并来降低复杂度；
     * 注意这个过程明显会修改输入的数组array。
     *
     *
     * 时间：O(nlogn)
     * 空间：O(n)
     * @param array
     * @return
     */
    public int InversePairs(int [] array) {
        if (array == null || array.length <= 1) {
            return 0;
        }
        return mergeSort(array,0,array.length - 1);
    }

    private int mergeSort(int[] array, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = (l + r) / 2;//l + (r - l)>>1;
        int leftre = mergeSort(array,l,mid);           //1
        int rightre = mergeSort(array,mid + 1,r);   //1
        int insidere = merge(array,l,mid,r);           //3
        return leftre + rightre + insidere;
    }

    //l,mid有序 mid+1,r有序
    private int merge(int[] array, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int helpi = r -l;
        int p1 = mid;
        int p2 = r;
        int insidere = 0;
        while (p1 >= l && p2 >= mid + 1) {
            if (array[p1] > array[p2]) {
                //System.out.println(array[p1]+""+array[p2]);
                insidere += p2-(mid+1)+1;//p2-(mid+1)+1    p2-mid
                help[helpi--] = array[p1--];
            } else {
                help[helpi--] = array[p2--];
            }
        }
        while (p1 >= l) {
            help[helpi--] = array[p1--];
        }
        while (p2 >= mid + 1) {
            help[helpi--] = array[p2--];
        }
        for (int i = 0; i < help.length; i++) {
            array[l + i] = help[i];
        }
        return insidere;
    }

    public static void main(String[] args) {
        InversePairs36 inversePairs36 = new InversePairs36();
        int result = inversePairs36.InversePairs(new int[]{1,2,3,4,5,6,7,0});
        System.out.println(result);
    }


}
