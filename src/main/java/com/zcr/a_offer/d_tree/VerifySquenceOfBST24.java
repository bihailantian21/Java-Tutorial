package com.zcr.a_offer.d_tree;

/**
 * 24、二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class VerifySquenceOfBST24 {

    /**
     * 思路:
     * 在后序遍历得到的序列中，最后一个数是树的根节点 root；
     * 二叉搜索树的后序遍历数组可以划分为两部分。第一部分是左子树结点的值，它们都比根节点的值小；第二部分是右子树结点的值，它们都比根节点的值大；
     * 所以按照上面的方法，递归的时候，每次先确定根root，
     * 然后在[L,R]范围内每次先找到mid，即第一个>root.val的位置，后面的就是右子树，必须要全部>root.val，如果不满足就返回false；
     *
     * 举个栗子:
     * 数组{5, 7, 6, 9, 11, 10, 8} ，后序遍历结果的最后一个数字8就是根结点(root)的值。
     * 在这个数组中，前3 个数字5、7 、6都比8小，是值为8的结点的左子树结点；后 3 个数字9、11、10 都比 8 大，是值为 8 的结点的右子树结点。
     * 我们接下来用同样的方法确定与数组每一部分对应的子树的结构。这其实就是一个递归的过程。
     * 对于序列 5、7、6，最后一个数字 6 是左子树的根结点的值。数字 5 比 6 小，是值为 6 的结点的左子结点，而 7 则是它的右子结点。
     * 同样，在序列 9、11、10 中，最后一个数字 10 是右子树的根结点，数字9 比10 小，是值为 10 的结点的左子结点，而 11 则是它的右子结点。
     *
     * 反例: 另一个整数数组{7, 4, 6, 5}。
     * 后序遍历的最后一个数是根结点，因此根结点的值是 5。
     * 由于第一个数字 7 大于 5，因此在对应的二叉搜索树中，根结点上是没有左子树的，数字 7、4 和 6 都是右子树结点的值。
     * 但我们发现在右子树中有一个结点的值是 4，比根结点的值 5 小， 这违背了二叉搜索树的定义。因此不存在一棵二又搜索树, 它的后序遍历的结果是 7、4、6、5。
     *
     * 递归
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        //首先要明确，递归需要的初始变量：数组、左边界、右边界
        //递归的形式要按标准的来
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return helper(sequence,0,sequence.length - 1);
    }

    public boolean helper(int[] sequence,int left,int right) {
        //这个递归结束条件很重要！
        if (left >= right) {
            return true;
        }
        int root = sequence[right];
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
        boolean leftresult = helper(sequence,left,j - 1);
        boolean rightresult = helper(sequence,j,right - 1);
        return leftresult && rightresult;
    }


    /**
     * 非递归的写法:
     * 这种写法，是后往前(从前往后也可以)，将每一个数都看做某一棵子树的根，然后判断这颗子树之后是否满足(即前一部分是<root，后一部分是>root)；
     * 但是这种方式重复判断了一些问题，效率没有这么高；
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST2(int[] sequence) {
        if (sequence == null || sequence.length == 0)
            return false;
        for(int root = sequence.length - 1; root >= 0; root--){
            int p = 0;
            while(sequence[p] < sequence[root]) p++;
            while(sequence[p] > sequence[root]) p++;
            if(p != root)
                return false;
        }
        return true;
    }





    //自己的思路，一点也不流畅，写不出来
    //其实思路是对的，但是没有按照标准的递归形式来写，初始变量没有确定好，导致纠结于截取数组
    /*public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        int len = sequence.length;
        int root = sequence[len - 1];
        int i = 0;
        while (i < len && sequence[i] < root) {
            i++;
        }
        int j = i;
        for (; i < len; i++) {
            if (sequence[i] < root) {
                return false;
            }
        }
        boolean left = VerifySquenceOfBST(Arrays.copyOf(sequence,j - 1));
        //VerifySquenceOfBST(sequence,);); 没法处理，除非用system.copy
        int[] new1 =System.arraycopy(sequence, j, sequence1, 0, len - j - 1);
        boolean right = VerifySquenceOfBST();
        return left && right;
    }*/
}
