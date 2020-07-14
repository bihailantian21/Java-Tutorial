package com.zcr.b_leetcode;

/**
 * 79. Word Search
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */

/**
 * 79、单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 */
public class WordSearch79 {

    /**
     * 二维数组
     * 给定string 能不能由二维数组中相邻的字符组成
     * 相邻：方向是可变的，不一定要是一条直线的相邻
     * 递归
     * 1、选其中任意一个位置：
     * 它是不是等于字符串的第一个，如果不想等，移动到下一位；如果相等，都要往上下左右四个方向都遍历一遍，看一下有和字符串第二个相等的吗
     * 2、对于类似ABA的字符串，怎样避免它走回头路呢？通常情况下，对这种二维数组，我们会建立一个同样size的boolean数组，如果这个位置已经被访问过了，就置为true。
     * 3、变量：二维数组、boolean数组、字符串、当前字符串下标、行、列
     * 对二维数组中每一个位置都检查，直到返回true。
     * 4、结束条件：下标超过数组了则成果；如果监测位置超出范围了，则失败
     * 如果当前的位置已经被用过了，或者当前位置的字符和字符不相同，则失败。
     * 5、否则则说明找对了，将当前位置置为true，说明这个位置我们呢访问过了。
     * 6、接下来就是向上下左右找，每个方向找完后，如果找到了，就直接返回，不需要到其他方向继续找了。
     * 7、每次都是下标+1、然后位置变化一下
     * 8、如果四个位置都没有找到，做回溯处理，将此位置置为false。
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int len1 = board.length;
        int len2 = board[0].length;
        if (board == null || len1 == 0 || len2 == 0) {
            return false;
        }
        boolean res = false;
        boolean[][] used = new boolean[len1][len2];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                res = helper(board,word,used,0,i,j);
                if (res) {
                    return res;
                }
            }
        }
        return res;
    }

    public boolean helper(char[][] board, String word,boolean[][] used,int idx,int row,int col) {
        if (idx == word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        if (used[row][col] == true || board[row][col] != word.charAt(idx)) {
            return false;
        }
        used[row][col] = true;
        boolean upexit = helper(board,word,used,idx + 1,row + 1,col);
        if (upexit) {
            return true;
        }
        boolean downexit = helper(board,word,used,idx + 1,row - 1,col);
        if (downexit) {
            return true;
        }
        boolean leftexit = helper(board,word,used,idx + 1,row,col - 1);
        if (leftexit) {
            return true;
        }
        boolean rightexit = helper(board,word,used,idx + 1,row,col + 1);
        if (rightexit) {
            return true;
        }
        used[row][col] = false;
        return false;
    }
}
