package com.liubo.rabbit.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by hzlbo on 2016/12/22 0022.
 */
public class ReceiveLogsDirect {
    private static final String EXCHANGE_NAME = "direct_logs";
    /**
     * 添加main参数 绑定不同队列
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        //1 建立channel
        Channel channel = ChannelUtils.getInstance();
        String queueName = channel.queueDeclare().getQueue();

        List<String> bindkeys = Arrays.asList(args);
        for (String bindKey : bindkeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, bindKey);
        }
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
