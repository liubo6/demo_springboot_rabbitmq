package com.liubo.rabbit.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by hzlbo on 2016/12/22 0022.
 */
public class ChannelUtils {

    private static Channel channel = null;

    public static Channel getInstance() throws IOException, TimeoutException {
            return getChannel();
    }

    private static Channel getChannel() throws IOException, TimeoutException {
        //建立连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置连接地址
        factory.setHost("seaof-153-125-234-173.jp-tokyo-10.arukascloud.io");
        factory.setPort(31084);
        //获取连接
        Connection connection = factory.newConnection();
        //获取渠道
        Channel channel = connection.createChannel();
        return channel;
    }

    public static void close() throws IOException, TimeoutException {
        channel.close();
    }
}
