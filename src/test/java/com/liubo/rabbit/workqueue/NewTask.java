package com.liubo.rabbit.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeoutException;

/**
 * 简单生产者生成复杂消息
 * Created by hzlbo on 2016/12/22 0022.
 */
public class NewTask {
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
        //发送的消息
        for (int i = 0; i < 10; i++) {

            String message = "Hello";
            int num = ThreadLocalRandom.current().nextInt(10);
            String append = "";
            for (int j = 0; j < num; j++) {
                append = append + ".";
            }
            message = message + (append);

            //参数1 交换机；参数2 路由键；参数3 基础属性,（持久化方式）；参数4 消息体
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(Thread.currentThread().getName() + "[send]" + message);
        }
        channel.close();
        connection.close();


    }
}
