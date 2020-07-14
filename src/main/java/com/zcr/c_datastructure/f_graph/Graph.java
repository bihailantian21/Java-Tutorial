package com.zcr.c_datastructure.f_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zcr
 * @date 2019/7/9-21:05
 */
public class Graph {

    public static void main(String[] args) {
        //测试图
        int n = 8;//节点的个数
        //String Vertexs[] ={"A","B","C","D","E"};
        String Vertexs[] ={"1","2","3","4","5","6","7","8"};
        //创建图
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }
        /*//添加边 A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);*/
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);


        //打印图矩阵
        graph.showGraph();

        //深度优先遍历
        System.out.println("深度优先遍历结果：");
        graph.dfs();//A->B->C->D->E->
        System.out.println();

        //广度优先遍历
        System.out.println("广度优先遍历结果：");
        graph.bfs();//A->B->C->D->E->


    }

    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    //定义数组，记录某个顶点是否被访问过
    private boolean isVisited[];

    //构造器
    public Graph(int n) {//n顶点的个数
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);

    }

    //插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边
    /**
     *
     * @param v1 第一个顶点的下标，即是第几个顶点 "A"-"B" 0-1
     * @param v2 第二个顶点的下标
     * @param weight 值
     */
    public void insertEdge(int v1,int v2,int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点 i（下标）对应的数据 0->"A"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1,int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //得到第一个邻接节点的下标w
    /**
     *
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历
    //对一个节点的深度优先遍历
    private void dfs(boolean[] isVisited,int i) {
        //首先我们访问该节点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将该节点设置为已访问
        isVisited[i] = true;

        //查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) { //w存在
            if (!isVisited[w]) {//如果w还未被访问
                dfs(isVisited,w);
            }
            //如果w已经被访问
            w = getNextNeighbor(i,w);
        }
    }

    //对dfs进行重载，遍历所有的节点，并进行dfs
    public void dfs() {
        isVisited = new boolean[8];
        //遍历所有的节点，进行dfs【回溯】
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited,i);
            }
        }
    }

    //广度优先遍历
    //对一个节点的广度优先遍历
    private void bfs(boolean[] isVisited,int i) {
        int u;//表示队列的头结点对应的下标
        int w;//邻接节点w
        //队列
        LinkedList queue = new LinkedList();//addLast removeFirst

        //访问节点
        //首先我们访问该节点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将该节点设置为已访问
        isVisited[i] = true;

        //将这个节点加入队列
        queue.addFirst(i);

        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer)queue.removeFirst();//自动拆箱
            //得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {
                //是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱，找w后面的下一个邻接点
                w = getNextNeighbor(u,w);//体现出我们的广度优先
            }
        }
    }

    //重载广度优先，遍历所有的节点都进行广度优先搜索
    public void bfs() {
        isVisited = new boolean[8];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited,i);
            }
        }
    }




}
