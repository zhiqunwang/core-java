package org.elvis.wang.java.concurrent.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wangzhiqun on 2017/11/19.
 */
public class WaitTest {
    public static Object u1 = new Object();
    public static Object u2 = new Object();
    public static List<Integer> list = new ArrayList<Integer>();

    public int size = 5;

    public static int get() throws InterruptedException {
        synchronized (u1){
            Integer i = null;
            if(list.size()>0){
                 i =  list.get(list.size()-1);
            }
            if(i==null){
                System.out.println("get waiting!");
                u1.wait();
                i =  list.get(list.size()-1);
                System.out.println("get notify!"+i);
            }
            System.out.println("get.."+list);
            list.remove(list.size()-1);
            u1.notify();
            return  i;
        }
    }

    public static void set() throws InterruptedException{
        synchronized (u1){
            if(list.size()==5) {
                System.out.println("Set waiting!");
                u1.wait();
                System.out.println("Set notify!");
            }
            Integer num = new Random().nextInt();
            list.add(num);
            System.out.println("Set "+num);
            u1.notify();
        }
    }


    public static class GThread extends Thread{
        public void run(){
            while (true){
                try {
                    get();
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Sthread extends Thread{
        public void run(){
            while (true){
                try {
                    set();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void main(String[] args){

        GThread t1 = new GThread();
        Sthread t2 = new Sthread();

        t1.start();
        t2.start();
    }




}
