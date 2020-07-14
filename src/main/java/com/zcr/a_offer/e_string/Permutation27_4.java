package com.zcr.a_offer.e_string;


/**
 * 27_4、N皇后问题
 * 在8 X 8的国际象棋上摆放八个皇后，使其不能相互攻击，即任意两个皇后不得处于同一行，同一列或者同意对角线上，求出所有符合条件的摆法。
 * 思路：由于8个皇后不能处在同一行，那么肯定每个皇后占据一行，
 * 这样可以定义一个数组A[8]，数组中第i个数字，即A[i]表示位于第i行的皇后的列号。
 * 先把数组A[8]分别用0-7初始化，接下来对该数组做全排列，由于我们用0-7这7个不同的数字初始化数组，因此任意两个皇后肯定也不同列。
 * 那么我们只需要判断每个排列对应的8个皇后中是否有任意两个在同一对角线上即可，
 * 即对于数组的两个下标i和j，如果i-j==A[i]-A[j]或i-j==A[j]-A[i]，则认为有两个元素位于了同一个对角线上，则该排列不符合条件。
 */

public class Permutation27_4 {




}

    /*vector<vector<int>>ans;
    bool isRight(vector<int>a)
    {
        for (int i = 0; i < a.size(); i++)
        {
            for (int j = i+1; j < a.size(); j++)
            {
                if (abs(j - i) == abs(a[i] - a[j]))return false;
            }
        }
        return true;
    }

*/
