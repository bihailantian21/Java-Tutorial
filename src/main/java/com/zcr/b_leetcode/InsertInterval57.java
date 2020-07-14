package com.zcr.b_leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. Insert Interval
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition
 * to get new method signature.
 */

/**
 * 57、插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 示例 1:
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 *
 * 示例 2:
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class InsertInterval57 {

    /**
     * 给定几个不重叠的线段，将给定的一个线段插入
     * 循环遍历所有的区间：两个循环：
     * 1、最前面有很多不相关的，我们直接加到结果集合中即可。
     * 2、首先找到第一个需要merge的线段：
     * （[给定线段的start>第一个需要merge的线段的end]直到给定线段的start<=第一个需要merge的线段的end），
     * 则merge后新生成的线段的start为min(给定线段的start，第一个需要merge的线段的start)。
     * 3、然后找到最后一个需要merge的线段end：
     * （[给定线段的end>=最后一个需要merge的线段的start]直到给定线段的end<最后一个需要merge的线段的start，
     * 所以我们要找第一个start）给定线段的end，则这条线段不需要merge，是它之前的线段是需要merge的最后一个线段），
     * （一段一段不停的延伸end，直到直到最后满足的值才停止更新）则merge后新生成的线段的end为max
     * (给定线段的end，最后一个需要merge的线段的end)。
     * 4、最后还有很多不相关的，我们直接加到结果集合中即可。
     * 例1：
     * |---|  |-----| |----| |----|
     *     e        e s      s
     *           |------|
     *           S      e
     * |---|  |------------| |----|
     * 例2：
     * 特殊位置：也是合适的
     * |---|  |-----|   |----| |----|
     *        s     e   s    e
     *               |-|
     *               S e
     * |---|  |-----||-||----| |----|
     * 例2：
     *  |---|  |-----| |----| |----|
     *         e        e s      s
     *             |-------------------|
     *             S                   e
     *  |---|  |-----------------------|
     * 例3：
     * |---|  |-----|   |----| |----|
     *       s   e
     *   |-|
     *   s e
     * |-| |---|  |-----|   |----| |----|
     * 特殊处理一下：当最后一个需要merge的终点是第一个的时候，说明直接将新插入的节点直接加到最前面就行。
     * 例4：
     * |---|  |-----|   |----| |----|
     *                            s   e
     *                                 |-|
     *                                 s e
     * |---|  |-----|   |----| |----||-|
     * 特殊处理一下：当找第一个需要merge的起点的时候就已经找到了最后，说明新插入的区间直接加到后面就行。
     *
     *
     * @param interval1s
     * @param newInterval
     * @return
     */
    public List<Interval1> insertInterval(List<Interval1> interval1s,Interval1 newInterval) {
        List<Interval1> result = new ArrayList<>();
        int len = interval1s.size();
        int newStart = newInterval.getStart();
        int newEnd = newInterval.getEnd();
        int  i = 0;
        while (i < len && newStart > interval1s.get(i).end) {
            result.add(interval1s.get(i));
            i++;
        }
        if (i == len) {
            result.add(newInterval);
            return result;
        }
        newStart = Math.min(newStart,interval1s.get(i).getStart());
        while (i < len && newEnd >= interval1s.get(i).start) {
            newEnd = Math.max(newEnd,interval1s.get(i).getEnd());
            i++;
        }
        result.add(new Interval1(newStart,newEnd));
        while (i < len) {
            result.add(interval1s.get(i));
        }
        return result;
    }

    public int[][] insert(int[][] intervals,int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int len = intervals.length;
        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        int  i = 0;
        while (i < len && newStart > intervals[i][1]) {
            result.add(intervals[i]);
            i++;
        }
        if (i == len) {
            result.add(newInterval);
            return result.toArray(new int[0][]);
        }
        newStart = Math.min(newStart,intervals[i][0]);
        while (i < len && newEnd >= intervals[i][0]) {
            newEnd = Math.max(newEnd,intervals[i][1]);
            i++;
        }
        int[] in = {newStart,newEnd};
        result.add(in);
        while (i < len) {
            result.add(intervals[i]);
        }
        return result.toArray(new int[0][]);
    }
}
