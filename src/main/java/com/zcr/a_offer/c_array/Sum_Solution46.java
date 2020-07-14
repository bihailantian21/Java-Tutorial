package com.zcr.a_offer.c_array;


/**
 * 46.求1+2+3+...+n，
 * 要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class Sum_Solution46 {

    /**
     * 思路: 利用逻辑与的短路特性实现递归终止。
     * 当n == 0时，n > 0 && (res += Sum_Solution(n-1)) > 0只执行前面的判断，为false，然后直接返回0；
     * 当n > 0时，递归res += Sum_Solution(n-1)，实现递归计算；
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        int res = n;
        boolean b = n > 0 && (res += Sum_Solution(n-1)) > 0;
        return res;
    }
}
