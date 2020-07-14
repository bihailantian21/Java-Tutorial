package com.zcr.a_offer.c_array;

/**
 * 54.表示数字的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class isNumeric54 {

    /**
     * 模拟题，比较好的方式是用三个bool变量sign、dot、E记录前面是否已经出现过+/-、.、E/e，然后就去考虑一些情况:
     * 首先最后一个字符不能是E、e、.、+、e；
     * 当str[i] == E/e时，先判断之前有没有出现过E/e，且E前面只能是数字；
     * 当str[i] == +/-时，如果是前面以及出现了+/-，则这个+/-只能在E/e之后。如果第一次出现+/-，则必须出现在开头或者在E/e之后；
     * 当str[i] == '.'时，判断.只能出现一次，且.不能出现在E之后；
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        int len = str.length;
        if (str[len - 1] == 'E' || str[len - 1] == 'e' || str[len - 1] == '.' || str[len - 1] == '+' || str[len - 1] == '-') {
            return false;
        }
        boolean sign = false;
        boolean dot = false;
        boolean E = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'e' || str[i] == 'E') {
                if (E) return false; // 只能出现一个E
                if (i == str.length - 1) return false; // E后面一定要有东西
                if (i > 0 && (str[i - 1] == '+' || str[i - 1] == '-' || str[i - 1] == '.')) {
                    return false; //E 前面是数字
                }
                E = true;
            } else if (str[i] == '-' || str[i] == '+') {
                // 第二次出现+- 必须在e之后
                if (sign && str[i - 1] != 'e' && str[i - 1] != 'E') return false; // 第二个符号必须在E的后面
                // 第一次出现+-符号，且不是在字符串开头，则也必须紧接在e之后
                if (!sign && i > 0 && str[i - 1] != 'e' && str[i - 1] != 'E') return false;
                sign = true;
            } else if (str[i] == '.') { // dot
                if (E || dot) return false; // E后面不能有小数点, 小数点不能出现两次 例如: 12e+4.3
                dot = true;
            } else if (str[i] < '0' || str[i] > '9') {
                return false;
            }
        }
        return true;
    }
}
