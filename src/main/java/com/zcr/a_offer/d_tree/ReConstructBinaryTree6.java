package com.zcr.a_offer.d_tree;


import java.util.HashMap;
import java.util.Map;

/**
 * 6、重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class ReConstructBinaryTree6 {

    /**
     * 我们把构建一颗二叉树的大问题，分解成构建左右两棵子树的小问题。大问题和小问题在本质上是一样的，所以使用递归。
     * 以后解决复杂问题的套路。
     *
     * 前序：根左右
     * 中序：左根右
     * 1、根据前序和中序建树时，前序遍历的第一个结点就是根，在中序遍历中找到根所在的位置，
     * 计算的左子树长度(左边孩子的个数lLen)(可以得出右子树的长度 = 总长度-左子树长度-1)；
     * 2、这样在中序遍历中就确定了根节点的位置，且在pre数组中pre[pL+1, pL+lLen]之间都是根节点的左孩子；
     * 在in数组中in[iL, iL + lLen - 1]位置也都是根节点的左孩子，利用这个重新递归构造根节点的左子树即可；
     * 3、同理，在pre数组中pre[pL + lLen + 1 , pR]都是当前根节点的右孩子，
     * 在in数组中in[iL + lLen + 1 , iR]也都是当前根节点的右孩子，利用这两段重新构造根节点的右子树即可；
     * 4、注意根据前序遍历和中序遍历，中序遍历和后续遍历都可以建立一颗二叉树，
     * 但是根据前序遍历和后续遍历不可以确定一颗二叉树，前序和后序在本质上都只是将子节点和父节点分离，没有指明左右子树的能力。
     *
     * 递归的变量：
     * 递归结束条件：
     *
     * 例：
     *            0 1 2 3 4 5 6 7
     * 前序遍历序列{1,2,4,7,3,5,6,8}
     *            _
     * 中序遍历序列{4,7,2,1,5,3,8,6}
     *                 llen
     *                  _
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        return helper(pre,0,pre.length - 1,in,0,in.length - 1);
    }
    public TreeNode helper(int [] pre, int pl, int pr, int [] in, int il, int ir) {
        if (pl > pr || il > ir) {
            return null;
        }
        int rootnum = pre[pl];
        TreeNode root = new TreeNode(rootnum);
        int llen = 0;
        int i = il;
        while (i <= ir && in[i] != rootnum) {
            i++;
            llen++;
        }
        root.left =helper(pre,pl + 1,pl + llen,in,il,il + llen - 1);
        root.right=helper(pre,pl + llen + 1,pr,in,il + llen + 1,ir);
        return root;
    }
















    // 缓存中序遍历数组每个值对应的索引
    //Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 7
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();
    public TreeNode reConstructBinaryTree2(int [] pre, int [] in) {
        for (int i = 0; i < in.length; i++) {
            indexForInOrders.put(in[i],i);
        }
        return helper2(pre,0,pre.length - 1,in,0,in.length - 1);
    }
    public TreeNode helper2(int [] pre, int pl, int pr, int [] in, int il, int ir) {
        if (pl > pr || il > ir) {
            return null;
        }
        int rootnum = pre[pl];
        TreeNode root = new TreeNode(rootnum);
        int llen = indexForInOrders.get(rootnum);
        /*int llen = 0;
        int i = il;
        while (i <= ir && in[i] != rootnum) {
            i++;
            llen++;
        }*/
        root.left =helper2(pre,pl + 1,pl + llen,in,il,il + llen - 1);
        root.right=helper2(pre,pl + llen + 1,pr,in,il + llen + 1,ir);
        return root;
    }
    public static void main(String[] args) {
        int[] pre = {1,2,3,4,5,6,7};
        int[] in = {3,2,4,1,6,5,7};
        ReConstructBinaryTree6 reConstructBinaryTree6 = new ReConstructBinaryTree6();
        TreeNode treeNode = reConstructBinaryTree6.reConstructBinaryTree2(pre,in);
        System.out.println(treeNode.val);
    }
}
