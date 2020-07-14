package com.zcr.b_leetcode;
import java.util.Arrays;

/**
 * 4. Median of Two Sorted Arrays
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * <p>
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */

/**
 * 4.寻找两个有序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 *
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class MedianofTwoSortedArrays4 {

    /**
     * 合并排序数组，根据数组的长度为奇数或是偶数得出最终的结果
     * 时间O(m+n)
     * 空间O(m+n)
     * @param nums1
     * @param nums2
     * @return
     */
    public double getmediaofTwoSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] array = new int[len1 + len2];
        double result = 0;
        array = mergeSortedArrays(nums1,nums2);

        int len = array.length;
        if (len % 2 == 1) {//奇数
            result = array[len / 2];
        } else {
            double right = array[len / 2 - 1];
            double left = array[len / 2];
            result = (right + left) / 2;//切记：int+int=int
        }
        return result;
    }

    /**
     * 合并两个有序数组，O(m+n)
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] mergeSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return nums2;
        }
        if (nums2 == null || nums2.length == 0) {
            return nums1;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] array = new int[len1 + len2];
        int k = 0;
        for (int i = 0; i < len1; i++) {
            array[k++] = nums1[i];
        }
        for (int i = 0; i < len2; i++) {
            array[k++] = nums2[i];
        }
        Arrays.sort(array);
        return array;
    }


    /**
     * 这道题让我们求两个有序数组的中位数，而且限制了时间复杂度为O(log (m+n))，看到这个时间复杂度，自然而然的想到了应该使用二分查找法来求解。
     * 那么回顾一下中位数的定义，如果某个有序数组长度是奇数，那么其中位数就是最中间那个，如果是偶数，那么就是最中间两个数字的平均值。
     * 这里对于两个有序数组也是一样的，假设两个有序数组的长度分别为m和n，由于两个数组长度之和 m+n 的奇偶不确定，因此需要分情况来讨论，
     * 对于奇数的情况，直接找到最中间的数即可，偶数的话需要求最中间两个数的平均值。
     * 为了简化代码，不分情况讨论，我们使用一个小trick，我们分别找第 (m+n+1) / 2 个，和 (m+n+2) / 2 个，然后求其平均值即可，这对奇偶数均适用。
     * 假如 m+n 为奇数的话，那么其实 (m+n+1) / 2 和 (m+n+2) / 2 的值相等，相当于两个相同的数字相加再除以2，还是其本身。
     *
     * 这里我们需要定义一个函数来在两个有序数组中找到第K个元素，下面重点来看如何实现找到第K个元素。
     * 首先，为了避免产生新的数组从而增加时间复杂度，我们使用两个变量i和j分别来标记数组nums1和nums2的起始位置。
     * 然后来处理一些边界问题，比如当某一个数组的起始位置大于等于其数组长度时，说明其所有数字均已经被淘汰了，相当于一个空数组了，
     * 那么实际上就变成了在另一个数组中找数字，直接就可以找出来了。
     * 还有就是如果K=1的话，那么我们只要比较nums1和nums2的起始位置i和j上的数字就可以了。
     *
     * 难点就在于一般的情况怎么处理？因为我们需要在两个有序数组中找到第K个元素，为了加快搜索的速度，我们要使用二分法，
     * 对K二分，意思是我们需要分别在nums1和nums2中查找第K/2个元素，注意这里由于两个数组的长度不定，
     * 所以有可能某个数组没有第K/2个数字，所以我们需要先检查一下，数组中到底存不存在第K/2个数字，如果存在就取出来，
     * 否则就赋值上一个整型最大值。
     *
     * 如果某个数组没有第K/2个数字，那么我们就淘汰另一个数字的前K/2个数字即可。
     * 有没有可能两个数组都不存在第K/2个数字呢，这道题里是不可能的，因为我们的K不是任意给的，而是给的m+n的中间值，
     * 所以必定至少会有一个数组是存在第K/2个数字的。
     *
     * 最后就是二分法的核心啦，比较这两个数组的第K/2小的数字midVal1和midVal2的大小，
     * 如果第一个数组的第K/2个数字小的话，那么说明我们要找的数字肯定不在nums1中的前K/2个数字，所以我们可以将其淘汰，
     * 将nums1的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归。
     *
     * 反之，我们淘汰nums2中的前K/2个数字，
     * 并将nums2的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归即可。
     * @param nums1
     * @param nums2
     * @return
     */
    public double getmediaofTwoSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] array = new int[len1 + len2];
        double result = 0;

        return result;
    }

    /**
     * 时间O(log(m+n))
     * 空间O(m+n)
     * 需要用二分法来做，难点：两个数组有重合部分的，所以去找中值的话，明显不是他们的medium
     * 对于偶数个：10(5,6  (10+1)/2=5 (10+2)/2=6 )中位数是最中间两个数的平均值，(len+1)/2
     * 对于奇数个：11(6    (11+1)/2=6)中位数是最中间的那个数，(len+1)/2
     * 不管是奇数还是偶数，我们medium中的第一个数的位置就是(len+1)/2
     * 我们可以知道中位数所在位置就是(len+1)/2
     * 总结：我们要求的就是两个数组中第(len+1)/2位置上的数
     * 我根本不在乎中位数前面那些数和后面那些数具体是什么，有没有排好序，我们都不需要去考虑，只需要考虑边界值，也就是第(len+1)/2位置上的数---这样的话就是可以用二分法求的
     * 只用知道中位数前半段一共有(len+1)/2个数【包含中位数在内】
     *
     * 例：A 1 3 6 9 20 25
     *    B 2 4 7 16 17 28
     * A长度为6，B长度为6，AB总长度为12，则要找的中位数的位置为(12+1)/2=6，前半段所用的元素数量一共是6个！
     *
     * 首先对A做二分法
     * start:0 end:6 Apart:(0+6)/2=3即A在前半段中出了3个  Bpart:6-3=3即B在前半段中出3个
     *           apartleft aprtright
     * 将A分割也就是 1 3 6 | 9 20 25
     * 将B分割也就是 2 4 7 |16 17 28
     *          bpartleft prtright
     * 然后看这样出力，这样分割到底行不行
     *
     * 我们所要关注的就是四个边界值，通过边界值的比较验证这样取行不行，看他们两两之间的关系
     * apartleft需要小于bpartright apartleft< bpartright 6<16 满足
     * bpartleft需要小于apartright bpartleft< apartright 7<9 满足
     * 没问题，我们一步就求出来了直接在这里分割就可以。
     * 前6个数1 3 6 2 4 7 取得是正确的
     *
     * 二分法分割完毕后最后我们取得正确的数
     * max(apartleft,bpartleft) 最大的那一个就是靠近边界的值了，就是前半部分的最大值。7
     * 如果总长度是奇数个，我们到这里就结束了，就求得了中位数是7.
     * 如果总长度是偶数个，还需要求后半部分的最小值，min(apartright,bpartright)。9
     * 中位数为:(7+9)/2=8
     * -----------------------------------------------------------------------------------------
     * 例：A 1 3 6 6 20 25
     *    B 2 4 7 16 17 28
     * A长度为6，B长度为6，AB总长度为12，则要找的中位数的位置为(12+1)/2=6，前半段所用的元素数量一共是6个！
     *
     * 首先对A做二分法
     * start:0 end:6 Apart:(0+6)/2=3即A在前半段中出了3个  Bpart:6-3=3即B在前半段中出3个
     *           apartleft aprtright
     * 将A分割也就是 1 3 6 | 6 20 25
     * 将B分割也就是 2 4 7 | 16 17 28
     *          bpartleft prtright
     * 然后看这样出力，这样分割到底行不行
     *
     * 我们所要关注的就是四个边界值，通过边界值的比较验证这样取行不行，看他们两两之间的关系
     * apartleft需要小于bpartright apartleft< bpartright 6<16 满足
     * bpartleft需要小于apartright bpartleft< apartright 7<6 不满足
     * 有问题，在这里分割不可以。
     * 前6个数1 3 6 2 4 7 取得是不对的的，7已经不属于前半部分了，说明在总长度中，B占的太多了
     * 应该在A的二分法中，要在A的右半部分取值
     *
     * 原来A 0  3  6 现在在3和6之间找中位数
     * start:3 end:6 Apart:(3+6)/2=4即A在前半段中出了4个  Bpart:6-4=2即B在前半段中出2个
     *             apartleft aprtright
     * 将A分割也就是 1 3 6 6 | 20 25
     * 将B分割也就是 2 4 | 7  16 17 28
     *         bpartleft prtright
     * 然后看这样出力，这样分割到底行不行
     *
     * 我们所要关注的就是四个边界值，通过边界值的比较验证这样取行不行，看他们两两之间的关系
     * apartleft需要小于bpartright apartleft< bpartright 6<7 满足
     * bpartleft需要小于apartright bpartleft< apartright 4<20 满足
     * 没有问题，在这里分割可以。
     * 前6个数1 3 6 6 2 4  取得是对的
     *
     * 二分法分割完毕后最后我们取得正确的数
     * max(apartleft,bpartleft) 最大的那一个就是靠近边界的值了，就是前半部分的最大值。6
     * 如果总长度是奇数个，我们到这里就结束了，就求得了中位数是6.
     * 如果总长度是偶数个，还需要求后半部分的最小值，min(apartright,bpartright)。20
     * 中位数为:(6+20)/2=13
     *
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double getmediaofTwoSortedArrays3(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0 && len2 == 0) {
            return 0;
        }
        if (len2 < len1) {//每次都以最小的那个数组找二分点，这么做其实原因是时间复杂度是O(log(min(m,n)))
            int[] temp = nums2;
            nums2 = nums1;
            nums1 = temp;
            len1 = nums1.length;
            len2 = nums2.length;
        }
        int half = (len1 + len2 + 1) / 2;//求中位数前半部分有多少个元素
        boolean even = ((len1 + len2) % 2) == 0 ? true:false;
        //如果是奇数个，只需要求max(apartleft,bpartleft)
        //偶数个，求max(apartleft,bpartleft)、min(apartrigth,bpartright)的平均值
        int start = 0;
        int end = len1;
        int apart = 0;
        int bpart = 0;
        while (start <= end) {
            apart = (start + end) / 2;// A在前半部分占的个数
            bpart = half - apart;// B在前半部分占的个数
            if (apart > start && nums1[apart - 1] > nums2[bpart]) {//apart > start为了排除比较短的array是空的
                //注意：A左边的是是apart-1右边的值是apart,B左边的是是bpart-1右边的值是bpart
                // apartleft需要小于bpartright apartleft< bpartright否则A的二分就在A在前半部分重新进行（在总长度中，A占的太多了）
                end = apart - 1;
            } else if (apart < end && nums1[apart] < nums2[bpart - 1]) {
                //bpartleft需要小于apartright bpartleft< apartright否则A的二分就在A的后半部分重新进行（在总长度中，B占的太多了）
                start = apart + 1;
            } else {//说明已经找到值 apartleft<bpartright bpartleft<apartright
                //就可以找max(apartleft,bpartleft)、min(apartright,bpartright)了
                int leftmax = 0;
                if (apart == 0) {//说明a中最小数都大于B的数，就是说两个数组没有重合 A：6 7 8 9 10 B：1 2 3 4 5
                    leftmax = nums2[bpart - 1];//那么前半段的最后一个临界值，就是B中的左
                } else if (bpart == 0) {//说明a中最大数小于B中第一个数           A：1 2 3 4 5  B：6 7 8 9 10
                    leftmax = nums1[apart - 1];//那么前半段的最后一个临界值，就是A中的左
                } else {//常规于中间位置符合条件
                    leftmax = Math.max(nums1[apart - 1],nums2[bpart - 1]);
                }
                if (!even) {
                    return leftmax;
                }
                int minRight = 0;
                if (apart == len1) {//说明前半部分全在Len1部分，a中所有数小于b的第一个数
                    //说明a中最大数小于B中第一个数           A：1 2 3 4 5  B：6 7 8 9 10
                    minRight = nums2[bpart];//那么后半段的第一个临界值，就是B中的右
                } else if (bpart == len2) {//b中所有数都小于a中的最后一个数
                    //说明a中最小数都大于B的数，就是说两个数组没有重合 A：6 7 8 9 10 B：1 2 3 4 5
                    minRight = nums1[apart];//那么后半段的第一个临界值，就是A中的右
                } else {//常规情况
                    minRight = Math.min(nums2[bpart],nums1[apart]);
                }
                return (leftmax + minRight) / 2.0;
            }
        }
        return 0;

    }

    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2,4};
        MedianofTwoSortedArrays4 medianofTwoSortedArrays4 = new MedianofTwoSortedArrays4();
        double result = medianofTwoSortedArrays4.getmediaofTwoSortedArrays3(nums1,nums2);
        System.out.println(result);
    }


}
