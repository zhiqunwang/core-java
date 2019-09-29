package org.elvis.wang.java.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by zhiqun.wang on 2019/6/20 17:13
 */
public class AtomicStampedReferenceTest {

    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<>(1,0);

    public static void main(String[] args) {
        Thread main = new Thread(() -> {
            System.out.println("操作线程" + Thread.currentThread() +",初始值 a = " + atomicStampedRef.getReference());
            int stamp = atomicStampedRef.getStamp();//获取当前更新版本号

            try {
                Thread.sleep(1000);//等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isCASSuccess = atomicStampedRef.compareAndSet(1,2,stamp,stamp+1);
            System.out.println("操作线程" + Thread.currentThread() +",CAS操作结果: " + isCASSuccess);
        },"主操作线程");


        Thread other = new Thread(() -> {
            Thread.yield(); // 确保thread-main 优先执行
            atomicStampedRef.compareAndSet(1,2,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
            System.out.println("操作线程" + Thread.currentThread() +",【increment】 ,值 = "+ atomicStampedRef.getReference());
            atomicStampedRef.compareAndSet(2,1,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
            System.out.println("操作线程" + Thread.currentThread() +",【decrement】 ,值 = "+ atomicStampedRef.getReference());
        },"干扰线程");

        main.start();
        other.start();

    }
}
