package com.zcr.a_offer.c_array;


import java.util.ArrayList;
import java.util.List;

/**
 * 38.数字在排序数组中出现的次数
 * 统计一个数字在排序数组中出现的次数。
 */
public class GetNumberOfK38 {

    /**
     * 因为数组是排序的，所以从头到尾遍历O(N)的方法肯定不是好方法，所以想到二分。
     * 其实思路很简单，我们只需要利用二分找到第一个等于key的和最后一个等于key的位置，就能得到key出现的次数。
     * 而用二分得到这两个位置也很简单。
     *
     * 直观想法：使用二分法找到这个数之后，然后向前扫描、向后扫描。
     *
     * 如何更好的利用二分查找：如何使用二分法直接找到第一个k和最后一个k。
     *
     * 用二分法直接找到第一个k：
     * 如果中间数字比k大，下一轮在前半段找；
     * 如果中间数字比k小，下一轮在后半段找；
     * 如果中间数字等于k，先判断这个数字是不是第一个k：
     * 如果中间数的前一个数不等于k，那么说明中间数就是第一个k；
     * 如果中间数的前一个数等于k，那么就说明第一个k要在前半段找
     *
     *
     * 用二分法直接找到最后一个k：
     *
     * 时间：O(logn)
     *
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int [] array , int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int first = getFirstK(array,0,array.length - 1,k);
        //System.out.println(first);
        int last = getLastK(array,0,array.length - 1,k);
        //System.out.println(last);
        if (first != -1 && last != -1) {
            return last - first + 1;
        }
        return 0;
    }
    private int getFirstK(int[] array, int l, int r, int k) {
        while (l <= r) {
            int mid = (l + r) / 2;
            if (array[mid] > k) {
                r = mid - 1;
            } else if (array[mid] < k) {
                l = mid + 1;
            } else {
                //注意这里一定要先判断下标，不然会越界访问
                if ((mid > 0 && array[mid - 1] != k) || mid == 0) {
                    return mid;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    private int getLastK(int[] array, int l, int r, int k) {
        while (l <= r) {
            int mid = (l + r) / 2;
            if (array[mid] > k) {
                r = mid - 1;
            } else if (array[mid] < k) {
                l = mid + 1;
            } else {
                //注意这里一定要先判断下标，不然会越界访问
                if ((mid < array.length - 1 && array[mid + 1] != k) || mid == array.length - 1) {
                    return mid;
                } else {
                    l = mid + 1;
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        GetNumberOfK38 getNumberOfK38 = new GetNumberOfK38();
        int[] array = {3,3,3,3,4,5};
        int count = getNumberOfK38.GetNumberOfK(array,3);
        System.out.println(count);
    }




    //当存在多个相同的数值时，把所有的值都找到
    //思路分析：在找到mid值时，不要马上返回；
    // 向mid索引值的左边扫描，将所有满足1000的元素的下标都加入到一个集合中ArrayList
    //向mid索引值的右边扫描，将所有满足1000的元素的下标都加入到一个集合中ArrayList
    public static List<Integer> binarySerach2(int[] arr, int left, int right, int findVal) {

        /*//当left > right时，说明递归整个数组就是没有找到，就返回-1
        if (left > right) {
            return -1;
        }*/
        System.out.println("hello");


        while (left <= right) {
            int mid = (left + right) / 2;
            int midVal = arr[mid];

            if (findVal > midVal) {//向右递归
                return binarySerach2(arr,mid + 1,right,findVal);
            } else if (findVal < midVal) {//向左递归
                return binarySerach2(arr,left,mid,findVal);
            } else {
                List<Integer>  resIndexlist = new ArrayList<Integer>();
                int temp = mid - 1;
                while (true) {
                    if (temp < 0 || arr[temp] != findVal) {//退出
                        break;
                    }
                    //否则，就把temp放入到集合中
                    resIndexlist.add(temp);
                    temp--;//temp左移
                }

                resIndexlist.add(mid);

                temp = mid + 1;
                while (true) {
                    if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
                        break;
                    }
                    //否则，就把temp放入到集合中
                    resIndexlist.add(temp);
                    temp++;//temp左移
                }
                return resIndexlist;
            }
        }
        return new ArrayList<Integer>();

    }


}
