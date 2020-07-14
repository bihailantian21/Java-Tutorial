package com.zcr.a_offer.d_tree;

import java.util.Stack;

/**
 * 63.二叉搜索树的第k个节点
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
 *
 * 例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 */
public class KthNode63 {

    /**
     * 这题目也不难，二叉搜索树中序遍历是升序的，可以中序遍历然后计数即可。
     *
     * 左根右
     * @param pRoot
     * @param k
     * @return
     */
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = pRoot;
        int cnt = 0;
        while(!stack.isEmpty() || p != null){
            while(p != null){
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            cnt++;
            if(k == cnt)
                return p;
            p = p.right;
        }
        return null;
    }
}
