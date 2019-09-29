package org.elvis.wang.java.concurrent;

/**
 * Created by zhiqun.wang on 2019/6/22 11:04
 *
 *  指令重排序的目的是处理器层面最大提高程序的并发性，前提是重排序的数据不存在数据依赖
 *
 *  happen-before原则
 *
 */
public class ReOrderExample {

    private int a = 0;

    private volatile  boolean flag = false;

    public void write(){
        a = 1;
        flag = true;
    }

    public void read(){
        if(flag){
            int i = a * a;
            System.out.printf("i 最终的值 "+ i);
        }
    }

    public static void main(String[] args) {
        ReOrderExample reOrderExample = new ReOrderExample();
        Thread t1 = new Thread(()-> {
            reOrderExample.write();
        },"线程1");

        Thread t2 = new Thread(()-> {
            reOrderExample.read();
        },"线程2");


        t1.start();
        t2.start();


    }
}
