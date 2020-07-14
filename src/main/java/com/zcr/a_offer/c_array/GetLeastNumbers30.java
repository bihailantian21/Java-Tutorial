package com.zcr.a_offer.c_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 30.最小的k个数
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class GetLeastNumbers30 {

    /**
     * 思路一使用类似快排partition
     * 1.使用快速排序类似partition的过程，概率复杂度可以做到O(n)。(BFPRT算法可以稳定做到O(N))；
     * 2.和快排不同的是，这个递归的时候只需要去某一边，但是快排两边都要去；
     * 3.这种方法修改了原数组；
     *
     * 具体思路
     * 根据partition不停的划分，直到我们的border(分界点) = K-1，这时，<=K-1位置的数就是最小的K个数，每次只需要往一边：
     * 1.如果我们选的划分数很好(在中间): 则T(N) = T(N/2) + O(N) (注意不是2*T(N/2)，因为只需要往某一边走)，根据Master公式可以得到时间复杂度为: O(N)；
     * 2.如果我们选的划分数很差(极端) : 则T(N) = T(N-1) + O(N) ，根据Master公式可以得到，时间复杂度为 O(N2)；
     * 3.但是概率平均复杂度为O(N)，或者可以使用BFPRT优化到O(N)；
     *
     * 时间：O(n)
     * @param array
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] array, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        int len = array.length;
        if (array == null || len == 0 || k <= 0 || k > len) {
            return res;
        }
        int low = 0;
        int high = len - 1;
        int pivot = partition(array, low, high);
        while (pivot != k - 1) {
            if (pivot > k - 1) {
                high = pivot - 1;
                pivot = partition(array, low, high);
            } else {
                low = pivot + 1;
                pivot = partition(array, low, high);
            }
        }
        for (int i = 0; i < k; i++) {
            res.add(array[i]);
        }
        return res;
    }

    public static int partition(int[] a, int low, int high) {
        int pivotKey = a[low];  //用第一个元素作为基准元素
        while (low < high) { //两侧交替向中间扫描
            while (low < high && a[high] >= pivotKey)
                high--;
            a[low] = a[high];
            while (low < high && a[low] <= pivotKey)
                low++;
            a[high] = a[low];
        }
        a[low] = pivotKey;
        return low;     //返回基准元素所在位置
    }


    /**
     * 时间：O(nlogk)，没有修改原数组，适合处理海量数据
     * 由于内存的大小是有限的，有可能不能把这些海量数据一次性全部载入内存。
     * 这时候我们可以从辅助存储空间（如：磁盘）中每次读入一个数，根据GetLeastNumbers_Solution2方式判断是不是需要放入到容器中即可
     *
     * 思路二使用堆
     * 1.使用最大堆维护K个数(堆顶最大)，一直保持堆中有K个最小的数；
     * 2.堆顶元素就是K个数中的最大数，然后每次和插入的数比较，如果有更小的，就替换堆顶即可；否则（插入的数比最大数还大），继续插入下一个数。
     * 3.时间复杂度N*logK，也可以使用最小堆来做；
     *
     * 使用红黑树：查找、删除、插入都只需要O(logk)
     *
     *
     * PriorityQueue默认是一个小顶堆，然而可以通过传入自定义的Comparator函数来实现大顶堆。如下代码实现了一个初始大小为11的大顶堆。
     * 这里只是简单的传入一个自定义的Comparator函数，就可以实现大顶堆了。
     * private static final int DEFAULT_INITIAL_CAPACITY = 11;
     * PriorityQueue<Integer> maxHeap=
     *      new PriorityQueue<Integer>(DEFAULT_INITIAL_CAPACITY,
     *      new Comparator<Integer>() {
     * @Override
     * public int compare(Integer o1, Integer o2) {
     *      return o2-o1;
     * }
     * });
     * PriorityQueue的逻辑结构是一棵完全二叉树，存储结构其实是一个数组。逻辑结构层次遍历的结果刚好是一个数组。
     *
     *
     * @param array
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution2(int [] array, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        int len = array.length;
        if (array == null || len == 0 || k <= 0 || k > len) {
            return res;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k,new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }//大顶堆，从大到小排序
        });
        for (int i = 0; i < len; i++) {
            if (maxHeap.size() < k) {
                maxHeap.add(array[i]);
            } else if (array[i] < maxHeap.peek()){
                maxHeap.poll();
                maxHeap.add(array[i]);
            }
        }
        for (Integer item : maxHeap) {
            res.add(item);
        }
        return res;
    }


    /**
     * 我们可以手写一个堆
     * 空间：O(k)
     * @param array
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution3(int [] array, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        int len = array.length;
        if (array == null || len == 0 || k <= 0 || k > len) {
            return res;
        }
        int[] maxHeap = new int[k];
        for (int i = 0; i < k; i++) {
            heapInsert(maxHeap,array[i],i);//这里加一个array[i]，因为数组和最大堆是两个不同的数组
        }
        //之前堆排序的时候，就是每次找到最大的数，与最后的数交换，然后把heap的size--继续进行调整。都是在array中进行的。
        //现在输入的是一个数组，建立最大堆的又是一个新的数组
        //现在就是固定size为k，替换这个堆中的大的数，从而找到更小的数。
        for (int i = k; i < len; i++) {
            if (array[i] < maxHeap[0]) {
                maxHeap[0] = array[i];
                heapify(maxHeap,0,k);
            }
        }
        for (int i = 0; i < k; i++) {
            res.add(maxHeap[i]);
        }
        return res;
    }

    public void heapInsert(int[] maxHeap,int num,int index) {
        maxHeap[index] = num;
        while (maxHeap[index] > maxHeap[(index - 1) / 2]) {
            swap(maxHeap,index,(index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void heapify(int[] maxHeap,int index,int size) {
        int left = index * 2 + 1;
        while (left < size) {
            //找出左右孩子中最大的数（如果右孩子也存在的话，比较左右孩子找出最大的；如果右孩子不存在的话，最大的数就是左孩子自己）
            int largest = left + 1 < size && maxHeap[left + 1] > maxHeap[left] ? left + 1 : left;
            //比较最大的孩子与父节点
            largest = maxHeap[index] > maxHeap[largest] ? index : largest;
            //如果本身就是最大的，直接不需要调整了
            if (largest == index) {
                break;
            }
            //否则，交换两个数的位置
            swap(maxHeap,index,largest);
            //开始下一轮的循环
            index = largest;
            left = index * 2 + 1;
        }
    }

    public void swap(int[] array,int i,int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {4,5,1,6,2,7,3,8};
        GetLeastNumbers30 getLeastNumbers30 = new GetLeastNumbers30();
        ArrayList result = getLeastNumbers30.GetLeastNumbers_Solution3(array,4);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
