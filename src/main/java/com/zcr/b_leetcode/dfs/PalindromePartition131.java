package com.zcr.b_leetcode.dfs;

import java.util.ArrayList;
import java.util.List;


/**
 * 131. 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class PalindromePartition131 {

    /**
     * 例：abc     a1b2c
     * a b c [1,2]
     * a bc[1]
     * ab c[2]
     * abc[]
     * 三个字母的切割问题，就是两个数字的组合问题
     * n                    n-1
     * 记录上一刀切在哪，那么下一刀就得在下一个位置切
     *
     *
     *
     *
     * 搜索问题主要使用回溯法。
     *
     * 回溯法思考的步骤：
     *
     * 1、画递归树；
     *
     * 2、根据自己画的递归树编码。
     *
     *
     *
     * 思考如何根据这棵递归树编码：
     * 1、每一个结点表示剩余没有扫描到的字符串，产生分支是截取了剩余字符串的前缀；
     * 2、产生前缀字符串的时候，判断前缀字符串是否是回文。
     * 如果前缀字符串是回文，则可以产生分支和结点；
     * 如果前缀字符串不是回文，则不产生分支和结点，这一步是剪枝操作。
     * 3、在叶子结点是空字符串的时候结算，此时从根结点到叶子结点的路径，
     * 就是结果集里的一个结果，使用深度优先遍历，记录下所有可能的结果。
     * 采用一个路径变量 path 搜索，path 全局使用一个（注意结算的时候，需要生成一个拷贝），
     * 因此在递归执行方法结束以后需要回溯，即将递归之前添加进来的元素拿出去；
     * path 的操作只在列表的末端，因此合适的数据结构是栈。
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        List<String> cur = new ArrayList<>();
        helper(s,0,result,cur);
        return result;
    }

    public void helper(String s, int curIdx,List<List<String>> result,List<String> cur ) {
        if (curIdx == s.length()) {
            result.add(new ArrayList<>(cur));
        } else {
            for (int i = curIdx; i < s.length(); i++) {
                String subString = s.substring(curIdx,i+1);
                if (!isPalindrome(subString)) {
                    continue;
                }
                cur.add(subString);
                helper(s,i + 1,result,cur);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }


    //优化：每次都需要判断是否回文串
    /**
     * 在上一步，验证回文串那里，每一次都得使用“两边夹”的方式验证子串是否是回文子串。于是“用空间换时间”，
     * 利用「力扣」第 5 题：最长回文子串 的思路，利用动态规划把结果先算出来，
     * 这样就可以以 O(1)的时间复杂度直接得到一个子串是否是回文。
     *
     *
     */
    public List<List<String>> partition2(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }

        int len = s.length();
        // 预处理
        // 状态：dp[i][j] 表示 s[i][j] 是否是回文
        boolean[][] dp = new boolean[len][len];
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        for (int right = 0; right < len; right++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
        }



        List<String> cur = new ArrayList<>();
        helper2(s,0,result,cur,dp);
        return result;
    }

    public void helper2(String s, int curIdx,List<List<String>> result,List<String> cur,boolean[][] dp ) {
        if (curIdx == s.length()) {
            result.add(new ArrayList<>(cur));
        } else {
            for (int i = curIdx; i < s.length(); i++) {
                String subString = s.substring(curIdx,i+1);
                if (!dp[curIdx][i+1]) {
                    continue;
                }
                cur.add(subString);
                helper(s,i + 1,result,cur);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
