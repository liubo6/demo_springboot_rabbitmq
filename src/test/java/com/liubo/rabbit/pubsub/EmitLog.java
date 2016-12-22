package com.liubo.rabbit.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * 发布订阅生产者
 * Created by hzlbo on 2016/12/22 0022.
 */
public class EmitLog {
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

        //产生随机数字
        String message = RandomStringUtils.randomNumeric(8);

        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());

        channel.close();
        connection.close();

    }
}
