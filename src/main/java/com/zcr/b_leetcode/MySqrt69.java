package com.zcr.b_leetcode;

public class MySqrt69 {

    /**
     * x:1~2^31-1
     * res:1~根号下2^31-1=N
     * res:1______N
     *       t1
     * 用二分法，先选定start（1）和end（N），取mid(t1).
     * 然后比较t1^2和n做比较，
     *
     * 1~105
     * 1~10
     * 90
     * 104
     * @param n
     * @return
     */
    public int mySqrt(int n) {
        if (n <= 0) {
            return 0;
        }
        int resMax = (int) Math.sqrt(Integer.MAX_VALUE);
        int start = 1;
        int end = resMax;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int sum = mid * mid;
            if (sum == n) {
                return mid;
            } else if (sum < n) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (end * end <= n) {
            return end;
        } else {
            return start;
        }
    }

    /**
     * 解法二：
     * 1^2 2^2 3^2 4^2 5^2 6^2
     * 1   4   9   16  25  36
     * counter1   2   3   4   5   6
     * 差值add 3   5   7    9   11
     * 间隔是2
     * Cur    1   4   9   16   25  36
     * Add+=2 cur+=add counter++
     * 1+2=3 1+3=4       2
     * 3+2=5 4+5=9       3
     * 5+2=7 7+9=16      4
     * Cur<x
     * Cur>x时候，记录一个counter
     * 当x接近最大值的时候，可能会有溢出，所以需要判断
     * @param n
     * @return
     */
    public int mySqrt2(int n) {
        if (n <= 0) {
            return 0;
        }
        int add = 1;
        int cur = 0;
        int counter = 0;
        while (cur <= n) {
            if (Integer.MAX_VALUE - cur < add) {//不能写这样！！因为这样写你就已经溢出了if (cur + add > Integer.MAX_VALUE - )
                return counter;
            }
            cur += add;
            add += 2;
            counter++;
        }
        return counter - 1;
    }
}
