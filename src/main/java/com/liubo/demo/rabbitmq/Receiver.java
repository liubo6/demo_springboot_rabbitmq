package com.liubo.demo.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 监听的业务类，实现接口MessageListener
 * Created by hzlbo on 2016/7/1.
 */

public class Receiver implements MessageListener {

    public void onMessage(Message message) {
        System.out.println("========start=========");
        System.out.println(message);
        System.out.println("=========end==========");
    }
}
