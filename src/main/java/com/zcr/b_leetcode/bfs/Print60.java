package com.zcr.b_leetcode.bfs;

import com.zcr.a_offer.d_tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 60.按照之字形顺序打印二叉树
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class Print60 {

    /**
     * 然后只需要将偶数层的翻转一下即可。
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(pRoot == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        boolean ok = false;
        ArrayList<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            int n = queue.size();
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int i = 0; i < n; i++){
                TreeNode cur = queue.poll();
                tmp.add(cur.val);
                if(cur.left != null) queue.add(cur.left);
                if(cur.right != null) queue.add(cur.right);
            }
            if(ok) Collections.reverse(tmp);
            ok = !ok;
            res.add(tmp);
        }
        return res;
    }
}
