package com.zcr.a_offer.g_recursionandloop;

/**
 * 动态规划问题总结
 * 感谢左大神的讲解
 * 在介绍动态规划是什么之前，先来看一道题目
 * 给定数组arr，arr中所有的值都为正数且不重复，每个值代表一种面值的货币，每种面值的货币可以使用任意张，
 * 再给定一个整数aim代表要找的钱数，求换钱有多少种方法。
 * arr={5,10,25,1}  aim=1000
 *
 * 求解该题，有四种方法：暴力搜索方法、记忆搜索方法、动态规划方法、状态继续化简后的动态规划方法
 * 在面试题中出现类似的题目，优化轨迹高度一致。
 */
public class Coins {

    /**
     * arr={5,10,25,1}  aim=1000
     *     0  1  2  3
     *
     * 方法一：暴力搜索方法
     *  * 1.用0张5元的货币，让[10,25,1]组成剩下的1000，最终方法数记为res1
     *  * 2.用1张5元的货币，让[10,25,1]组成剩下的995，最终方法数记为res2
     *  * 3.用2张5元的货币，让[10,25,1]组成剩下的990，最终方法数记为res3
     *  * ...
     *  * 201.用200张5元的货币，让[10,25,1]组成剩下的0，最终方法数记为res201
     *  * 累加即为结果
     *
     *  如果已经使用0张5元和1张10元的情况下，后续将求p1(arr,2,990)
     *  2:表示arr剩下的钱为arr[2,3]，即[25,1]
     *  990:表示要找的剩余钱数
     *  当已经使用2张5元和0张10元的情况下，后续还是要求p1(arr,2,990)
     * 暴力搜索的时间复杂度很高，因为存在太多的重复劳动
     * @param arr
     * @param aim
     * @return
     */
    public int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr,0,aim);
    }
    public int process1(int[] arr,int index,int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim ; i++) {
                res += process1(arr,index+1,aim-arr[index]*i);
            }
        }
        return res;
    }


    /**
     * p(index,aim)   结果表map
     * 1.每计算完一个p(index,aim)，都将结果放入map中，index和aim组成共同的key，返回结果为value。
     * 2.要进入一个递归过程p(index,aim)，先以index和aim注册的key在map中查询是否已经存在value，
     * 如果存在，则直接取值，如果不存在，才进行递归计算。
     *
     * 其实记忆化搜索的方法，本质上与动态规划的思想非常相似。时间复杂度和动态规划一样0(m*n^2)
     * @param arr
     * @param aim
     * @return
     */
    public int coins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim + 1];
        return process2(arr,0,aim,map);
    }
    public int process2(int[] arr,int index,int aim,int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim ; i++) {
                mapValue = map[index + 1][aim-arr[index]*i];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += process2(arr, index + 1, aim - arr[index] * i,map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1:res;
        return res;
    }

    /**
     * 方法三：动态规划
     * 如果arr长度为N，生成行数为N，列数为aim+1的矩阵dp。
     * dp[i][j]：在使用arr[0..i]货币的情况下，组成钱数j有多少种方法。
     *      0  1  2  3...j...aim
     * 0    1
     * 1    1
     * 2
     * ..
     * i               dp[i][j]
     * ..
     * N-1   1
     *
     * 1.如果完全不用arr[i]货币，只使用arr[0..i-1]货币时，方法数为dp[i-1][j]
     * 2.如果用1张arr[i]货币，剩下的钱用arr[0..i-1]货币时，方法数为dp[i-1][j-1*arr[i]]
     * 3.如果用2张arr[i]货币，剩下的钱用arr[0..i-1]货币时，方法数为dp[i-1][j-2*arr[i]]
     * 4.如果用3张arr[i]货币，剩下的钱用arr[0..i-1]货币时，方法数为dp[i-1][j-3*arr[i]]
     * ...
     *
     * 求每个位置都需要枚举，时间复杂度为O(aim)。dp一共有N*aim个位置，所以总体的时间复杂度为O(N*aim^2)
     *
     *
     *
     * 记忆搜索方法和动态规划方法的联系：
     * 1.记忆化搜索方法就是某种形态的动态规划方法；
     * 2.记忆化搜索方法不关心到达某一个递归过程的路径，只是单纯地对计算过的递归过程进行记录，避免重复计算；
     * 3.动态规划的方法则是规定好每一个递归过程的计算顺序，依次进行计算，后面的计算过程严格依赖前面的计算过程。
     * 4.两者都是空间换时间的方法，也都有枚举的过程没区别就在于动态规划规定了计算顺序，而记忆搜索不用规定。
     *
     * 什么是动态规划方法？
     * 1.其本质是利用申请的空间来记录每一个暴力搜索的计算结果，下次要用结果的时候直接使用，而不再进行重复的递归过程。
     * 2.动态规划规定每一种递归状态的计算顺序，依次进行计算。
     *
     * 总结：虽然动态规划方法和记忆搜索方法本质上是相同的，但是由于动态规划规定了计算过程，所以使状态继续优化得以实现。
     * 面试中遇到的暴力递归题目可以优化为动态规划方法的过程：
     * 1.实现暴力递归方法
     * 2.在暴力搜索方法的函数中看看哪些参数可以代表递归过程
     * 3.找到代表递归过程的参数以后，记忆化搜索的方法非常容易实现
     * 4.通过分析记忆化搜索的依赖路径，进而实现动态规划
     * 5.根据记忆化搜索方法改出动态规划方法，进而看看能不能化简
     *
     * 动态规划方法的关键点：
     * 1.最优化原理：也就是最优子结构性质。这指的是一个最优化策略具有这样的性质，不论过去状态和决策如何，对
     * 前面的决策所形成的状态而言，余下的诸决策必须构成最优策略。简单来说就是一个最优化策略的子策略总是最优的，如果
     * 一个问题满足最优化原理，就称其具有最优子结构性质。
     * 2.无后效性：指的是某状态下决策的收益，只与状态和决策相关，与到达该状态的方式无关。
     * 3.子问题的重叠性：将原来具有指数级时间负责度的暴力搜搜算法改进成了具有多项式时间复杂度的算法。关键在于解决冗余。
     *
     * @param arr
     * @param aim
     * @return
     */
    public int coins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim + 1];
        return process3(arr,0,aim,map);
    }
    public int process3(int[] arr,int index,int aim,int[][] map) {
         return 1;
    }




}
