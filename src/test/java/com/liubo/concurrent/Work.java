package com.liubo.concurrent;

/**
 * Created by hzlbo on 2017/1/6 0006.
 */
public class Work implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
