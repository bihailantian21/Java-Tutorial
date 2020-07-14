package com.zcr.a_offer.bitoperateandmath;

/**
 * 12、打印1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数即 999。
 */
public class PrintMaxN12 {

    /**
     * 思路1：最简单的想法就是先找出最大的n位数，然后循环打印即可。
     * 跳进面试官的陷阱 如果n很大，用int或者long类型都会溢出
     */
    public static void print1ToMaxOfNDigits0(int n) {
        if (n <= 0)
            return;
        int maxNum = 1;
        for (int i = 0; i < n; i++) {
            maxNum *= 10;
        }
        for (int i = 1; i < maxNum; i++) {
            System.out.println(i);
        }
    }


    /**
     * 这个n有可能很大，要考虑大数问题，用字符串或者数组进行表示。
     *
     * 由于 n 可能会非常大，因此不能直接用 int 表示数字，而是用 char 数组进行存储。
     * 使用回溯法得到所有的数。
     * 递归！
     *
     * 如：n=30
     * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29
     * 9 9 9 9 9 9 9 9 9 9 9  9  9  9  9  9  9  9  9  9  9  9  9  9  9  9  9  9  9  9
     *
     * i = 0
     * 0 0 i=0
     * 1 1 i=1
     * 2 2
     * 3 3
     * 4 4
     * 5 5
     * 6 6
     * 7 7
     * 8 8
     * 9 9
     *
     * i = 0
     *
     * i = 0
     * ...
     * 9个
     *
     *
     *
     * @param n
     */
    public void print1ToMaxOfNDigits(int n) {
        if (n <= 0)
            return;
        char[] number = new char[n];
        print1ToMaxOfNDigits(number, 0);
    }

    private void print1ToMaxOfNDigits(char[] number, int digit) {
        if (digit == number.length) {
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[digit] = (char) (i + '0');
            print1ToMaxOfNDigits(number, digit + 1);
        }
    }

    private void printNumber(char[] number) {
        int index = 0;
        while (index < number.length && number[index] == '0')
            index++;
        while (index < number.length)
            System.out.print(number[index++]);
        System.out.println();
    }

    public static void main(String[] args) {
        PrintMaxN12 printMaxN12 = new PrintMaxN12();
        int n = 9;
        printMaxN12.print1ToMaxOfNDigits(n);
    }


    /**
     * 然而上述程序在n很大时，显然会有溢出,int 的范围不够。那么我们会想到用long来存储，但是如果n的值还是很大，以至于long也无法满足要求。那么该怎么办？
     *
     * 只能用其他的数据结构来存储我们的非常大的数字。
     *
     * 思路2：用字符串来存储数字。
     * @param n
     */
    public static void Print1ToMaxOfNDigits_2(int n){

        if(n <= 0){
            return;
        }
        StringBuffer number = new StringBuffer();

        for(int i = 0; i < n; i++){
            number.append('0');

        }

        while(!Increment(number)){
            PrintNumber2(number);
        }
    }
    public static boolean Increment(StringBuffer s){
        boolean isOverflow = false;
        int nTakeOver = 0;
        int nLength = s.length();
        for(int i = nLength - 1; i >= 0; i--){
            int nSum = s.charAt(i) - '0' + nTakeOver;
            if( i == nLength - 1){
                nSum++;
            }
            if(nSum >= 10){
                if(i == 0){
                    isOverflow = true;

                }else{
                    nSum -= 10;
                    nTakeOver = 1;
                    s.setCharAt(i, (char) ('0' + nSum));
                }

            }else{
                s.setCharAt(i, (char) ('0' + nSum));
                break;
            }
        }
        return isOverflow;
    }

    public static void PrintNumber2(StringBuffer s){
        boolean isBeginning0 = true;
        for(int i = 0; i < s.length(); i++){
            if(isBeginning0 && s.charAt(i) != '0'){
                isBeginning0 = false;
            }
            if(!isBeginning0){
                System.out.print(s.charAt(i));
            }
        }

        System.out.println();
    }


    /**
     * 思路3：用数字排列的方法表示：如果我们在数字前面补0的话，就会发现n位所有十进制数其实就是n个从0到9的全排列。
     * 也就是说，我们把数字的每一位都从0到9排列一遍，就得到了所有的十进制数。当然打印的时候，我们应该将前面的0补位去掉。
     * @param n
     */
    public static void Print1ToMaxOfNDigits_3(int n){
        if(n < 0){
            return;
        }
        StringBuffer s = new StringBuffer(n);
        for(int i = 0; i < n; i++){
            s.append('0');
        }
        for(int i = 0; i < 10; i++){
            s.setCharAt(0, (char) (i+'0'));
            Print1ToMaxOfNDigits_3_Recursely(s, n, 0);
        }

    }
    public static void Print1ToMaxOfNDigits_3_Recursely(StringBuffer s, int n , int index){
        if(index == n - 1){
            PrintNumber3(s);
            return;
        }

        for(int i = 0; i < 10; i++){
            s.setCharAt(index+1, (char) (i+'0'));
            Print1ToMaxOfNDigits_3_Recursely(s, n, index+1);
        }
    }
    public static void PrintNumber3(StringBuffer s){
        boolean isBeginning0 = true;
        for(int i = 0; i < s.length(); i++){
            if(isBeginning0 && s.charAt(i) != '0'){
                isBeginning0 = false;
            }
            if(!isBeginning0){
                System.out.print(s.charAt(i));
            }
        }

        System.out.println();
    }



}
