package org.elvis.wang.java.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by wangzhiqun on 2018/1/9.
 */
public class ProxyTest {

    public static void main(String[] args){
        CalculatorImpl calculatorImpl = new CalculatorImpl();
        CalculatorHandler handler = new CalculatorHandler(calculatorImpl);
        Calculator calculator = (Calculator) Proxy.newProxyInstance(
                calculatorImpl.getClass().getClassLoader(), calculatorImpl.getClass().getInterfaces(), handler);
        System.out.println(calculator.add(1,2));
        System.out.println(calculator.minus(1, 2));
    }
}
