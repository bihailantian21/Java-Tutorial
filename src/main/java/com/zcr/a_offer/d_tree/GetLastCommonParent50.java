package com.zcr.a_offer.d_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 50.树中两个结点的最低公共祖先
 */
public class GetLastCommonParent50 {

    /**
     * 情景一：
     * 假设该树是一棵二叉搜索树：（二叉搜索树是排过序的，位于左子树的节点都比根节点小、位于右子树的节点都比根节点大）
     * 我们只需从根结点判断，如果二结点与根的左右子树比较一大一小，那么根结点就是二者最低公共祖先；
     * 如果二结点都比左子结点小，向左子树递归进行比较；
     * 如果二结点都比右子树结点大，向右子树递归进行比较；
     * @param pRoot
     * @param pLeft
     * @param pRight
     * @return
     */
    public static TreeNode getLastCommonNode(TreeNode pRoot,TreeNode pLeft,
                                             TreeNode pRight) {
        TreeNode treeNode = null;
        if(pRoot==null || pLeft.val>pRight.val)
            return null;
        if((pRoot.val>=pRight.val) && (pRoot.val>=pLeft.val))
            treeNode = getLastCommonNode(pRoot.left,pLeft,pRight);
        if((pRoot.val<=pLeft.val) && (pRoot.val<=pRight.val))
            treeNode = getLastCommonNode(pRoot.right,pLeft,pRight);
        if(pRoot.val>=pLeft.val && pRoot.val<=pRight.val)
            return pRoot;
        return treeNode;
    }


    /**
     * 情景二：
     * 假设该树是一棵普通的树，且有指向父节点的指针：
     * 如果不是二叉搜索树，但带有指向父节点的指针，那么此题转换成在两个有相交的链表上求第一个相交点。
     * 指向父节点的指针是pParent，那么从树的每一个叶节点开始，都有一个由指针pParent串起来的链表，这些链表的尾节点都是根节点。
     * 从下到上查找
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    private static Object findParent(TreeNode2<Character> root,
                                     TreeNode2<Character> node1,TreeNode2<Character> node2) {
        if(node1==null || node2==null)
            return null;
        //得到两链表的长度
        int len1=1,len2=1;
        TreeNode2<Character> temp=node1;
        while(temp.parent!=null) {
            len1++;
            temp=temp.parent;
        }
        temp=node2;
        while(temp.parent!=null) {
            len2++;
            temp=temp.parent;
        }
        //长度长的链表先移动
        int step = Math.abs(len1-len2);
        TreeNode2 tempLong = len1>=len2? node1:node2;
        TreeNode2 tempShort = len1<len2? node1:node2;
        for(int i=0;i<step;i++)
            tempLong = tempLong.parent;

        //两链表一起移动，知道找到相等的节点则返回
        for(int i=0;i<Math.min(len1, len2);i++) {
            if(tempLong.data==tempShort.data) {
                return tempLong.data;
            }
            tempLong = tempLong.parent;
            tempShort = tempShort.parent;
        }
        return null;
    }

    private class TreeNode2<T>{
        T data;
        TreeNode2<T> parent = null;
        ArrayList<TreeNode2<T>> children = new ArrayList<TreeNode2<T>>();
        TreeNode2(T data){
            this.data = data;
        }
    }


    /**
     * 情景三：
     * 假设该树是普通的树，且没有指向父节点的指针：
     * 需要保存从root根节点到p和q节点的路径，并将路径存入list中，则问题转化成求两个list集合的最后一个公共元素
     * 从上到下查找
     * @param root
     * @param p1
     * @param p2
     * @return
     */
    public static TreeNode3 getLastCommonParent(TreeNode3 root,TreeNode3 p1,
                                                TreeNode3 p2) {
        //存储根节点到p1和p2的路径（不包括p1和p2）
        List<TreeNode3> path1 = new ArrayList<TreeNode3>();
        List<TreeNode3> path2 = new ArrayList<TreeNode3>();
        List<TreeNode3> tmpList = new ArrayList<TreeNode3>();

        getNodePath(root,p1,tmpList,path1);
        getNodePath(root,p2,tmpList,path2);
        //如果路径不存在，返回空
        if(path1.size()==0 || path2.size()==0)
            return null;

        return getLastCommonParent(path1,path2);
    }

    //获取根节点到目标节点的路径
    public static void getNodePath(TreeNode3 root,TreeNode3 target,
                                   List<TreeNode3>tmpList,List<TreeNode3>path) {
        //鲁棒性
        if(root==null || root==target)
            return ;
        tmpList.add(root);
        //System.out.println(root);
        List<TreeNode3> children = root.children;
        for(TreeNode3 node : children) {
            if(node==target) {
                path.addAll(tmpList);
                break;
            }
            getNodePath(node,target,tmpList,path);
        }
        tmpList.remove(tmpList.size()-1);
    }

    //将问题转换为求链表最后一个公共节点
    private static TreeNode3 getLastCommonParent(List<TreeNode3>p1,
                                                List<TreeNode3>p2) {
        TreeNode3 tmpNode = null;
        for(int i=0;i<p1.size();i++) {
            if(p1.get(i) != p2.get(i))
                break;
            tmpNode = p1.get(i);
        }
        return tmpNode;
    }

    private static class TreeNode3{
        int val;
        List<TreeNode3> children = new ArrayList<>();
        public TreeNode3(int val) {
            this.val = val;
        }
        @Override
        //重写
        public String toString() {
            return val+"";
        }
    }

}
