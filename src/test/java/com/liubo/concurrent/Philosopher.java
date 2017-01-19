package com.liubo.concurrent;

import java.util.Random;

/**
 * Created by hzlbo on 2017/1/5 0005.
 */
public class Philosopher extends Thread {

    private Chopstick left, right;
    private Random random;


    public Philosopher(Chopstick left, Chopstick right) {
        this.left = left;
        this.right = right;
        random = new Random();
    }

    class Chopstick {
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(100));
                synchronized (left) {
                    synchronized (right) {
                        Thread.sleep(random.nextInt(100));
                    }
                }
            }
        } catch (InterruptedException e) {

        }
    }

}
