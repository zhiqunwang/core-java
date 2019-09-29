package org.elvis.wang.java.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangzhiqun on 2017/11/25.
 */
public class ConditionTest implements Runnable {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void run() {
        try {
            lock.lock();
            Thread.sleep(1000L);
            condition.await();
        }catch (InterruptedException e){
             e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

    }


}
