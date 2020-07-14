package com.zcr.b_leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 56. Merge Intervals
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * NOTE: input types have been changed on April 15, 2019.
 * Please reset to default code definition to get new method signature.
 */

/**
 * 56、合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class MergeIntervals56 {

    /**
     * 将有重叠的部分进行合并操作
     *
     *
     * 对于有起点和终点的区间的题：
     * 1、将所有的区间的起点和终点进行排序、重新构建
     * 把所有的起点生成一个数组、所有的终点生成一个数组。
     * 做排序。
     * 将第一个起点和第一个终点组成新的区间1…
     *
     * 2、生成一组从小到大排序的start、一组从小到大排序的end
     * start end
     * (1,2)
     * (4,7)
     * (6,9)
     * (8,10)
     * 然后生成几组数据。
     * 一组一组的进行遍历：
     * 起点：当前Interval的起点
     * 终点：当前Interval的终点和下一个Interval的起点相比较
     * 如果当前终点>下一个的起点： 有重叠i++
     * 就这样一直找下去，直到结束后，得到终点，于是得到一个区间，加到结果种。然后i向后移动。
     * 如果当前终点<=下一个的起点：
     *
     *
     * @param intervals
     * @return
     */
    public List<Interval1> mergeIntervals(List<Interval1> intervals) {
        List<Interval1> result = new LinkedList<>();
        if (intervals == null) {
            return  result;
        }
        int len = intervals.size();
        int[] start = new int[len];
        int[] end = new int[len];
        for (int i = 0; i < len; i++) {
            start[i] = intervals.get(i).getStart();
            end[i] = intervals.get(i).getEnd();
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int i = 0;
        while (i < len) {
            int st = start[i];
            while (i < len - 1 && end[i] >= start[i + 1]) {
                i++;
            }
            int en = end[i];
            Interval1 in = new Interval1(st,en);
            result.add(in);
            i++;
        }
        return result;
    }

    /**
     * 数组int[]与ArrayList的转换
     * int[][] ArrayList<int[]>
     *
     * list和array的转换：
     * return res.toArray(new int[0][])
     * //为什么放0，0长度？可以看下源码就知道l
     * return res.toArray(new int[0][0]);
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int len = intervals.length;
        List<int[]> result = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            return  result.toArray(new int[0][]);
        }
        int[] start = new int[len];
        int[] end = new int[len];
        for (int i = 0; i < len; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int i = 0;
        while (i < len) {
            int st = start[i];
            while (i < len - 1 && end[i] >= start[i + 1]) {
                i++;
            }
            int en = end[i];
            int[] in = {st,en};
            result.add(in);
            i++;
        }
        return result.toArray(new int[0][]);
    }
}

class Interval1{
    int start;
    int end;

    public Interval1() {
    }

    public Interval1(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

}
