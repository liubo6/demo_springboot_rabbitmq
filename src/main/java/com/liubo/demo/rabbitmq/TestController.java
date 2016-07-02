package com.liubo.demo.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hzlbo on 2016/7/2.
 */
@RestController
public class TestController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String testSend() {
        Message message = MessageBuilder.withBody(new PersonDO("1", "10086", "liubo", 20).toString().getBytes()).setMessageId("123").build();
        rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE, AmqpConfig.ROUTINGKEY, message);
        return "ok";
    }
}
