package com.liubo.rabbit.direct;

import org.junit.Test;

/**
 * Created by hzlbo on 2016/12/22 0022.
 */
public class DirectTest {

    @Test
    public void testSend() throws Exception {
        EmitLogDirect.send("info", "信息");
        EmitLogDirect.send("error", "错误信息");
        EmitLogDirect.send("info", "信息");
        EmitLogDirect.send("info", "信息");
    }

    @Test
    public void testReceive() throws Exception {

    }

}
