package com.zcr.g_huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * 字符串合并处理
 * 题目描述
 * 按照指定规则对输入的字符串进行处理。
 *
 * 详细描述：
 *
 * 将输入的两个字符串合并。
 *
 * 对合并后的字符串进行排序，要求为：下标为奇数的字符和下标为偶数的字符分别从小到大排序。这里的下标意思是字符在字符串中的位置。
 *
 * 对排序后的字符串进行操作，如果字符为‘0’——‘9’或者‘A’——‘F’或者‘a’——‘f’，则对他们所代表的16进制的数进行BIT倒序的操作，并转换为相应的大写字符。
 * 如字符为‘4’，为0100b，则翻转后为0010b，也就是2。转换后的字符为‘2’；
 * 如字符为‘7’，为0111b，则翻转后为1110b，也就是e。转换后的字符为大写‘E’。
 *        A
 *        a
 *
 *
 * 举例：输入str1为"dec"，str2为"fab"，合并为“decfab”，分别对“dca”和“efb”进行排序，排序后为“abcedf”，转换后为“5D37BF”
 *
 * 接口设计及说明：
 *
 *
 *
 *
 *
 *
 * 功能:字符串处理
 *
 * 输入:两个字符串,需要异常处理
 *
 * 输出:合并处理后的字符串，具体要求参考文档
 *
 * 返回:无
 *void ProcessString(char* str1,char *str2,char * strOutput)
 *
 * {
 *
 * }
 *输入描述:
 * 输入两个字符串
 *
 * 输出描述:
 * 输出转化后的结果
 *
 * 示例1
 * 输入
 * 复制
 * dec fab
 * 输出
 * 复制
 * 5D37BF
 *
 *
 *
 */
public class mergeString30 {

    public static <Char> void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            //读取string类型的数据
            String str = scanner.nextLine();
            String[] strArr = str.split(" ");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(strArr[0]);
            stringBuilder.append(strArr[1]);
            String strop = stringBuilder.toString();

            //将奇数和偶数下标的数分别存到两个字符数组中，分别进行排序
            StringBuilder stringBuilder1 = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();
            for (int i = 0; i < strop.length(); i+=2) {
                stringBuilder1.append(strop.charAt(i));
                stringBuilder1.append(strop.charAt(i + 1));
            }
            char[] c1 = stringBuilder1.toString().toCharArray();
            Arrays.sort(c1);
            char[] c2 = stringBuilder2.toString().toCharArray();
            Arrays.sort(c2);

            //之后再把排序后的奇数偶数重新放到char型数组中(之前我是采用遍历c1和c2的方法，这样不好，因为还得判断哪个长，最后把长的那个剩余的字符都加上)
            char[] c = new char[strop.length()];
            for (int i = 0; i < c.length; i+=2) {
                c[i] = c1[i];
                c[i + 1] = c2[i];
            }

            //遍历char数组，对每一个字符进行转换
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < c.length; i++) {
                char re = c[i];
                if ((re >= '0' && re <= '9') || (re >= 'a' && re <= 'f') || (re >= 'A' && re <= 'F') ) {
                    result.append(processChar(re));
                } else {
                    result.append(re);
                }
            }
            System.out.println(result.toString());
        }
    }

    //将一个字符转换成十六进制，然后调用函数对十六进制进行处理
    private static char processChar(char re) {
        int num = 0;
        if (re >= '0' && re <= '9') {
            num = Integer.parseInt(re + "");
        } else if (re >= 'a' && re <= 'f') {
            num = re - 87;
        } else {
            num = re - 55;
        }
        return getReverseResult(num);
    }

    //对十六进制数进行处理，先调用函数转换成二进制的数，然后进行反转
    private static char getReverseResult(int num) {
        String nums = convertBinaryString(num);
        int res = Integer.parseInt(nums,2);
        if (res >= 0 && res <= 9) {
            return (res +"").charAt(0);
        } else {
            return (char) (res + 55);
        }
    }

    //将十六进制数转换为4位的二进制数，然后反转
    private static String convertBinaryString(int num) {
        int k = 1 << 3;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int flag = ((num & k) == 0 ? 0 : 1);
            sb.append(flag);
            num = num << 1;
        }
        return sb.reverse().toString();
    }
}


//代码较多，但是思路比较清晰，一步步的来！
class Main2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.next();
            str += sc.next();
            System.out.println(processString(str));
        }
    }
    private static String processString(String str){
        if(str==null||str.length()==0){
            return "";
        }
        int n = str.length();
        ArrayList<Character> ji = new ArrayList<>();
        ArrayList<Character> ou = new ArrayList<>();
        for(int i=0;i<n;i++){//奇数和偶数分开存到集合中
            if(i%2==0){
                ou.add(str.charAt(i));
            }else{
                ji.add(str.charAt(i));
            }
        }
        //排序
        Collections.sort(ji);
        Collections.sort(ou);
        char[] chs = new char[n];
        int ouIndex = 0,jiIndex = 0;
        for(int i=0;i<n;i++){//之后再把排序后的奇数偶数重新放到char型数组中
            if(i%2==0){
                chs[i] = ou.get(ouIndex++);
            }else{
                chs[i] = ji.get(jiIndex++);
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<n;i++){//依次把char型数组中的字符经过处理后就加入到stringbuffer中
            char ch = chs[i];
            if((ch>='0'&&ch<='9')||(ch>='a'&&ch<='f')||(ch>='A'&&ch<='F')){
                sb.append(processChar(ch));
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    private static char processChar(char c){//处理这些特殊字符，返回char型字符
        int num = 0;//num代表该字符所代表的十六进制数字
        if(c>='0'&&c<='9'){
            num = Integer.parseInt(c+"");
        }else if(c>='a'&&c<='f'){
            num = c-87;
        }else {
            num = c-55;
        }
        return getReverseResult(num);//也就是对该十六进制数字进行处理
    }
    private static char getReverseResult(int num){//对该十六进制数字进行处理
        String nums = reverseBinaryString(num);//对该数字进行转化为4位二进制数，然后反转。
        int res = Integer.parseInt(nums,2);//之后再对返回后的二进制字符串转换为十进制数字
        if(res>=0&&res<=9){//对十进制数字分两种情况转换为十六进制字符
            return (res+"").charAt(0);
        }else{
            return (char)(res+55);
        }
    }
    //对该数字进行转化为4位二进制数，然后反转，返回反转后的字符串
    private static String reverseBinaryString(int num){
        int k = 1<<3;
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<4;i++){
            int flag = ((num&k)==0?0:1);
            sb.append(flag);
            num=num<<1;
        }
        return sb.reverse().toString();
    }
}


class Main3{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str_1 = sc.next();
            String str_2 = sc.next();
            Dispose(str_1,str_2);
        }

    }

    public static void Dispose(String str_1,String str_2){
        //组合成新字符串
        String str = str_1+str_2;
        //新字符串长度
        int len = str.length();
        //偶
        StringBuilder sb_events = new StringBuilder();
        //奇
        StringBuilder sb_odds = new StringBuilder();
        for(int i=0;i<len;i++){
            if(i%2==0){
                sb_events.append(str.charAt(i));
            }else{
                sb_odds.append(str.charAt(i));
            }
        }

        //排序
        char[] ch_events = sb_events.toString().toCharArray();
        char[] ch_odds = sb_odds.toString().toCharArray();
        Arrays.sort(ch_events);
        Arrays.sort(ch_odds);
        //创建新字符串
        StringBuilder sb_new = new StringBuilder();
        //索引
        int events = 0;
        int odds = 0;
        for(int i=0;i<len;i++){
            if(i%2==0){
                //将处理后的放入偶数位
                sb_new.append(Change(ch_events[events]));
                events++;
            }else{
                //放入奇数位
                sb_new.append(Change(ch_odds[odds]));
                odds++;
            }
        }
        System.out.println(sb_new.toString());
    }
    //变换处理
    public static char Change(char c){
        String c_str1 = "";
        String c_str2 = "";
        if(c>='0'&& c<='9'){
            //转换成二进制表示的字符串
            c_str1 = Integer.toBinaryString(c);
            int le = c_str1.length();
            //二进制字符串不够4位，前面添加0补位
            while(le<4){
                c_str1 = "0" + c_str1;
                le ++;
            }
            //反转，利用StringBuffer类的reverse方法，或StringBuilder类的reverse方法
            c_str2 = new StringBuffer(c_str1).reverse().toString();
        }else if((c>='A' && c<= 'F') || (c>='a' && c<='f')){
            char cc = Character.toLowerCase(c);
            int c_int = 0;
            switch (cc){
                case 'a':c_int = 10;
                    break;
                case 'b':c_int = 11;
                    break;
                case 'c':c_int = 12;
                    break;
                case 'd':c_int = 13;
                    break;
                case 'e':c_int = 14;
                    break;
                case 'f':c_int = 15;
                    break;
            }
            c_str1 = Integer.toBinaryString(c_int);
            c_str2 = new StringBuffer(c_str1).reverse().toString();

        }else {//其他字符，直接返回
            return c;
        }

        int sum = 0;
        if(c_str2 != null){
            for(int i = 0;i<c_str2.length();i++){
                char tem = c_str2.charAt(i);
                //字符1的ASCII码转换成十进制数是49，如果int型比较，则条件应为
                // if(tem == 49);
                //如果用字符比较，则条件为 if(tem == '1')
                if(tem == '1'){
                    sum += (int)Math.pow(2,(3-i));
                }
            }
            if(sum >=0 && sum <=9){
                //sum 整数型的0~9转换成字符型的'0'~'9'；
                //如果直接（char）sum，则是按照用sum表示的ASCII码转换成字符
                return (char)(sum+'0');
            }else{
                switch (sum){
                    case 10:return 'A';
                    case 11:return 'B';
                    case 12:return 'C';
                    case 13:return 'D';
                    case 14:return 'E';
                    case 15:return 'F';
                    default:return (Character)null;
                }
            }
        }else {
            return (Character)null;
        }

    }
}