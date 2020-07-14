package com.zcr.c_datastructure.b_stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zcr
 * @date 2019/7/5-22:36
 */
public class PolandNotation {
    public static void main(String[] args) {
        /*//先定义一个逆波兰表达式
        //说明为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "30 4 + 5 * 6 -";
        //思路
        //1.先将"3 4 + 5 * 6 -"放到ArrayList中
        //2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);

        int res = calculate(rpnList);
        System.out.println("计算结果是="+res);*/



        //完成将一个中缀表达式转成后缀表达式的功能
        //说明：先将中缀表达式转换成对应的List
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List"+infixExpressionList);//[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        //将得到的中缀表达式列表转成后缀表达式对应的额List
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List"+parseSuffixExpressionList);//[1, 2, 3, +, 4, *, +, 5, -]



    }

    //将一个逆波兰表达式，依次将数字和运算符放到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    /**
     * 对list的遍历
     */
    public static int calculate(List<String> ls) {
        //创建一个栈
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String item : ls) {
            //这里使用正则表达式来取出数字
            if (item.matches("\\d+")) {//匹配的是多位数
                //直接入栈
                stack.push(item);
            } else {//如果是符号
                //从栈中弹出两个数，运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(res + "");
            }
        }
        //最后留在stack中的数据就是运算结果了
        return Integer.parseInt(stack.pop());
    }

    //将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0;//这是一个指针，用于遍历中缀表达式字符串
        String str;//对多位数进行拼接
        char c;//每遍历一个字符，就放到x
        do {
            //如果c是一个非数字，就需要加入到ls中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(""+c);
                i++;//i需要后移
            } else {//如果是一个数，要考虑多位数
                str = "";//先将str置为空值   '0'[48]->'9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }

    //中缀表达式列表转换成后缀表达式列表
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        //存储中间结果的栈，这个栈在整个转换过程中没有pop的操作，而且后面还需要逆序输出，所以使用ArrayList结构实现
        List<String> s2 = new ArrayList<String>();

        //遍历ls
        for (String item : ls) {
            //如果是一个数，就入栈s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push("(");
            } else if (item.equals(")")) {
                //如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将(弹出，消除这一对小括号
            } else {
                //当item是一个运算符且优先级小于等于s1栈顶的运算符的优先级
                //将s1栈顶的运算符弹出并压入到s2，再次转到前面与s1中的新栈顶运算符比较
                //问题：我们缺少一个比较优先级高级的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈中
                //若item是一个运算符且优先级大于s1栈顶的运算符的优先级
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出加入到s2中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//因为是存放到List中的，因此按顺序输出就是对应的后缀表达式对应的list

    }


}

//比较运算符比较级高低，返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
