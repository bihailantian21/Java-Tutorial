package com.zcr.b_leetcode.ali;


/**
 *
 * 一个n行m列的矩阵，其中每行和每列都是等差数列，但是其中有一些数据缺失，用零表示。给定一个位置，得到该位置的数值，
 * （如果是缺失的，需要补全）
 *
 *
 *
 *
 * 输入n * m的数组，以及q次查询。
 * 例如 如下数组， 为0代表未知。
 * 数组的行和列都可以构成等差数列（忘了有没有等比的条件）
 * 1 0 3
 * 0 0 0
 *
 * q次查询为输入的数组，分别输入x,y，代表x行y列。例如当q = 4时。
 * 1 1
 * 1 2
 * 2 1
 * 2 3
 *
 * 输出：
 * q次查询的结果，有值输出值，值不确定输出Unknow
 *
 *
 *
 *
 * 给出一个二维矩阵，这个矩阵的每一行和每一列都是一个独立的等差数列，其中一些数据缺失了，现在需要推理隐藏但是可以被唯一确定的数字，然后对输入的查询进行回答。
 *
 * 输入描述：
 * 第一行，n,m,q分别表示矩阵的行数，列数和查询的条数。
 * 接下来的n行，每行m个数表示这个矩阵，0表示缺失数据。−10^9≤Aij≤10^9
 * 接下来q行，每行两个数字i,j表示对矩阵中第i行第j列的数字进行查询。
 *
 * 输出描述：
 * 如果可以确定该位置的数字，则输出数字，如果不能确定则输出UNKNOWN。
 *
 * 输入示例：
 * 2 3 6
 * 1 0 3
 * 0 0 0
 * 1 1
 * 1 2
 * 1 3
 * 2 1
 * 2 2
 * 2 3
 *
 * 输出示例：
 * 1
 * 2
 * 3
 * Unknown
 * Unknown
 * Unknown
 *
 *   这个题目有点变态，我没有想出什么好办法，提交的代码也有bug，结束后做一做这个题目的分析。
 * 根据题意，如果一个矩阵中可以确定两行或者两列就可以完全确定这个矩阵。
 * 如何确定两行或者两列呢，这两行和这两列必须有两个以上的数字。
 * 如果有两个以上的数字，则可以对这行或列求出公差，整行或列就可以确定。
 *   所以我觉得求出公差是比较关键的一步，我的代码直接奔着求出公差去了。
 * 一旦求出公差，则只需要保存该行或者列的一个数就可以确定整行整列。下面看我求出公差的代码。
 *   这里补充解释一下为什么我要求公差，因为求出来公差确定这行肯定是已知的，所以即便本来有元素就是0，那么也可以正确返回。
 * 但是如果不求出公差的话，检索到这个位置是0，无法判断是否是Unknown还是本来就是0。
 *
 * 求公差的python代码
 * n, m, q = [int(i) for i in input().split(' ')]
 * A = []
 * Q=[]
 * for i in range(n):
 *     A.append([int(i) for i in input().split(' ')])
 * for i in range(q):
 *     Q.append([int(i) for i in input().split(' ')])
 * row=[0]*n  # 求行的公差
 * col=[0]*m  # 求列的公差
 * numRow=[-1]*n # 求该行的一个数的索引
 * numCol=[-1]*m # 求该列的一个数的索引
 * for i in range(n):
 *     for j in range(m):
 *         if A[i][j]:
 *             p=j
 *             numRow[i]=j
 *             for j in range(j+1,m):
 *                 if A[i][j]:
 *                     row[i]=(A[i][j]-A[i][p])//(j-p)
 *                     for j in range(m):
 *                         if not A[i][j]:
 *                             A[i][j]=A[i][numRow[i]] + ((j - numRow[i]) * row[i])
 *                     break
 *             break
 * for i in range(m):
 *     for j in range(n):
 *         if A[j][i]:
 *             p = j
 *             numCol[i]=j
 *             for j in range(j + 1, n):
 *                 if A[j][i]:
 *                     col[i]=(A[j][i]-A[p][i])//(j-p)
 *                     for j in range(n):
 *                         if not A[j][i]:
 *                             A[j][i]=A[numCol[i]][i] + ((j - numCol[i]) * col[i])
 *                     break
 *             break
 *
 * # print(A)
 * for i,j in Q:
 *     i=i-1
 *     j=j-1
 *     if row[i] or col[j] or A[i][j]:
 *     	print(A[i][j])
 *     else:
 *     	print('Unknown')
 *
 *
 */
public class FillMatrix325_2 {

    /**
     * 第二题，将矩阵转化为图，dfs做
     */
}
