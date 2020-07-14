package com.zcr.c_datastructure.b_stack;

/**
 * @author zcr
 * @date 2019/7/5-21:23
 */
public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack<Student> linkStack=new LinkedStack<Student>();
        Student[] students= {new Student("小A",11),new Student("小B",12),new Student("小C",13),
                new Student("小D",14),new Student("小E",151)};
        for(int i=0;i<5;i++) {
            linkStack.push(students[i]);
        }
        linkStack.printStack();
        System.out.println("----");
        for(int i=0;i<5;i++) {
            System.out.println(linkStack.pop());
        }
        linkStack.printStack();
    }


}

//定义栈中的每一个节点
class StackNode<E>{
    E data;
    StackNode<E> next;
    public StackNode(E data, StackNode<E> next) {
        this.data=data;
        this.next=next;
    }
}

class LinkedStack<E> {
    private StackNode<E> top;
    private int count;

    public LinkedStack() {
        top = new StackNode<E>(null, null);
        count = 0;
    }

    public void push(E e) {
        StackNode<E> node = new StackNode<E>(e, top);
        node.next = top;//把当前的栈顶元素赋值给新结点的直接后继
        top = node;//将新的节点node赋值给栈顶指针
        count++;
    }

    public E pop() {
        if (count == 0)
            throw new RuntimeException("空栈，无法出栈！");
        StackNode<E> node;
        node = top;
        top = top.next;
        count--;
        E e = node.data;
        node = null;
        return e;
    }

    public void printStack() {
        if (count == 0) {
            System.out.println("空栈");
        } else {
            StackNode<E> node = top;
            for (int i = 0; i < count; i++) {
                System.out.println(node.data);
                node = node.next;
            }
        }
    }
}

class Student{
    public Student(String name, int age) {
        this.name=name;
        this.age=age;
    }
    String name;
    int age;
    public String toString() {
        return name;
    }
}