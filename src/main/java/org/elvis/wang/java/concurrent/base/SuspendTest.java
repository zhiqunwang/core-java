package org.elvis.wang.java.concurrent.base;

/**
 * Created by wangzhiqun on 2017/11/19.
 */
public class SuspendTest {
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
                Thread.currentThread().suspend();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();

       //suspend (挂起)  resume（继续执行） 不推荐使用resume 和 suspend 时间控制不好

    }
}
