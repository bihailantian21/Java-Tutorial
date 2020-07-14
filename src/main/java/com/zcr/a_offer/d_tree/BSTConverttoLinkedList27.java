package com.zcr.a_offer.d_tree;

import java.util.Stack;

/**
 * 27、二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class BSTConverttoLinkedList27 {

    /**
     * 二叉搜索树：指向左节点、指向右节点。左节点小于根，右节点大于根。
     * 双向链表：指向前一个节点、指向后一个节点
     * 所以它们可以互相转换。
     * 而且二叉搜索树的中序遍历为从小到大排序，所以可以将二叉搜索树与排序双向链表互相转换。
     * 二叉搜索树指向左节点-》链表中指向前一个
     * 二叉搜索树指向右节点-》链表中指向后一个
     *
     * 中序遍历二叉搜索树，当遍历到根节点时，将左子树中最后一个节点与根节点连起来，将根节点与右子树中第一个节点连起来。
     * 递归
     *
     * @param pRootOfTree
     * @return
     */
    private TreeNode pre;

    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        pre = null;
        convert(pRootOfTree);
        //返回的pre是双向链表的最后一个结点
        while (pre != null && pre.left != null) {
            pre = pre.left;
        }
        return pre;
    }

    public void convert(TreeNode root) {
        if (root == null) {
            return;
        }
        convert(root.left);    //中序遍历的递归写法
        root.left = pre;      //每次到根的时候，将左边的最后pre和根root连接起来，然后将根与右边的第一个连接起来。
        if (pre != null) {
            pre.right = root;
        }
        pre = root;
        convert(root.right);
    }


    /**
     * 我们知道中序遍历树的非递归写法：（左根右）用栈
     *
     * 改进中序非递归遍历(使用pre保存上一个访问的结点)，这个只要会非递归的中序遍历，其实是最简单的方法。
     * 每次记录上一个访问的结点pre，然后每次当前结点为空(访问到了最左边)的时候，就设置pre和cur的关系即可；
     * 这里使用了一个isFirst变量标注了一开始的节点，用res记录一开始的结点；
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert2(TreeNode pRootOfTree) {// 返回排序的双向链表的头结点
        if (pRootOfTree == null)
            return null;
        Stack<TreeNode> s = new Stack<>();
        TreeNode pre = null, cur = pRootOfTree, res = null;
        boolean isFirst = true;
        while (!s.isEmpty() || cur != null) {
            if (cur != null) {
                s.push(cur);
                cur = cur.left;
            } else { // 走到最左边了
                cur = s.pop();
                if (isFirst) {
                    isFirst = false;
                    res = cur;
                    pre = cur;
                } else {
                    pre.right = cur;
                    cur.left = pre;
                    pre = cur;
                }
                cur = cur.right; //当前结点向右
            }
        }
        return res;
    }
}
