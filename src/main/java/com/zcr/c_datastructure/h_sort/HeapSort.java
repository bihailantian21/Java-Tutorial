package com.zcr.c_datastructure.h_sort;

/**
 * 堆排序O(nlogn).   额外空间复杂度O(1)
 * 堆：可以看成一棵完全二叉树，每个结点的值都大于等于（小于等于）其左右孩子结点的值，称为大顶堆（小顶堆）。
 * 左孩子下标2i+1
 * 右孩子下标2i+2
 * 父节点下标(i-1)/2
 *
 * 堆排序的基本思想：
 * （1）将带排序的序列构造成大顶堆，最大值为根结点。
 * （2）将根结点与最后一个元素交换，对除最大值外的剩下n-1个元素重新构造成大顶堆，可以获得次大的元素。
 * （3）反复执行，就可以得到一个有序序列了。
 *
 * 构造大顶堆的方法：
 * 1.首先复习完全二叉树的性质，层序遍历，当第一个元素索引从0开始时，索引为i的左孩子的索引是 (2i+1)，右孩子的索引是 (2i+2)。
 * 2.设计一个函数heapAdjust()，对于一个序列（除了第一个根结点外，其余结点均满足最大堆的定义），通过这个函数可以将序列调整为正确的大顶堆。
 * 3.正式构造：将带排序的序列看成一棵完全二叉树的层序遍历，我们从下往上，从右往左，依次将每个非叶子结点当作根结点，使用heapAdjust()调整成大顶堆。
 */

/**
 * 时间复杂度O(N*logN)，额外空间复杂度O(1)
 * 堆结构非常重要
 * 1，堆结构的heapInsert与heapify
 * 2，堆结构的增大和减少
 * 3，如果只是建立堆的过程，时间复杂度为O(N)
 * 4，优先级队列结构，就是堆结构
 *
 * 堆很有用！
 * 不管是增加一个数还是减少一个数，时间复杂度都是O(logN)
 *
 * 堆的一个应用：
 * 小根堆里放的是小的N/2，
 * 大根堆里放的是大的N/2，
 */
public class HeapSort {

    //堆排序的时候，就是每次找到最大的数，与最后的数交换，然后把heap的size--继续进行调整。都是在array中进行的。
    public static void heapSort(int arr[]){
        //第一步：建立大根堆heapInsert
        //第二步：把最后一个位置和堆顶做交换，最大值来到了数组最后的位置。然后数组长度减1。
        //第三步：就再把前面的再调整为大根堆heapify的过程。
        //把堆顶和现在最后一个数做交换，倒数第二个位置上留着第二大的数。数组长度减一。继续循环
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            ////建立大根堆的过程
            //依次把i位置的数加进来，形成0~i位置的大根堆
            heapInsert(arr, i);//0~i
        }
        int size = arr.length;
        swap(arr, 0, --size);//最后一个位置上的数和堆顶数字交换
        while (size > 0) {
            heapify(arr, 0, size);//调整出一个大根堆
            swap(arr, 0, --size);//最后一个位置上的数和堆顶数字交换
        }
    }
    public static void heapInsert(int[] arr, int index) {//建立大根堆的过程
        while (arr[index] > arr[(index - 1) / 2]) {
            //循环结束条件是，等于或者大于了。其中包含了那种0位置上的数和(0-1)/2=0上的数比较，兼顾了往前跳。
            //只要我比我当前的父位置的数要大，我就和我的父位置的数交换
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;//然后我来到了我的父位置，继续这个while循环
        }
    }
    public static void heapify(int[] arr, int index, int size) {//假设数组中有一个数的值发生变化了，变小了，现在如何把它还调整为大根堆
        //size就是数组的大小 0~size-1范围内已经形成了大根堆，再往右就是越界
        //index是发生变化的位置，变小了，所以应该往下沉
        int left = index * 2 + 1;//左孩子
        while (left < size) {//左孩子的下标不越界才循环，结束条件就是左孩子越界了是叶节点了
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;//left+1是右孩子。largest 是找出左右孩子中最大的那个值。
            //如果右孩子不越界，且右孩子大于左孩子，最大值就是右孩子。
            //右孩子越界，说明你只有左孩子，最大值就是左孩子。右孩子小于左孩子，最大值就是左孩子。
            largest = arr[largest] > arr[index] ? largest : index;//孩子和父亲之间比较，看谁最大
            if (largest == index) {//自己就最大，那么就还是大根堆，不需要做调整往下沉了
                break;
            }
            swap(arr, largest, index);//largest!=index，把自己和孩子中最大的那个交换
            index = largest;//
            left = index * 2 + 1;//开始下一次的循环
        }
    }
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
