package com.zcr.c_datastructure.f_graph;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/7/10-21:53
 */
public class Prim {

    public static void main(String[] args) {

        //创建图
        //顶点的值
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示
        //10000这个大数字用来表示两个点之间不连通
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},
        };

        //创建MGraph对象
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,verxs,data,weight);

        //输出
        minTree.showGraph(graph);

        //prim算法
        minTree.prim(graph,0);


    }
}

//创建最小生成树
class MinTree {
    //创建图的邻接矩阵
    /**
     *
     * @param graph 图对象
     * @param verxs 图的顶点个数
     * @param data 图的顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {//遍历顶点
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weigth[i][j] = weight[i][j];
            }
        }
    }

    //显示图的方法，显示图的邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weigth) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写Prim算法，得到最小生成树
    /**
     *
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph, int v) {

        //标记节点是否被访问过
        int visited[] = new int[graph.verx];
        //visited[]默认元素的值都是0，表示都没有访问过，所以下面这个循环赋初值可以不写
        for (int i = 0; i < graph.verx; i++) {
            visited[i] = 0;
        }

        //把当前这个节点标记为已访问
        visited[v] = 1;
        //h1、h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//将这个初始化为最大值，后面在遍历过程中会被替换
        for (int k = 1; k < graph.verx; k++) {//生成 (顶点个数-1) 条边

            //确定每一次生成的子图和哪个节点的距离最近
            for (int i = 0; i < graph.verx; i++) {//i表示被访问过的节点
                for (int j = 0; j < graph.verx; j++) {//j表示还没有访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weigth[i][j] < minWeight) {
                        //替换minWeight，寻找已经访问过的节点和未访问过的节点间的权值最小的边
                        minWeight = graph.weigth[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "权值：" + minWeight);
            //将当前这个找到的节点标记为已经访问
            visited[h2] = 1;
            //重置minWeight为一个最大值
            minWeight = 10000;
        }

    }




}

class MGraph {
    int verx;//表示图中节点个数
    char[] data;//存放节点数据
    int[][] weigth;//存放边，就是我们的邻接矩阵

    public MGraph(int verx) {
        this.verx = verx;
        data = new char[verx];
        weigth = new int[verx][verx];
    }
}