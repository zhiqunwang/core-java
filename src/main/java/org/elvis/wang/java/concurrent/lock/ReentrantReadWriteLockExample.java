package org.elvis.wang.java.concurrent.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zhiqun.wang on 2019/6/26 10:17
 *
 * 使用场景 read   阻塞 write 不阻塞 read
 *         write  阻塞 write read
 */
public class ReentrantReadWriteLockExample {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();







}
