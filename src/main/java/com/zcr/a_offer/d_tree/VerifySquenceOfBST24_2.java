package com.zcr.a_offer.d_tree;

/**
 * 24_2、二叉搜索树的前序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的前序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class VerifySquenceOfBST24_2 {

    /**
     * 递归
     *
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        //首先要明确，递归需要的初始变量：数组、左边界、右边界
        //递归的形式要按标准的来
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return helper(sequence, 0, sequence.length - 1);
    }

    public boolean helper(int[] sequence, int left, int right) {
        //这个递归结束条件很重要！
        if (left >= right) {
            return true;
        }
        int root = sequence[left];
        int i = left;
        while (i < right && sequence[i] < root) {
            i++;
        }
        int j = i;
        for (; i < right; i++) {
            if (sequence[i] < root) {
                return false;
            }
        }
        boolean leftresult = helper(sequence, left + 1, j - 1);
        boolean rightresult = helper(sequence, j, right);
        return leftresult && rightresult;
    }
}


