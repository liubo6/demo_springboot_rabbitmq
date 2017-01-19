package com.liubo.concurrent;

/**
 * Created by hzlbo on 2017/1/5 0005.
 */
public class ProgressListener {

    public void onProgress(int n) {
        System.out.println("progress " + n + " %");
    }
}
