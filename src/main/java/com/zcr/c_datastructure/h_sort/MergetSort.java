package com.zcr.c_datastructure.h_sort;

import com.zcr.b_leetcode.ListNode;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/7/7-16:41
 */
public class MergetSort {

    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length - 1,temp);
        System.out.println("归并排序后="+ Arrays.toString(arr));
    }

    //分 + 合方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归分解
            mergeSort(arr,left,mid,temp);
            //向右递归分解
            mergeSort(arr,mid + 1,right,temp);
            //每分解一次合并
            merge(arr,left,mid,right,temp);
        }
    }
    //合并的方法
    /**
     *
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param middle 中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr,int left,int middle,int right,int[] temp) {
        System.out.println("xxxx");
        int i = left;//初始化i，左边有序序列的初始索引
        int j = middle + 1;//初始化j，右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引

        //先把左右两边的数据（已经有序）按照规则填充到temp数组中，直到左右两边有一边全部处理完毕
        while (i <= middle && j <= right) {
            if (arr[i] <= arr[j]) {//如果左边的有序序列的当前元素，小于等于，右边有序序列的当前元素
                temp[t] = arr[i];//将左边的当前元素拷贝到temp数组中
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //把有剩余数据的一方依次全部填充到temp数组中
        while (i <= middle){//说明左边的有序序列还有剩余的元素
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right){//说明右边的有序序列还有剩余的元素
            temp[t] = arr[j];
            t++;
            j++;
        }
        //将temp数组重新全部拷贝到arr数组中
        //注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        System.out.println("tempLeft=" + tempLeft + "right=" + right);
        while (tempLeft <= right) {//第一次合并时，tempLeft=0,right=1//第一次合并时，tempLeft=2,right=3//第一次合并时，tempLeft=0,right=3
            arr[tempLeft] = temp[t];//最后一次合并时，tempLeft=0,right=7
            tempLeft++;
            t++;
        }

    }


    /**
     * 归并排序O(nlogn)   空间复杂度O(N)  不稳定交换过程中，大的和前面的根交换，所以不会保证稳定性。
     * 递归
     * 递归就是自己调用自己。系统栈。把大问题划分为小问题，划分时候涉及到分治的思想。任何递归行为都可以转化为非递归。
     * 例：求数组中最大值。把max的所有信息压栈，代码行数、参数，然后调用子过程，先跑子过程。
     *
     * 利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略
     * （分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。
     * 规则：一个数组，先把左边排好序，再把右边排好序，再把整体排好序。
     * 准备一个辅助数组，两侧一个下标为a，一个下标为b，谁小谁到辅助数组里面填充，一个移动到末尾了，把另一个后面的都拷贝到辅助数组。
     * @param arr
     */
    public static void mergeSort(int arr[]) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {//不可缺少！！这是结束条件！
            return;
        }
        int mid = (l + r) / 2;//int mid = l + ((r - l) >> 1);
        mergeSort(arr,l,mid);//左侧部分先排好T(N/2)
        mergeSort(arr,mid + 1,r);//右侧部分先排好T(N/2)
        merge(arr,l,mid,r);//此时相当于从l到mid排好序了，从mid+1到r排好序了，如何让整体有序O(N)
    }

    public static void merge(int[] arr,int l,int m,int r) {
        int[] help = new int[r - l + 1];//辅助数组
        int i = 0;//依次在辅助数组中的位置
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r ) {
            //help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
            if (arr[p1] < arr[p2]) {
                help[i++] = arr[p1++];
            } else {
                help[i++] = arr[p2++];
            }
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }















    /**
     * 使用归并排序对链表进行排序
     * 在O（n log n）时间内对链表进行排序。
     *
     * 解决思路
     * 看到题目中的 nlogn 以及 sort，很自然的联想到mergesort的思想。只不过mergesort通常是以排序数组元素出现的，现在变成了排序链表。
     * 找到链表中点 （快慢指针思路，快指针一次走两步，慢指针一次走一步，快指针在链表末尾时，慢指针恰好在链表中点）；
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null) return head;

        ListNode mid = findMiddle(head);
        //跟数组不同，这里只传递头指针
        //分成两段，一段的head是head，另一半的head是mid.next
        ListNode head1 = sortList(mid.next);
        mid.next = null;
        ListNode head2 = sortList(head);
        //归并排序
        return mergeSort(head1,head2);
    }
    //归并排序
    public static ListNode mergeSort(ListNode head1,ListNode head2){
        if(head1==null){
            return head2;
        }
        if(head2==null){
            return head1;
        }

        ListNode newhead = new ListNode(0);//new一个链表
        ListNode newlist = newhead;//用于记录新链表的head
        while(head1!=null && head2!=null){
            if(head1.value < head2.value){
                newlist.next = head1;
                head1 = head1.next;
            }
            else{
                newlist.next = head2;
                head2 = head2.next;
            }
            newlist = newlist.next;
        }
        if(head1==null){
            newlist.next = head2;
        }
        if(head2==null){
            newlist.next = head1;
        }

        return newhead.next;
    }

    //寻找链表中点
    public static ListNode findMiddle(ListNode head){
        ListNode c = head;
        ListNode r = head.next;//c与r需要一直间隔两倍，c在1，r在2，c在2，r在4

        while(r!=null && r.next!=null){
            //当链表长为单数时，r最后一位会跳到null，跳出，c也刚好调到最中间那点
            //当链表长为双数时，r会刚好跳到链表末段，跳出，c也刚好跳到双数的中间
            c = c.next;
            r = r.next.next;
        }
        return c;
    }
}

/*
1. 给定一个单链表的头节点和整数n，从列表末尾删除第n个节点并返回链表头部，限制在一次操作内完成。
        例如给定链表：1-> 2-> 3-> 4-> 5，n=2 ，从末尾删除第2个节点后，链表变为：1-> 2-> 3-> 5

        2. 给定一个乱序的单链表的头节点，对该链表中的节点进行排序，尽量考虑时间复杂度和空间复杂度。
        例如给定链表：1-> 4-> 3-> 2-> 5，排序后链表变为：1-> 2-> 3-> 4-> 5




*/

/*
public class Solution {

    class ListNode() {
        private int value;
        private ListNode next;
    public ListNode(int value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return this.value + this.next;
        }
    }

    public static ListNode deleteNode(ListNode head, int n) {
        if(head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; fast != null && i < n;i++) {
            fast = fast.next;
        }
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        if(slow.next.next != null) {
            slow.next = slow.next.next;
        } else {
            slow.next = null;
        }
        return head;
    }

    public static void sort(ListNode head) {
        ListNode result;
        if(head == null) {
            return null;
        }
        ListNode middle = findMid(head);
        ListNode miidle2 = middle.next;
        middle.next = null;
        ListNode left = sort(head);
        ListNode right = sort(middle2);
        result = mergeSort(left,right);
    }

    public ListNode mergeSort(ListNode left, ListNode right) {
        ListNode result = new ListNode(0);
        while(left != null && right != null) {
            if(left.value <= right.value) {
                result.next = left;
                left = left.next;
            } else {
                result.next = right;
                right = right.next;
            }
        }
        while(left != null) {
            result.next = left;
            left = left.next;
        }
        while(right != null) {
            result.next = right;
            right = right.next;
        }
        return result;
    }

    public ListNode findMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int n = 2;
        ListNode result1 = deleteNode(node1,n);
        System.out.println(result1);

        ListNode result2 = sort(node1);
        System.out.println(result1);
    }
}



*/
