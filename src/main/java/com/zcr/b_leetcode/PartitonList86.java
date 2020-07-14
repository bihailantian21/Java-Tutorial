package com.zcr.b_leetcode;

/**
 * 86. Partition List
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */

/**
 * 86、分隔链表
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 */
public class PartitonList86 {

    /**
     * 把list中小于指定数的放在一遍，大于等于的放在另一边
     * 要保持原来元素的相对顺序
     * 1-3-4-2
     * 1-|3-4-2 T=3
     * 1-2-|3-4
     *
     * 1-2-4-3-2-9   T=3
     * 1、Dummy、left：左部分和右部分的分界线、pre:当前数的前一个元素
     * Cur:当前元素
     * 如果现在：
     *    left
     *     |
     * 0-1-2-|4-3-2-9
     *          | |
     *        pre cur
     * 2、把2移到前面：
     * 首先,pre.next=cur.next ，跳过当前节点
     * 然后cur.next=left.next  left.next=cur
     *    left
     *     |
     * 0-1-2-|2-4-3-9
     *        |   |
     *       cur  pre
     * left=left.next、cur=pre.next、pre不需要动
     *     left
     *       |
     * 0-1-2-2-|4-3-9
     *            | |
     *          precur
     * 3、left
     *     |
     * 0-1-2-|4-3-2
     *        | |
     *       precur
     * 3不需要移动，那么就是把pre和cur向前移动一位，继续处理下一位
     * 4、当前值大于边界值的时候：
     *   left
     *     |
     * 0-1-2-|4-3-2   T=3
     *     |  |
     *    precur
     * 只需要移动pre、cur
     *    left
     *     |
     * 0-1-2-|4-3-2   T=3
     *        | |
     *       precur
     *
     * 5、需要特殊处理的情况
     * left
     * |
     * 0-|1-1  T=2
     * |  |
     * pre cur
     * 1、pre.next =cur.next
     * 2、cur.next=left.next
     * 3、left.next=cur
     * 这样不对，因为cur又指向了自己，应该做特殊处理
     * Pre=cur:
     * 检查cur
     * Cur<T:移动pre、cur、left。边界也需要移动
     * left
     * |
     * 0-1-|1    T=2
     * | |
     * pre cur
     *
     *  left
     *   |
     * 0-1-|1    T=2
     *    | |
     *    pre cur
     *
     * Cur>T：简单的只是移动pre、cur。Left不动，边界不需要移动
     * left
     * |
     * 0-1-|4
     *   |  |
     *  pre cur
     * 简单的只是移动pre、cur。Left不动，边界不需要移动
     * left
     * |
     * 0-1-|4
     * |  |
     * pre cur
     *
     * 类似于荷兰国旗问题，可以使用双指针的形式
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode left = dummy;
        ListNode pre = dummy;
        dummy.next = head;
        ListNode cur = head;
        while (cur != null) {
            if (cur == left) {
                if (cur.value > x) {
                    left = left.next;//移动边界值
                }
                pre = cur;
                cur = cur.next;
            } else {
                if (cur.value >= x) {
                    pre = cur;
                    cur = cur.next;
                } else {
                    pre.next = cur.next;
                    cur.next = left.next;
                    left.next = cur;
                    left = cur;//移动边界值
                    cur = pre.next;
                }
            }
        }
        return dummy.next;
    }


        public ListNode partition2(ListNode head, int x) {
            ListNode smallerHead = null;  // 存放比x小的节点组成的链表的头部
            ListNode smallerTail = null;  // 存放<x的节点组成的链表的尾部
            ListNode biggerHead = null;   // 存放>=x的节点组成的链表的头部
            ListNode biggerTail = null;   // 存放>=x的节点组成的链表的尾部

            ListNode ptr = head;
            while (ptr != null) {
                int tmp = ptr.value;
                if (tmp < x) {
                    if (smallerHead == null) {
                        smallerHead = ptr;
                    }
                    if (smallerTail == null) {
                        smallerTail = ptr;
                    } else {
                        smallerTail.next = ptr;
                        smallerTail = ptr;
                    }
                } else {
                    if (biggerHead == null) {
                        biggerHead = ptr;
                    }
                    if (biggerTail == null) {
                        biggerTail = ptr;
                    } else {
                        biggerTail.next = ptr;
                        biggerTail = ptr;
                    }
                }

                ptr = ptr.next;
            }

            if (biggerTail != null) {
                biggerTail.next = null;
            }

            if (smallerTail != null) {
                smallerTail.next = biggerHead;
            }

            return smallerHead != null ? smallerHead : biggerHead;
        }

}
