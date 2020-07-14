package com.zcr.c_datastructure.a_linkedlist;

import java.util.Stack;

/**
 * @author zcr
 * @date 2019/7/5-10:21
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //先创建几个节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨 ");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        //创建单链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        /*singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);*/

        //按照编号的顺序添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.list();

        //修改节点信息
        HeroNode newHeroNode = new HeroNode(2,"小卢","小于");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表：");
        singleLinkedList.list();

        //删除节点
        singleLinkedList.delete(1);
        singleLinkedList.delete(4);
        System.out.println("删除后的链表：");
        singleLinkedList.list();

        //求单链表中有效节点的个数
        System.out.println("有效的节点个数有："+ SingleLinkedList.getLength(singleLinkedList.getHead()));

        //得到倒数第k个元素
        HeroNode res = SingleLinkedList.findLastIndexNode(singleLinkedList.getHead(),2);
        System.out.println("res："+res);

        //单链表的反转
        /*System.out.println("原链表为：");
        singleLinkedList.list();
        System.out.println("反转后的链表为：");
        SingleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.list();*/

        //逆序打印
        System.out.println("将链表逆序打印：");
        SingleLinkedList.reversePrint(singleLinkedList.getHead());
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点
    //构造器
    public HeroNode(int no,String name,String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方便，重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                //", next=" + next +这个nect域不要打印了，否则每次一连串都会打印出来
                '}';
    }
}

//定义SingLinkedList管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //返回头结点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时，找到最后一个节点，把它的next域指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        /*while (temp != null){
            temp = temp.next;
        }
        temp.next = heroNode;*/
        while (true) {
            //找到链表的最后
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        temp.next = heroNode;//第一次用的时候发生了空指针异常，因为我用了空对象去调用方法和属性
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //如果有这个排名，则添加失败，并给出提示
    //就可以在内存中把顺序排好，比数据库中肯定要快
    //因为头结点不能动，因此我们仍然通过一个辅助变量来帮助找到添加位置
    //因此我们找的temp是位于添加位置的前一个节点，否则插入不了
    //说明我们在比较时，是temp.next.no和需要插入的节点的no做比较
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;//标识添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null){//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到，就在temo的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已经存在了
                flag = true;
                break;
            }
            temp =temp.next;//后移，遍历当前的链表
        }
        if (flag) {//不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号%d已经存在，不能加入\n",heroNode.no);
        } else {
            //插入到链表中，temp的后边
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //完成修改节点的信息，根据编号来修改，即编号不能改
    public void update(HeroNode newHeroNode) {
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据编号找
        //先定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//是否找到该节点
        while (true) {
            if (temp == null){
                break;//到链表的最后的下一个节点了，已经遍历结束了
            }
            if (temp.no == newHeroNode.no) {//找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname =newHeroNode.nickname;
        } else {//没有找到
            System.out.printf("没有找到编号为%d的节点，不能修改\n",newHeroNode.no);
        }
    }

    //删除节点
    //因为头结点不能动，因此我们仍然通过一个辅助变量来帮助找到添加位置
    //因此我们找的temp是位于添加位置的前一个节点，否则删除不了
    //说明我们在比较时，是temp.next.no和需要删除的节点的no做比较
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;//是否找到待删除节点的前一个节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no){
                flag = true;//找到了待删除节点的前一个节点
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点%d不存在\n",no);
        }
    }

    //显示链表，通过遍历
    public void list() {
        //判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while(true){
            //判断是否到链表最后
            if (temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    //获取单链表的节点的个数
    // （如果是带头结点的链表，需要补统计头结点）
    /**
     *
     * @param head 链表的头结点
     * @return 返回的是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {//空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量
        HeroNode cur = head.next;//这里没有统计头结点
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表中的倒数第K个节点
    //接收head节点，同时接收index（倒数第index个节点）；
    //把链表从头到尾遍历得到链表的长度；调用getLength()
    //用size-index，从链表的第一个开始遍历(size-index)个就可得
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断链表为空，返回null
        if (head.next == null){
            return null;//没有在找到
        }
        //第一个遍历得到链表的长度
        int size = getLength(head);
        //第二次遍历size-index位置，就是我们倒数第k个节点
        //先做一个index的校验
        if (index <= 0 || index > size){
            return null;
        }
        //定义一个辅助变量
        HeroNode cur = head.next;
        for (int i = 0; i < size-index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //将单链表进行反转
    public static void reverseList(HeroNode head) {
        //如果当前连标王为空或者只有一个节点，无需反转直接返回
        if (head.next == null || head.next.next == null) {
            return ;
        }
        //定义一个辅助变量，作用：帮助我们遍历原来的链表
        HeroNode cur = head.next;
        //指向当前节点的下一个节点
        HeroNode next = null;
        //定义反转链表的头结点
        HeroNode reverseHead = new HeroNode(0,"","");
        //开始遍历原来的链表，并完成反转工作
        //每遍历一个节点，就将其取出，并放在新的链表中
        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面要用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的头部
            reverseHead.next = cur;//新链表的头部
            cur = next;//让cur后移
        }
        head.next = reverseHead.next;
    }

    //逆序打印
    //可以使用栈这个数据结构
    public static void reversePrint(HeroNode head) {
        if(head.next == null){
            return;//空链表不能打印
        }
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while(cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点进行打印，出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

}
