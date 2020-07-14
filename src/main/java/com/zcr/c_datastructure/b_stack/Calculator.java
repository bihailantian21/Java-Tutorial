package com.zcr.c_datastructure.b_stack;

/**
 * @author zcr
 * @date 2019/7/5-21:38
 */
public class Calculator {
    public static void main(String[] args) {
        //直接把中缀表达式进行计算的
        //根据前面的分析思路，完成表达式的一个运算
        String expression = "34+2*60-2";//13  //30+2*6-2却计算不正确！如何处理多位数的问题
        //创建两个栈，数字栈、符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 opeStack = new ArrayStack2(10);
        //定义相关的变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch中
        String keepNum = "";//用于拼接多位数

        //开始用while循环扫描expression
        while (true) {
            //依次得到expression里面的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (opeStack.isOper(ch)) {//如果是运算符
                //判断当前的符号栈是否为空
                if (!opeStack.isEmpty()) {
                    //处理
                    //如果符号栈有操作符，就进行比较
                    //如果当前的操作符的优先级小于或等于栈中的操作符，就需要从数字栈中弹出两个数字
                    //在符号栈中弹出一个符号，进行运算，将得到结果放入到数字栈中。然后将当前的操作符入栈。
                    if (opeStack.priority(ch) <= opeStack.priority(opeStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = opeStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //把运算的结果放到数字栈中
                        numStack.push(res);
                        //把当前的操作符放到符号栈中
                        opeStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        opeStack.push(ch);
                    }
                } else {
                    //符号栈如果为空，直接入符号栈
                    opeStack.push(ch);
                }
            } else {//如果是数字，择直接入数字栈
                //numStack.push(ch-48);//"1+3"   '1'->1
                //你发现是3就入栈，后面还有数呀，这是一个多位数
                //当处理多位数时，不能发现是一个数就立即入栈
                //当处理数字时，需要向expression的表达式的index后面再看一位，如果是数就进行扫描，如果是符号再入栈
                //因此我们需要定义一个字符串变量，用于拼接

                //处理多位数
                keepNum += ch;
                //判断下一个字符是不是数字，如果是数字，则进行继续扫描，拼接

                //如果ch已经是表达式的最后一位了，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (opeStack.isOper(expression.substring(index + 1,index + 2).charAt(0))) {//只是往后面看一位，index本身不要变
                        //如果是运算符，则直接入数字栈
                        numStack.push(Integer.parseInt(keepNum));//"1"->1字符串转为数字，用Integer.parserInt
                        //重要！！！keepNum要清空
                        keepNum = "";

                    }
                }
            }
            //让index+1，并判断是否扫描到expression的最后了
            index++;
            if (index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数字栈和符号栈中pop出响应的数字和符号进行计算
        while (true) {
            //如果符号栈为空，则计算结束，数字栈中只有一个数字了就是结果
            if (opeStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = opeStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);//入栈
        }
        System.out.printf("表达式%s = %d",expression,numStack.pop());


    }
}

//定义栈
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据就放在该数组
    private int top = -1;

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //返回当前栈顶的元素
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈，将栈顶的数据返回
    public int pop() {
        //先判断栈是否为空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈，需要从栈顶开始显示
    public void list() {
        if (isEmpty()) {
            System.out.println("没有数据");
            return;
        }
        for (int i = top;i >= 0;i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员来确定的，优先级使用数字表示
    //数字越大，优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {//int和char都是底层用数字来比较的
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前的表达式只有+ - * 、
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper) {
        int res = 0;//用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//把后弹出来的那个数作为减数。注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
