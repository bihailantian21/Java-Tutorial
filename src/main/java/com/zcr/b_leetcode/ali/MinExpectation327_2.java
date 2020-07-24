package com.zcr.b_leetcode.ali;


/**
 *
 * 给定n(n <= 2000)个区间[L,R](1 <= L <= R <= 2000)，从这n个区间中分别等概率取一个整数，一共n个数，求这些数最小值的期望。
 * 思路：求出每个数作为最小值的概率即可，数x为最小值的概率为（所有数大等于x的概率 - 所有数大等于x + 1的概率）
 *
 * 期望：
 * 可能考80分、90分、100分
 *     0.5    0.3   0.2
 *     80*0.5+90*0.3+100*0.2=
 *
 *
 * 最小期望：
 */
public class MinExpectation327_2 {
}