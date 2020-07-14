package com.zcr.a_offer.e_string;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * 27_3、
 * 输入一个含有8个数字的数组，判断有么有可能把这8个数字分别放到正方体的8个顶点上，使得正方体上三组相对的面上的4个顶点的和相等。
 *  思路：相当于求出8个数字的全排列，判断有没有一个排列符合题目给定的条件（ a1+a2+a3+a4=a5+a6+a7+a8
 *  a1+a3+a5+a7=a2+a4+a6+a8、a1+a2+a5+a6=a3+a4+a7+a8），即三组对面上顶点的和相等。
 *
 *  如：
 *  在正方体上标注a1,a2,a3,a4,a5,a6,a7,a8
 *  使得：
 *  a1+a2+a3+a4=a5+a6+a7+a8
 *  a1+a3+a5+a7=a2+a4+a6+a8
 *  a1+a2+a5+a6=a3+a4+a7+a8
 *
 */


public class Permutation28_3 {

    public static void main(String[] args) {
       Permutation28_3 permutation28_3 = new Permutation28_3();
        System.out.println(permutation28_3.com(new int[]{0, 1, 2, 3, 4, 5, 6, 7}));
    }

    public boolean com(int[] B) {
        Permutation28 permutation27 = new Permutation28();
        ArrayList<String> result = new ArrayList<>();
        String str = B.toString();
        result = permutation27.Permutation2(str);
        System.out.println(Arrays.toString(result.toArray()));
        for (int i = 0; i < result.size(); i++) {
            String s = result.get(i);
            char[] A = s.toCharArray();
            if (A[0] + A[1] + A[2] + A[3] == A[4] + A[5] + A[6] + A[7] &&
                    A[0] + A[2] + A[4] + A[6] == A[1] + A[5] + A[3] + A[7] &&
                    A[0] + A[1] + A[4] + A[5] == A[2] + A[3] + A[6] + A[7]) {
                System.out.println(s);
                return true;
            }
        }
        return false;
    }




}

   /*
        if (k == A.size())
        {
            if (A[0] + A[1] + A[2] + A[3] == A[4] + A[5] + A[6] + A[7] &&
                    A[0] + A[2] + A[4] + A[6] == A[1] + A[5] + A[3] + A[7] &&
                    A[0] + A[1] + A[4] + A[5] == A[2] + A[3] + A[6] + A[7])
            {
                for (int i = 0; i < A.size(); i++)cout << A[i] << " ";
                cout << endl;
            }
            return;
        }


*/
