package org.elvis.wang.java.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * Created by zhiqun.wang on 2019/6/20 18:17
 */
public class LongAdderTest {
    public static void main(String[] args) {
        LongAdder adder = new LongAdder();
        adder.add(2L);

    }
}
