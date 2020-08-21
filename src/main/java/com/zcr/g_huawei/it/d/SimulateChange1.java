package com.zcr.g_huawei.it.d;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * 第一题 模拟找钱
 * 假设咖啡卖 5 元，每个客户可能给你 5、10、20 元的纸币，初始情况下你没有任何纸币，问是否能够找零。
 * 如果能找零就输出 true，总用户数.
 * 则输出 false,失败的用户index。
 * 例如：
 * 5,5,5,10 => true,4
 * 10,10 => false,1
 *
 *
 *
 *
 *
 *
 *
 */
public class SimulateChange1 {


    /**
     * 教训：
     * 1.全局变量的定义和初始化一定注意放在while外面！！要不然每次循环就会把上一次循环的值覆盖掉
     * 2.这道题，自己一直觉得自己不对，其实是对的，就是简单的挨个遍历        自己以为能找到规律   还是不相信自己
     * 3.解耦，因为那个count，每次都是要记录下来的，所以没必要与下面的逻辑代码耦合起来
     *
     * @param args
     */
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(5,0);
        map.put(10,0);
        map.put(20,0);
        while (scanner.hasNextInt()) {
            int coffee = 5;
            int coin = scanner.nextInt();
            count++;
            int change = coin - coffee;

            if (change == 0) {
                map.put(5,map.get(5)+1);
                continue;
            }

            if (change == 5) {
                if (map.get(5) != 0) {
                    map.put(5,map.get(5)-1);
                    map.put(10,map.get(10)+1);
                    continue;
                } else {
                    System.out.println(false+","+count);
                    return;
                }
            }

            if (change == 15) {
                if (map.get(10) != 0) {
                    map.put(10,map.get(5)-1);
                    if (map.get(5) != 0) {
                        map.put(5,map.get(5)-1);
                        map.put(15,map.get(15)+1);
                        continue;
                    } else {
                        System.out.println(false+","+count);
                        return;
                    }
                } else if (map.get(5) >= 3) {
                    map.put(5,map.get(5)-3);
                    map.put(15,map.get(15)+1);
                    continue;
                } else {
                    System.out.println(false+","+count);
                    return;
                }
            }
        }
        System.out.println(true+","+count);
    }

    /**
     * 这是牛客上一个人的做法
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(5,0);
        map.put(10,0);
        map.put(20,0);
        String str = scanner.nextLine();
        String[] strArr = str.split("'");
        for (int i = 0; i < strArr.length; i++) {
            int coin = Integer.parseInt(strArr[i]);
            count++;
            if (coin == 5) {
                map.put(5,map.get(5)+1);
                continue;
            }

            if (coin == 10) {
                map.put(5,map.get(5)-1);
                map.put(10,map.get(10)+1);
                if (map.get(5) < 0) {
                    System.out.println(false+","+count);
                    return;
                }
                continue;
            }

            if (coin == 15) {
                map.put(15,map.get(15)+1);
                if (map.get(10) != 0 && map.get(5) != 0) {
                        map.put(10,map.get(5)-1);
                        map.put(5,map.get(5)-1);
                } else if (map.get(5) != 0) {
                    map.put(5,map.get(5)-3);
                    if (map.get(5) < 0) {
                        System.out.println(false+","+count);
                        return;
                    }
                    continue;
                } else {
                    System.out.println(false+","+count);
                    return;
                }
            }
        }
        System.out.println(true+","+count);
    }
}
