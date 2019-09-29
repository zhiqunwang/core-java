package org.elvis.wang.java.bigdecimal;

import java.math.BigDecimal;

/**
 * Created by zhiqun.wang on 2019/7/24 15:09
 */
public class Big {

    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal(275);
        BigDecimal rate = new BigDecimal("0.0018");
        String ss = decimal.multiply(rate).toString();
        System.out.println(ss);
        System.out.println(new BigDecimal(ss).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).longValue());
    }
}
