package com.zcr.b_leetcode.bfs;


import java.util.*;

/**
 * 133. 克隆图
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 *
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 *
 * 测试用例格式：
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
 *
 * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 *
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
 *
 *
 *
 * 示例 1：
 * 输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
 * 输出：[[2,4],[1,3],[2,4],[1,3]]
 * 解释：
 * 图中有 4 个节点。
 * 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
 * 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
 * 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
 * 节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
 *
 * 示例 2：
 * 输入：adjList = [[]]
 * 输出：[[]]
 * 解释：输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。
 *
 * 示例 3：
 * 输入：adjList = []
 * 输出：[]
 * 解释：这个图是空的，它不含任何节点。
 *
 * 示例 4：
 * 输入：adjList = [[2],[1]]
 * 输出：[[2],[1]]
 *
 *
 * 提示：
 * 节点数不超过 100 。
 * 每个节点值 Node.val 都是唯一的，1 <= Node.val <= 100。
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
 * 图是连通图，你可以从给定节点访问到所有节点。
 */
public class CloneGraph133 {

    /**
     * 考虑到调用栈的深度，使用 BFS 进行图的遍历比 DFS 更好。
     *
     * 方法一与方法二的区别仅在于 DFS 和 BFS。DFS 以深度优先，BFS 以广度优先。这两种方法都需要借助 HashMap 避免陷入死循环。
     *
     * 算法
     * 1.使用 HashMap 存储所有访问过的节点和克隆节点。HashMap 的 key 存储原始图的节点，value 存储克隆图中的对应节点。
     * visited 用于防止陷入死循环，和获得克隆图的节点。
     * 2.将第一个节点添加到队列。克隆第一个节点添加到名为 visited 的 HashMap 中。
     * 3.BFS 遍历
     * （1）从队列首部取出一个节点。
     * （2）遍历该节点的所有邻接点。
     * （3）如果某个邻接点已被访问，则该邻接点一定在 visited 中，那么从 visited 获得该邻接点。
     * 否则，创建一个新的节点存储在 visited 中。
     * （4）将克隆的邻接点 添加到    克隆图对应节点的邻接表中。
     *
     * 时间复杂度：O(N)，每个节点只处理一次。
     * 空间复杂度：O(N)。visited 使用 O(N)的空间。BFS 中的队列使用 O(W)的空间，其中 W 是图的宽度。总体空间复杂度为O(N)。
     *
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        HashMap<Node, Node> visited = new HashMap<>();

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);//offer poll peek     false null null
        visited.put(node, new Node(node.val,new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node neighbor : cur.neighbors) {//bfs
                if (!visited.containsKey(neighbor)) {
                    visited.put(cur,new Node(cur.val,new ArrayList<>()));//拷贝自己
                    queue.offer(cur);//bfs
                }
                visited.get(cur).neighbors.add(visited.get(neighbor));//拷贝自己的临节点
            }
        }
        return visited.get(node);

    }


    /**
     * dfs
     * 1.从给定节点开始遍历图。
     *
     * 2.使用一个 HashMap 存储所有已被访问和复制的节点。
     * HashMap 中的 key 是原始图中的节点，value 是克隆图中的对应节点。
     * 如果某个节点已经被访问过，则返回其克隆图中的对应节点。
     * 给定边 A - B，表示 A 能连接到 B，且 B 能连接到 A。如果对访问过的节点不做标记，则会陷入死循环中。
     *
     * 3.如果当前访问的节点不在 HashMap 中，则创建它的克隆节点存储在 HashMap 中。
     * 注意：在进入递归之前，必须先创建克隆节点并保存在 HashMap 中。
     *
     * 如果不保证这种顺序，可能会在递归中再次遇到同一个节点，再次遍历该节点时，陷入死循环。
     *
     * 4.递归调用每个节点的邻接点。
     * 每个节点递归调用的次数等于邻接点的数量，每一次调用返回其对应邻接点的克隆节点，
     * 最终返回这些克隆邻接点的列表，将其放入对应克隆节点的邻接表中。这样就可以克隆给定的节点和其邻接点。
     *
     * 提示：如果在递归调用中传入节点自身会出现什么情况？为什么每次递归调用输入不同的节点，
     * 却执行相同的操作。实际上，只需要保证对一个节点的递归调用正确即可，其他的节点也会在递归过程中建立正确的连接关系。
     *
     *
     */
    private HashMap <Node, Node> visited = new HashMap <> ();
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return node;
        }

        // If the node was already visited before.
        // Return the clone from the visited dictionary.
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a clone for the given node.
        // Note that we don't have cloned neighbors as of now, hence [].
        Node cloneNode = new Node(node.val, new ArrayList());
        // The key is original node and value being the clone node.
        visited.put(node, cloneNode);

        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }


}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
