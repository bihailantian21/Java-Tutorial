package com.zcr.a_offer.c_array;

/**
 * 51.数组中重复的数字
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
 * 请找出数组中任意一个重复的数字。
 *
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 *                       {T,T,T,T,2,5,3}
 */
public class Duplicate51 {


    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false

    /**
     * 直接用一个数组保存那个数之前有没有出现过即可。因为数在0~n-1之间，所以数组只需要开n即可。
     *
     * 时间：O(n)
     * 空间：O(n)
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        boolean[] used = new boolean[length];
        for (int num : numbers) {
            if (used[num]) {
                duplication[0] = num;
                return true;
            }
            used[num] = true;
        }
        return false;
    }

    /**
     * 充分利用数只出现在0 ~ n-1之间，所以我们每次将arr[ abs(arr[i])] 标记成它的相反数；
     * 下次，如果再发现一个arr[i]，且arr[ abs(arr[i])] < 0，说明之前已经标记过了，所以可以返回arr[i]；
     * 时间：O(n)
     * 空间：O(1)
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate2(int numbers[],int length,int [] duplication) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            int idx = Math.abs(numbers[i]);
            if (numbers[idx] >= 0) {
                numbers[idx] = -numbers[idx];
            } else {
                duplication[0] = idx;
                return true;
            }
        }
        return false;
    }


}
