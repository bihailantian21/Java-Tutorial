package com.zcr.a_offer.c_array;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 34.丑数
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。
 *
 * 求按从小到大的顺序的第N个丑数。
 */
public class GetUglyNumber34 {




    /**
     *
     * 一个数m是另一个数n的因子，是指n能被m整除。n%m==0
     * 如果一个数能被2整除，我们把它连续除以2；如果一个数能被3整除，我们把它连续除以3；如果一个数能被5整除，我们把它连续除以5
     * 如果最后我们得到的是1，那么这个数就是丑数，否则不是。
     *
     * 按照顺序判断每一个数是不是丑数
     *
     * 最大的问题：每个整数都需要计算，即使它不是丑数，也需要做取余数和除法运算。时间复杂度太大。
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) {
            return 0;
        }
        int number = 0;
        int uglufoung = 0;
        while (uglufoung < index) {
            ++number;
            if (isUgly(number)) {
                ++uglufoung;
            }
        }
        return number;
    }

    public boolean isUgly(int number) {
        while (number % 2 == 0) {
            number /= 2;
        }
        while (number % 3 == 0) {
            number /= 3;
        }
        while (number % 5 == 0) {
            number /= 5;
        }
       /* if (number == 1) {
            return true;
        } else {
            return false;
        }*/
        return (number == 1) ? true : false;
    }

    public static void main(String[] args) {
        int index = 11;
        GetUglyNumber34 getUglyNumber34 = new GetUglyNumber34();
        int result = getUglyNumber34.GetUglyNumber_Solution2(index);
        System.out.println(result);
    }



    /**
     * 比较常规的方法，枚举Integer.MAX_VALUE范围内的所有丑数，打表之后排序，时间复杂度O(NlogN)。
     *
     * 这里将筛选部分写成静态代码块，就不会在测试的时候调用多次。
     *
     * 空间换取时间。O(nlogn)
     *
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution2(int index) {
        if (index <= 0) {
            return 0;
        }
        ArrayList<Integer> nums = new ArrayList<>();
        for(long a = 1; a <= Integer.MAX_VALUE; a *= 2){
            for(long b = a; b <= Integer.MAX_VALUE; b *= 3){
                for(long c = b; c <= Integer.MAX_VALUE; c *= 5)
                    nums.add((int)c);
            }
        }
        Collections.sort(nums);// NlogN
        return nums.get(index - 1);
    }


    /**
     *
     * 1、丑数应该是另一个丑数乘以2、3或5的结果（除了1之外）。因此我们可以创建一个数组，里面的数字都是排好序的丑数。每一个丑数都是前面的丑数乘以2、3或5的结果。
     * 2、关键：怎样确保数组里的丑数是排好序的。
     * 3、假设数组中已经有若干个丑数排好序后存放在数组中，把已有的最大丑数记为M。
     * 接下来分析如何生成下一个丑数。
     * 4、首先考虑把已有的每个丑数乘以2，得到若干个小于或等于M的结果。由于按照顺序生成，所以小于或等于M的结果已经在数组中了，。
     * 5、还有若干个大于M的结果，但我们只需要第一个大于M的结果，因为我们希望丑数是从小到大顺序生成的，其他更大的结果以后再说。
     * 6、我们把得到的第一个乘以2后大于M的结果记为M2。第一个乘以3后大于M的结果记为M3。第一个乘以5后大于M的结果记为M5。
     * 7、下一个丑数应该是M2M3M5这三个数的最小值。
     * 注意：提到要把每个已有的丑数都乘以2、3、5，事实上这不是必须的，因为已有的丑数是按照顺序存放在数组中的。
     * 对乘以2而言，肯定存在某一个丑数T2，排在它之前的每一个丑数乘以2d得到的结果都会小于已有的最大丑数，
     * 排在它之后的每一个丑数乘以2d得到的结果都会太大。我们只需要记下这个丑数的位置，同时每次生成新的丑数的时候，去更新这个T2。
     * 也存在着这样的T3T5。
     *
     * 空间换取时间。O(n)
     *
     *
     * O(N)的思路如下:
     * 为了保持丑数数组的顺序，可以维护三个队列q2、q3、q5，分别存放每次由上一个最小的没有用过的丑数乘以2、3、5得到的丑数：
     * 过程:
     * (1)、一开始第一个丑数为1，将 1 * 2放入q2，1 * 3放入q3，1 * 5放入q5；
     * (2)、取三个队列中最小的为2，将2 * 2 = 4放入q2，2 * 3 = 6放入q3，2 * 5 = 10放入q5；
     * (3)、取三个队列中最小的为3，将3 * 2 = 6放入q2，3 * 3 = 9放入q3，3 * 5 = 15放入q5；
     * ....
     * (6)、取三个队列中最小的为6，注意这里要将q2、q3都要弹出，因为q2、q3的对头都是6，然后。。。
     * 然后我们只需要在丑数数组中取index- 1个即可，由于只是一个索引，所以丑数数字可以用一个变量candi和一个索引count记录即可，代码如下:
     * 这里不需要再用列表把每个结果都存下来了，直接就只是滚动更新。
     *
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution3(int index) {
        if (index <= 0) {
            return 0;
        }
        Queue<Integer> q2 = new LinkedList<>();
        Queue<Integer> q3 = new LinkedList<>();
        Queue<Integer> q5 = new LinkedList<>();
        int candi = 1;//记录候选数
        int count = 1;//记录下标值
        while (count < index) {
            q2.add(candi * 2);
            q3.add(candi * 3);
            q5.add(candi * 5);
            int min = Math.min(q2.peek(),Math.min(q3.peek(),q5.peek()));
            if (q2.peek() == min) {
                q2.poll();
            }
            if (q3.peek() == min) {
                q3.poll();
            }
            if (q5.peek() == min) {
                q5.poll();
            }
            candi = min;
            count++;
        }
        return candi;
    }


}
