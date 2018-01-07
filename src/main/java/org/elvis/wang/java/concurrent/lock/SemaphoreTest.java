package org.elvis.wang.java.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by wangzhiqun on 2017/11/25.
 */
public class SemaphoreTest implements Runnable {

    final Semaphore semaphore = new Semaphore(5);

    public void run() {
        try {
            semaphore.acquire();//共享锁
            System.out.println("Thread ID is"+ Thread.currentThread().getId());
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        SemaphoreTest test = new SemaphoreTest();
        for(int i = 0;i<20;i++){
            executorService.submit(test);
        }
        executorService.shutdown();
    }


}
