package com.liubo.demo.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 监听的业务类，实现接口MessageListener
 * Created by hzlbo on 2016/7/1.
 */
@Component
@RabbitListener(containerFactory = "rabbitListenerContainer", queues = AmqpConfig.QUEUE_NAME)
public class Receiver {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void receiverMsg(PersonDO personDO) {
        logger.info("##### = {}", personDO);
    }
}
