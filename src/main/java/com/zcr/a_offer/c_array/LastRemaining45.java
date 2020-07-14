package com.zcr.a_offer.c_array;

import com.zcr.b_leetcode.ListNode;

import java.util.ArrayList;

/**
 * 45.圆圈中最后剩下的数
 * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
 * HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
 * 然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,
 * 继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
 * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 *
 * 如果没有小朋友，请返回-1
 * 如： 0 1 2 3 4 5 每次删掉第3个人，依次删除2 0 4 1，剩下3
 * 约瑟夫环问题：
 */
public class LastRemaining45 {

    /**
     * 解法一
     * 常规解法，使用链表模拟。
     * <p>
     * 在环形链表中遍历每一个节点，不断转圈，不断让每个节点报数；
     * 当报数达到m互，就删除当前报数的点；
     * 删除节点后，继续连接整个环形链表；
     * 继续报数，继续删除，直到链表节点数为1；
     * <p>
     * 遍历环形链表会是一个无限循环，如果链表中的数据逐渐减少，
     * 不控制终究会一个不剩，这又不满足我们问题的求解，
     * 因此我们需要定义出循环结束的条件，按照约瑟夫环的规则，
     * 只剩下一个的时候就结束，在环形链表结构中，
     * 那就是结点本身的下一个节点就是它自己。这样就可以结束遍历了。
     * 最后打印出剩下的结点，问题解决。
     * <p>
     * 如：0 1 2 3 4   m=3
     * de
     * 依次删除2 0 4 1，剩下3
     * <p>
     * 时间：O(mn)每删除一个数字需要m步，一共有n个数字
     * 空间：O(n)
     *
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0) {
            return -1;
        }
        //构建环形链表
        ListNode head = new ListNode(0);
        ListNode pre = head;
        for (int i = 1; i < n; i++) {
            ListNode cur = new ListNode(i);
            pre.next = cur;
            pre = cur;
        }
        pre.next = head;

        ListNode de = head;
        //然后遍历环形链表，进行删除
        while (de.next != de) {
            for (int i = 1; i < m - 1; i++) {//注意这里实i<m-1，而不是i<=m-1
                de = de.next;
            }
            System.out.println(de.next.value);
            de.next = de.next.next;
            de = de.next;
        }
        return de.value;
    }

    public static void main(String[] args) {
        LastRemaining45 lastRemaining45 = new LastRemaining45();
        int result = lastRemaining45.LastRemaining_Solution(5, 3);
        System.out.println(result);

    }


    /**
     * 解法二
     * 利用取摸。看个例子就懂了。
     * n = 5, m = 4。
     * <p>
     * 使用列表实现：可以每次标记一下然后最后再删除，也可以每次找到就删除
     *
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution2(int n, int m) {
        if (n == 0 || m == 0)
            return -1;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(i);
        int pos = -1;
        while (list.size() > 1) {
            pos = (pos + m) % list.size();
            list.remove(pos);
            pos--;
        }
        return list.get(0);
    }


    /**
     * 其实这道题还可以用递归来解决，递归是思路是每次我们删除了某一个士兵之后，
     * 我们就对这些士兵重新编号，然后我们的难点就是找出删除前和删除后士兵编号的映射关系。
     *
     * 我们定义递归函数 f(n，m) 的返回结果是存活士兵的编号，显然当 n = 1 时，f(n, m) = 1。
     * 假如我们能够找出 f(n，m) 和 f(n-1，m) 之间的关系的话，我们就可以用递归的方式来解决了。
     * 我们假设人员数为 n, 报数到 m 的人就自杀。则刚开始的编号为
     * …
     * 1
     * …
     * m - 2
     * m - 1
     * m
     * m + 1
     * m + 2
     * …
     * n
     * …
     * 进行了一次删除之后，删除了编号为 m 的节点。删除之后，就只剩下 n - 1 个节点了，删除前和删除之后的编号转换关系为：
     * 删除前     ---     删除后
     * …          ---      …
     * m - 2     ---     n - 2
     * m - 1    ---      n - 1
     * m         ----    无(因为编号被删除了)
     * m + 1     ---     1(因为下次就从这里报数了)
     * m + 2     ----     2
     * …         ----         …
     * 新的环中只有 n - 1 个节点。且删除前编号为 m + 1, m + 2, m + 3 的节点成了删除后编号为 1， 2， 3 的节点。
     *
     * 假设 old 为删除之前的节点编号， new 为删除了一个节点之后的编号，则 old 与 new 之间的关系为 old = (new + m - 1) % n + 1。
     *
     *
     *
     * old=m-2  new=n-2
     *    m-2=(n-2+m-1)%n+1
     * 这样，我们就得出 f(n, m) 与 f(n - 1, m)之间的关系了，而 f(1, m) = 1.所以我们可以采用递归的方式来做。代码如下：
     *
     * 注：有些人可能会疑惑为什么不是 old = (new + m ) % n 呢？
     * 主要是因为编号是从 1 开始的，而不是从 0 开始的。
     * 如果 new + m == n的话，会导致最后的计算结果为 old = 0。
     * 所以 old = (new + m - 1) % n + 1.
     *
     * 时间复杂度是 O(n)
     * 空间复杂度是O(1)
     *
     *
     *
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution3(int n, int m) {
        if (n == 1) return n;
        return (LastRemaining_Solution3(n - 1, m) + m - 1) % n + 1;
    }


    /**
     * 函数F(n, m)关于n个数字的序列中最后剩下的数字 与 函数F(n-1, m)关于n-1个数字的序列中最后一个数字存在一定的关系。
     * 要得到n个数字的序列中最后剩下的数字，只需要得到n-1个数字的序列中最后一个数字即可。
     * 先给出递归公式:
     * F(n, m) = [F(n-1, m) + m] % n。
     * 当 n == 1时 F(n, m) = 0。
     *
     * 下面给出一些简单的推理。
     * 定义F(n, m)表示每次在n个数字(0, 1, ... n-1)中，每次删除第m个数字最后剩下的数字。在这个n个数字中，第一个被删除的数字 = (m - 1)%n；(记为k)
     * 看个例子:
     * 那么删除 k 之后剩下的 n-1 个数字为 (0， 1，.…, k-1，k+1,，.…, n-1)，并且下一次删除从k+1开始计数，
     *                      从而形参(k+1, k+2, ... n-1, 0, 1, ... k-1)。
     * 上述序列最后剩下的数字也是关于n和 m的函数，由于这个序列的规律和前面最初的序列不一样，记为F'(n-1, m)；
     * 最初序列最后剩下的数字F(n, m)一定是删除一个数字之后的序列最后剩下的数字，即F(n, m) = F'(n-1, m)；
     *
     *
     * 把上面的序列和新的索引做一个映射如下:
     * 我们把映射定义为 p，则 p(x)=(x-k-1)%n。它表示如果映射前的数字是x，那么映射后的数字是(x-k-1)%n。该映射的着映射是 p-1(x)=(x + k + 1)%n。
     * 由于映射之后的序列和最初的序列具有同样的形式，即都是从 0 开始的连续序列，因此仍然可以用函数F来表示，记为 F(n-1 m)。
     * 根据我们的映射规则, 映射之前的序列中最后剩下的数字F'(n-1, m) = p-1[F(n-1, m)] = [F(n-1, m) + k + 1] % n；
     * 把k = (m-1)%n带入得到F(n, m) = F'(n-1, m) = [F(n-1, m) + m] %n。
     * @param n
     * @param m
     * @return
     */

    //递归
    public int LastRemaining_Solution4(int n, int m) {
        if (m == 0 || n == 0)
            return -1;
        return news(n, m);
    }

    public int news(int n, int m) {
        if (n == 1) // 当链表中只有一个元素的时候就返回编号1
            return 0;
        int upper = news(n - 1, m); // 从 n-1中移除后最后剩下的
        return (upper + m) % n;  // 回到 n 层 对应的编号是啥
    }




    //非递归
    public int LastRemaining_Solution5(int n, int m) {
        if (m == 0 || n == 0)
            return -1;
        int last = 0;
        for(int i = 2; i <= n; i++) last = (last + m) % i;
        return last;
    }
}
