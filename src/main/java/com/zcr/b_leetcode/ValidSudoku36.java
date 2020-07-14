package com.zcr.b_leetcode;

/**
 * 36. Valid Sudoku
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * A partially filled sudoku which is valid.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 *
 * Example 1:
 * Input:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 *
 * Example 2:
 * Input:
 * [
 *   ["8","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 *     modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 * Note:
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 */

/**
 * 36、有效的数独
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 上图是一个部分填充的有效的数独。
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 * 示例 1:
 * 输入:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: true
 *
 * 示例 2:
 * 输入:
 * [
 *   ["8","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: false
 *
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 *      但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 * 说明:
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 */
public class ValidSudoku36 {

    /**
     * 一行一行的看、一列一列的看、3*3子矩阵的看，看有没有重复的数字
     * 如果这一行出现数字1，taken[0]=true;…如果这一行出现数字9，taken[8]=true;
     * 所以，检查某一行的时候，就一个一个的看。则如果到一个数，发现这个数对应的位置上已经置为了true，说明出现了重复，直接返回false。
     * 然后检查列。
     * 然后检查3*3的子矩阵。
     * 如果所有的查找都没有发现无效数字的存在，那么我们呢就返回true。
     *
     * 1、	检查所有的行中：
     * 第一层for循环：行数
     * 每一行都有一个boolean数组
     * 第二层for循环：第一行所有的格
     * 2、	检查所有的列
     * 3、	检查所有的3*3子矩阵
     * 第一层for循环：一共有9格子矩阵
     * 第二层for循环：
     * 第三层for循环
     * 坐标：根据目前是哪一个格子、哪一行、哪一列计算出数字位置
     *
     *   (box/3)*3  (box%3)*3   row+ col+
     *                 (0,0)(0,1)(0,2)(1,0)(1,1)(1,2))(2,0)(2,1)(2,2)
     * 0  0          0 (0,0)(0,1)(0,2)(1,0)(1,1)(1,2))(2,0)(2,1)(2,2)
     * 第一个格子行不变，列不变
     * 1  0			3 (0,3)(0,4)
     * 第二个格子行不变，列整体向右移3格
     * 2  0			6
     * 第三个格子行不变，列整体向右移6格
     * 3  3			0
     * 第四个格子行整体向下移3格，列不变，
     * 4  3			3
     * 第五个格子行整体向右移3格，列整体向下移3格
     * 5	3			6
     * 第六个格子行整体向下移3格，列整体向下移6格
     * 6	6			0
     * 第七个格子行整体向下移6格，列不变
     * 7	6			3
     * 第八个格子行整体向下移6格，列整体向下移3格
     * 8	6			6
     * 第九个格子行整体向下移6格，列整体向下移6格
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }
        for (int row = 0; row < 9; row++) {
            boolean[] taken =new boolean[9];
            for (int idx = 0; idx < 9; idx++) {
                char c = board[row][idx];
                if (c != '.') {
                    int num = c - '1';
                    if (taken[num] == true) {
                        return false;
                    } else {
                        taken[num] = true;
                    }
                }
            }
        }

        for (int col = 0; col < 9; col++) {
            boolean[] taken =new boolean[9];
            for (int idx = 0; idx < 9; idx++) {
                char c = board[idx][col];
                if (c != '.') {
                    int num = c - '1';
                    if (taken[num] == true) {
                        return false;
                    } else {
                        taken[num] = true;
                    }
                }
            }
        }

        for (int box = 0; box < 9; box++) {
            boolean[] taken =new boolean[9];
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    char c = board[row + (box / 3) * 3][col + (box % 3) * 3];
                    if (c != '.') {
                        int num = c - '1';
                        if (taken[num] == true) {
                            return false;
                        } else {
                            taken[num] = true;
                        }
                    }
                }
            }
        }
        return true;
    }


}
