package com.liubo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by hzlbo on 2017/1/17 0017.
 */
public class Demo {
    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        InvocationHandler handler = new InvocationHandlerImpl(realSubject);
        ClassLoader loader = realSubject.getClass().getClassLoader();
        Class[] interfaces = realSubject.getClass().getInterfaces();

        Subject subject = (Subject) Proxy.newProxyInstance(loader, interfaces, handler);
        System.out.println("动态代理对象的类型" + subject.getClass().getName());

        String str = subject.sayHello();
        System.out.println(str);
    }
}
