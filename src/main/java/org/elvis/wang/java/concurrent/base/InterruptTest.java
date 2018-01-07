package org.elvis.wang.java.concurrent.base;

/**
 * Created by wangzhiqun on 2017/11/19.
 */
public class InterruptTest {
    //interrupt 优雅中断线程，不推荐用stop stop会释放所有线程上的minitor，很可能会导致脏读
    public static void main(String[] args) throws Exception{

      Thread t =   new Thread(new Runnable() {
            public void run() {
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Thread is interrupted!");
                        break;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("线程处于休眠阶段被中断！");
                        //线程抛出异常后会清楚线程的中断标记,所以在此标记线程中断,if条件即可判断跳出循环
                        Thread.currentThread().interrupt();
                    }
                }
//释放CPU资源
                Thread.yield();

            }
        },"T1");

      t.start();
      Thread.sleep(1000);
      t.interrupt();


    }


}
