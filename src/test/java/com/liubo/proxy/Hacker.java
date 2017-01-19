package com.liubo.proxy;

import org.assertj.core.internal.cglib.proxy.MethodInterceptor;
import org.assertj.core.internal.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by hzlbo on 2017/1/17 0017.
 */
public class Hacker implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("I am a hacker start");
        methodProxy.invokeSuper(o, objects);
        System.out.println("I am a hacker end");
        return null;
    }
}
