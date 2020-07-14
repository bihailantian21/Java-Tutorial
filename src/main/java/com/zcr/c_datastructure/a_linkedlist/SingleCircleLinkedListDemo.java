package com.zcr.c_datastructure.a_linkedlist;

/**
 * @author zcr
 * @date 2019/7/5-18:25
 */
public class SingleCircleLinkedListDemo {

    public static void main(String[] args) {
        //创建环形链表
        SingleCircleLinkedList circleLinkedList = new SingleCircleLinkedList();
        circleLinkedList.addBoy(125);//加入五个小孩节点
        circleLinkedList.showBoy();

        //测试小孩出圈问题---约瑟夫问题
        circleLinkedList.countBoy(10,20,125);//2 4 1 5 3


    }
}

//创建一个Boy类，表示一个节点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点，初始为空

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

//创建单向环形链表
class SingleCircleLinkedList {
    //创建一个first节点
    private Boy first = null;

    //添加小孩节点，构建环形链表
    public void addBoy(int nums) {
        //nums做校验
        if (nums < 2) {
            System.out.println("nums的值不正确");
            return;
        }
        //辅助变量，帮助构建环形链表
        Boy curBoy = null;
        //使用for循环来创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                 first = boy;
                 first.setNext(first);//构成一个环，只是这个环里只有一个小孩
                 curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历单向循环链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if (curBoy.getNext() == first) {//说明遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo,int countNum,int nums) {
        //先对数据做校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建辅助变量，帮助完成小孩出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {//说明helper指向最后的小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时的移动m-1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true) {
            if (helper == first) {//说明圈中只有一个节点了
                break;
            }
            //让first和helper指针同时的移动countNum - 1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要除权的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号为%d\n",first.getNo());

    }
}