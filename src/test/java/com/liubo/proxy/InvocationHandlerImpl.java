package com.liubo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hzlbo on 2017/1/17 0017.
 */
public class InvocationHandlerImpl implements InvocationHandler {

    private Object subject;

    public InvocationHandlerImpl(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用之前的工作");
        System.out.println("Method :" + method);
        Object returnValue = method.invoke(subject, args);
        System.out.println("调用之后的工作");
        return returnValue;
    }
}
