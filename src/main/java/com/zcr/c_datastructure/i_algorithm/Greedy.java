package com.zcr.c_datastructure.i_algorithm;

import java.util.*;

/**
 * @author zcr
 * @date 2019/7/10-19:35
 */
public class Greedy {

    public static void main(String[] args) {

        //创建广播电台，放入到一个Map中
        Map<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("深圳");
        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加入到map
        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);

        //存放所有的地区
        Set<String> allAreas = new HashSet<String>();
        /*allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("深圳");
        allAreas.add("大连");*/
       /* for (Map.Entry<String, HashSet<String>> entry : broadcasts.entrySet()) {
            for (String city : entry.getValue()) {
                if (!allAreas.contains(city)) {
                    allAreas.add(city);
                }
            }
        }
        System.out.println(allAreas);*/
        for (String key : broadcasts.keySet()) {
            for (String city : broadcasts.get(key)) {
                //if (!allAreas.contains(city)) {
                    allAreas.add(city);
                //}
            }
        }
        System.out.println(allAreas);



        //创建列表，存放选择的电台集合
        List<String> selects = new ArrayList<String>();

        //定义一个临时的集合，在遍历的过程中存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        Set<String> tempSet = null;

        //保存在一次遍历过程中，能够覆盖最多未覆盖的地区，对应的电台的k
        //如果maxKey不为空，则会加入到selects
        String maxKey;

        while (allAreas.size() != 0) {//allAreas是不断减少的，不为0则表示还没有覆盖到所有地区
            //每进行一次while，就需要
            maxKey = null;

            //遍历broadcasts，取出对应的key
            for (String key : broadcasts.keySet()) {
                //每进行一次for，就需要
                tempSet = new HashSet<String>();

                //当前这个key能够覆盖的地区
                Set<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas两个集合的交集，交集会赋值给tempSet
                tempSet.retainAll(allAreas);
                //如果当前集合包含的未覆盖地区的数量，比maxKey指向的集合未覆盖的地区还多，就需要重置maxKey
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {//每次都选择最优的
                    maxKey = key;
                }
            }
            //maxKey != null，就应该将maxKey加入selects
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区从allAreas中去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的选择结果是："+selects);
    }


}
