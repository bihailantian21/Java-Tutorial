package com.zcr.c_datastructure.i_algorithm;

/**
 * @author zcr
 * @date 2019/7/10-11:03
 */
public class DivideAndConquer {

    public static void main(String[] args) {
        Hanoitower.hanoiTower(5,'A','B','C');


    }
}

class Hanoitower {

    //汉诺塔的移动的方法
    //使用分治算法

    /**
     *
     * @param num 有多少个盘
     * @param a 柱子1
     * @param b 柱子2
     * @param c 柱子3
     */
    public static void hanoiTower(int num,char a,char b,char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            //如果我们有n>=2个盘，我们总是可以看做：最下边一个盘、上面的所有盘
            //1.先把最上面的所有盘A-B，移动过程会使用到C
            hanoiTower(num - 1,a,c,b);
            //2.把最下边的盘A-C
            System.out.println("第" + num + "个盘从" + a +"->" + c);
            //3.把B塔所有盘移动到C,B-C，移动过程中使用A
            hanoiTower(num - 1,b,a,c);
        }
    }
}
