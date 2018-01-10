package org.elvis.wang.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wangzhiqun on 2018/1/9.
 */
public class CalculatorHandler implements InvocationHandler{
    //被代理对象
    private Object obj;

    public CalculatorHandler(Object obj){
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("in calculatorhandler, before invocation");
        Object ret = method.invoke(obj,args);
        System.out.println("in calculationhandler, after invocation");
        return ret;
    }
}
