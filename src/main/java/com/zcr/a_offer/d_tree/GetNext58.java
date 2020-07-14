package com.zcr.a_offer.d_tree;

/**
 * 58.二叉树的下一个节点
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class GetNext58 {

    class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null; //觉得改成parent更好一点

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    /**
     * 中序遍历：左根右
     *     a
     *  b      c
     * d e   f  g
     *  h i
     *
     * 例如：二叉树中序遍历{d，b，h，e，i，a，f，c，g}
     *
     * 情况1：
     * 如果一个节点有右子树，那么它的下一个节点就是它的右子树中的最左子节点。
     * 即从右子节点出发一直沿着指向左子节点的指针，找到它下一个节点。
     * 即b的下一个节点为h
     * a的下一个节点为f
     * （因为中序遍历的顺序是左，中，右，则节点X
     * 打印之后，就该打印它的右子树了，而打印它的右子树肯定最先打印右子树的最左节点了）
     *
     * 情况2：
     * 没有右子树：
     * 如果节点是它父亲的左子节点，下一节点是它的父节点，
     * 即d的下一个节点为b，
     * f的下一个节点为c
     *
     * 情况3：
     * 既没有右子树，并且还是它父节点的右子节点
     * 沿着父节点的指针一直向上遍历，直到找到一个是它的父节点的左节点的节点，如果这样的节点存在，那么这个节点的父节点就是我们要找的下一个节点。
     * 即i沿着父节点指针向上遍历到e，e是它的父节点b的右节点，继续向上到b，b是父节点a的左子节点，即i的下一个节点是b的父节点a
     *
     * （如果X没有右子树，就要考虑X是作为哪个节点的左子树的最后一个节点？也就是说哪个节点的左子树是以X结尾的？则               
     * 这个节点就是X的后继节点。也就是说X没有右子树就通过它的parent指针往上找，直到找到当前节点是它父节点的左孩               
     * 子的时候停下来，则这个父节点就是要找的X的后继节点。)
     *
     * @param pNode
     * @return
     */
    // next 最好写成 parent
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) return null;
        if (pNode.right != null) return getMostLeft(pNode.right); // 答案是: 右孩子的最左节点
        if (pNode.next != null && pNode.next.left != null && pNode.next.left == pNode) // 答案是: 父亲
            return pNode.next;
        while (pNode.next != null && pNode.next.right != null && pNode.next.right == pNode) //答案是不断的往上找
            pNode = pNode.next;
        return pNode.next;
    }

    //获得node的最左下角节点
    public TreeLinkNode getMostLeft(TreeLinkNode node) {
        while (node.left != null) node = node.left;
        return node;
    }


    /**
     * 那么同样的，X的前驱节点怎么找？（和后继节点是对应的规律）
     * 1.如果X有左子树，那么前驱节点是X的左子树上的最右节点
     * 2.如果X没有左子树，那么同样往上找，直到当前节点是他父节点的右孩子的时候停下来，这个父节点就是X的前驱节点
     *
     * 左根右
     *
     * 找前驱结点
     * 这个和找后继是同理的:
     * 当一个结点有左子树的时候，就是最左子树的最右结点；
     * （因为中序遍历的顺序是左，中，右，则节点X
     *  打印之前，就该打印它的左子树了，而打印它的左子树肯定最先打印左子树的最右节点了）
     *
     * 没有左子树的时候，
     * a. 看当前结点node是不是它父亲(node.parent)的右孩子，如果是，那么它父亲(node.parent)就是它的前驱；
     * b. 如果当前结点是它父亲的左孩子(node.parent.left == node)，那么就向上不停的寻找它的前驱结点，
     * 即当前结点为node，它的父亲为parent，如果node还是parent的左孩子，
     * 就令node= parent，parent = parent.parent，一直向上，直到parent.right = node，就停止，
     * 此时parent就是当初要找的结点的前驱。
     *
     * （如果X没有左子树，就要考虑X是作为哪个节点的右子树的第一个节点？也就是说哪个节点的右子树是以X开头的？则               
     *  这个节点就是X的前驱节点。
     *  也就是说X没有左子树就通过它的parent指针往上找，直到找到当前节点是它父节点的右孩               
     *  子的时候停下来，则这个父节点就是要找的X的前驱节点。)
     * @param pNode
     * @return
     */
    public TreeLinkNode GetPre(TreeLinkNode pNode) {
        if (pNode == null) return null;
        if (pNode.left != null) return getMostRight(pNode.left); // 答案是: 左子树的最右节点
        if (pNode.next != null && pNode.next.right != null && pNode.next.right == pNode) // 答案是: 父亲
            return pNode.next;
        while (pNode.next != null && pNode.next.left != null && pNode.next.left == pNode) //答案是不断的往上找
            pNode = pNode.next;
        return pNode.next;
    }

    //获得node的最左下角节点
    public TreeLinkNode getMostRight(TreeLinkNode node) {
        while (node.right != null) node = node.right;
        return node;
    }
}
