package org.elvis.wang.java.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by wangzhiqun on 2017/11/25.
 */
public class LockSupportTest {
    public static Object u = new Object();

    static ChangeObjectThread t1 = new ChangeObjectThread("T1");
    static ChangeObjectThread t2 = new ChangeObjectThread("T2");

    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super.setName(name);
        }
        public void run(){
            synchronized (u){
                System.out.println("current Thread is "+this.getName());
                LockSupport.park();//线程挂起
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();


    }
}
