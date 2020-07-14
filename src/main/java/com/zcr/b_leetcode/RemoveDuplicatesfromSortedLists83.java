package com.zcr.b_leetcode;

/**
 * 83. Remove Duplicates from Sorted List
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 * Input: 1->1->2
 * Output: 1->2
 *
 * Example 2:
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */

/**
 * 83、删除有序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 *
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class RemoveDuplicatesfromSortedLists83 {

    /**
     * 遍历一遍，只需要比较当前和下一个是否相同
     * 不相同：向后移动指针
     * 相同：把当前指针指向，next->next
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode i = head;
        while (i != null && i.next != null) { //注意这里一定要写i!=null，否则会报空指针异常
            if (i.value == i.next.value) {
                i.next = i.next.next;
            } else {
                i = i.next;
            }
        }
        return head;
    }
}
