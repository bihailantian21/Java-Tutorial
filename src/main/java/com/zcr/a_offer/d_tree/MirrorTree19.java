package com.zcr.a_offer.d_tree;

import java.util.Stack;

/**
 * 19、二叉树的镜像
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 输入描述:
 * 二叉树的镜像定义：源二叉树
 *     	    8
 *     	   /  \
 *     	  6   10
 *     	 / \  / \
 *     	5  7 9 11
 *     	镜像二叉树
 *     	    8
 *     	   /  \
 *     	  10   6
 *     	 / \  / \
 *     	11 9 7  5
 */
public class MirrorTree19 {

    /**
     * 思路很简单，递归求解的过程:
     * 先把当前根节点的左右子树换掉；
     * 然后递归换自己的左右子树即可；
     *
     * @param root
     */
    public void mirror(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        mirror(root.left);
        mirror(root.right);
    }

    /**
     * 非递归、使用栈
     * @param root
     */
    public void Mirror2(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left != null || cur.right != null) {
                TreeNode t = cur.left;
                cur.left = cur.right;
                cur.right = t;
            }
            if (cur.left != null)
                stack.push(cur.left);
            if (cur.right != null)
                stack.push(cur.right);
        }
    }


}
