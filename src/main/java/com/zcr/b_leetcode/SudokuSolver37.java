package com.zcr.b_leetcode;

/**
 * 37. Sudoku Solver
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * A sudoku solution must satisfy all of the following rules:
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 * Note:
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 */

import java.util.Arrays;

/**
 * 37、解数独
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * 一个数独的解法需遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * Note:
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */
public class SudokuSolver37 {

    /**
     * 数独：每一行9个格必须包含1~9的9个数字、每一列9个格必须包含1~9的9个数字、每一个3*3的矩阵必须包含1~9的9个数字
     *
     * 递归：
     * 都是看当前的行列有什么
     * 一行一行的扫描，遇到需要求的位置，看这个位置可以填写的数字是什么
     * 第一个位置填写了1->第二个位置可以填2、3
     * 先看2是不是有效的解，然后一直填到最后一个位置，看能不能组成有效解，不能的话，回到前一个位置，换成别的可以的数。
     *
     * 怎么写递归函数：
     * 函数的入口：
     * 第一步：找出需要填字的位置target location
     * 第二步：这个位置可以填写的数字
     * 1~9个数字，是否这一行这一列这一3*3矩阵中中已经存在这个数字了，存在则无效，不存在则有效
     * 这个检查是否有效的方法会返回true、false
     * 第三步：返回结果
     *
     * 1、首先找到第一个需要填数的位置；
     * 2、如果已经到界外，到最下面一行的下一行了，说明我们已经找到了有效解；
     * 3、下一个我们要计算的位置；
     * 4、1~9中哪个数合适填在我们当前的位置：
     * 如果有效的话，先把她填进去，然后再调用helper
     * 针对下一个我们要计算的位置，（也就是说：当前数填好以后，下一个数是否能够找到合适的解），如果后面找到一组有效解就返回true，否则
     * 做回溯，将当前位置重新置为’.’，然后for循环进行++。
     * 如果找了所有的number。都没有有效解，就返回false。但其实false是达不到的，一定会有一个有效解。
     * @param board
     */
    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return;
        }
        boolean tmp = helper(board,0,0);
    }

    public boolean helper(char[][] board,int row,int col) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }
        /*for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '.') {
                    break;
                }
            }
        }*/
        while (row < 9 && col < 9) {
            if (board[row][col] == '.') {
                break;
            }
            if (col == 8) {
                col = 0;
                row++;
            } else {
                col++;
            }
        }
        if (row >= 9) {
            return true;
        }
        int newRow = row + col / 8;//新行数：当列数是8的时候才将行数++，否则不变
        int newCol = (col + 1) % 9;//新列数：一直++，当遇到列数是8的时候，变为1
        for (int num = 1; num <= 9; num++) {
            if (isValid(board,row,col,num)) {
                board[row][col] = (char)(num + '0');
                boolean result = helper(board,newRow,newCol);
                if (result) {
                    return true;
                }
                board[row][col] = '.';
            }
        }
        return false;

    }

    /**
     * 检查特定位置的这一个数是否有效
     * 1、
     * 2、
     *
     *      这里是已知第n个格子，让你求行数、列数。
     *      *   (box/3)*3  (box%3)*3   row+(box/3)*3     col+(box%3)*3
     *      *                 (0,0)(0,1)(0,2)(1,0)(1,1)(1,2))(2,0)(2,1)(2,2)
     *      * 0  0          0 (0,0)(0,1)(0,2)(1,0)(1,1)(1,2))(2,0)(2,1)(2,2)
     *      * 第一个格子行不变，列不变
     *      * 1  0			3 (0,3)(0,4)
     *      * 第二个格子行不变，列整体向右移3格
     *      * 2  0			6
     *      * 第三个格子行不变，列整体向右移6格
     *      * 3  3			0
     *      * 第四个格子行整体向下移3格，列不变，
     *      * 4  3			3
     *      * 第五个格子行整体向右移3格，列整体向下移3格
     *      * 5	3			6
     *      * 第六个格子行整体向下移3格，列整体向下移6格
     *      * 6	6			0
     *      * 第七个格子行整体向下移6格，列不变
     *      * 7	6			3
     *      * 第八个格子行整体向下移6格，列整体向下移3格
     *      * 8	6			6
     *      * 第九个格子行整体向下移6格，列整体向下移6格
     *
     *      这里不知道是第几个格子，只知道行数、列数，要寻找第n个格子。
     *      (row / 3) * 3 + i       (col / 3) * 3 + j
     *      第一个格子： (0,0)(0,1)(0,2)(1,0)(1,1)(1,2))(2,0)(2,1)(2,2)
     *      第二个格子：
     *      第三个格子：
     *      第四个格子：
     *      第五个格子：
     *      第六个格子：
     *      第七个格子：
     *      第八个格子：
     *      第九个格子：
     *
     * @param board
     * @param row
     * @param col
     * @param num
     * @return
     */
    public boolean isValid(char[][]  board,int row,int col,int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num + '0' || board[i][col] == num + '0') {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[(row / 3) * 3 + i][(col / 3) * 3 + j] == num + '0') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        SudokuSolver37 sudokuSolver37 = new SudokuSolver37();
        sudokuSolver37.solveSudoku(board);
        System.out.println(Arrays.toString(board[0]));
        System.out.println(Arrays.toString(board[1]));
        System.out.println(Arrays.toString(board[2]));
        System.out.println(Arrays.toString(board[3]));
        System.out.println(Arrays.toString(board[4]));
        System.out.println(Arrays.toString(board[5]));
        System.out.println(Arrays.toString(board[6]));
        System.out.println(Arrays.toString(board[7]));
        System.out.println(Arrays.toString(board[8]));
    }
}


class Solution {
    // box size
    int n = 3;
    // row size
    int N = n * n;

    int [][] rows = new int[N][N + 1];
    int [][] columns = new int[N][N + 1];
    int [][] boxes = new int[N][N + 1];

    char[][] board;

    boolean sudokuSolved = false;

    public boolean couldPlace(int d, int row, int col) {
    /*
    Check if one could place a number d in (row, col) cell
    */
        int idx = (row / n ) * n + col / n;
        return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
    }

    public void placeNumber(int d, int row, int col) {
    /*
    Place a number d in (row, col) cell
    */
        int idx = (row / n ) * n + col / n;

        rows[row][d]++;
        columns[col][d]++;
        boxes[idx][d]++;
        board[row][col] = (char)(d + '0');
    }

    public void removeNumber(int d, int row, int col) {
    /*
    Remove a number which didn't lead to a solution
    */
        int idx = (row / n ) * n + col / n;
        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        board[row][col] = '.';
    }

    public void placeNextNumbers(int row, int col) {
    /*
    Call backtrack function in recursion
    to continue to place numbers
    till the moment we have a solution
    */
        // if we're in the last cell
        // that means we have the solution
        if ((col == N - 1) && (row == N - 1)) {
            sudokuSolved = true;
        }
        // if not yet
        else {
            // if we're in the end of the row
            // go to the next row
            if (col == N - 1) backtrack(row + 1, 0);
                // go to the next column
            else backtrack(row, col + 1);
        }
    }

    /**
     * 现在准备好写回溯函数了
     * backtrack(row = 0, col = 0)。
     * 从最左上角的方格开始 row = 0, col = 0。直到到达一个空方格。
     * 从1 到 9 迭代循环数组，尝试放置数字 d 进入 (row, col) 的格子。
     * 如果数字 d 还没有出现在当前行，列和子方块中：
     * 将 d 放入 (row, col) 格子中。
     * 记录下 d 已经出现在当前行，列和子方块中。
     * 如果这是最后一个格子row == 8, col == 8 ：意味着已经找出了数独的解。
     * 否则,放置接下来的数字。
     * 如果数独的解还没找到：将最后的数从 (row, col) 移除。
     * @param row
     * @param col
     */
    public void backtrack(int row, int col) {
    /*
    Backtracking
    */
        // if the cell is empty
        if (board[row][col] == '.') {
            // iterate over all numbers from 1 to 9
            for (int d = 1; d < 10; d++) {
                if (couldPlace(d, row, col)) {
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col);
                    // if sudoku is solved, there is no need to backtrack
                    // since the single unique solution is promised
                    if (!sudokuSolved) removeNumber(d, row, col);
                }
            }
        }
        else placeNextNumbers(row, col);
    }

    public void solveSudoku(char[][] board) {
        this.board = board;

        // init rows, columns and boxes
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int d = Character.getNumericValue(num);
                    placeNumber(d, i, j);
                }
            }
        }
        backtrack(0, 0);
    }

    public static void main(String[] args) {
        char[][] board;
        board = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        Solution solution = new Solution();
        solution.solveSudoku(board);
        System.out.println(Arrays.toString(board[0]));
        System.out.println(Arrays.toString(board[1]));
        System.out.println(Arrays.toString(board[2]));
        System.out.println(Arrays.toString(board[3]));
        System.out.println(Arrays.toString(board[4]));
        System.out.println(Arrays.toString(board[5]));
        System.out.println(Arrays.toString(board[6]));
        System.out.println(Arrays.toString(board[7]));
        System.out.println(Arrays.toString(board[8]));

    }
}

