package com.zcr.a_offer.d_tree;


import java.util.ArrayList;

/**
 * 25、二叉树中和为某一值的路径
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class FindSumPath25 {

    /**
     * 1、路径以根为第一个节点，所以先遍历树，以前序遍历。
     * 2、每访问一个节点的时候，我们就把当前节点添加到路径中去,累加。
     * （如果是叶结点且和等于目标值，加到结果中）
     * （不是叶结点，继续访问访问它的子节点。）
     * 3、然后到叶节点后，开始往回递归。每次从子节点回到父节点的时候，需要从路径中删除子节点。
     *
     * 树大部分是递归的套路。递归函数思路如下：
     * 遍历到当前结点，每次先把当前结点加入path中，并累加当前curSum += node.val，
     * 然后判断是否是叶子而且curSum == target，如果没有达到目标，就递归左右子树；
     * 如果到达了叶子且curSum == target，就将中间结果path存入到总结果res中，递归函数中path记得要回溯；
     *
     *   10
     *  5 12
     * 4 7
     * 递归层 1  1-1(2)   2-1(3)  3-1    3-2        2-2
     * root  10   5    4(叶)     null   null  5     7(叶)
     * path [10][10,5][10,5,4]             [10,5]  [10,5,7]
     * cuSum 10  15   19                    15      22
     * res
     *
     *
     * 递归的变量：根、目标值、结果、当前累加和
     * @param root
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        int curSum = 0;
        ArrayList<Integer> path = new ArrayList<>();
        helper(root,target,result,path,0);
        return result;
    }
    public void helper(TreeNode root, int target, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> path, int curSum) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        curSum += root.val;
        if (root.right == null && root.right == null && curSum == target) {
            result.add(new ArrayList<>(path));
        }
        helper(root.left,target,result,path,curSum);
        helper(root.right,target,result,path,curSum);
        path.remove(path.size() - 1);//递归到叶子节点如果还没有找到路径，就要回退到父节点继续寻找，依次类推
    }


    /**
     *
     *   10
     *  5 12
     * 4 7
     * 递归层 1   1-1(2)           2-1            2-2               1-2
     * root  10   5    4(叶)       5     7(叶)      5        10      12        10
     * path [10][10,5][10,5,4]   [10,5]  [10,5,7] [10,5]     [10]   [10,12]    [10]    []
     * cuSum 10  15   19           15    22         15     10         22         10    结束
     * res
     *
     * 疑问：为什么数字curSum不需要回退？而数组path需要回退？
     * 因为在递归中每次需要回溯的是数组
     *
     * 递归的变量：根、目标值、结果、当前累加和
     * @param root
     * @param target
     * @return
     */
    public void helper2(TreeNode root, int target, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> path, int curSum) {
        if (path == null) { //递归的技术条件
            return;
        }
        path.add(root.val);
        curSum += root.val;
        if (root.right == null && root.right == null && curSum == target) {
            result.add(new ArrayList<>(path));
        }
        if (root.left != null) {
            helper2(root.left,target,result,path,curSum);
        }
        if (root.right != null) {
            helper2(root.right,target,result,path,curSum);
        }
        path.remove(path.size() - 1);//递归到叶子节点如果还没有找到路径，就要回退到父节点继续寻找，依次类推
        //curSum -= path.get(path.size() - 1);//在后面加或不加都没意义啊，要知道变量是作为方法参数往下传的，target是int型，在方法内部赋予新值不会影响方法外该变量的值。
                                               //值传递，都是函数栈中的局部变量，返回上一个函数栈时，上一个函数栈中的target是一个没减之前的。所以加不加是没有任何影响的
                                                //targrt在递归中就是他所在那层的值，下一层递归所改变的值不会递归到上一层，不需要加回去。
    }
}
