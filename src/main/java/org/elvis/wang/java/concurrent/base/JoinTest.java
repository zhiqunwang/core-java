package org.elvis.wang.java.concurrent.base;

/**
 * Created by wangzhiqun on 2017/11/19.
 */
public class JoinTest {

    public volatile static int i = 0;

    static AThread t1 = new AThread("T1");
    static AThread t2 = new AThread("T2");
    static AThread t3 = new AThread("T3");

    public static class AThread extends Thread{
        public AThread(String name){
            super.setName(name);
        }
        public void run(){
            System.out.println(this.getName()+"开始执行");
            for(int i = 0;i<100000;i++);
            System.out.println("for is end");
        }
    }

    public static void main(String[] args) throws InterruptedException {
     t1.start();
     t1.join();
     t2.start();
     t2.join();
     t3.start();

    }
}
