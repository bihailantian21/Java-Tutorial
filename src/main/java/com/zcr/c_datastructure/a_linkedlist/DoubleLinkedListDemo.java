package com.zcr.c_datastructure.a_linkedlist;

/**
 * @author zcr
 * @date 2019/7/5-17:37
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表测试:");
        //先创建几个节点
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨 ");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        /*doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();*/

        //按照编号添加
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.list();

        //修改
        HeroNode2 hero5 = new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(hero5);
        System.out.println("修改后的链表：");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.delete(3);
        System.out.println("删除后的链表：");
        doubleLinkedList.list();





    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点，初始值为null
    public HeroNode2 pre;//指向前一个节点，初始值为null
    //构造器
    public HeroNode2(int no,String name,String nickname) {
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

//创建一个双向列表的类
class DoubleLinkedList {
    //初始化
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头结点
    public HeroNode2 getHead() {
        return head;
    }


    //遍历双向链表
    //显示链表，通过遍历
    public void list() {
        //判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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

    //添加（默认添加到双向链表的最后面）
    //思路，当不考虑编号顺序时，找到最后一个节点，把它的next域指向新的节点
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode2 temp = head;
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
        heroNode.pre = temp;//形成一个双向链表
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //如果有这个排名，则添加失败，并给出提示
    //就可以在内存中把顺序排好，比数据库中肯定要快
    //因为头结点不能动，因此我们仍然通过一个辅助变量来帮助找到添加位置
    //因此我们找的temp是位于添加位置的前一个节点，否则插入不了
    //说明我们在比较时，是temp.next.no和需要插入的节点的no做比较
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
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
            heroNode.pre = temp;
            heroNode.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = heroNode;
            }
            temp.next = heroNode;

        }
    }

    //完成修改节点的信息，根据编号来修改，即编号不能改
    public void update(HeroNode2 newHeroNode) {
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据编号找
        //先定义一个辅助变量
        HeroNode2 temp = head.next;
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

    //从双向链表中删除一个节点
    //不需要找要删除的节点的前一个节点，可以直接找到要删除的这个节点，找到了后自我删除即可
    public void delete(int no) {
        //判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
        }
        //辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;//是否找到待删除节点的前一个节点
        while (true) {
            if (temp == null) {//已经到了链表的最后一个节点
                break;
            }
            if (temp.no == no){
                flag = true;//找到了待删除节点
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //这里我们的代码有问题，以为如果要删除的节点是最后一个节点时
            //如果是最后一个节点，就不需要执行下面这句话，否则会出现空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的节点%d不存在\n",no);
        }
    }
}
