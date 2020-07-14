package com.zcr.a_offer.d_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 59.对称的二叉树
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class isSymmetrical59 {

    /**
     * 递归思路。
     *
     * 首先根节点，只要pRoot.left和pRoot.right对称即可；
     * 左右节点的值相等且对称子树left.left 和 right.right对称 ，且left.rigth和right.left也对称。
     *
     * 递归:
     * @param pRoot
     * @return
     */
    boolean isSymmetrical(TreeNode pRoot) {
        return pRoot == null ? true : mirror(pRoot.left,pRoot.right);
    }

    public boolean mirror(TreeNode pleft, TreeNode pright) {
        if (pleft == null && pright == null) {
            return true;
        }
        if (pleft == null || pright == null) {
            return false;
        }
        return pleft.val == pright.val
                && mirror(pleft.right,pright.left)
                && mirror(pleft.left,pright.right);
    }


    /**
     * 非递归:
     *
     * 层次遍历即可，注意队列中要成对成对的取。
     * @param pRoot
     * @return
     */
    boolean isSymmetrical2(TreeNode pRoot) {
        if (pRoot == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot.left);
        queue.add(pRoot.right);
        while (!queue.isEmpty()) {
            TreeNode right = queue.poll();
            TreeNode left = queue.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            //成对插入
            queue.add(left.left); queue.add(right.right);
            queue.add(left.right); queue.add(right.left);
        }
        return true;
    }


    /**
     * 栈也可以
     * @param pRoot
     * @return
     */
    boolean isSymmetrical3(TreeNode pRoot) {
        if (pRoot == null) return true;
        Stack<TreeNode> s = new Stack<>();
        s.push(pRoot.left);
        s.push(pRoot.right);
        while (!s.isEmpty()) {
            TreeNode right = s.pop();
            TreeNode left = s.pop();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            //成对插入
            s.push(left.left); s.push(right.right);
            s.push(left.right); s.push(right.left);
        }
        return true;
    }
}
