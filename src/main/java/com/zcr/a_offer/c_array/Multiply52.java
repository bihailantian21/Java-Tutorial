package com.zcr.a_offer.c_array;

/**
 * 52.构建乘积数组
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。（注意：规定B[0]和B[n-1] = 1）
 */
public class Multiply52 {

    /**
     * 显然O(N^2)的方法不是好方法，好的方法是分别从两边开始乘。
     * 一开始从左往右累乘到B[i]，但是不要包括A[i] (也就是A[0 ~ i-1])；
     * 第二次从后往前累乘到B[i]，也不要包括A[i](也就是A[i+1 ~ n-1])；
     *
     * 看个例子:
     *   0 1 2 3 4
     * A 1 2 3 4 5
     * B 1 1 2 6 24
     * B       30  24
     *
     *
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        int len = A.length;
        int[] B = new int[len];
        int mul = 1;                   //0 1 2 3 4
        for (int i = 0; i < len; i++) {//1 2 3 4 5
            B[i] = mul;                //1 1 2 6 24
            mul *= A[i];               //1 2 6 24 120
        }
        mul = 1;                            //0 1 2 3 4
        for (int i = len - 1; i >= 0; i--) {//1 1 2 6 24      1 2 3 4 5
            B[i] *= mul;                    //120 60 40 30 24
            mul *= A[i];                    //120 120  60  20  5
        }
        return B;

    }
}
