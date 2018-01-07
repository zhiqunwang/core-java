package org.elvis.wang.java.loadbalance;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzhiqun on 2018/1/5.
 *
 * dubbo 负载均衡策略 算法
 * 一致Hash，相同参数的请求总是发到同一个提供者，
 * 当某一台提供者挂时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者，不会引起剧烈变动。
 */
public class ConsistentHash {

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


    public static String getServerIp(String arguments){
       return null;
    }

/**
 *  dubbo 核心代码
 *
 *  其中virtualInvokers是一棵红黑树，tailMap方法会返回所有hash值大于key的节点，
 *  然后取第一个节点即可。其中key值是根据调用的参数md5后再hash计算得出的：
    private Invoker<T> sekectForKey(long hash) {
        Invoker<T> invoker;
        Long key = hash;
        if (!virtualInvokers.containsKey(key)) {
            SortedMap<Long, Invoker<T>> tailMap = virtualInvokers.tailMap(key);
            if (tailMap.isEmpty()) {
                key = virtualInvokers.firstKey();
            } else {
                key = tailMap.firstKey();
            }
        }
        invoker = virtualInvokers.get(key);
        return invoker;
    }


 public Invoker<T> select(Invocation invocation) {
     String key = toKey(invocation.getArguments());
     byte[] digest = md5(key);
     Invoker<T> invoker = sekectForKey(hash(digest, 0));
     return invoker;
 }

 */

}
