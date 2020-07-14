package com.zcr.b_leetcode;

public class RomanToInteger13 {

    public int romanToInt(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        int result = 0;
        if (input.indexOf("CM") != -1) result -= 200;
        if (input.indexOf("CD") != -1) result -= 200;
        if (input.indexOf("XC") != -1) result -= 20;
        if (input.indexOf("XL") != -1) result -= 20;
        if (input.indexOf("IX") != -1) result -= 2;
        if (input.indexOf("IV") != -1) result -= 2;
        for (char c : input.toCharArray()) {
            if (c == 'M') result += 1000;
            else if (c == 'D') result += 500;
            else if (c == 'C') result += 100;
            else if (c == 'L') result += 50;
            else if (c == 'X') result += 10;
            else if (c == 'V') result += 5;
            else if (c == 'I') result += 1;
        }
        return result;
    }

    public static void main(String[] args) {
        String input = "";
    }
}
