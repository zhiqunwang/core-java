package org.elvis.wang.java.consistenthash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 *  Created by zhiqun.wang on 2018/6/27.
 *  一致性Hash算法（不带虚拟节点）
 */
public class ConsistentHashingWithoutVirtualNode {


    private static  String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};


    private static SortedMap<Integer,String>  sortedMap = new TreeMap<>();


    static {
        for(int i= 0;i < servers.length;i++){
            int hash = getHash(servers[i]);
            System.out.println("hash:"+hash+"  server:"+servers[i]);
            sortedMap.put(hash,servers[i]);
        }
    }


    public static  String getServer(String key){

        int hash = getHash(key);
        System.out.println("getHash:"+hash);
        // 得到大于该Hash值的所有Map  并取出第一个key
        int nodeKey = sortedMap.tailMap(hash).firstKey();

        return sortedMap.get(nodeKey);

    }

    /**
       * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
    */
   private static int getHash(String str){
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

    public static void main(String[] args) {

       //构建key
        String[] keys = {"hello","world","nihao"};

        for(int i = 0;i< keys.length;i++){
            String server = getServer(keys[i]);
            System.out.printf(keys[i]+":"+server);
        }






    }

}
