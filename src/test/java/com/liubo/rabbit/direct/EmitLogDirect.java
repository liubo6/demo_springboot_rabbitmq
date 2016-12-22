package com.liubo.rabbit.direct;

import com.rabbitmq.client.Channel;

/**
 * 直接交换机生产者
 * Created by hzlbo on 2016/12/22 0022.
 */
public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void send(String routingKey, String message) throws Exception {
        //1 建立channel
        Channel channel = ChannelUtils.getInstance();

        //2 声明交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);

        //3 发送消息
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());


    }
}
