package com.zcr.c_datastructure.g_search;

import java.util.Scanner;

/**
 * @author zcr
 * @date 2019/7/8-9:06
 */
public class HashTableDemo {

    public static void main(String[] args) {

        //创建哈希表
        HashTable hashTable =new HashTable(7);

        //通过简单菜单测试
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add：添加雇员");
            System.out.println("list：显示雇员");
            System.out.println("exit：退出系统");
            System.out.println("find：查找雇员");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id,name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;//默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建一个EmpLinkedList，表示链表
class EmpLikedList {
    //头指针，指向第一个Emp，因此我们这个链表的head是直接指向第一个Emp的（没有头结点）
    private Emp head;//默认为空

    //添加雇员到链表
    //1.假定添加雇员的时候就是加在链表的最后
    //2.即id是自增长，id的分配总是从小到大
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是添加第一个雇员，则使用一个辅助指针，帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {//说明到链表最后
                break;
            }
            curEmp = curEmp.next;
        }
        //退出时，直接将emp加到最后
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {//说明链表为空
            System.out.println("第"+(no+1)+"条链表为空");
            return;
        }
        System.out.print("第"+(no+1)+"条链表的信息为：");
        Emp curEmp = head;//辅助指针
        while (true) {
            System.out.printf("=> id = %d name = %s \t",curEmp.id,curEmp.name);
            if (curEmp.next == null) {//说明到链表最后
                break;
            }
            curEmp = curEmp.next;//后移，遍历
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到，就返回Emp，如果没有找到，就返回null
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//找到了
                break;//这时curEmp就指向要查找的雇员
            }
            //退出
            if (curEmp.next == null) {//说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;//后移
        }
        return curEmp;
    }


}

//创建哈希表，用来管理多条链表
class HashTable {
    private EmpLikedList[] empLikedListArray;
    private int size;//表示共有多少条链表

    //构造器
    public HashTable(int size) {
        //初始化哈希表
        this.size = size;
        empLikedListArray = new EmpLikedList[size];
        //留一个坑！！
        //这时不要忘了分别初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLikedListArray[i] = new EmpLikedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        //将雇员加入到对应的链表中
        empLikedListArray[empLinkedListNO].add(emp);
    }

    //遍历所有的链表，遍历哈希表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLikedListArray[i].list(i);
        }
    }

    //根据输入的id查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找

        int empLinkedListNO = hashFun(id);
        Emp emp = empLikedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null){
            System.out.printf("在第%d条链表中找到该雇员id=%d\n",empLinkedListNO+1,id);
        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    //编写散列函数，使用一个简单取模法
    public int hashFun(int id) {
        return id % size;
    }
}
