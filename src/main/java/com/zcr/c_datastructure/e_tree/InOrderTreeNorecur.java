package com.zcr.c_datastructure.e_tree;

import com.zcr.a_offer.d_tree.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * https://blog.csdn.net/Applying/article/details/84982712
 */
public class InOrderTreeNorecur {

    public void binaryTreeInOrder(TreeNode root) {
        if (root == null){
            return;
        }
        binaryTreeInOrder(root.left);
        System.out.print(root.val+" ");
        binaryTreeInOrder(root.right);
    }

    public ArrayList<Integer> binaryTreeInOrder2(TreeNode head) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = head;
        TreeNode top = null;
        while (!s.isEmpty() || cur != null) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }
            // 走到最左边了
            top = s.pop();
            res.add(top.val);
            //将cur指向栈顶元素的右孩子
            cur = top.right;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        //说明：我们先手动创建该二叉树，后面学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        InOrderTreeNorecur inOrderTreeNorecur = new InOrderTreeNorecur();
        //inOrderTreeNorecur.binaryTreeInOrder(root);
        ArrayList<Integer> res = new ArrayList<>();
        res = inOrderTreeNorecur.binaryTreeInOrder2(root);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));

        }



    }
}
