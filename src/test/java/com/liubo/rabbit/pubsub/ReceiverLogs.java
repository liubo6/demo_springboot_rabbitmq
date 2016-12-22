package com.liubo.rabbit.pubsub;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 发布订阅消费者
 * Created by hzlbo on 2016/12/22 0022.
 */
public class ReceiverLogs {
    private static final String EXCHANGE_NAME = "logs";


    public static void main(String[] args) throws Exception {
        //建立连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置连接地址
        factory.setHost("seaof-153-125-234-173.jp-tokyo-10.arukascloud.io");
        factory.setPort(31084);
        //获取连接
        Connection connection = factory.newConnection();
        //获取渠道
        Channel channel = connection.createChannel();

        //声明交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        System.out.println("waiting for messages");

        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String message = new String(body, "UTF-8");
                System.out.println("[reviced]" + message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
