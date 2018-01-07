package org.elvis.wang.java.limit;

/**
 * Created by wangzhiqun on 2018/1/7.
 * 高并发 限流算法
 *
 * 漏桶 算法
 *
 * 固定速率流出  对于处理突发性流量缺乏效率
 */
public class LeakyBucket {
    double rate;               // leak rate in calls/s
    double burst;              // bucket size in calls
    long refreshTime;          // time for last water refresh
    double water;              // water count at refreshTime

    private void  refreshWater() {
        long  now = System.currentTimeMillis();

        //水随着时间流逝,不断流走,最多就流干到0.
        water = Math.max(0, water- (now - refreshTime)*rate);
        refreshTime = now;
    }

    public boolean permissionGranted() {
        refreshWater();
        if (water < burst) { // 水桶还没满,继续加1
            water ++;
            return true;
        } else {
            return false;
        }
    }
}
