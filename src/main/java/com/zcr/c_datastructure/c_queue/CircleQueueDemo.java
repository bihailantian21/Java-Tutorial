package com.zcr.c_datastructure.c_queue;

import java.util.Scanner;

/**
 * @author zcr
 * @date 2019/7/4-22:28
 */
public class CircleQueueDemo {
    public static void main(String[] args) {
        //创建一个环形队列
        CircleQueue circleQueue = new CircleQueue(4);//有一个空的，所以这里有效空间为3
        char key = ' ';//接口用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列中取出数据");
            System.out.println("h(head)：查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    circleQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    circleQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = circleQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = circleQueue.headQueue();
                        System.out.printf("队列头是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//使用数组模拟队列--编写一个ArrayQueue类
class CircleQueue{
    private int maxsize;//数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//存放数组

    //创建队列的构造器
    public CircleQueue(int maxsize) {
        this.maxsize = maxsize;
        arr = new int[maxsize];
        front = 0;//指向队列的第一个元素
        rear = 0;//指向队列的最后一个的后一个位置。因为希望空出一个位置
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxsize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[rear] = n;//以为rear指的就是最后一个元素的后一个位置
        rear = (rear + 1) % maxsize;//让rear后移
    }

    //获取队列的数据，数据出队列
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            //System.out.println("队列空，不能");
            //通过抛出异常来处理
            throw new RuntimeException("队列空，不能取数据");
        }
        int value = arr[front]; //本身指向队列的第一个元素，所以直接返回后，再移动
        front = (front + 1) % maxsize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        //从front开始遍历，遍历多少个元素
        //
        for (int i = front; i < front + size(); i++) {//因为加上大小的话会超过！
            System.out.printf("arr[%d]=%d\n",i % maxsize,arr[i % maxsize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear + maxsize - front) % maxsize;
    }

    //显示队列的头数据是多少，注意不是取出数据
    public int headQueue() {
        if (isEmpty()){
            //System.out.println("队列空，没有数据");
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front];
    }

}
