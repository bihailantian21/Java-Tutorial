package com.zcr.a_offer.c_array;

/**
 * 66.矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
 * 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
 * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 */
public class hasPath66 {

    /**
     * 比较简单的dfs。
     *
     * 注意边界判断if (cur == str.length-1 && matrix[x * c + y] == str[cur]) return true;。不要判断cur == str.length。。
     * 深度优先算法
     * 首先对所整个矩阵遍历，找到第一个字符，然后向上下左右查找下一个字符，
     * 由于每个字符都是相同的判断方法（先判断当前字符是否相等，再向四周查找），因此采用递归函数。
     *
     * 由于字符查找过后不能重复进入，所以还要定义一个与字符矩阵大小相同的布尔值矩阵，进入过的格子标记为true。
     *
     * 如果不满足的情况下，需要进行回溯，此时，要将当前位置的布尔值标记回false。（所谓的回溯无非就是对使用过的字符进行标记和处理后的去标记）
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @return
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || rows < 1 || cols < 1 || str == null) {
            return false;
        }
        boolean[] isVisited = new boolean[rows * cols];
        for (boolean v : isVisited) {
            v = false;
        }
        int pathLength = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (hasPathCore(matrix, rows, cols, row, col, str, pathLength, isVisited))
                    return true;
            }
        }
        return false;
    }

    private boolean hasPathCore(char[] matrix, int rows, int cols, int row, int col, char[] str, int pathLength,
                                boolean[] isVisited) {
        if (row < 0 || col < 0 || row >= rows || col >= cols || isVisited[row * cols + col] == true
                || str[pathLength] != matrix[row * cols + col])
            return false;
        if (pathLength == str.length - 1)
            return true;
        boolean hasPath = false;
        isVisited[row * cols + col] = true;
        hasPath = hasPathCore(matrix, rows, cols, row - 1, col, str, pathLength + 1, isVisited)
                || hasPathCore(matrix, rows, cols, row + 1, col, str, pathLength + 1, isVisited)
                || hasPathCore(matrix, rows, cols, row, col - 1, str, pathLength + 1, isVisited)
                || hasPathCore(matrix, rows, cols, row, col + 1, str, pathLength + 1, isVisited);

        if (!hasPath) {
            isVisited[row * cols + col] = false;
        }
        return hasPath;
    }
}
