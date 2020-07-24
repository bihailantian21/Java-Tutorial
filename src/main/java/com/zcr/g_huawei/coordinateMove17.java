package com.zcr.g_huawei;

import java.util.Scanner;

/**
 * 坐标移动
 * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
 *
 * 输入：
 *
 * 合法坐标为A(或者D或者W或者S) + 数字（两位以内）
 *
 * 坐标之间以;分隔。
 *
 * 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
 *
 * 下面是一个简单的例子 如：
 *
 * A10;S20;W10;D30;X;A1A;B10A11;;A10;
 *
 * 处理过程：
 *
 * 起点（0,0）
 *
 * +   A10   =  （-10,0）
 *
 * +   S20   =  (-10,-20)
 *
 * +   W10  =  (-10,-10)
 *
 * +   D30  =  (20,-10)
 *
 * +   x    =  无效
 *
 * +   A1A   =  无效
 *
 * +   B10A11   =  无效
 *
 * +  一个空 不影响
 *
 * +   A10  =  (10,-10)
 *
 * 结果 （10， -10）
 *
 * 注意请处理多组输入输出
 *
 * 输入描述:
 * 一行字符串
 *
 * 输出描述:
 * 最终坐标，以,分隔
 *
 * 示例1
 * 输入
 * 复制
 * A10;S20;W10;D30;X;A1A;B10A11;;A10;
 * 输出
 * 复制
 * 10,-10
 *
 *
 *
 */
public class coordinateMove17 {

    /**
     * 我靠我忘了写switch case的break导致代码不通过
     *
     * switch case 语句语法格式如下：
     *
     * switch(expression){
     *     case value :
     *        //语句
     *        break; //可选
     *     case value :
     *        //语句
     *        break; //可选
     *     //你可以有任意数量的case语句
     *     default : //可选
     *        //语句
     * }
     *
     * }
     * switch case 语句有如下规则：
     *
     * switch 语句中的变量类型可以是： byte、short、int 或者 char。从 Java SE 7 开始，switch 支持字符串 String 类型了，同时 case 标签必须为字符串常量或字面量。
     *
     * switch 语句可以拥有多个 case 语句。每个 case 后面跟一个要比较的值和冒号。
     *
     * case 语句中的值的数据类型必须与变量的数据类型相同，而且只能是常量或者字面常量。
     *
     * 当变量的值与 case 语句的值相等时，那么 case 语句之后的语句开始执行，直到 break 语句出现才会跳出 switch 语句。
     *
     * 当遇到 break 语句时，switch 语句终止。程序跳转到 switch 语句后面的语句执行。case 语句不必须要包含 break 语句。如果没有 break 语句出现，程序会继续执行下一条 case 语句，直到出现 break 语句。
     *
     * switch 语句可以包含一个 default 分支，该分支一般是 switch 语句的最后一个分支（可以在任何位置，但建议在最后一个）。default 在没有 case 语句的值和变量值相等的时候执行。default 分支不需要 break 语句。
     *
     * switch case 执行时，一定会先进行匹配，匹配成功返回当前 case 的值，再根据是否有 break，判断是否继续输出，或是跳出判断。
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] coords = str.split(";");
            int result1 = 0;
            int result2 = 0;
            for (String coord : coords) {
                int len = coord.length();
                if (len > 3 || len < 2) {
                    continue;
                }
                char c = coord.charAt(0);
                char number1 = coord.charAt(1);
                int number = Integer.parseInt(String.valueOf(number1));
                if ('0' > number1 || number1 > '9') {
                    continue;
                }
                if (len > 2) {
                    char number2 = coord.charAt(2);
                    if ('0' > number2 || number2 > '9') {
                        continue;
                    }
                    number = Integer.parseInt(number1 + String.valueOf(number2));
                }
                switch(c){
                    case 'A':
                        result1 -= number;
                        break;
                    case 'D':
                        result1 += number;
                        break;
                    case 'W':
                        result2 += number;
                        break;
                    case 'S':
                        result2 -= number;
                        break;
                    default:
                        continue;
                }
            }
            System.out.println(result1 + "," + result2);
        }
    }

    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] coords = str.split(";");
            int result1 = 0;
            int result2 = 0;
            for (String coord : coords) {
                int number = Integer.valueOf(coord.substring(1,coord.length()));//我怎么忘了用substring   而是用chaeAt一个一个的截取
                switch (coord.charAt(0)) {
                    case 'A':
                        result1 -= number;
                        break;
                    case 'D':
                        result1 += number;
                        break;
                    case 'W':
                        result2 += number;
                        break;
                    case 'S':
                        result2 -= number;
                        break;
                    default:
                        continue;
                }
            }
            System.out.println(result1 + "," + result2);
        }
    }
}
