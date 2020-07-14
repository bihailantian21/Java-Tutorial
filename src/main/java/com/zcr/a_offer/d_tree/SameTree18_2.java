package com.zcr.a_offer.d_tree;

/**
 * 判断两棵树是否相等
 */
public class SameTree18_2 {

    public boolean sameTree(TreeNode T1, TreeNode T2) {
        if (T1 == null && T2 == null)
            return true;
        else {
            return T1 != null && T2 != null
                    && T1.val == T2.val
                    && sameTree(T1.left, T2.left)
                    && sameTree(T1.right, T2.right);
        }
    }
}
