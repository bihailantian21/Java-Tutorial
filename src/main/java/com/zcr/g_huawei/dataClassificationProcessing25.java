package com.zcr.g_huawei;


import java.util.*;

/**
 * 数据分类处理
 * 题目描述
 * 信息社会，有海量的数据需要分析处理，比如公安局分析身份证号码、 QQ 用户、手机号码、银行帐号等信息及活动记录。
 *
 * 采集输入大数据和分类规则，通过大数据分类处理程序，将大数据分类输出。
 *
 * ﻿
 * 输入描述:
 * ﻿一组输入整数序列I和一组规则整数序列R，I和R序列的第一个整数为序列的个数（个数不包含第一个整数）；整数范围为0~0xFFFFFFFF，序列个数不限
 *
 * 输出描述:
 * ﻿从R依次中取出R<i>，对I进行处理，找到满足条件的I<j>：
 *
 * I<j>整数对应的数字需要连续包含R<i>对应的数字。比如R<i>为23，I<j>为231，那么I<j>包含了R<i>，条件满足 。
 *
 * 按R<i>从小到大的顺序:
 *
 * (1)先输出R<i>；
 *
 * (2)再输出满足条件的I<j>的个数；
 *
 * (3)然后输出满足条件的I<j>在I序列中的位置索引(从0开始)；
 *
 * (4)最后再输出I<j>。
 *
 * 附加条件：
 *
 * (1)R<i>需要从小到大排序。相同的R<i>只需要输出索引小的以及满足条件的I<j>，索引大的需要过滤掉
 *
 * (2)如果没有满足条件的I<j>，对应的R<i>不用输出
 *
 * (3)最后需要在输出序列的第一个整数位置记录后续整数序列的个数(不包含“个数”本身)
 *
 *
 *
 * 序列I：15,123,456,786,453,46,7,5,3,665,453456,745,456,786,453,123（第一个15表明后续有15个整数）
 *
 * 序列R：5,6,3,6,3,0（第一个5表明后续有5个整数）
 *
 * 输出：30, 3,6,0,123,3,453,7,3,9,453456,13,453,14,123,6,7,1,456,2,786,4,46,8,665,9,453456,11,456,12,786
 *
 * 说明：
 *
 * 30----后续有30个整数
 *
 * 3----从小到大排序，第一个R<i>为0，但没有满足条件的I<j>，不输出0，而下一个R<i>是3
 *
 * 6--- 存在6个包含3的I<j>
 *
 * 0--- 123所在的原序号为0
 *
 * 123--- 123包含3，满足条件
 *
 * 示例1
 * 输入
 * 复制
 * 15 123 456 786 453 46 7 5 3 665 453456 745 456 786 453 123
 * 5 6 3 6 3 0
 * 输出
 * 复制
 * 30 3 6 0 123 3 453 7 3 9 453456 13 453 14 123 6 7 1 456 2 786 4 46 8 665 9 453456 11 456 12 786
 */
public class dataClassificationProcessing25 {


    /**
     * 1,数组去重+排序问题，使用treeset，但是泛型要是数字，不是数字是别的的话，还需要实现comparator
     *
     * 2,这道题不难，主要考察数据结构的应用
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            String[] i = new String[n];
            for (int j = 0; j < n; j++) {
                i[j] = scanner.next();
            }

            int m = scanner.nextInt();
            TreeSet<Integer> r = new TreeSet<>();
            for (int j = 0; j < m; j++) {
                r.add(scanner.nextInt());
            }

            /*下面选用ArrayList和LinkedHashMap的方式不行，因为可能遇到没有匹配的也记上了数，后面还得判断然后去掉。
            int size = 0;
            ArrayList<LinkedHashMap<Integer,String>> list = new ArrayList<>();
            for (Integer integer : r) {
                String rnumber = String.valueOf(integer);
                int count = 0;
                LinkedHashMap<Integer,String> map = new LinkedHashMap<>();
                for (int k = 0; k < n; k++) {
                    if (i[k].contains(rnumber)) {
                        map.put(k,i[k]);
                        count++;
                    }
                }
                size += count*2+2;
                list.add(map);
            }

            System.out.print(size + " ");

            int ruleindex = 0;
            for (Integer integer : r) {
                LinkedHashMap<Integer,String> map = list.get(ruleindex);
                if (map.size() == 0) {
                    continue;
                }
                System.out.print(r + " " + map.size() + " ");
                for (Map.Entry<Integer,String> entry : map.entrySet()) {
                    System.out.print(entry.getKey() + " " + entry.getValue());
                }
            }
            System.out.println();*/


            //选用arraylist作为结果集
            ArrayList<Integer> list = new ArrayList<>();
            for (Integer integer : r) {
                String rnumber = String.valueOf(integer);
                int count = 0;
                ArrayList<Integer> sublist = new ArrayList<>();
                for (int k = 0; k < n; k++) {
                    if (i[k].contains(rnumber)) {
                        count++;
                        sublist.add(k);
                        sublist.add(Integer.valueOf(i[k]));
                    }
                }
                if (count > 0) {
                    list.add(integer);
                    list.add(count);
                    list.addAll(sublist);
                }
            }

            System.out.print(list.size() + (list.size() > 0 ? " " : ""));
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j) + ((j == list.size() - 1) ? "" : " "));
            }
            System.out.println();//注意这个非常重要！！！因为测试数据是有多组的


        }
    }
}
