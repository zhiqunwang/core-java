package org.elvis.wang.java.concurrent.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangzhiqun on 2017/11/25.
 */
public class CountDownLatchTest implements Runnable {
    static final CountDownLatch end = new CountDownLatch(1);

    static final CountDownLatchTest test = new CountDownLatchTest();

    public void run() {
        try {
            end.await();
            Thread.sleep(new Random().nextInt(10)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId()+"is complete!");

      }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService execu = Executors.newFixedThreadPool(10);

        for (int i = 0;i<10;i++){
            execu.submit(test);
        }
        Thread.sleep(2000L);
        end.countDown();
        //System.out.println("all is complete！");
        execu.shutdown();
    }
}
