package org.elvis.wang.java.atomic;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhiqun.wang on 2019/6/20 16:27
 */
public class AtomicIntegerTest {
    private static final int THREADS_COUNT = 20;
    public static AtomicInteger count = new AtomicInteger(0);
   // public static volatile  int count  = 0;

    public static void increase() {
        //count++;
        //count = count +1
        count.getAndAdd(1);
    }

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS_COUNT];
        CyclicBarrier barrier = new CyclicBarrier(20);
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        barrier.await();
                        for (int i = 0; i < 1000; i++) {
                            increase();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

            threads[i].start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(count);
    }
}


