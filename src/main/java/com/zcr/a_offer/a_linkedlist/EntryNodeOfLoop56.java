package com.zcr.a_offer.a_linkedlist;

import com.zcr.b_leetcode.ListNode;

/**
 * 56.链表中环的入口节点
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 *
 *
 * 判断一个链表是否有环
 * private static Boolean isLoop(Node head) {//判断一个链表是否有环
 *         if (head.next == null || head.next.next == null) {
 *             return null;
 *         }
 *         Node slow = head.next;
 *         Node fast = head.next.next;
 *         while (slow != fast) {
 *             if (fast.next == null || fast.next.next == null) {
 *                 return false;
 *             }
 *             slow = slow.next;
 *             fast = fast.next.next;
 *         }
 *         return true;
 *     }
 *
 *
 *   判断一个链表是否有环，并打印出入口节点
 * private static Node getLoopNode(Node head) {//判断一个链表是否有环，并打印出入口节点
 *         if (head.next == null || head.next.next == null) {
 *             return null;
 *         }
 *         Node slow = head.next;
 *         Node fast = head.next.next;
 *         while (slow != fast) {
 *             if (fast.next == null || fast.next.next == null) {
 *                 return null;
 *             }
 *             slow = slow.next;
 *             fast = fast.next.next;
 *         }
 *
 *         fast = head;
 *         while (fast != slow) {
 *             fast = fast.next;
 *             slow = slow.next;
 *         }
 *         return fast;
 *     }
 */
public class EntryNodeOfLoop56 {

    /**
     * 判断链表是否有环
     *
     * 1->2->3->4->5->6->7->8
     *    s  f
     *       s     f
     *          s        f
     *
     *
     *
     * 1->2->3->4->5->1->2
     *    s  f
     *       s     f
     *    f     s
     *
     *
     *
     *
     * @param head
     * @return
     */
    public boolean isLoop(ListNode head) {
        if (head.next == null || head.next.next == null) {//链表中只有一个节点、或者只有两个节点
            return false;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {//能走到头，说明没有环
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 这题可以用HashSet来做，但是那样空间复杂度为O(N)，比较好的经典的算法是双指针(数学)方法。
     *
     * （1）两个指针fast、slow，fast一次走两步，slow一次走一步；
     * （2）如果有环，他们一定会在环内相遇；
     * （3）当他们相遇之后，让fast回到起点，slow不动；
     * （4）然后两个指针一起走，都是走一步，当他们走到一起的时候，他们的交点就是入环点；
     *
     * 为什么当他们相遇之后，让fast回到起点，slow不动，然后两个指针一起走，都是都一步，当他们走到一起的时候，他们的节点就是入环节点。
     * 证明：     边c
     *          9<-8<-7-相交
     * 1->2->3->4->5->6
     *   边a  环l  边b
     *
     * 相遇时：slow走的路程：a+n*l+b
     *        fast走的路程：a+m*l+b
     * 又因为fast速度是slow的两倍，2*slow=fast
     *         2(a+n*l+b)=a+m*l+b
     *         a+b=(m-2*n)*l
     *         a=(m-2*n)*l-b
     *          =(m-2*n-1)*l+(l-b)
     *          =x*l+c
     *    可以说a=c，这样flow移到开头节点、slow在相交节点。一起走。flow走了a，slow走了c。相交点就是入环点。
     *
     *
     * @param head
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode head) {
        if (head.next == null || head.next.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
