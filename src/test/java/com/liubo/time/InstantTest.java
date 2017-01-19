package com.liubo.time;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hzlbo on 2017/1/16 0016.
 */
public class InstantTest {

    @Test
    public void instantTest() throws Exception {
        int threadPollSize = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executor = Executors.newFixedThreadPool(threadPollSize);


    }
}
