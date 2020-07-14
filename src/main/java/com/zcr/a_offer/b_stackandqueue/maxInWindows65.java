package com.zcr.a_offer.b_stackandqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 *
 * 65.滑动窗口的最大值
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
 * 他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
 * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class maxInWindows65 {

    /**
     * 思路：
     * 1、遍历数组，每次取滑动窗口的所有值，遍历窗口中的元素，取出最大值
     * O(nm)
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || size <= 0 || num.length < size) {
            return res;
        }
        // 暴力破解法
        int len = num.length;
        int maxIdx = len - size;
        for (int i=0; i<= maxIdx; i++) {
            // 获取当前序列的最大值
            int curMax = num[i];
            for (int j=i+1; j<i+size; j++) {
                curMax = curMax > num[j] ? curMax : num[j];
            }
            // 最大值加入res
            res.add(curMax);
        }

        return res;
    }
    /**
     * 思路：
     * 1、双端队列保存滑动窗口的最大值(保存在头部)，次大值数据
     * 2、窗口滑动，从右侧遍历，比当前值小的移出队列，队首元素过期 移出队列，
     *    当前元素的索引加入队列
     *
     *
     * Deque<String> deque = new LinkedList<String>();
     *
     * 继承关系是：deque => queue => collection=》Iterable
     * 1.使用队列的时候，new LinkedList的时候为什么用deque接收，不用LinkedList呢？
     * 　　答：deque继承queue接口，因为它有两个实现，LinkedList与ArrayDeque。用deque接收是因为向上转型(子类往父类转，会丢失子类的特殊功能)了。
     *       可以试试，用get()方法，LinkedList接收才有。
     * 2.为什么有一个实现还不够，还弄两个呢，它们总有区别吧？
     * 　　答：ArrayDeque是基于头尾指针来实现的Deque，意味着不能访问除第一个和最后一个元素。想访问得用迭代器，可以正反迭代。
     * 　　　　ArrayDeque一般优于链表队列/双端队列，有限数量的垃圾产生（旧数组将被丢弃在扩展），建议使用deque，ArrayDeque优先。
     *
     * Deque是一个线性collection，支持在两端插入和移除元素。名称 deque 是“double ended queue（双端队列）”的缩写，通常读为“deck”。
     * 大多数 Deque 实现对于它们能够包含的元素数没有固定限制，但此接口既支持有容量限制的双端队列，也支持没有固定大小限制的双端队列。
     *
     * 此接口定义在双端队列两端访问元素的方法。提供插入、移除和检查元素的方法。每种方法都存在两种形式：
     * 一种形式在操作失败时抛出异常，另一种形式返回一个特殊值（null 或 false，具体取决于操作）。
     * 插入操作的后一种形式是专为使用有容量限制的 Deque 实现设计的；在大多数实现中，插入操作不能失败。
     *
     * 下表总结了上述 12 种方法：
     *               第一个元素 (头部)	最后一个元素 (尾部)
     *           抛出异常	 特殊值	     抛出异常	特殊值
     * 插入	addFirst(e)	offerFirst(e)	addLast(e)	offerLast(e)
     * 删除	removeFirst()	pollFirst()	removeLast()	pollLast()
     * 检查	getFirst()	peekFirst()	    getLast()	peekLast()
     *
     * Deque接口扩展了 Queue 接口。在将双端队列用作队列时，将得到 FIFO（先进先出）行为。
     * 将元素添加到双端队列的末尾，从双端队列的开头移除元素。从 Queue 接口继承的方法完全等效于 Deque 方法，如下表所示：
     * Queue方法	等效Deque方法
     * add add(e)	addLast(e)
     * offer(e)	offerLast(e)
     * remove()	removeFirst()
     * poll()	pollFirst()
     * element()	getFirst()
     * peek()	peekFirst()
     *
     * 双端队列也可用作 LIFO（后进先出）堆栈。应优先使用此接口而不是遗留 Stack 类。
     * 在将双端队列用作堆栈时，元素被推入双端队列的开头并从双端队列开头弹出。堆栈方法完全等效于 Deque 方法，如下表所示：
     * 堆栈方法	等效Deque方法
     * push(e)	addFirst(e)
     * pop()	removeFirst()
     * peek()	peekFirst()
   、
     *
     *
     */


    /**
     * 思路??不懂
     * 　　蛮力直接在每个滑动窗口依次比较找出最大值，时间复杂度太高。
     * 　　我们考虑把每个可能成为最大值的数字记录下来，就可以快速的得到最大值。
     *
     * 　　思路：建立一个两端开口的队列，放置所有可能是最大值的数字（存放的其实是对应的下标），且最大值位于队列开头。从头开始扫描数组，
     * 　　1.如果遇到的数字比队列中所有的数字都大，那么它就是最大值，其它数字不可能是最大值了，将队列中的所有数字清空，放入该数字，该数字位于队列头部；
     * 　　2.如果遇到的数字比队列中的所有数字都小，那么它还有可能成为之后滑动窗口的最大值，放入队列的末尾；
     * 　　3.如果遇到的数字比队列中最大值小，最小值大，那么将比它小数字不可能成为最大值了，删除较小的数字，放入该数字。
     *　　 4.由于滑动窗口有大小，因此，队列头部的数字如果其下标离滑动窗口末尾的距离大于窗口大小，那么也删除队列头部的数字。
     * 　　注：队列中存放的是下标，以上讲的 队列头部的数字 均指 队列头部的下标所指向的数字。写代码时不要弄混了。
     *
     * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
     *  * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     *
     *    0 1 2  3 4 5 6 7
     *  {[2,3,4],2,6,2,5,1}
     *  --------
     *  2      3
     *  --------
     *  4
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows2(int [] num, int size){
        ArrayList<Integer> max = new ArrayList<Integer>();
        if(num==null || num.length<=0 || size<=0 || size>num.length)
            return max;
        ArrayDeque<Integer> indexDeque = new ArrayDeque<Integer>();
        for(int i=0;i<size-1;i++){
            while(!indexDeque.isEmpty() && num[i]> num[indexDeque.getLast()])
                indexDeque.removeLast();
            indexDeque.addLast(i);
        }
        for(int i=size-1;i<num.length;i++){
            while(!indexDeque.isEmpty() && num[i]> num[indexDeque.getLast()])
                indexDeque.removeLast();
            if(!indexDeque.isEmpty() && (i-indexDeque.getFirst())>=size)
                indexDeque.removeFirst();
            indexDeque.addLast(i);
            max.add(num[indexDeque.getFirst()]);
        }

        return max;
    }
}
