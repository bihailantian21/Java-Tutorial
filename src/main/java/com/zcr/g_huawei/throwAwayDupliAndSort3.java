package com.zcr.g_huawei;



import java.util.*;

/**
 * 题目描述
 * 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作(同一个测试用例里可能会有多组数据，希望大家能正确处理)。
 *
 *
 *
 * Input Param
 *
 * n               输入随机数的个数
 *
 * inputArray      n个随机整数组成的数组
 *
 *
 * Return Value
 *
 * OutputArray    输出处理后的随机整数
 *
 *
 *
 * 注：测试用例保证输入参数的正确性，答题者无需验证。测试用例不止一组。
 *
 * 样例输入解释：
 * 样例有两组测试
 * 第一组是3个数字，分别是：2，2，1。
 * 第二组是11个数字，分别是：10，20，40，32，67，40，20，89，300，400，15。
 *
 * 输入描述:
 * 输入多行，先输入随机整数的个数，再输入相应个数的整数
 *
 * 输出描述:
 * 返回多行，处理后的结果
 *
 * 示例1
 * 输入
 * 复制
 * 3
 * 2
 * 2
 * 1
 * 11
 * 10
 * 20
 * 40
 * 32
 * 67
 * 40
 * 20
 * 89
 * 300
 * 400
 * 15
 * 输出
 * 复制
 * 1
 * 2
 * 10
 * 15
 * 20
 * 32
 * 40
 * 67
 * 89
 * 300
 * 400
 *
 *
 *
 * 去掉重复：Set
 * 排序：TreeSet
 */
public class throwAwayDupliAndSort3 {

    /**
     * 将排序后的数组去重，就比较相邻的就行
     * 第一个数字或者不等于前一个数字都可以输出
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            Arrays.sort(arr);
            //List<Integer> list = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if (i == 0 || arr[i] != arr[i - 1]) {
                    System.out.println(arr[i]);//因为要求是输出一个数字一行，所以只能这样
                    //list.add(arr[i]);
                }
            }
            //System.out.println(Arrays.toString(list.stream().mapToInt(i->i).toArray()));
        }
    }



    /**
     * 补充：数组去重问题
     *
     * 法一：使用数组
     * (1)遍历数组，将元素依次添加进结果集中，如果结果集中已经存在，则不再添加，O(n*n)
     * (2)如果知道元素范围，比如是字母，或者数字在固定范围内，可以采用辅助数组，辅助数组下标是去重数组的元素，辅助数组元素时去重数组元素的个数，O(n)
     * (3)先将原数组排序，在与相邻的进行比较，如果不同则存入新数组
     * 法二：使用List()
     * (1)用list.contains()判断是否重复.创建一个集合，然后遍历数组逐一放入集合，只要在放入之前用contains()方法判断一下集合中是否已经存在这个元素就行了.
     *
     * 法三：使用Set(HashSet、TreeSet)
     * (1)利用HashSet集合无序不可重复的特性进行元素过滤
     * (2)TreeSet不仅可以使元素不重复，而且可以实现排序等功能的集合
     *
     * @param arr
     */
    public static int[] main3(int[] arr) {
        //一、（1）
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                if (arr[i] == list.get(j)) {
                    continue;
                }
            }
            list.add(arr[i]);
        }
        for(Iterator iterator = list.iterator();iterator.hasNext();) {
            System.out.println(iterator.next());
        }
        //int[] arrayResult = list.stream().mapToInt(i->i).toArray();
        //return arrayResult;

        //一、（2）
        //假设元素范围是0-100,既可以去重，又可以排序
        int[] temp = new int[101];
        for (int i = 0; i < arr.length; i++) {
            temp[arr[i]]++;
        }
        List<Integer> list3 = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != 0) {
                list3.add(i);
            }
        }
        for(Iterator iterator = list3.iterator();iterator.hasNext();) {
            System.out.println(iterator.next());
        }
        //return list3.stream().mapToInt(i->i).toArray();


        //一、（3）
        Arrays.sort(arr);
        List<Integer> list4 = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                System.out.println(arr[i]);
                list4.add(arr[i]);
            }
        }
        //return list4.stream().mapToInt(i->i).toArray();

        //二、（1）
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (!list2.contains(arr[i])) {
                list2.add(arr[i]);
            }
        }
        for(Iterator iterator = list2.iterator();iterator.hasNext();) {
            System.out.println(iterator.next());
        }
        //return list.stream().mapToInt(i>i).toArray();

        //三、（1）
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        for(Iterator iterator = set.iterator();iterator.hasNext();) {
            System.out.println(iterator.next());
        }
        //return set.stream().mapToInt(i->i).toArray();

        //三、（2）
        Set<Integer> set2 = new TreeSet<>();
        for (int i = 0; i < arr.length; i++) {
            set2.add(arr[i]);
        }
        /*for(Iterator iterator = set2.iterator();iterator.hasNext();) {
            System.out.println(iterator.next());
        }*/
        return set.stream().mapToInt(i->i).toArray();
    }

    /**
     * 补充：数组排序问题
     *
     * 法一：使用Arrays.sort()
     * java.util.Arrays.sort()
     * 支持对int[],long[],short[],char[],byte[],float[],double[],Object[]进行排序
     *
     * 法二：使用Collections.sort()
     * java.util.Collections.sort()   对List数组排序 推荐使用方法 使用 Collections.sort(list, Comparator(){});
     * 通过实现内部compare方法实现对象的比较
     *
     * 法三：使用TreeSet
     *
     * @param arr
     */
    public static void main4(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }

        List<Integer> list = new ArrayList<>();
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (o2).compareTo(o1);
            }
        });

    }



    /**
     * 使用TreeSet排序+去重
     * @param args
     */
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                set.add(scanner.nextInt());
            }
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }

}
