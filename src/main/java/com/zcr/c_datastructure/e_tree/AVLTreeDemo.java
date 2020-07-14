package com.zcr.c_datastructure.e_tree;

/**
 * @author zcr
 * @date 2019/7/9-17:46
 */
public class AVLTreeDemo {

    public static void main(String[] args) {

        //int[] arr = {4,3,6,5,7,8};//测试左旋转
        //int[] arr = {10,12,8,9,7,6};//测试右旋转
        int[] arr = {10,11,7,6,8,9};//测试双旋转

        //创建一颗AVL树
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node5(arr[i]));
        }

        //中序遍历AVL树
        System.out.println("中序遍历AVL树");
        avlTree.infixOrder();

        //求出AVL树的高度
        System.out.println("在平衡处理后情况：");
        System.out.println("树的高度="+avlTree.getRoot().height());//4
        System.out.println("树的左子树的高度="+avlTree.getRoot().leftHeight());//1->2
        System.out.println("树的右子树的高度="+avlTree.getRoot().rightHeight());//3->2
        System.out.println("当前的根节点为="+avlTree.getRoot());

        //

    }
}

//创建二叉平衡树
class AVLTree {
    private Node5 root;

    public Node5 getRoot() {
        return root;
    }

    public void setRoot(Node5 root) {
        this.root = root;
    }

    //添加节点的方法
    public void add(Node5 node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}

//创建节点
class Node5 {
    int value;
    Node5 left;
    Node5 right;

    public Node5(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node5{" + "value=" + value + '}';
    }

    //添加节点
    //递归的形式添加节点，注意需要满足二叉排序树的要求
    public void add(Node5 node) {
        if (node == null) {
            return;
        }

        //判断传入节点的值，和当前子树的根节点的值的关系
        if (node.value < this.value) {
            if (this.left == null) {//如果当前节点左子树为空
                this.left = node;
            } else {
                this.left.add(node);//递归的向左子树添加
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        //当添加完一个节点后，如果右子树的高度-左子树的高度 > 1，就左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (right != null && right.rightHeight() < right.leftHeight()) {
                //先对右子树进行右旋转
                right.rightRotate();
                //再对当前节点进行左旋转
                leftRotate();
            } else {
                leftRotate();//直接左旋转即可
            }
            return;
        }

        //当添加完一个节点后，如果左子树的高度-右子树的高度 > 1，就左旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树的高度大于它左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前节点的左子树进行左旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            } else {
                rightRotate();//直接进行右旋转即可
            }
            return;
        }

    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //查找要删除的节点

    /**
     *
     * @param value 希望删除的节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public Node5 search(int value) {
        if (value == this.value) {//找到就是该节点
            return this;
        } else if (value < this.value) {//如果查找的值小于当前节点，向左子树递归查找
            if (this.left == null) {//如果左子节点为空，就不能再找了
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {//如果右子节点为空，就不能再找了
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点

    /**
     *
     * @param value 要找的节点的值
     * @return 返回的是要删除的节点的父节点，如果没有就返回null
     */
    public Node5 searchParent(int value) {
        //如果当前节点就是要删除的节点的父节点，就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            //递归的向左查找
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;//没有找到父节点（要删除的节点是根节点）
            }
        }
    }

    //返回当前节点的高度，以该节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(),right == null ? 0 : right.height()) + 1;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //左旋转
    private void leftRotate() {
        //创建新的节点，以当前根节点的值
        Node5 newNode = new Node5(value);
        //把新的节点的左子树，设置为，当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树，设置为，当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值，替换成，当前节点的右子节点的值
        value = right.value;
        //把当前节点的右子树，设置为，当前节点右子树的右子树
        right = right.right;
        //把当前节点的左子树，设置为，新的节点
        left = newNode;
    }

    //右旋转
    private void rightRotate() {
        //创建新的节点，以当前根节点的值
        Node5 newNode = new Node5(value);
        //把新的节点的右子树，设置为，当前节点的右子树
        newNode.right = right;
        //把新的节点的左子树，设置为，当前节点的左子树的右子树
        newNode.left = left.right;
        //把当前节点的值，替换成，当前节点的左子节点的值
        value = left.value;
        //把当前节点的左子树，设置为，当前节点左子树的左子树
        left = left.left;
        //把当前节点的右子树，设置为，新的节点
        right = newNode;
    }


}