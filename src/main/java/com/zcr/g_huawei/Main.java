package com.zcr.g_huawei;


import java.util.*;

/**
 *
 *
 *  shoppinglist28
 * 购物单
 *
 *
 * 王强今天很开心，公司发给N元的年终奖。王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
 * 主件	附件
 * 电脑	打印机，扫描仪
 * 书柜	图书
 * 书桌	台灯，文具
 * 工作椅	无
 * 如果要买归类为附件的物品，必须先买该附件所属的主件。每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。
 * 王强想买的东西很多，为了不超出预算，他把每件物品规定了一个重要度，分为 5 等：用整数 1 ~ 5 表示，第 5 等最重要。
 * 他还从因特网上查到了每件物品的价格（都是 10 元的整数倍）。他希望在不超过 N 元（可以等于 N 元）的前提下，使每件物品的价格与重要度的乘积的总和最大。
 * 设第 j 件物品的价格为 v[j] ，重要度为 w[j] ，共选中了 k 件物品，编号依次为 j 1 ， j 2 ，……， j k ，则所求的总和为：
 * v[j 1 ]*w[j 1 ]+v[j 2 ]*w[j 2 ]+ … +v[j k ]*w[j k ] 。（其中 * 为乘号）
 * 请你帮助王强设计一个满足要求的购物单。
 *
 * 输入描述:
 * 输入的第 1 行，为两个正整数，用一个空格隔开：N m
 * （其中 N （ <32000 ）表示总钱数， m （ <60 ）为希望购买物品的个数。）
 * 从第 2 行到第 m+1 行，第 j 行给出了编号为 j-1 的物品的基本数据，每行有 3 个非负整数 v p q
 * （其中 v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号）
 *
 * 输出描述:
 *  输出文件只有一个正整数，为不超过总钱数的物品的价格与重要度乘积的总和的最大值（ <200000 ）。
 * 示例1
 * 输入
 * 1000 5
 * 800 2 0
 * 400 5 1
 * 300 5 1
 * 400 3 0
 * 500 2 0
 * 输出
 * 2200
 *
 *
 */
public class Main {

    /**
     * 考虑到每个主件最多只有两个附件，因此我们可以通过转化，把原问题转化为01背包问题来解决，在用01背包之前我们需要对输入数据进行处理，把每一种物品归类，
     * 即：把每一个主件和它的附件看作一类物品。处理好之后，我们就可以使用01背包算法了。
     * 在取某件物品时，我们只需要从以下四种方案中取最大的那种方案：只取主件、取主件＋附件1、取主件＋附件2、既主件＋附件1＋附件2。
     * 很容易得到如下状态转移方程：
     *
     *
     * dp[i][j]代表了：用j块钱去买前i件产品，能得到的最大价值     （0-1背包问题）
     *
     *
     * @param args
     */
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String[] str1Arr = str1.split(" ");
        int n = Integer.parseInt(str1Arr[0]);//钱数
        int m = Integer.parseInt(str1Arr[1]);//个数
        int[] v = new int[m + 1];
        int[] p = new int[m + 1];
        int[] w = new int[m + 1];
        int[] q = new int[m + 1];
        for (int i = 1; i <= m; i++) {// v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件
            String str = scanner.nextLine();
            String[] strArr = str.split(" ");
            v[i] = Integer.parseInt(strArr[0]);
            p[i] = Integer.parseInt(strArr[1]);
            w[i] = v[i] * p[i];
            q[i] = Integer.parseInt(strArr[2]);

        }
        int[][] result = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (q[i] == 0) {
                    if (v[i] <= j) {
                        result[i][j] = Math.max(result[i - 1][j], result[i - 1][j - v[i]] + v[i]*p[i]);
                    }
                } else {
                    if(v[i] + v[q[i]] <= j){
                        result[i][j] = Math.max(result[i-1][j], result[i-1][j-v[i]] + v[i]*p[i]);
                    }
                }
            }
        }

        System.out.println(result[m][n]);
    }


    /**
     * 背包问题,作为一个小白,耗时三天,利用业余时间学习了崔添翼大神<<背包九讲>>.
     *
     * 这里分析一下傻瓜式解题思路:
     * 1.首先进行数据读入,这里注意题目中有一个条件为"每个物品的价格都是10的整数倍",由此将相关数据进行缩小.(可以减小存储空间)
     * 2.根据背包九讲中第七讲,有依赖的背包问题.把问题进行细化,细化如下:
     * 3.将物品分为K组,每组当中包含主物品及其附属物品.
     * 4.根据分组,将每组中的物品使用第一讲中的01背包问题,将其所有的可能组合成包含花费和得到价值的物品组.
     * 5.根据分组,使用第六讲的分组背包问题,每个组当中最多取一个,进行价值计算.最后得到最大价值.
     *
     * 总结：
     * 把（每个主件与其相应附件的集合）的（所有包含主件的子集）归到一个组中，组的个数就是主件的个数。然后每组中只选一个子集，这样就转化为了分组背包问题
     */
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String[] str1Arr = str1.split(" ");
        int n = Integer.parseInt(str1Arr[0]);//钱数
        int m = Integer.parseInt(str1Arr[1]);//个数
        int[] v = new int[m + 1];
        int[] p = new int[m + 1];
        int[] w = new int[m + 1];
        int[] q = new int[m + 1];

        int[][] group = new int[m + 1][3];////group[][0]为主件，group[][1]和group[][2]为两个附件
        for (int i = 1; i <= m; i++) {// v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件
            String str = scanner.nextLine();
            String[] strArr = str.split(" ");
            v[i] = Integer.parseInt(strArr[0]);
            p[i] = Integer.parseInt(strArr[1]);
            w[i] = v[i] * p[i];
            q[i] = Integer.parseInt(strArr[2]);
            if (q[i] == 0) {
                group[i][2] = 1;
            } else if (group[q[i]][2] == 0) {
                group[q[i]][2] = i;
            } else {
                group[q[i]][1] = i;
            }
        }

        for (int i = 1; i <= m; i++) {
            if (group[i][0] == 0) {
                continue;
            }
            //转化为分组背包问题，每组有s种选择,
            //int s =

        }

/*

#include<iostream>
#include<algorithm>
        using namespace std;
const int N_size = 32001, m_size = 61;
        int N, m, v[m_size], p[m_size], q, f[N_size]; //f[i]为最大价格为i时，所有被选物品的价格、重要度之积的和
        int group[m_size][3]; //group[][0]为主件，group[][1]和group[][2]为两个附件
        int main() {
            cin >> N >> m;
            for (int i = 1; i <= m; ++i) {
                cin >> v[i] >> p[i] >> q;
                if (q == 0)
                    group[i][0] = 1; //如果第i件物品是主件，将group[i][0]置1
                    //如果第i件物品是附件
                else if (group[q][2] == 0)//如果两个附件位都未使用，将第二个附件位置为i
                    group[q][2] = i;
                else                      //如果第二个附件位已使用，将第一个附件位置为i
                    group[q][1] = i;
            }
            for (int i = 1; i <= m; ++i) {
                if (group[i][0] == 0)     //若不是主件直接跳过
                    continue;
                //转化为分组背包问题，每组有s种选择
                int s = (group[i][1] == 0 ? 1 : 2) * (group[i][2] == 0 ? 1 : 2);
                for (int j = N; j >= 0; --j) {
                    for (int k = 0; k < s; ++k) {
                        int vk = v[i] + v[group[i][1]] * ((k & 2) >> 1) + v[group[i][2]] * (k & 1); //每种选择物品所占体积
                        if (j >= vk) {
                            int wk = v[i] * p[i] + v[group[i][1]] * p[group[i][1]] * ((k & 2) >> 1) + v[group[i][2]] * p[group[i][2]] * (k & 1); //每种选择物品价格、重要度之积的和
                            f[j] = max(f[j], f[j - vk] + wk);
                        }
                    }
                }
            }
            cout << f[N];
*/
    }

       /* 下面的对，但是太绕了
       //将物品分为K组,每组当中包含主物品及其附属物品.
        int[] flag = new int[m + 1];
        int[] groupnum = new int[m + 1];
        Map<Integer, ArrayList<Integer>> V = new HashMap();
        Map<Integer, ArrayList<Integer>> W = new HashMap();
        for (int k = 1; k <= m; k++) {
            if (q[k] == 0) {//i本身是主件，主件编号为i
                V.get(k).add(v[k]);
                W.get(k).add(w[k]);
                flag[k] = 1;
            } else {//是主件q[i]的副件
                V.get(q[k]).add(v[k]);
                W.get(q[k]).add(w[k]);
                groupnum[q[k]]++;//逐渐q[i]的副件的个数
            }
        }
        //根据分组,将每组中的物品使用第一讲中的01背包问题,将其所有的可能组合成包含花费和得到价值的物品组.
        int[][] dp = new int[m][n];
        for (int k = 1; k <= m; k++) {
            if (flag[k] > 0) {
                for (int i = 1; i <= groupnum[k]; i++) {
                    for (int j = n - v[k]; j >= V.get(k).get(i); j--) {
                        //ZeroOnePack(Fmid[k], viW[k][i], viC[k][i], V-C[k]);
//                        void ZeroOnePack(int F[], int W, int C, int V)
//                        {
//                            for(int v=V;v>=C;v--)
//                            {
//                                F[v] = max(F[v], F[v-C] + W);
//                            }
//                        }
                        dp[][] = Math.max();

                    }
                }
            }
        }
        //根据分组,使用第六讲的分组背包问题,每个组当中最多取一个,进行价值计算.最后得到最大价值.
        int[][] result = new int[m+1][n+1];*/


    /**
     * /**
     *      * 背包问题,作为一个小白,耗时三天,利用业余时间学习了崔添翼大神<<背包九讲>>.
     *      *
     *      * 这里分析一下傻瓜式解题思路:
     *      * 1.首先进行数据读入,这里注意题目中有一个条件为"每个物品的价格都是10的整数倍",由此将相关数据进行缩小.(可以减小存储空间)
     *      * 2.根据背包九讲中第七讲,有依赖的背包问题.把问题进行细化,细化如下:
     *      * 3.将物品分为K组,每组当中包含主物品及其附属物品.
     *      * 4.根据分组,将每组中的物品使用第一讲中的01背包问题,将其所有的可能组合成包含花费和得到价值的物品组.
     *      * 5.根据分组,使用第六讲的分组背包问题,每个组当中最多取一个,进行价值计算.最后得到最大价值.
     *      *
     *      * 总结：
     *      * 把（每个主件与其相应附件的集合）的（所有包含主件的子集）归到一个组中，组的个数就是主件的个数。然后每组中只选一个子集，这样就转化为了分组背包问题
     *
     * 本题为依赖背包类型题目。即所给部分物品为附属物品，有些物品是主物品。选择附属物品必须要选择主物品
     * 对于此类题目可以做出以下思考
     *
     * 对于主物品和附属物品可以这样处理
     * a. 先将所有附属物品放到 总容量-主物品花费 的包中做01背包问题找出最优
     * b. 将附属物品的最优解和主物品重新组合成一个物品组，只从这个组中取一件物品
     * 将所有带有附属物品的主物品都按上一步获取到一个最优的物品
     * 将所有的最优物品合并到一起
     * 现在给出代码。此代码还可以解决多层主、附属物品的问题（即树形结构的）
     *
     * @param args
     */
    public static void main3(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Package p = new Package(scanner.nextInt());

        int size = scanner.nextInt();
        Itemm[] items = new Itemm[size];

        for (int i = 0; i < size; i++) {
            Itemm item = new Itemm(scanner.nextInt(), scanner.nextInt());
            int masterIndex = scanner.nextInt();
            if (0 == masterIndex) {
                items[i] = item;
            } else {
                items[masterIndex -1].addAttach(item);
            }
        }

        System.out.println(p.maxValue(Arrays.asList(items)));
    }

    static class Package {
        private int limit;
        private int[] valueList;

        public Package(int limit) {
            this.limit = limit;
            this.valueList = new int[limit + 1];
        }

        public int maxValue(List<Itemm> items){
            for (Itemm item : items) {
                if (null != item) {
                    putItem(item);
                }
            }
            return valueList[limit];
        }         //将物品添加到背包中
        private void putItem(Itemm item) {
            if (null != item.attaches) {   //如果这个物品有附属物品，则使分组的背包解决方案
                maxValueForGroup(item);
            } else {                 //如果这个物品没有附属物品，直接使用01背包解决方案
                maxValueFor01(item);
            }
        }
        //01背包解决方案的意思就是，对一个物品，要么取，要么不取，         //取决于价值最大
        private void maxValueFor01(Itemm item) {
            for (int i = limit; i>= item.price; i--) {
                int oldValue = valueList[i], newValue;                 //当前物品和其他物品组合起来价值比前面所取的相同空间的物品价值更大。替换
                if (oldValue < (newValue = valueList[i-item.price] + item.value)) {
                    valueList[i] = newValue;
                }                 //当前物品价值比前面所取的相同空间的物品价值更大。替换 else if (oldValue < (newValue = item.value)) {
                valueList[i] = newValue;
            }
        }

        //使用分组的背包解决方案
        private void maxValueForGroup (Itemm item) {
            List<Itemm> groupItems = changeToGroup(item);
            for (int i = limit; i >= item.price; i--) {
                for (Itemm gi : groupItems) {
                    if (i < gi.price) continue;

                    int oldValue = valueList[i], newValue;
                    if (oldValue < (newValue = valueList[i-gi.price] + gi.value)) {
                        valueList[i] = newValue;
                    } else if (oldValue < (newValue = gi.value)) {
                        valueList[i] = newValue;
                    }
                }
            }
        }

        private List<Itemm> changeToGroup(Itemm item) {
            //主、附属物品可看成。要么所有都不取，要么取主物品，要么取主物品与所有附属物品的最优值之和
            // 先将所有附属物品放到一个容量为 总容量-主物品消耗 的背包中取最优解（使用01背包解决方案）。
            Package p = new Package(limit - item.price);
            // 这里使用了一个新的Package对象去处理是可以处理多层主、附属物品问题的
            p.maxValue(item.attaches);
            List<Itemm> groupItems = new ArrayList<>();                          //将最优解转换成分组物品。即只从此组选择出一个物品或不选
            for (int i = 0; i <= p.limit; i++) {
                int price = i + item.price;
                int value = p.valueList[i] + item.value;
                if (groupItems.stream().noneMatch(gi -> gi.value == value && gi.price < price)) {
                    groupItems.add(new Itemm(price, 0, value));
                }
            }
            return groupItems;
        }
    }

    static class Itemm {
        private int price;
        private int level;
        private int value;
        private List<Itemm> attaches;

        public Itemm(int price, int level) {
            this.price = price;
            this.level = level;
            this.value = level * price;
        }

        public Itemm(int price, int level, int value) {
            this.price = price;
            this.level = level;
            this.value = value;

        }

        public void addAttach(Itemm item) {
            if (attaches == null) {
                attaches = new ArrayList<>();
            }
            attaches.add(item);
        }
    }







    class Item{
        private int weight;
        private int value;
        private int flag;

        public Item(int weight, int value, int flag) {
            this.weight = weight;
            this.value = value;
            this.flag = flag;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }

    /**
     * 分组背包问题
     * 以下代码从分组中选择价值最大的。
     * 分组背包，每组有四种情况，a.主件 b.主件+附件1 c.主件+附件2 d.主件+附件1+附件2
     *
     * //依赖01背包问题，可以利用泛化物品思想转化为分组01背包问题（不取，取一个主件，取一个主件和第一个附件，取一个主件和第二个附件，取一个主件和两个附件）是一个分组
     * //参考博客https://blog.csdn.net/yandaoqiusheng/article/details/84782655
     * //ACCEPT
     * @param args
     */
    public static void main(String[] args) {
        Main main = new Main();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int N = in.nextInt();
            N/=10;
            int m = in.nextInt();
            Item[] items = new Item[m];
            Map<Item, List<Item>> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                int weight = in.nextInt();
                int value = in.nextInt() * weight;
                weight/=10;
                int flag = in.nextInt();
                items[i] = main.new Item(weight, value, flag);
                if (items[i].getFlag() == 0){
                    map.put(items[i], new ArrayList<>());
                }
            }

            for (int i = 0; i < items.length; i++) {
                if (items[i].getFlag() != 0){
                    Item item = items[items[i].getFlag() - 1];
                    map.get(item).add(items[i]);
                }
            }
            int[] table = new int[N + 1];
            for (Item item : map.keySet()) {//主件
                for (int i = N; i >= 1; i--) {//价格
                    if (item.getWeight() <= i){
                        table[i] = Math.max(table[i], table[i - item.getWeight()] + item.getValue());
                        for (Item addition : map.get(item)) {//副件
                            if (addition.getWeight() + item.getWeight() <= i){
                                table[i] = Math.max(table[i], table[i - item.getWeight() - addition.getWeight()] + item.getValue() + addition.getValue());
                            }
                        }
                        if (map.get(item).size() == 2){
                            Item a1 = map.get(item).get(0);
                            Item a2 = map.get(item).get(1);
                            if (a1.getWeight() + a2.getWeight() + item.getWeight() <= i){
                                table[i] = Math.max(table[i], table[i - item.getWeight() - a1.getWeight() - a2.getWeight()] + item.getValue() + a1.getValue() + a2.getValue());
                            }
                        }
                    }
                }
            }
            System.out.println(table[N]);
        }
    }


}
