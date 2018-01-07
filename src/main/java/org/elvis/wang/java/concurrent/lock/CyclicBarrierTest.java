package org.elvis.wang.java.concurrent.lock;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by wangzhiqun on 2017/11/25.
 */
public class CyclicBarrierTest {
    public static class Soldier implements Runnable{
        private String soldier;
        private CyclicBarrier cyclic;

        Soldier(CyclicBarrier cyclicBarrier,String cyclicName){
            this.cyclic = cyclicBarrier;
            this.soldier = cyclicName;
        }
        public void run() {
            try {
                cyclic.await();
                doWork();
                cyclic.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {//当某个线程无法到达栅栏 其他线程抛出BrokenBarrierException
                e.printStackTrace();
            }

        }

        void doWork(){
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务完成！");
        }
    }

    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;
        public BarrierRun(boolean flag,int N){
            this.flag = flag;
            this.N = N;
        }
        public void run() {
            if(flag){
               System.out.println("司令：【士兵"+N+"个，任务完成】");
            }else{
                System.out.println("司令：【士兵"+N+"个，集合完毕】");
                flag = true;
            }

        }
    }

    public static void main(String[] args){
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        //等待N个线程到达后执行new BarrierRun(flag,N)
        CyclicBarrier cyclic = new CyclicBarrier(N,new BarrierRun(flag,N));
        System.out.println("集合队伍！");
        for(int i = 0 ; i<N; i++){
            System.out.println("士兵"+i+"报道!");
            allSoldier[i] = new Thread(new Soldier(cyclic,"士兵"+i));
            allSoldier[i].start();
            if(i==5){
               allSoldier[0].interrupt();
            }
        }
    }
}
