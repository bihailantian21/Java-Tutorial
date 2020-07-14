package com.zcr.c_datastructure.e_tree;

import java.util.Arrays;

/**
 * @author zcr
 * @date 2019/7/8-16:56
 */
public class HeapSort {

    public static void main(String[] args) {
        int arr[] = {4,6,8,5,9,-1,90,89,56,-999};
        heapSort(arr);
    }

    //编写堆排序的方法
    public static void heapSort(int arr[]) {
        int temp = 0;
        System.out.println("堆排序");

        /*//分步完成
        adjustHeap(arr,1,arr.length);
        System.out.println("第一次调整后"+ Arrays.toString(arr));//[4,9,8,5,6]

        adjustHeap(arr,0,arr.length);
        System.out.println("第二次调整后"+Arrays.toString(arr));//[9,6,8,5,4]
*/
        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 -1; i >= 0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }
//        System.out.println(Arrays.toString(arr));

        //将堆顶元素与末尾元素交换，将最大元素沉到数组末端
        //重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        for (int j = arr.length - 1; j > 0; j--) {//调整4个数就够了
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);//真实的调整总是从顶上开始的
        }
        System.out.println(Arrays.toString(arr));



    }

    //将一个数组（二叉树的顺序存储），调整成一个大顶堆
    /**
     * 功能：完成将以i指向对应的非叶子节点的树调整成大顶堆
     * 举例：{4,6,8,5,9} 第一个非叶子节点是i=1 -》{4,9,8,5,6}-i=0》{9,6,8,5,4}
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，length在逐渐减少
     */
    public static void adjustHeap(int arr[],int i,int length) {

        //先取出当前元素的值，保存在临时变量
        int temp = arr[i];
        //开始调整
        //1.k = i * 2 + 1 k是i的左子节点
        //2.k = k * 2 + 1 继续判断k的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明左子节点小于右子节点的值
                k++;//k指向右子节点
            }
            if (arr[k] > temp) {//说明子节点大于父节点
                arr[i] = arr[k];//把较大的值赋值给当前节点
                i = k;//让i指向k，继续循环比较（k下面还有左子树或右子树）
            } else {
                break;//从左到右，从下到上逐渐调整的
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值，放在了最顶上（局部）
        arr[i] = temp;//将temp的值放到调整后的位置
    }
}
