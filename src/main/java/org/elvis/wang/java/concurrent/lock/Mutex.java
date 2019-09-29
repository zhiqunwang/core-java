package org.elvis.wang.java.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhiqun.wang on 2019/6/23 21:46
 */
public class Mutex implements Lock {



    private static  class Sync extends AbstractQueuedSynchronizer{
        private static final long serialVersionUID = 9077937924035919769L;

        public boolean tryAcquire(int acquires){


            return false;
        }




    }
    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
