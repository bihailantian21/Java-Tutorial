package com.zcr.a_offer.d_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 64.数据流中的中位数
 * 如何得到一个数据流中的中位数？
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 */
public class GetMedian64 {

    /**
     * 利用一个最大堆和一个最小堆即可。
     *
     * 优先队列的结构。
     * PriorityQueue通过二叉小顶堆实现，可以用一棵完全二叉树表示。
     * 优先队列的作用是能保证每次取出的元素都是队列中权值最小的，这里牵涉到了大小关系，
     * 元素大小的评判可以通过元素本身的自然顺序（natural ordering），也可以通过构造时传入的比较器（Comparator）。
     *
     * 优先队列默认按从小到大的顺序排列，如果需要一个大顶堆的话，需要重写compar方法。
     */
    //堆顶最小，但是存的是最大的 n/2个元素
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    //堆顶最大，但是存的是最小的 n/2个元素
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

  /*  public PriorityQueue<Integer> maxHeap = new PriorityQueue<>(11,
            new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });*/


    /**
     * ①.当获取到数据时，先判断最大堆是否为空？
     * ②若为空时，则将获取到的数据添加到最大堆中；
     * ③若最大堆不为空时需要比较最大堆堆顶元素与获取到的元素的大小，
     * 若最大堆堆顶元素大，则将该元素添加至最大堆中；若新元素大，则需要判断最小堆的情况。
     * ④若最小堆为空时，直接将该元素添加至最小堆中；若最小堆不为空，则需比较新元素与最小堆堆顶元素的大小；
     * ⑤若最小堆堆顶元素小于等于，则将该元素添加至最小堆中；反之，则将该元素添加至最大堆中。
     * ⑥添加完成后，需要根据两个堆的大小情况平衡两堆中的元素，使之相差不超过1。
     *
     * 用两个堆保存数据，保证两个堆的数据保持平衡（元素个数相差不超过1）
     * 大顶堆存放的数据要比小顶堆的数据小
     * 当两个堆中元素为偶数个，将新加入元素加入到大顶堆，
     * 如果要加入的数据，比小顶堆的最小元素大，先将该元素插入小顶堆，然后将小顶堆的最小元素插入到大顶堆。
     *
     * 当两个堆中元素为奇数个，将新加入元素加入到小顶堆，
     * 如果要加入的数据，比大顶堆的最大元素小，先将该元素插入大顶堆，然后将大顶堆的最大元素插入到小顶堆。
     * @param num
     */
    private int count = 0;

    public void Insert(Integer num) {
        if (count %2 == 0) {//当数据总数为偶数时，新加入的元素，应当进入小根堆
            //（注意不是直接进入小根堆，而是经大根堆筛选后取大根堆中最大元素进入小根堆）
            //1.新加入的元素先入到大根堆，由大根堆筛选出堆中最大的元素
            maxHeap.offer(num);
            int filteredMaxNum = maxHeap.poll();
            //2.筛选后的【大根堆中的最大元素】进入小根堆
            minHeap.offer(filteredMaxNum);
        } else {//当数据总数为奇数时，新加入的元素，应当进入大根堆
            //（注意不是直接进入大根堆，而是经小根堆筛选后取小根堆中最大元素进入大根堆）
            //1.新加入的元素先入到小根堆，由小根堆筛选出堆中最小的元素
            minHeap.offer(num);
            int filteredMinNum = minHeap.poll();
            //2.筛选后的【小根堆中的最小元素】进入大根堆
            maxHeap.offer(filteredMinNum);
        }
        count++;
    }
    public Double GetMedian() {
        if (count %2 == 0) {
            return new Double((minHeap.peek() + maxHeap.peek())) / 2;
        } else {
            return new Double(minHeap.peek());
        }
    }











    public void Insert2(Integer num) {
        if(maxHeap.isEmpty() || num <= maxHeap.peek()){
            maxHeap.add(num);
        }else{
            minHeap.add(num);
        }

        if(minHeap.size() - maxHeap.size() > 1)
            maxHeap.add(minHeap.poll());
        else if(maxHeap.size() - minHeap.size() > 1){
            minHeap.add(maxHeap.poll());
        }
    }

    public Double GetMedian2() {
        if(minHeap.size() > maxHeap.size())
            return 1.0 * minHeap.peek();
        else if(maxHeap.size() > minHeap.size())
            return 1.0 * maxHeap.peek();
        else
            return 1.0 * (minHeap.peek() + maxHeap.peek())/2;
    }















    ArrayList<Integer> res = new ArrayList<>();
    public void Insert3(Integer num) {
        res.add(num);
        Collections.sort(res);
    }
    public Double GetMedian3() {
        int n = res.size();
        if (n % 2 == 0) {
            return Double.valueOf((res.get(n / 2) + res.get(n / 2 - 1)) / 2.0);
        } else {
            return Double.valueOf(res.get(n / 2));
        }
    }



}
