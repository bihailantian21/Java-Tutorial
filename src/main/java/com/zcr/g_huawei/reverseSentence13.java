package com.zcr.g_huawei;

import java.util.Scanner;


/**
 * 句子逆序
 *
 * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
 * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 *
 *
 * 接口说明
 *
 * **
 *  * 反转句子
 *  *
 *  * @param sentence 原句子
 *  * @return 反转后的句子
 *  */
// public String reverse(String sentence);
//
//        *输入描述:
//        *将一个英文语句以单词为单位逆序排放。
//        *
//        *输出描述:
//        *得到逆序的句子
//        *
//        *示例1
//        *输入
//        *复制
//        *I am a boy
//        *输出
//        *复制
//        *boy a am I
// */
public class reverseSentence13 {

    /**
     * int转换为String
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String sentence = scanner.nextLine();
            StringBuilder reversesentence = new StringBuilder(sentence);
            reversesentence.reverse();
            String[] strings = reversesentence.toString().split(" ");
            StringBuilder stringBuilderResult = new StringBuilder();
            for (String str : strings) {
                stringBuilderResult.append(new StringBuilder(str).reverse() + " ");
            }

            System.out.println(stringBuilderResult.toString().substring(0,stringBuilderResult.length() - 1));
        }
    }



}
