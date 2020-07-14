package com.zcr.a_offer.d_tree;

/**
 * 18.树的子结构
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class HasSubtree18 {

    /**
     * 思路:使用递归遍历数
     *
     * 1、先在A里面找到B的根的值(某个结点A.val = B.val)，然后看看子树是不是都相同(具体来说不是相同，而是A是否包含(A>=B))，
     * 这里判断是另一个函数AcontainsB()来判断；
     * 2、如果上述条件不满足，递归在A.left或A.right中找这个值，然后再递归看子树是不是满足AcontiansB；
     * 3、然后看递归函数函数AcontainsB()，递归条件root2只要达到空，就说明找到了，
     * 返回true，反之，root1达到空，返回false，注意这里不是判断两个树完全相等；
     *
     * 看一个例子:
     * 首先我们在树 A 中找到值为8 (树 B 的根结点的值) 的结点。从树 A 的根结点开始遍历，我们发现它的根结点的值就是 8。
     * 接着我们就去判断树 A 的根结点下面的子树是不是含有和树 B 一样的结构(如图)。在树 A 中，根结点的左子结点的值是8，而树 B 的根结点的左子结点是 9，对应的两个结点不同。
     * 然后需要继续查找。
     * 因此我们仍然需要遍历树 A，接着查找值为 8 的结点。我们在树的第二层中找到了一个值为 8 的结点，
     * 然后进行第二步判断，即判断这个结点下面的子树是否含有和树 B 一样结构的子树 (如图)。
     * 于是我们遍历这个结点下面的子树，先后得到两个子结点 9 和 2，这和树 B 的结构完全相同。
     * 此时我们在树 A 中找到了一个和树 B 的结构一样的子树，因此树B 是树 A 的子结构。
     *
     *
     * @param root1
     * @param root2
     * @return
     */
    // 判断root2是不是root1的子结构
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return false;
        boolean res = false;
        if (root1.val == root2.val)
            res = aContainsB(root1, root2);
        if (!res)
            res = HasSubtree(root1.left, root2);//左边有可能包含root2
        if (!res)
            res = HasSubtree(root1.right, root2);//右边也有可能包含root2
        return res;
    }

    //注意不是判断两棵树是不是完全相等，而是判断A是否包含B
    private boolean aContainsB(TreeNode A, TreeNode B) {
        if (B == null)// B遍历完了, 说明可以
            return true;
        if (A == null)
            return false;
        // A != null && B != null 利用短路特性
        return A.val == B.val
                && aContainsB(A.left, B.left)
                && aContainsB(A.right, B.right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);

        //{8,8,7,9,2,#,#,#,#,4,7},{8,9,2}
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node2.left = node6;
        node2.right = node7;

        TreeNode node11 = new TreeNode(8);
        TreeNode node22 = new TreeNode(9);
        TreeNode node33 = new TreeNode(2);
        node11.left = node22;
        node11.right = node33;

        HasSubtree18 hasSubtree18 = new HasSubtree18();
        boolean result = hasSubtree18.HasSubtree(node1,node11);
        System.out.println(result);
    }
}
