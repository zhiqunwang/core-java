package org.elvis.wang.java.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by zhiqun.wang on 2019/6/23 21:05
 *
 *  工作窃取算法&双端队列&切分的足够小
 */
public class ForkJoinCountTaskExample extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2; //最小切分大小

    private int start;

    private int end;

    public ForkJoinCountTaskExample(int start,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;

        if(canCompute){
            for(int i = start;i <= end;i++){
                sum += i;
            }
        }else {
            int middle = (start + end)/2;
            ForkJoinCountTaskExample leftTask = new ForkJoinCountTaskExample(start,middle);
            ForkJoinCountTaskExample rightTask = new ForkJoinCountTaskExample(middle+1,end);
            leftTask.fork();
            rightTask.fork();
            sum = leftTask.join() + rightTask.join();
        }
        return sum;
    }


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinCountTaskExample task = new ForkJoinCountTaskExample(1,4);

        Future<Integer> result = forkJoinPool.submit(task);
        try {
            result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
