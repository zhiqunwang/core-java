package org.elvis.wang.java.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wangzhiqun on 2018/1/9.
 *
 * 生成动态代理类class
 */
public class ProxyUtils {

    /**
     * Save proxy class to path
     *
     * @param path path to save proxy class
     * @param proxyClassName name of proxy class
     * @param interfaces interfaces of proxy class
     * @return
     */
    public static boolean saveProxyClass(String path, String proxyClassName, Class[] interfaces) {
        if (proxyClassName == null || path == null) {
            return false;
        }

        // get byte of proxy class
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyClassName, interfaces);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] args){
        CalculatorImpl calculatorImpl = new CalculatorImpl();
        ProxyUtils.saveProxyClass("D:/test.class",
                "CalculatorImpl",calculatorImpl.getClass().getInterfaces());
    }

}
