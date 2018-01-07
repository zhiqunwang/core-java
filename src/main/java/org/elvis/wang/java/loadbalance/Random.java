package org.elvis.wang.java.loadbalance;

import java.util.*;

/**
 * Created by wangzhiqun on 2018/1/5.
 *
 * dubbo 负载均衡策略 算法
 * -随机，按权重设置随机概率
 */
public class Random {

    /**
     * key:IP 服务提供方  Value：weight 权重
     */
    static Map<String,Integer> serverWeigthMap  = new HashMap<String,Integer>();

    static{
        serverWeigthMap.put("192.168.1.12", 1);
        serverWeigthMap.put("192.168.1.13", 1);
        serverWeigthMap.put("192.168.1.14", 2);
        serverWeigthMap.put("192.168.1.15", 2);
        serverWeigthMap.put("192.168.1.16", 3);
        serverWeigthMap.put("192.168.1.17", 4);
        serverWeigthMap.put("192.168.1.18", 1);
        serverWeigthMap.put("192.168.1.19", 2);
    }

    public static String random()
    {
        Map<String,Integer> serverMap  = new HashMap<String,Integer>();
        serverMap.putAll(serverWeigthMap);
        //獲取ip列表list
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> invokeList = new ArrayList<String>();
        invokeList.addAll(keySet);

        int length = invokeList.size();//获取服务提供方个数
        int totalWeight = 0;//总权重
        boolean sameWeight = true;//权重是否都一样

        for (int i = 0; i < length; i++) {
            int weight = serverMap.get(invokeList.get(i));
            totalWeight += weight; // 累计总权重
            if (sameWeight && i > 0
                    && weight != serverMap.get(invokeList.get(i-1))) {
                sameWeight = false; // 计算所有权重是否一样
            }
        }

        java.util.Random random = new java.util.Random();
        if (totalWeight > 0 && ! sameWeight) {
            // 如果权重不相同且权重大于0则按总权重数随机
            int offset = random.nextInt(totalWeight);
            // 并确定随机值落在哪个片断上
            for (int i = 0; i < length; i++) {
                offset = offset -  serverMap.get(invokeList.get(i));
                if (offset < 0) {
                    return invokeList.get(i);
                }
            }
        }
        // 如果权重相同或权重为0则均等随机
        return invokeList.get(random.nextInt(length));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String serverIp = random();
            System.out.println(serverIp);
        }
    }
}
