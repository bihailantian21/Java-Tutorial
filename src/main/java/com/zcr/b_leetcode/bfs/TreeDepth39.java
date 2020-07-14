package com.zcr.b_leetcode.bfs;

import com.zcr.a_offer.d_tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 39.二叉树的深度
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 */
public class TreeDepth39 {

    /**
     * 1、递归
     * 递归的思路很简单。当前结点为根的树的高度 = 左右子树中高的那个 + 1 (自己)。
     *
     * 时间：O(logn)
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return left > right ? left + 1 : right + 1;

    }


    /**
     * 2、非递归
     * 可以利用层次遍历。来求树的层数(高度)。
     * （1）每一层的数量用一个变量count统计，总的层数用depth统计；
     * （2）同时，我们在当前层的时候，可以得知下一层的节点的数量(通过queue.size())；
     * （3）然后在到了下一层的时候， 就判断统计的数量count == nextLevelSize，如果等于，就加一层depth++；
     *
     * 层序遍历二叉树的变形
     * @param root
     * @return
     */
    public int TreeDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        int levelSize= 1;
        int depth = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            count++;
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
            if (count == levelSize) {//三个条件   计数器置为0、层数加1、下一层的数量为目前队列中的元素数量
                count = 0;
                depth++;
                levelSize = queue.size();
            }
        }
        return depth;
    }
}
