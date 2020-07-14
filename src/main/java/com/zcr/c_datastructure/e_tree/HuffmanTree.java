package com.zcr.c_datastructure.e_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zcr
 * @date 2019/7/8-18:16
 */
public class HuffmanTree {

    public static void main(String[] args) {

        int arr[] = {13,7,8,3,29,6,1};
        Node2 noderes = createHuffmanTree(arr);
        System.out.println(noderes);
        //前序遍历创建好的赫夫曼二叉树
        preOrder(noderes);//67 29 38 15 7 8 23 10 4 1 3 6 13



    }

    //创建赫夫曼树的方法

    /**
     *
     * @param arr 需要创建成赫夫曼树的数组
     * @return 创建好的赫夫曼树的根节点
     */
    public static Node2 createHuffmanTree(int[] arr) {
        //第一步：为了操作方便
        //1.遍历arr数组
        //2.将arr的每一个元素构建成一个Node
        //3.将Node放入到ArrayList中
        List<Node2> nodes = new ArrayList<Node2>();
        for (int value : arr) {
            nodes.add(new Node2(value));
        }

        //处理的过程是一个循环的过程！
        while (nodes.size() > 1) {
            //排序，从小到大排序
            Collections.sort(nodes);
            System.out.println("nodes="+nodes);

            //取出根节点权值最小的两颗二叉树
            //1.取出权值最小的二叉树节点【一个节点也可以看做是一颗二叉树】
            Node2 leftNode  = nodes.get(0);
            //2.取出权值第二小的节点
            Node2 rightNode = nodes.get(1);
            //3.构建一颗新的二叉树
            Node2 parent = new Node2(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4.从arrlist中删除掉处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5.把新节点加入到Arrlist中
            nodes.add(parent);

            /*//Collections.sort(nodes);
            System.out.println("第一次处理后："+ nodes);*/
        }
        //返回赫夫曼的root节点
        return nodes.get(0);
    }

    //前序遍历方法
    public static void preOrder(Node2 root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树无法遍历");
        }
    }



}

//创建节点类
//为了让Node对象支持排序Collection集合排序
//让Node实现comparable接口
class Node2 implements Comparable<Node2>{
    int value;//节点权值
    //char c;//字符
    Node2 left;//指向左子节点
    Node2 right;//指向右子节点


    public Node2(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node2{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node2 o) {
        return this.value - o.value;//从小到大排序  自己大，返回正数；自己小，返回负数
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
