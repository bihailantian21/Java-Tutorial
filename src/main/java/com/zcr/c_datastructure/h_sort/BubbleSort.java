package com.zcr.c_datastructure.h_sort;

/**
 * @author zcr
 * @date 2019/7/6-22:54
 */
public class BubbleSort {

    public static void main(String[] args) {
        //int arr[] = {3,9,-1,10,20};

        //int temp = 0;//临时变量

        /*//为了容易理解，我们把冒泡排序的过程给展示出来
        //第一趟排序，就是将最大的那个数排在最后
        for (int i = 0; i < arr.length - 1; i++) {//一共比较数组大小-1-0次
            //如果前面的数比后面的大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] =temp;
            }
        }
        System.out.println("第一趟排序后的数组：");
        System.out.println(Arrays.toString(arr));

        //第二趟排序，就是将第二大的数排在倒数第二位
        for (int i = 0; i < arr.length - 1 - 1; i++) {//一共比较数组大小-1-1次
            //如果前面的数比后面的大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] =temp;
            }
        }
        System.out.println("第二趟排序后的数组：");
        System.out.println(Arrays.toString(arr));

        //第三趟排序，就是将第三大的数排在倒数第三位
        for (int i = 0; i < arr.length - 1 - 2; i++) {//一共比较数组大小-1-2次
            //如果前面的数比后面的大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] =temp;
            }
        }
        System.out.println("第三趟排序后的数组：");
        System.out.println(Arrays.toString(arr));

        //第四趟排序，就是将第四大的数排在倒数第四位
        for (int i = 0; i < arr.length - 1 - 3; i++) {//一共比较数组大小-1-3次
            //如果前面的数比后面的大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] =temp;
            }
        }
        System.out.println("第四趟排序后的数组：");
        System.out.println(Arrays.toString(arr));*/

        //一共要进行数组长度-1趟排序
        //冒泡排序
        /*for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 -i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] =temp;
                }
            }
            System.out.println("第"+ (i+1) +"趟排序后的数组：");
            System.out.println(Arrays.toString(arr));
        }
        */




        //时间复杂度O(n2)

        //冒泡排序的优化
        /*boolean flag = false;//表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 -i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] =temp;
                }
            }
            System.out.println("第"+ (i+1) +"趟排序后的数组：");
            System.out.println(Arrays.toString(arr));

            if (!flag) {//在这一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false;//重置flag,进行下次判断
            }
        }*/

        //测试冒泡排序
        /*System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后的数组：");
        System.out.println(Arrays.toString(arr));*/

        //测试一下冒泡排序的速度，事前O(n2)，事后
        //创建一个8万个随机数的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 80000);//会生成一个0~80000的随机数
        }
        //System.out.println(Arrays.toString(arr));
        /*Date date1 = new Date();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String date1str = df1.format(date1);
        System.out.println("排序前的时间是："+date1str);
        bubbleSort(arr);
        Date date2 = new Date();
        String date2str = df1.format(date1);
        System.out.println("排序后的时间是："+date2str);*/
        long l1 = System.currentTimeMillis();
        bubbleSort(arr);
        long l2  = System.currentTimeMillis();
        System.out.println(l2 - l1);



    }

    /**
     * 冒泡排序O(n^2)    O(n)   O(n^2)    O(1)   稳定（前一个大于后一个，才交换他们。等于和大于的时候不交换。）
     * （1）	从后往前两两比较相邻元素的值，若前一个大于后一个，则交换他们，知道序列比较完。这是一趟冒泡的结果：将最小的元素交换到待排序序列的第一个位置。
     * （2）	下一趟冒泡时，前一趟确定的最小元素不再参加比较，待排序序列减少一个元素，每趟冒泡的结果把序列中最小元素放到了序列的最终位置。
     * （3）	依次类推，最多做n-1趟冒泡就能把所有元素排好序。
     * 规则：一共进行n-1次大循环、每一趟排序的次数在减少、发现某一趟中没有发生交换提前结束
     * @param arr
     */
    //将前面的冒泡排序算法，封装成一个方法
    public static void bubbleSort(int[] arr) {
        int temp = 0;//临时变量
        boolean flag = false;//表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 -i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] =temp;
                }
            }
            //System.out.println("第"+ (i+1) +"趟排序后的数组：");
            //System.out.println(Arrays.toString(arr));

            if (!flag) {//在这一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false;//重置flag,进行下次判断
            }
        }

    }
    
    
}
