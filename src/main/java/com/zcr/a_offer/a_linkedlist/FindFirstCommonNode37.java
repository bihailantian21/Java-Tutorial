package com.zcr.a_offer.a_linkedlist;

import com.zcr.b_leetcode.ListNode;

import java.util.HashSet;

/**
 * 37.两个链表的第一个公共节点
 * 输入两个链表，找出它们的第一个公共结点。
 */
public class FindFirstCommonNode37 {

    /**
     * 直观解法：在第一个链表上顺序遍历每个节点，每遍历到一个节点的时候，在第二个链表上顺序遍历每个节点。
     * 如果在第二个链表上有一个节点和第一个链表上的节点一样，说明两个链表在z这个节点上重合，就找到了t他们的公共节点。
     * 时间：O(mn)
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode p2 = pHead2;;
        while (pHead1 != null) {
            pHead2 = p2;//这里尤其注意，内层循环中对于for循环每次都是从1~n，但是对于while循环我们需要自己重置一下
            while (pHead2 != null) {
                if (pHead1 == pHead2) {
                    return pHead1;
                } else {
                    pHead2 = pHead2.next;
                }
            }
            pHead1 = pHead1.next;
        }
        return null;
    }

    /**
     * 最简单的想法，使用一个Set存储pHead1中的所有节点，然后遍历pHead2每一个节点，
     * 查看set中是否存在该节点即可。
     *
     * 时间复杂度为O(N)。
     * 空间复杂度O(N)
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode3(ListNode pHead1, ListNode pHead2) {
        HashSet<ListNode> set = new HashSet<>();
        while (pHead1 != null) {
            set.add(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            if (set.contains(pHead2)) {
                return pHead2;
            }
            pHead2 = pHead2.next;
        }
        return null;
    }

    /**
     * 两个有序链表的第一个公共节点
     * 时间：O(n)
     * @param head1
     * @param head2
     * @return
     */
    public ListNode FindFirstCommonNode2(ListNode head1, ListNode head2) {
        while (head1 != null && head2 != null) {
            if (head1.value< head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                return head1;
            }
        }
        return null;
    }


    /**
     * O(N)时间，O(1)空间优化解法。
     *
     * 有公共节点的链表的特点：从第一个公共节点之后，节点都是重合的，不可能出现分叉了。
     * 像是一个Y型。
     *
     * 那么说明从两个链表尾部开始比较，若相同同时再往前，直到不相同就找到了第一个相同的公共节点。
     * 可以使用栈：先把两个链表分别放到两个栈中，然后从栈中弹出的就是从尾部开始得了。
     *
     * 思路如下:
     * 先求出两个链表的长度n1、n2；
     * 记长度较长的链表为p1，较短的为p2；
     * 先让p1走abs(n1 - n2)步；
     * 然后让p1、p2一起走，当p1 == p2的时候，就是第一个公共节点；
     *
     * 时间复杂度为O(N)。
     * 空间复杂度O(1)
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode4(ListNode pHead1, ListNode pHead2) {
        int len1 = 0;
        int len2 = 0;
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        //统计两个链表的长度
        while (p1 != null) {
            len1++;
            p1 = p1.next;
        }
        while (p2 != null) {
            len2++;
            p2 = p2.next;
        }
        int lendif = 0;
        p1 = pHead1;
        p2 = pHead2;
        //先让长度比较长的那个链表走
        if (len1 > len2) {
            lendif = len1 - len2;
            for (int i = 0; i < lendif; i++) {
                p1 = p1.next;
            }
        } else {
            lendif = len2 - len1;
            for (int i = 0; i < lendif; i++) {
                p2 = p2.next;
            }
        }
        //然后两个一起走，然后相同的时候就找大了相同节点
        while (p1 != null && p2 != null && p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        ListNode firstCommon = p1;
        return firstCommon;
    }



}
