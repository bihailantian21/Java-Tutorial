package com.zcr.g_huawei.it.d;

import java.util.Scanner;

/**

 *
 * 给定 X 的行数，按照 X 样式输出字符串：
 *
 * 例如：
 * EVERYTHINGGOESWELL,5 => EIWETGELYORHGSLVNE
 * 输入一个仅由大写字母组成的字符串string和一个指定的奇列数N
 * 其中(1 < len(string) <= 2000), (3 <= N < 1000)
 * 输出变形后的新字符串
 例如：
 输入：
 EVERYTHINGGOESWELL,5
 ['E', 0, 0, 0, 'V']
 [0, 'E', 0, 'R', 0]
 [0, 0, 'Y', 0, 0]
 [0, 'T', 0, 'H', 0]
 ['I', 0, 0, 0, 'N']
 [0, 'G', 0, 'G', 0]
 [0, 0, 'O', 0, 0]
 [0, 'E', 0, 'S', 0]
 ['W', 0, 0, 0, 'E']
 [0, 'L', 0, 'L', 0]
 输出：
 EIWETGELYORHGSLVNE
 * 第三题是对字符串进行X形状重排列，假设奇数n为列数，那么只要构造好一个循环的坐标就行，
 * 每个循环有2n-3个字符，之后只要按照顺序把每个字符放到对应的列里，最后按列来输出即可。
 *
 * 给定一个仅有大写字母组成的字符串以及一个指定的奇数列，按从左到右，从上到下的顺序将给定的字符串以及大X字形排列指定的列数。
 */
public class printX3 {


    /**
     * 教训：
     * 1.把循环的i,j写错了
     * 2.本应该是for循环外面的东西，写到了for循环里面
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        in.close();

        String[] strings=line.split(",");
        String str=strings[0];
        int odd=Integer.valueOf(strings[1]);

        StringBuilder[] builders = new StringBuilder[odd];
        for (int m = 0; m < odd; m++) {
            builders[m] = new StringBuilder();
        }

        int index = 0;
        int length = str.length();
        int i = 0;

        while (index < length) {
            for (int j = 0; j < odd; j++) {
                if (i == j || (i + j + 1) == odd) {
                    builders[j].append(str.charAt(index));
                    index++;
                    if (index == length) {
                        break;
                    }
                }
            }
            i = (i+1)%(odd-1);
        }
        for (int k = 0; k < odd ; k++) {
            System.out.print(builders[k]);
        }
    }



    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        in.close();

        String[] strings=line.split(",");
        String str=strings[0];
        int odd=Integer.valueOf(strings[1]);

        StringBuilder[] builders = new StringBuilder[odd];
        for (int j = 0; j < odd; j++) {
            builders[j] = new StringBuilder();
        }

        int rowNumber = 0;
        int index = 0;
        int length = str.length();

        while(index<length){
            for (int i = 0; i < odd; i++) {
                if(rowNumber==i||(rowNumber+i+1)==odd) {
                    builders[i].append(str.charAt(index++));
                    if(index==length) break;
                }
            }
            rowNumber=(rowNumber+1)%(odd-1);
        }

        for (int i = 0; i < odd ; i++) {
            System.out.print(builders[i]);
        }

    }
}
