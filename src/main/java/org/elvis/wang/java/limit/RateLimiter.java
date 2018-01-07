package org.elvis.wang.java.limit;

/**
 * Created by wangzhiqun on 2018/1/7.
 * 高并发 限流算法
 * 令牌桶
 */
public class RateLimiter {

    /**
     * Google开源工具包Guava提供了限流工具类RateLimiter,
     * 该类基于令牌桶算法(Token Bucket)来完成限流,非常易于使用.RateLimiter经常用于限制对一些物理资源或者逻辑资源的访问速率.
     * 它支持两种获取permits接口,一种是如果拿不到立刻返回false,一种会阻塞等待一段时间看能不能拿到.

     RateLimiter和Java中的信号量(java.util.concurrent.Semaphore)类似,Semaphore通常用于限制并发量.
     */
}
