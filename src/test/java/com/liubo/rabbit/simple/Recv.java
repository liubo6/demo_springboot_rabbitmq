package com.liubo.rabbit.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单接收例子
 * Created by hzlbo on 2016/12/22 0022.
 */
public class Recv {
    //队列名称
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //建立连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置连接地址
        factory.setHost("seaof-153-125-234-173.jp-tokyo-10.arukascloud.io");
        factory.setPort(31084);
        //获取连接
        Connection connection = factory.newConnection();
        //获取渠道
        Channel channel = connection.createChannel();
        //声明队列，如果不存在就新建
        //参数1队列名称;参数2是否持久化;参数3排他性队列，连接断开自动删除;参数4是否自动删除;参数5.参数
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println("[recv] wating for messages");
        Consumer consumer = new DefaultConsumer(channel) {
            /**
             * No-op implementation of {@link Consumer#handleDelivery}.
             *
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("[recevied ]" + message);
            }
        };
        //参数1，队列名称；参数2，是否自动确认；参数3 消费者
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
