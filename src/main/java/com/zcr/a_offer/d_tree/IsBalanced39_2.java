package com.zcr.a_offer.d_tree;

/**
 * 39.平衡二叉树
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class IsBalanced39_2 {

    /**
     * 上面我们求了二叉树的深度，于是这道题我们就想：
     * 首先我们知道平衡二叉树:是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树；
     * 我们可以使用一个获取树的高度的函数depth()。然后递归比较左右子树是不是平衡二叉树且左右子树的高度不超过1即可。
     * <p>
     * 这里获取高度需要logN复杂度，主函数isBalance需要O(N)，所以总的时间复杂度为N*logN；
     * 缺点：会重复遍历节点
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBalanced(root.left) && isBalanced(root.right)
                && Math.abs(treeDepth(root.left) - treeDepth(root.right)) <= 1;
    }

    public static int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }


    /**
     * 如果我们用后序遍历的方式遍历每一个节点，在遍历到一个节点之前，我们就已经遍历过了它的左右节点。
     * 只需要在遍历每个节点时记录它的深度，我们就可以一边遍历一边判断每个节点是不是平衡的。
     * 当最后遍历到根节点的时候，也就判断除了整颗树是不是平衡的。
     * @param root
     * @return
     */
   /* public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
    }*/

    /**
     * 上面的方法需要先求高度，然后再判断是不是平衡二叉树，能否一起做呢?
     *
     * 所以假如我们要判断一个以x开头的结点是否为一颗平衡二叉树，则要满足以下几点 :
     * 它的左子树要给它一个左子树本身是不是平衡二叉树的信息；
     * 它的右子树要给它一个右子树本身是不是平衡二叉树的信息；
     * 它的左子树要给它左子树高度的信息；
     * 它的右子树要给它右子树高度的信息；
     * 所以我们知道上面的几点之后，可以完全按照上面写出一个递归结构函数，
     * 因为子树返回的信息中既包含高度信息，又包含子树是不是也是一颗平衡二叉树的信息，所以这里把这个信息封装成一个结构。
     *
     * 这里和O(n*logn)方法不同的是，我们求的是一个结构体，递归函数同时返回了两个信息: 高度和子树是否平衡，如果不平衡，我们就不再判断直接false了)。
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null)
            return true;
        return height(root) > -1;
    }

    private int height(TreeNode node) {
        if (node == null)
            return 0;
        int LH = height(node.left);
        int RH = height(node.right);
        if (Math.abs(LH - RH) > 1 || LH == -1 || RH == -1)
            return -1;
        return Math.max(LH, RH) + 1;
    }
}
