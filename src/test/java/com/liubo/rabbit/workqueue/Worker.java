package com.liubo.rabbit.workqueue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 多消费者消费消息，手动确认消息
 * Created by hzlbo on 2016/12/22 0022.
 */
public class Worker {
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
        //一次只接受一个未处理的消息
        channel.basicQos(1);
        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("[recevied ]" + message);
                try {
                    doWork(message);
                } finally {
                    System.out.println("[recevied] Done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        //参数1，队列名称；参数2，是否自动确认；参数3 消费者
        //收动确认消息
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }


    private static void doWork(String task) {
        for (char c : task.toCharArray()) {
            if (c == '.') {
                try {
                    //模拟耗时操作
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
