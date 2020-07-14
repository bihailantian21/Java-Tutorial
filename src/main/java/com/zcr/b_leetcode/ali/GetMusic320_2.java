package com.zcr.b_leetcode.ali;

import java.util.Arrays;


/**
 * 首先定义上升字符串，s[i]>=s[i-1]，比如aaa,abc是  acb不是
 * 给n个上升字符串，选择任意个拼起来，问能拼出来的最长上升字符串长度
 *
 * {
 * "xxxxxxxxxxz",
 * "zzz",
 * "azz",
 * "bcdz"
 * }
 */
public class GetMusic320_2 {

    /**
     * 动态规划
     * @param s
     * @return
     */
    public static int music(String[] s){
        Arrays.sort(s);
        int count = s[0].length();
        int dp[] = new int[s.length];   //dp数组为包含当前字符串的最大长度
        dp[0] = s[0].length();
        for (int i = 1; i < s.length; i++) {
            int j = s[i].length();
            char x = s[i].charAt(0);
            for (int k = 0; k < i; k++) {
                char y = s[k].charAt(s[k].length() - 1);
                if(x >= y){   //判断是否可以连接
                    j = Math.max(dp[k] + s[i].length(), j);  //寻找可以连接的最大长度
                }
            }
            dp[i] = j;
            count = Math.max(count,j);
        }
        return count;
    }

    public static void main(String[] args) {
        //String[] s = new String[]{"xxxxxxxxxxz","zzz","azz","bcdz"};
        /*Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int n = Integer.parseInt(line);
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            line = scanner.nextLine();
            s[i] = line;
        }
*/

       /* Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
        }

        System.out.println(music(s));*/




    }
}
