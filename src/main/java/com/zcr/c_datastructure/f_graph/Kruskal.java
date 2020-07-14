package com.zcr.c_datastructure.f_graph;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/7/10-22:46
 */
public class Kruskal {

    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    private static final int INF = Integer.MAX_VALUE;//表示两个顶点不能联通


    public static void main(String[] args) {

        char[] vertexs = {'A','B','C','D','E','F','G'};
        int matrix[][] = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0},
        };

        //创建克鲁斯卡尔
        Kruskal kruskal = new Kruskal(vertexs,matrix);
        //输出构建的图的邻接矩阵
        kruskal.print();

        //返回所有边的数组
        System.out.println("未排序的边的数组" + Arrays.toString(kruskal.getEdges()));
        System.out.println("所有边的个数" + kruskal.edgeNum);

        //将边排序
        EData[] edges = kruskal.getEdges();
        kruskal.sortEdges(edges);
        System.out.println("排序后的边的数组" + Arrays.toString(edges));

        kruskal.kruskal();

    }

    //构造器
    public Kruskal(char[] vertexs,int[][] matrix) {
        //初始化顶点个数和边的个数
        int vlen = vertexs.length;

        //初始化顶点，用的是复制拷贝的方式
        //this.vertex = vertexs;也可以这样写
        this.vertexs = new char[vlen];
        for (int i = 0; i < vlen; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化边，用的是复制拷贝的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }


    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为：\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d \t",matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序，冒泡排序
    /**
     *
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {//交换
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     *
     * @param ch 顶点的值，比如'A'
     * @return 返回顶点对应的下标，如果找不到返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边，放到EData[]数组中，后面我们需要遍历该数组
     * 是通过matrix邻接矩阵来获取
     * EData[] 形式[['A','B',12],['B','F',3],...]
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能：获取下标为i的顶点的终点（判断是否有回路，就是判断两个顶点的终点是否是同一个）
     *
     * @param ends 这个数组记录了各个顶点对应的终点是哪个，是在我们遍历过程中逐步形成的
     * @param i 表示传入的顶点对应的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i) {//5
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;

    }

    //克鲁斯卡尔算法生成最小生成树
    public void kruskal() {
        int index = 0;//表示最后结果数组的索引
        int[] ends = new int[edgeNum];//用于保存已有最小生成树中的每个顶点它在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图汇总所有边的集合，一共有12条边
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + "共" + edges.length +"条边");

        //按照边的权值从小到大金星排序
        sortEdges(edges);

        //遍历edges数组，将边添加到最小生成树中时，判断准备加入的边是否构成了回路，如果没有就加入到结果数组中去，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点（起点）
            int p1 = getPosition(edges[i].start);//4
            //获取到第i条边的第二个顶点（终点）
            int p2 = getPosition(edges[i].end);//5

            //获取p1这个顶点它在已有最小生成树中的终点是哪一个
            int m = getEnd(ends,p1);//4
            //获取p2这个顶点它在已有最小生成树中的终点是哪一个
            int n = getEnd(ends,p2);//5

            //判断是否构成回路
            if (m != n) {//没有构成回路
                ends[m] = n;//设置m在已有最小生成树中的终点    [0,0,0,0,5,0,0,0,0,0,0,0,0,0]
                rets[index++] = edges[i];//有一条边加入到rets数组中了
            }
        }

        //统计并打印最小生成树，输出rets数组
        System.out.println("最小生成树为=");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }

    }


}

//创建一个类EData，它的对象实例就表示一条边
class EData {
    char start;//边的起点
    char end;//边的终点
    int weight;//边的权值

    //构造器
    public EData(char start,char end,int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写toString方法，便于输出这条边
    @Override
    public String toString() {
        return "EData{" + "start=" + start + ", end=" + end + ", weight=" + weight + '}';
    }
}

