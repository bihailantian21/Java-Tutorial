package com.zcr.c_datastructure.e_tree;

import java.io.*;
import java.util.*;

/**
 * @author zcr
 * @date 2019/7/8-20:49
 */
public class HuffmanCode {

    public static void main(String[] args) {

        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();//字符串转换成字节数组
        System.out.println(contentBytes.length);//40

        /*//分步骤过程
        //将字节数组转换成节点数组
        List<Node3> nodes = getNodes(contentBytes);
        System.out.println(nodes);

        //将节点数组生成哈夫曼树
        System.out.println("哈夫曼树：");
        Node3 huffmanTreeRoot = createHuffmanTree(nodes);//哈夫曼树的根节点

        //前序遍历哈夫曼树
        System.out.println("前序遍历哈夫曼树");
        preOrder(huffmanTreeRoot);

        //生成赫夫曼树对应的赫夫曼编码
//        getCodes(huffmanTreeRoot,"",stringBuilder);
        getCodes(huffmanTreeRoot);//返回也行不返回也行
        System.out.println("生成的哈夫曼编码表为："+huffmanCodes);

        //生成字符串的哈夫曼编码
        byte[] huffmanCodeByte = zip(contentBytes,huffmanCodes);//长度133
        System.out.println("压缩后的字符串为："+Arrays.toString(huffmanCodeByte));//17
*/

        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是"+Arrays.toString(huffmanCodeBytes));

        byte[] sourceBytes = decode(huffmanCodes,huffmanCodeBytes);
        System.out.println("原来的字符串是"+new String(sourceBytes));

        //压缩文件
        String srcFile = "F://we.jpg";
        String dsrFile = "F://we.zip";

        zipFile(srcFile,dsrFile);
        System.out.println("压缩文件成功");

        //解压文件
        String dsrFile2 = "F://we.zip";
        String srcFile2 = "F://we.jpg";

        unZipFile(srcFile2,dsrFile2);
        System.out.println("解压文件成功");



    }

    /**
     * @param bytes 接收一个字节数组（字符串-》字节数组）
     * @return 返回的就是一个节点列表（Map【值，次数】-》节点列表【值，次数】）
     */
    private static List<Node3> getNodes(byte[] bytes) {
        //1.先创建一个ArrayList
        List<Node3> nodes = new ArrayList<Node3>();


        //2.遍历bytes，统计每一个byte出现的次数，使用map存储
        Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {//map还没有这个字符数据
                counts.put(b, 1);
            } else {//map中已经有了这个字
                counts.put(b, count+1);
            }
        }

        //把每个键值对转成一个Node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node3(entry.getKey(), entry.getValue()));
        }
        return nodes;

    }

    //通过节点列表创建对应的huffman树
    private static Node3 createHuffmanTree(List<Node3> nodes) {
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
            System.out.println("nodes="+nodes);

            //取出第一颗最小的二叉树
            Node3 leftNode = nodes.get(0);
            //取出第二颗最小的二叉树
            Node3 rightNode = nodes.get(1);
            //创建一颗新的二叉树，它的根节点没有data，只有权值！（哈夫曼数中只有叶子节点有数据，飞叶子节点没有数据）
            Node3 parent = new Node3(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理过的两颗二叉树移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入
            nodes.add(parent);
        }
        //返回最后的节点就是哈夫曼树的根节点
        return nodes.get(0);

    }

    //前序遍历
    private static void preOrder(Node3 root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空，不能遍历");
        }
    }

    //为了调用方便，重载getCodes
    private static Map<Byte,String> getCodes(Node3 root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left,"0",stringBuilder);
        //处理root的右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    //生成哈夫曼树对应的哈夫曼编码
    //1.将哈夫曼编码表存放在Map<Byte,String>形式 32->01 97->100 100->11000....
    static Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
    //2.在生成哈夫曼编码表时，需要去拼接路径，定义一个StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();
    /**
     * 功能：将传入的node节点的所有叶子节点的哈夫曼编码得到，并放入到哈夫曼集合中
     * @param node 传入节点
     * @param code 路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node3 node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {//node为空时不处理
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {//非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left,"0",stringBuilder2);
                //向右递归
                getCodes(node.right,"1",stringBuilder2);
            } else {//叶子节点
                //就表示找到了某个叶子节点的最后
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }

    //将字符串对应的byte[]数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的byte[]

    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @param huffmanCodes 生成的哈夫曼编码表map
     * @return 返回哈夫曼编码处理后的对应的字节数组
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes) {
        //1.先利用哈夫曼编码表将传进来的原始字节数组先转成哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历byte数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        System.out.println("哈夫曼编码字符串"+stringBuilder);//133

        //将stringBuilder转成byte[]
        //统计返回byte[] huffmanCodeBytes长度
        //一句话搞定：int len = (stringBuilder.length() + 7) / 8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//定义一个计数器，记录是第几个byte
        //遍历stringBuilder，根据它八位八位的放
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            //因为是每8位对应一个byte
            String strByte;
            if (i + 8 > stringBuilder.length()) {//最后，不够8位了
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转成一个byte，放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //使用一个方法，将前面的方法封装起来，便于我们的调用
    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过哈夫曼编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes) {
        //1.将字节数组转换成节点数组
        List<Node3> nodes = getNodes(bytes);

        //2.将节点数组生成哈夫曼树
        Node3 huffmanTreeRoot = createHuffmanTree(nodes);//哈夫曼树的根节点

        //3.生成赫夫曼树对应的赫夫曼编码
        Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);//返回也行不返回也行

        //4.根据生成的哈夫曼编码，压缩得到压缩后的哈夫曼编码字节数组
        byte[] huffmanCodeByte = zip(bytes,huffmanCodes);//长度133

        return huffmanCodeByte;

    }

    //完成数据的解压
    //思路
    //1.将huffmanCodeBytes重新转成哈夫曼编码对应的二进制字符串
    //2.将哈夫曼编码对应的二进制字符串，对照着哈夫曼编码，转回到原始字符串

    /**
     *
     * @param flag 标识是否需要补高位，如果为真，表示需要补高位；如果为假，表示不需要补；如果是最后一个字节，无需补高位
     * @param b 传入的byte
     * @return 是b对应的二进制字符串（注意是按照补码返回的）
     */
    private static String byteToBitString(boolean flag,byte b) {
        //使用变量保存b
        int temp = b;//将b转成int

        //如果是正数我们还存在补高位
        if (flag) {
            temp |= 256;//按位与 temp 100000000|00000001=》100000001
        }

        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制补码
        //System.out.println(str);

        if (flag) {
            return str.substring(str.length() - 8);//取后面的八位
        } else {
            return str;
        }
    }

    //完成对压缩数据的解码

    /**
     *
     * @param huffmanCodes 哈夫曼编码表
     * @param huffmanBytes 经哈夫曼编码处理过的字符串得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes) {

        //1.先得到huffmanBytes得到的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,b));
        }
//        System.out.println("哈夫曼字节数组对应的二进制字符串="+stringBuilder.toString());
//        return null;
        //把字符串按照指定的哈夫曼编码进行解码
        //把哈夫曼编码表进行调换，因为要反向查询
        Map<String,Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte,String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }
        System.out.println(map);

        //创建一个集合存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            //i可以理解为一个索引，扫描
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b= null;

            while (flag) {
                //取出一个'1''0'
                //递增的取出key
                String key = stringBuilder.substring(i,i + count);//i不动，让count动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i直接移动到count
        }
        //当for循环结束后，我们list中就存放了所有的字符
        //把list中的数据放到byte[]并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    //将一个文件进行压缩

    /**
     *
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录下
     */
    public static void zipFile(String srcFile,String dstFile) {
        //创建输出流
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            //创建文件的输入流，准备读取文件
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[
            byte[] b = new byte[is.available()];
            //读取文件
            is.read();

            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);

            //创建文件的输出流，准备存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流相关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把哈夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里以对象流的方式写入哈夫曼编码，目的是为了以后好恢复
            //一定要把哈夫曼编码写入到压缩文件中，否则以后恢复不了
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //完成对压缩文件的解压

    /**
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile,String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois= null;
        //定义文件的输出流
        OutputStream os = null;



        try {
            //创建文件输入流
            is= new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);

            //读取byte数组
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取哈夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes,huffmanBytes);
            //将bytes写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到目标文件
            os.write(bytes);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ois.close();
                os.close();
                is.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }

        }


    }


}


//创建节点，带数据和权值
class Node3 implements Comparable<Node3>{
    Byte data;//存放数据本身的ASCII码值，'a'->97 ' '->32
    int weight;//权值，表示出字符出现的次数
    Node3 left;
    Node3 right;

    public Node3(Byte data,int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node3 o) {
        return this.weight - o.weight;//从小到大排序
    }

    @Override
    public String toString() {
        return "Node3{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
