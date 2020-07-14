package com.zcr.c_datastructure.e_tree;

/**
 * @author zcr
 * @date 2019/7/9-11:51
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {

        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node4(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树：");
        binarySortTree.infixOrder();

        /*//删除叶子节点
        binarySortTree.delNode(2);
        System.out.println("删除叶子节点2后：");
        binarySortTree.infixOrder();*/

        /*//删除只有一颗子树的节点
        binarySortTree.delNode(1);
        System.out.println("删除叶子节点1后：");
        binarySortTree.infixOrder();*/

        /*//删除有两颗子树的节点
        binarySortTree.delNode(7);
        System.out.println("删除叶子节点7后：");
        binarySortTree.infixOrder();*/

        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        System.out.println("删除叶子节点7后：");
        binarySortTree.infixOrder();




    }
}

//创建二叉排序树
class BinarySortTree {
    private Node4 root;
    //添加节点的方法
    public void add(Node4 node) {
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

    //查找要删除的节点
    public Node4 search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node4 searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.需要先去找到要删除的节点
            Node4 targetNode = search(value);
            //如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            //去找到targetNode的父节点
            Node4 parent = searchParent(value);
            if (targetNode.left == null && targetNode.right == null) {//如果要删除的节点是叶子节点
                //判断targetNode是父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    //说明targetNode是父节点的左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    //说明targetNode是父节点的右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//删除有两颗子树的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;

                //也可以从左子树找最大的
            } else {//删除只有一颗子树的节点
                if (targetNode.left != null) {//如果要删除的节点有左子节点
                    if (parent != null) {//如果还剩最后两个节点
                        if (parent.left.value == value) {//如果targetNode是parent的左子节点
                            parent.left = targetNode.left;
                        } else {//如果targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }

                } else {//如果要删除的节点有右子节点
                    if (parent != null) {//如果还剩最后两个节点
                        if (parent.left.value == value) {//如果targetNode是parent的左子节点
                            parent.left = targetNode.right;
                        } else {//如果targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     * 同时还要删除以node为根节点的二叉排序树的最小节点
     * @param node 传入的节点（当做二叉排序树的根节点）
     * @return 返回的以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node4 node) {
        Node4 target = node;
        //循环的查找左子节点,就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时候target就指向了最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;


    }
}

//创建节点
class Node4 {
    int value;
    Node4 left;
    Node4 right;

    public Node4(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node4{" + "value=" + value + '}';
    }

    //添加节点
    //递归的形式添加节点，注意需要满足二叉排序树的要求
    public void add(Node4 node) {
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
    public Node4 search(int value) {
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
    public Node4 searchParent(int value) {
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
}
