package com.liubo.concurrent;

/**
 * Created by hzlbo on 2017/1/6 0006.
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Work(),"t1");
        Thread t2 = new Thread(new Work(),"t2");
        Thread t3 = new Thread(new Work(),"t3");

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();

    }
}
