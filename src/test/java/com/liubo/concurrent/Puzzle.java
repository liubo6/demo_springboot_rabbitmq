package com.liubo.concurrent;

/**
 * Created by hzlbo on 2017/1/4 0004.
 */
public class Puzzle {
    static boolean answerReady = false;
    static int answer = 0;

    static Thread t1 = new Thread() {
        @Override
        public void run() {
            answer = 40;
            answerReady = true;
        }
    };

    static Thread t2 = new Thread() {
        @Override
        public void run() {
            if (answerReady) {
                System.out.println("answer is " + answer);
            } else {
                System.out.println("I don't know");
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
