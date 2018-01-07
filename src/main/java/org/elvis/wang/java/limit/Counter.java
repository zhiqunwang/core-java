package org.elvis.wang.java.limit;

import java.util.LinkedList;

/**
 * Created by wangzhiqun on 2018/1/7.
 *
 * 高并发 之限流算法
 *
 * 计数器算法  滑动的窗口
 */
public class Counter {
    Long counter  = 0L;//服务访问次数，可放在redis中，实现分布式系统的访问计数

    //使用LinkedList来记录滑动窗口的10个格子
    LinkedList<Long> ll = new LinkedList<Long>();

    private void doCheck() throws InterruptedException {
        while (true)
        {
            ll.addLast(counter);

            if (ll.size() > 10)
            {
                ll.removeFirst();
            }

            //比较最后一个和第一个，两者相差一秒
            if ((ll.peekLast() - ll.peekFirst()) > 100)
            {
                //To limit rate
            }

            Thread.sleep(100);
        }
    }
}
