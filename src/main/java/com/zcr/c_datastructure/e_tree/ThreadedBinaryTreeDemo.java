package com.zcr.c_datastructure.e_tree;

/**
 * @author zcr
 * @date 2019/7/8-12:36
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {

        //测试中序线索二叉树的功能

        //创建节点
        Node root= new Node(1,"tom");
        Node node2= new Node(3,"jack");
        Node node3= new Node(6,"smith");
        Node node4= new Node(8,"mary");
        Node node5= new Node(10,"king");
        Node node6= new Node(14,"dim");

        //创建二叉树，先手动创建，后面我们递归创建
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //中序线索化
        threadedBinaryTree.threadedNodes();

        //看看10这个点的前驱和后继节点是谁
        Node leftnode = node5.getLeft();
        Node rightnode = node5.getRight();
        System.out.println("10号节点的前驱节点是："+leftnode);
        System.out.println("10号节点的后驱节点是："+rightnode);

        //对中序线索化后的二叉树进行遍历
        System.out.println("使用线索化的方式遍历线索化二叉树：");
        threadedBinaryTree.threadedList();//8,3,10,1,14,6


    }
}

//先创建HeroNode节点
class Node {
    private int no;
    private String name;
    private Node left;//默认为空
    private Node right;//默认为空
    private int leftType;//0表示指向左子树 1表示指向前驱节点
    private int rightType;//0表示指向右子树 1表示指向后继节点

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }

    }
    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder() {
        //递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }
}


//定义二叉树，实现了线索化功能的二叉树，即ThreadedBinaryTree
class ThreadedBinaryTree {
    private Node root;

    //为了实现线索化，需要创建要指向当前节点的前驱节点的指针
    //在递归进行线索化时，总是保留前一个节点
    private Node pre = null;

    public void setRoot(Node root) {
        this.root = root;
    }

    //重载线索化的方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //编写对二叉树进行中序线索化的方法
    /**
     *
     * @param node 就是当前进行线索化的节点
     */
    public void threadedNodes(Node node) {
        //如果node == null，无法线索化，直接退出
        if (node == null) {
            return;
        }
        //（一）先线索化左子树
        threadedNodes(node.getLeft());

        //（二）线索化当前节点【有难度】
        //先处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型，指向的是前驱节点
            node.setLeftType(1);
        }

        //处理后继节点（是在下一次处理的）因为是单向的，不能说同时处理左右
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }

        pre = node;//重要！每处理一个节点后，让当前节点是下一个节点的前驱节点

        //（三）线索化右子树
        threadedNodes(node.getRight());
    }

    //遍历线索化二叉树的方法
    public void threadedList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        Node node = root;
        while (node != null) {
            //循环的找到leftType为1的节点，第一个找到的应该是8
            //后面随着遍历而变化，因为当leftType为1时，说明该节点是按照线索化处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }

            //替换这个遍历的节点
            node = node.getRight();

        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }


}
