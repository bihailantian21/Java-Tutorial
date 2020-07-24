package com.zcr.g_huawei;

import java.util.*;

/**
 * 字符串的连接最长路径查找：
 * 给定n个字符串，请对n个字符串按照字典序排列。
 * 输入描述:
 * 输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
 * 输出描述:
 * 数据输出n行，输出结果为按照字典序排列的字符串。
 * 示例1
 * 输入
 * 复制
 * 9
 * cap
 * to
 * cat
 * card
 * two
 * too
 * up
 * boat
 * boot
 * 输出
 * 复制
 * boat
 * boot
 * cap
 * card
 * cat
 * to
 * too
 * two
 * up
 */
public class dictionarySenqunse14 {


    /**
     * 按照字典序排序：
     * 和那种上升字符串还不一样，不仅要比较头尾还要比较中间的字符
     *
     *
     * 1.Arrays.sort()
     *
     *
     * 2.Collections
     * Collections.sort()
     *
     *
     * 3.Com
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(scanner.next());//注意：这里用next()而不是用nextLine()
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


    public static void main2(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        Vector<String> ve=new Vector<String>();
        for (int i = 0; i < n; i++) {
            ve.add(in.next());
        }
        Collections.sort(ve);
        Iterator it=ve.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
