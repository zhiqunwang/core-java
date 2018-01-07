package org.elvis.wang.java.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangzhiqun on 2017/11/25.
 */
public class ReentrantLockTest {
    public static int i = 0;
    static class ReenterLock implements Runnable{
        static ReentrantLock lock  = new ReentrantLock();

        public void run() {
            for(int j=0;j<10000000;j++){
                lock.lock();
                lock.lock();
                try{
                    i++;
                }finally {
                  //  lock.unlock();
                    lock.unlock();
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock t  = new ReenterLock();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();;
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}
