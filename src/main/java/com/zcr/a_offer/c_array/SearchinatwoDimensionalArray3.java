package com.zcr.a_offer.c_array;

/**
 * 3、二维数组中的查找
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
 * 判断数组中是否含有该整数。
 *
 * Consider the following matrix:
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 */
public class SearchinatwoDimensionalArray3 {

    /**
     * 这个题目比较好的解题思路是从右上角或者左下角开始找；这个是题目给定的每一行从左到右递增和每一列从上到下递增的原因；
     * 例如，从右上角开始找，设置两个变量row，col分别代表行坐标和列坐标，
     * 如果要找的数就是target，则直接返回；
     * 如果该数字大于要查找的数字arr[row][col] > target，则剔除这个数字所在的列，那 col = col - 1，因为它下面的都会arr[row][col]大，这是因为行增加的性质；
     * 如果该数字小于要查找的数字如果arr[row][col] < target，则剔除这个数字所在的行，那 row = row + 1，因为它左边的都会arr[row][col]小，这是因为列增加的性质；
     * 总结：也就是说，如果要查找的数字不在数组的右上角，则每次都在数组的查找范围内剔除一行或者一列，这样每一步都可以缩小查找的范围，直到找到要查找的数字，或者查找的范围为空
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int [][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        /*for (int i = 0; i < array.length; i++) {
            for (int j = array[0].length - 1; j >= 0; j--) {
                if (array[i][j] == target) {
                    return true;
                } else if(array[i][j] > target) {
                    j--;
                } else {
                    i++;
                }
            }
        }*/
        //教训：这里不确定每次都让i++、j--，所以不能用for，而应该使用false
        int len1 = array.length;
        int len2 = array[0].length;
        int row = 0;
        int col = len2 - 1;
        while (row < len1 && col >= 0) {
            if (array[row][col] == target) {
                return true;
            } else if(array[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
