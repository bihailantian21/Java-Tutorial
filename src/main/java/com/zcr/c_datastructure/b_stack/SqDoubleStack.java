package com.zcr.c_datastructure.b_stack;

/**
 * @author zcr
 * @date 2019/7/6-17:54
 * 栈的顺序储存结构（两栈共享空间）
 *
 * 注意点：栈满条件为top1+1==top2
 */

public class SqDoubleStack<E> {
    private E[] data;
    private int top1;  //栈1栈顶指针，top=-1时为空栈
    private int top2;  //栈2栈顶指针，top=maxSize-1时为空栈
    private int maxSize;
    private static final int DEFAULT_SIZE= 10;

    public SqDoubleStack() {
        this(DEFAULT_SIZE);
    }
    public SqDoubleStack(int maxSize) {
        //无法创建泛型数组 data=new E[maxSize];
        data=(E[]) new Object[maxSize];
        top1=-1;
        top2=maxSize-1;
        this.maxSize=maxSize;
    }

    /*
     * 进栈操作，stackNumber代表要进的栈号
     */
    public void push(int stackNumber,E e) {
        if(top1+1==top2)
            throw new RuntimeException("栈已满，无法进栈！");
        if(stackNumber==1) {
            data[++top1]=e;
        }else if(stackNumber==2) {
            data[--top2]=e;
        }else {
            throw new RuntimeException("栈号错误！");
        }

    }

    /*
     * 出栈操作
     */
    public E pop(int stackNumber) {
        E e;
        if(stackNumber==1){
            if(top1==-1)
                throw new RuntimeException("空栈1，无法出栈！");
            e=data[top1--];
        }else if(stackNumber==2) {
            if(top2==maxSize-1)
                throw new RuntimeException("空栈2，无法出栈！");
            e=data[top2++];
        }else {
            throw new RuntimeException("栈号错误！");
        }
        return e;
    }
}
