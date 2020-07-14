package com.zcr.b_leetcode;

public class ValidNumber65 {

    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int i = 0;
        int n = s.length();
        //检查开头的空格
        while (i < n && Character.isWhitespace(s.charAt(i))) {
            i++;
        }
        //检查正负号
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            i++;
        }
        //检查数字
        boolean isDigits = false;
        while (i < n && Character.isDigit(s.charAt(i))) {
            isDigits = true;
            i++;
        }
        //检查小数点
        if (i < n && s.charAt(i) == '.') {
            i++;
            //检查小数点后的数字
            while (i < n && Character.isDigit(s.charAt(i))) {
                isDigits = true;
                i++;
            }
        }
        //检查e
        if (i < n && s.charAt(i) == 'e' && isDigits) {
            i++;
            isDigits = false;
            //检查e之后的数字
            if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                i++;
            }
            while (i < n && Character.isDigit(s.charAt(i))) {
                isDigits = true;
                i++;
            }
        }
        while (i < n && Character.isWhitespace(s.charAt(i))) {
            i++;
        }
        return isDigits && i == n;

    }


        public boolean isNumber3(String s) {
        s = s.trim();

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }
        return numberSeen && numberAfterE;
    }

    /**
     * 如上图，从 0 开始总共有 9 个状态，橙色代表可接受状态，也就是表示此时是合法数字。
     * 总共有四大类输入，数字，小数点，e 和 正负号。我们只需要将这个图实现就够了。
     * @param s
     * @return
     */
    public boolean isNumber2(String s) {
        int state = 0;
        s = s.trim();//去除头尾的空格
        //遍历所有字符，当做输入
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                //输入正负号
                case '+':
                case '-':
                    if (state == 0) {
                        state = 1;
                    } else if (state == 4) {
                        state = 6;
                    } else {
                        return false;
                    }
                    break;
                //输入数字
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    //根据当前状态去跳转
                    switch (state) {
                        case 0:
                        case 1:
                        case 2:
                            state = 2;
                            break;
                        case 3:
                            state = 3;
                            break;
                        case 4:
                        case 5:
                        case 6:
                            state = 5;
                            break;
                        case 7:
                            state = 8;
                            break;
                        case 8:
                            state = 8;
                            break;
                        default:
                            return false;
                    }
                    break;
                //小数点
                case '.':
                    switch (state) {
                        case 0:
                        case 1:
                            state = 7;
                            break;
                        case 2:
                            state = 3;
                            break;
                        default:
                            return false;
                    }
                    break;
                //e
                case 'e':
                    switch (state) {
                        case 2:
                        case 3:
                        case 8:
                            state = 4;
                            break;
                        default:
                            return false;
                    }
                    break;
                default:
                    return false;

            }
        }
        //橙色部分的状态代表合法数字
        return state == 2 || state == 3 || state == 5 || state == 8;
    }
}
