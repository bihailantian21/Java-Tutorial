package com.zcr.b_leetcode.bfs;

import com.zcr.a_offer.d_tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 62.序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
 * 从而使得内存中建立起来的二叉树可以持久保存。
 * 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，
 * 序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），
 * 以 ！ 表示一个结点值的结束（value!）。
 *
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 */
public class Deserialize62 {

    /**
     * 前序序列化。
     * @param root
     * @return
     */
    String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            sb.append("#!");
            return sb.toString();
        } else {
            sb.append(root.val + "!");
            sb.append(Serialize(root.left));
            sb.append(Serialize(root.right));
        }
        return sb.toString();
    }

    /**
     * 定义了全局变量index，通过substring()方法来截取每一部分的字符串。
     * 3.字符串的比较以后尽量用equal来比较。在对某字符串采用substring()方法得到的字符串用==判断会返回false。
     * substring的==与equal()使用
     * 4.String 转int 类型采用 int i = Integer.parseInt( s ); 不能用Integer.valueOf(s)，这返回的是Integer对象。
     * 5.index++的位置一定不能放错
     * @param str
     * @return
     */
    public int index = -1;

    TreeNode Deserialize(String str) {
        index ++;
        int len = str.length();
        if (index >= len)   return null;
        String[] strings = str.split("!");
        TreeNode node = null;
        if (!strings[index].equals("#")){
            node = new TreeNode(Integer.parseInt(strings[index]));
            node.left  = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }


    /**
     * 什么时候需要序列化？
     * 1.内存中的数据进行持久化的时候
     * 2.网络传输的时候
     *
     *
     * 层序
     * @param root
     * @return
     */
    String Serialize2(TreeNode root) {
        StringBuilder res = new StringBuilder();
        if (root == null) {
            res.append("#!");
            return res.toString();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode top = null;
        while (!queue.isEmpty()) {
            top = queue.poll();
            if (top != null) {
                res.append(top.val + "!");
                queue.add(top.left);
                queue.add(top.right);
            } else {
                res.append("#!");
            }
        }
        return res.toString();
    }

    /**
     * @param str
     * @return
     */
    TreeNode Deserialize2(String str) {
        if (str == null || str.length() == 0) return null;
        String[] arr = str.split("!");
        int idx = 0;
        TreeNode root = recon(arr[idx++]);
        if (root == null) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode top = null;
        while (!queue.isEmpty()) {
            top = queue.poll();
            top.left = recon(arr[idx++]);
            top.right = recon(arr[idx++]);
            if (null != top.left)
                queue.add(top.left);
            if (null != top.right)
                queue.add(top.right);
        }
        return root;
    }

    private TreeNode recon(String str) {
        return str.equals("#") ? null : new TreeNode(Integer.valueOf(str));
    }
}
