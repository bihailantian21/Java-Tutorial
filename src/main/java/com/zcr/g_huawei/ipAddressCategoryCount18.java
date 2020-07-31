package com.zcr.g_huawei;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 识别有效的IP地址和掩码并进行分类统计
 * 请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。
 *
 * 所有的IP地址划分为 A,B,C,D,E五类
 *
 * A类地址1.0.0.0~126.255.255.255;
 *
 * B类地址128.0.0.0~191.255.255.255;
 *
 * C类地址192.0.0.0~223.255.255.255;
 *
 * D类地址224.0.0.0~239.255.255.255；
 *
 * E类地址240.0.0.0~255.255.255.255
 *
 *
 * 私网IP范围是：
 *
 * 10.0.0.0～10.255.255.255
 *
 * 172.16.0.0～172.31.255.255
 *
 * 192.168.0.0～192.168.255.255
 *
 *
 * 子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）
 * 注意二进制下全是1或者全是0均为非法
 *
 * 注意：
 * 1. 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时可以忽略
 * 2. 私有IP地址和A,B,C,D,E类地址是不冲突的
 *
 * 输入描述:
 * 多行字符串。每行一个IP地址和掩码，用~隔开。
 *
 * 输出描述:
 * 统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。
 *
 * 示例1
 * 输入
 * 复制
 * 10.70.44.68~255.254.255.0
 * 1.0.0.1~255.0.0.0
 * 192.168.0.2~255.255.255.0
 * 19..0.~255.255.255.0
 * 输出
 * 复制
 * 1 0 1 0 0 2 1
 */
public class ipAddressCategoryCount18 {

    /**
     * 题目的要求是
     * *1.要IP和掩码都正确，该记录才算正确
     * * 2.如果IP或者掩码错误，则该记录为无效
     * * 3.两者都错误，则只算一次错误
     * 1.
     * 7 6 5 4 3 2 1 0
     * 128 64 32 16 8 4 2 1
     * 2.子网掩码：255 254 252 248 240 224 192 128 0
     * 前面全是1，后面全是0   全是1或者全是0均为非法
     * <p>
     * 方法一：
     * 函数功能是判断mask(子网掩码)是否有效， 如果第一个是255,则判断第二个是不是255，若第二个不是255，则第三个和第四个必须是0,
     * （见子网掩码的规则，前面为1，后面为0），同时第二个必须是254,252,248,240,224,192,128,0；至于原因，将他们转换成二进制，
     * 前面为连续的1，后面为连续的0，符合要求。若第二个为255，则第三个以此类推。 如果第一个不是255，则同理，
     * 只能是254,252,248,240,224,192,128（注意不能为0），因为子网掩码mask可以以0开头，但不是有效的掩码。
     * 方法二：
     * 将子网掩码转换成32位0/1组成的二进制字符串，比较最后一次1出现的位置是否在0第一次出现的位置之后。
     * 只要第一个0在的位置比最后一个1的位置较前则掩码非法，反则合法
     * 1个十进制数转换为8位的二进制数=》4个十进制数的子网掩码转换为32位的二进制数
     * String binaryStr=Integer.toBinaryString(Integer.parseInt(maskArr[i]));
     * <p>
     * 当子网掩码错误时，不在判断ip是否有效，错误直接加一， 进行下次循环判断下一行
     * 若有效，再分别判断属于哪一类IP地址。
     * <p>
     * 3.IP地址
     * 当一个ip属于ABCDE类中的一个时候，也属于私有ip时，私有ip和他属于的分类都应该加一
     * <p>
     * 4.split(). 必须得加转义字符\\，以下是拆分 IP 地址的实例
     * 得知 split　的参数是　String regex 代表的是一个正则表达式。如果是正则中的特殊字符，就不能了。点正好是一个特殊字符。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int err = 0;
        int pri = 0;
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] ipAndMask = str.split("~");
            String ip = ipAndMask[0];
            String mask = ipAndMask[1];

           /* if (!isValidFormat(ip) || !isValidFormat(mask)) {
                err++;
                continue;
            }*/

            if (!isValidMask(mask)) {
                err++;
                continue;
            }

            String[] ip_arr = ip.split("\\.");
            int ip_sin1 = Integer.parseInt(ip_arr[0]);
            int ip_sin2 = Integer.parseInt(ip_arr[1]);
            if (1 <= ip_sin1 && ip_sin1 <= 126) {//注意不能写成1 <= ip_sin1 <= 126 就算是连续的也不行
                a++;
                if (ip_sin1 == 10) {
                    pri++;
                }
            } else if (128 <= ip_sin1 && ip_sin1 <= 191) {
                b++;
                if (ip_sin1 == 172 && ip_sin2 >= 16 && ip_sin2 <= 31) {
                    pri++;
                }
            } else if (192 <= ip_sin1 && ip_sin1 <= 223) {
                c++;
                if (ip_sin1 == 192 && ip_sin2 == 168) {
                    pri++;
                }
            } else if (224 <= ip_sin1 && ip_sin1 <= 239) {
                d++;
            } else if (240 <= ip_sin1 && ip_sin1 <= 255) {
                e++;
            }
        }
        scanner.close();
        System.out.printf("%d %d %d %d %d %d %d", a, b, c, d, e, err, pri);
    }

    /*private static boolean isValidFormat(String ip) {
        boolean res = true;
        if (ip == null || "".equals(ip))
            return false;
        Pattern pattern = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$");
        Matcher matcher = pattern.matcher(ip);

        if (matcher.matches()) {
            String[] nums = ip.split("\\.");
            for (String num : nums) {
                int n = Integer.valueOf(num);
                if (n < 0 || n > 255) {
                    res = false;
                    break;
                }
            }
        } else {
            res = false;
        }

        return res;
    }
*/
    private static boolean isValidMask(String mask) {
        String[] mask_arr = mask.split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        for (String mask_sin : mask_arr) {
            stringBuilder.append(Integer.toBinaryString(Integer.parseInt(mask_sin)));
        }
        int pos1 = stringBuilder.indexOf("0");
        int pos2 = stringBuilder.lastIndexOf("1");
        if (pos1 < pos2) {
            return false;
        }
        return true;
    }


    class Main {
        public void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int isA = 0;
            int isB = 0;
            int isC = 0;
            int isD = 0;
            int isE = 0;
            int isEroor = 0;
            int isPrivate = 0;

            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] ips = str.split("~");

                //判断掩码是否合法
                boolean isMask = false;
                String[] maskArr = ips[1].split("\\.");
                String binaryMask = "";
                //形成掩码二进制字符串
                for (int i = 0; i < maskArr.length; i++) {
                    String binaryStr = Integer.toBinaryString(Integer.parseInt(maskArr[i]));
                    //凑成4组8位二进制
                    for (int j = 0; j < 8 - binaryStr.length(); j++) {
                        binaryStr = "0" + binaryStr;
                    }
                    binaryMask += binaryStr;
                }
                //比较二进制字符串中第一个0的位置和最后一个1的位置来判断掩码是否合法
                if (binaryMask.indexOf("0") < binaryMask.lastIndexOf("1")) {
                    isMask = false;
                } else {
                    isMask = true;
                }

                //掩码合法后再来判断IP是否合法
                if (isMask) {
                    String[] strArr = ips[0].split("\\.");
                    if (strArr.length == 4) {
                        int[] ipArr = new int[4];
                        for (int i = 0; i < 4; i++) {
                            if (strArr[i] == "") {
                                ipArr[i] = -1;
                            } else {
                                ipArr[i] = Integer.parseInt(strArr[i]);
                            }
                        }
                        if ((0 <= ipArr[0] && ipArr[0] <= 255) && (0 <= ipArr[1] && ipArr[1] <= 255) && (0 <= ipArr[2] && ipArr[2] <= 255) && (0 <= ipArr[3] && ipArr[3] <= 255)) {
                            if (ipArr[0] >= 1 && ipArr[0] <= 126) {
                                isA++;
                                if (ipArr[0] == 10)
                                    isPrivate++;
                            } else if (ipArr[0] >= 128 && ipArr[0] <= 191) {
                                isB++;
                                if (ipArr[0] == 172 && (ipArr[1] >= 16 && ipArr[1] <= 31))
                                    isPrivate++;
                            } else if (ipArr[0] >= 192 && ipArr[0] <= 223) {
                                isC++;
                                if (ipArr[0] == 192 && ipArr[1] == 168)
                                    isPrivate++;
                            } else if (ipArr[0] >= 224 && ipArr[0] <= 239) {
                                isD++;
                            } else if (ipArr[0] >= 240 && ipArr[0] <= 255) {
                                isE++;
                            }
                        } else {
                            isEroor++;
                        }
                    } else {
                        isEroor++;
                    }

                } else {
                    isEroor++;
                }
            }
            System.out.println(isA + " " + isB + " " + isC + " " + isD + " " + isE + " " + isEroor + " " + isPrivate);
            sc.close();
        }
    }
}

