package org.elvis.wang.java.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangzhiqun on 2017/11/25.
 * 可重入 可中断 可限时 公平锁
 */
public class ReenterLock implements Runnable{

    ReentrantLock lock1 = new ReentrantLock();
    ReentrantLock lock2 = new ReentrantLock();

    int i = 0;
    public ReenterLock(int i){
        this.i = i ;
    }

    public void run() {
        try {
            if(i==1){
                lock1.lockInterruptibly();//可中断
              //  lock1.tryLock(100, TimeUnit.MINUTES)  可限时
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.lock();
            }else{
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.lock();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock r1 = new ReenterLock(1);
        ReenterLock r2 = new ReenterLock(2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(1000L);



    }
}
