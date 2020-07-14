package com.zcr.b_leetcode.bfs;

import com.zcr.a_offer.d_tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 61.把二叉树打印成多行
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class PrintMany61 {

    /**
     * 参考层序遍历二叉树的代码
     * @param pRoot
     * @return
     */
    public ArrayList<Integer> cengorder(TreeNode pRoot) {
        ArrayList<Integer> result = new ArrayList<>();
        if(pRoot == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            TreeNode cur= queue.poll();
            result.add(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return result;
    }

    /**
     * 创建一个队列，然后依次添加左、右子节点。从队列中取的时候，就相当于在每一层从左到右的取节点
     *
     * 借鉴其他人的思路，采用广度优先探索，使用队列。
     * 若根节点为空，直接返回；
     * 若根节点非空，则将根节点入队，然后，判断队列是否为空，
     * 若不为空，则将队首节点出队，访问，
     * 并判断其左右子节点是否为空，若不为空，则压入队列。
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(pRoot == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();//必须使用队列作为数据结构
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            int n = queue.size();
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {//注意：层序遍历中，需要分层的比不需要分层的多一个for循环
                TreeNode cur= queue.poll();
                temp.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            result.add(new ArrayList<>(temp));
        }
        return result;
    }
}
