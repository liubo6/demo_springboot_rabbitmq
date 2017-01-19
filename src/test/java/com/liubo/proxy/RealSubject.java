package com.liubo.proxy;

/**
 * Created by hzlbo on 2017/1/17 0017.
 */
public class RealSubject implements Subject {
    @Override
    public String sayHello() {
        return "hello";
    }

    @Override
    public String sayGoodBye() {
        return "good bye";
    }
}
