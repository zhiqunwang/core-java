package org.elvis.wang.java.concurrent.base;

/**
 * Created by wangzhiqun on 2017/11/19.
 */
public class DaemonTest {
    //守护线程
    public static class DaemonT extends Thread{
       public void run(){
           while(true){
               System.out.println("i am alvie!");
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }

       }
    }

    public static void main(String[] args) throws InterruptedException {
        DaemonT t  = new DaemonT();
        t.setDaemon(true);
        t.start();

        System.out.println("main Thread is end!");
    }
}
