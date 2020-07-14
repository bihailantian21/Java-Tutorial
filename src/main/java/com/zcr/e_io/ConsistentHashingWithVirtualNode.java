package com.zcr.e_io;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 带虚拟节点的一致性Hash算法
 */
public class ConsistentHashingWithVirtualNode
{
    /**
     * 待添加入Hash环的服务器列表
     */
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};

    /**
     * 真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好
     */
    private static List<String> realNodes = new LinkedList<String>();

    /**
     * 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
     */
    private static SortedMap<Integer, String> virtualNodes =
            new TreeMap<Integer, String>();

    /**
     * 虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应5个虚拟节点
     */
    private static final int VIRTUAL_NODES = 5;

    static
    {
        // 先把原始的服务器添加到真实结点列表中
        for (int i = 0; i < servers.length; i++)
            realNodes.add(servers[i]);

        // 再添加虚拟节点，遍历LinkedList使用foreach循环效率会比较高
        for (String str : realNodes)
        {
            for (int i = 0; i < VIRTUAL_NODES; i++)
            {
                String virtualNodeName = str + "&&VN" + String.valueOf(i);
                int hash = getHash(virtualNodeName);
                System.out.println("虚拟节点[" + virtualNodeName + "]被添加, hash值为" + hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
        System.out.println();
    }

    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     */
    private static int getHash(String str)
    {
        final int p = 16777619;
        int hash = (int)2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    /**
     * 得到应当路由到的结点
     */
    private static String getServer(String node)
    {
        // 得到带路由的结点的Hash值
        int hash = getHash(node);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap =
                virtualNodes.tailMap(hash);
        // 第一个Key就是顺时针过去离node最近的那个结点
        Integer i = subMap.firstKey();
        // 返回对应的虚拟节点名称，这里字符串稍微截取一下
        String virtualNode = subMap.get(i);
        return virtualNode.substring(0, virtualNode.indexOf("&&"));
    }

    /**
     * 虚拟节点[192.168.0.0:111&&VN0]被添加, hash值为1686427075
     * 虚拟节点[192.168.0.0:111&&VN1]被添加, hash值为354859081
     * 虚拟节点[192.168.0.0:111&&VN2]被添加, hash值为1306497370
     * 虚拟节点[192.168.0.0:111&&VN3]被添加, hash值为817889914
     * 虚拟节点[192.168.0.0:111&&VN4]被添加, hash值为396663629
     * 虚拟节点[192.168.0.1:111&&VN0]被添加, hash值为1032739288
     * 虚拟节点[192.168.0.1:111&&VN1]被添加, hash值为707592309
     * 虚拟节点[192.168.0.1:111&&VN2]被添加, hash值为302114528
     * 虚拟节点[192.168.0.1:111&&VN3]被添加, hash值为36526861
     * 虚拟节点[192.168.0.1:111&&VN4]被添加, hash值为848442551
     * 虚拟节点[192.168.0.2:111&&VN0]被添加, hash值为1452694222
     * 虚拟节点[192.168.0.2:111&&VN1]被添加, hash值为2023612840
     * 虚拟节点[192.168.0.2:111&&VN2]被添加, hash值为697907480
     * 虚拟节点[192.168.0.2:111&&VN3]被添加, hash值为790847074
     * 虚拟节点[192.168.0.2:111&&VN4]被添加, hash值为2010506136
     * 虚拟节点[192.168.0.3:111&&VN0]被添加, hash值为891084251
     * 虚拟节点[192.168.0.3:111&&VN1]被添加, hash值为1725031739
     * 虚拟节点[192.168.0.3:111&&VN2]被添加, hash值为1127720370
     * 虚拟节点[192.168.0.3:111&&VN3]被添加, hash值为676720500
     * 虚拟节点[192.168.0.3:111&&VN4]被添加, hash值为2050578780
     * 虚拟节点[192.168.0.4:111&&VN0]被添加, hash值为586921010
     * 虚拟节点[192.168.0.4:111&&VN1]被添加, hash值为184078390
     * 虚拟节点[192.168.0.4:111&&VN2]被添加, hash值为1331645117
     * 虚拟节点[192.168.0.4:111&&VN3]被添加, hash值为918790803
     * 虚拟节点[192.168.0.4:111&&VN4]被添加, hash值为1232193678
     *
     * [127.0.0.1:1111]的hash值为380278925, 被路由到结点[192.168.0.0:111]
     * [221.226.0.1:2222]的hash值为1493545632, 被路由到结点[192.168.0.0:111]
     * [10.211.0.1:3333]的hash值为1393836017, 被路由到结点[192.168.0.2:111]
     * @param args
     */
    public static void main(String[] args)
    {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
        for (int i = 0; i < nodes.length; i++)
            System.out.println("[" + nodes[i] + "]的hash值为" +
                    getHash(nodes[i]) + ", 被路由到结点[" + getServer(nodes[i]) + "]");
    }
}