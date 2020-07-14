package com.zcr.b_leetcode.bfs;

import com.zcr.a_offer.d_tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 23、从上往下打印二叉树
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class PrintFromTopToBottom23 {

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
     * 把起点放入队列中
     * 每一次从队列头部取出一个元素时，打印它并且：
     * 如果该节点有子节点，就把它加入到队列的尾部
     * 接下来依次打印队列中的元素。
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (queue.peek().left != null) {
                queue.add(queue.peek().left);
            }
            if (queue.peek().right != null) {
                queue.add(queue.peek().right);
            }
            result.add(queue.poll().val);
        }
        return result;
    }
}
