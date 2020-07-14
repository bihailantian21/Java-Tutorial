package com.zcr.c_datastructure.f_graph;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/7/11-18:59
 */
public class Floyd {

    public static void main(String[] args) {

        //测试图是否创建成功
        char[] vertex = {'A','B','C','D','E','F','G'};
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0,5,7,N,N,N,2};
        matrix[1] = new int[]{5,0,N,9,N,N,3};
        matrix[2] = new int[]{7,N,0,N,8,N,N};
        matrix[3] = new int[]{N,9,N,0,N,4,N};
        matrix[4] = new int[]{N,N,8,N,0,5,4};
        matrix[5] = new int[]{N,N,N,4,5,0,6};
        matrix[6] = new int[]{2,3,N,N,4,6,0};

        //创建图对象
        Graph2 graph2 = new Graph2(vertex.length,matrix,vertex);
        graph2.show();
        graph2.fliyed();
        graph2.show();

    }
}

//创建图
class Graph2 {
    private char[] vertex;//存放顶点的数组
    private int[][] dis;//保存从各个顶点出发到其他顶点的距离，最后的结果也是保留在此数组中的
    private int[][] pre;//保存到达目标顶点的前驱顶点

    //构造器
    /**
     *
     * @param length 顶点个数
     * @param martix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph2(int length,int[][] martix,char[] vertex) {
        this.vertex = vertex;
        this.dis = martix;
        this.pre = new int[length][length];

        //对pre数组初始化，存放的是前驱顶点的下标'A'
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    //显示pre数组和dis数组
    public void show() {
        //为了显示便于阅读，优化输出
        char[] vertex = {'A','B','C','D','E','F','G'};
        for (int k = 0; k < dis.length; k++) {
            //先将pre数组的一行数组输出
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            //输出dis数组的一行数据
            for (int i = 0; i < dis.length; i++) {
                System.out.print("("+vertex[k]+"到"+vertex[i]+"的最短路径是"+dis[k][i] + ")");
            }
            System.out.println();
        }
    }
    
    //弗洛伊德算法
    public void fliyed() {
        int len = 0;//变量保存距离
        
        //对中间顶点的遍历
        for (int k = 0; k < dis.length; k++) {
            //对出发节点的遍历
            for (int i = 0; i < dis.length; i++) {
                //对终点的遍历
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];//求出从i顶点出发，经过k中间顶点，到达顶点的距离
                    if (len < dis[i][j]) {
                        dis[i][j] = len;//更新距离
                        pre[i][j] = pre[k][j];//更新前驱顶点
                    }


                }
            }
        }
    }
}


