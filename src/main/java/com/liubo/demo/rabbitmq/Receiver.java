package com.liubo.demo.rabbitmq;

import com.liubo.demo.rabbitmq.person.model.PersonDO;
import com.liubo.demo.rabbitmq.person.service.PersonService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * 监听的业务类，实现接口MessageListener
 * Created by hzlbo on 2016/7/1.
 */
@Component
//@RabbitListener(containerFactory = "rabbitListenerContainer", queues = AmqpConfig.QUEUE_NAME)
public class Receiver implements ChannelAwareMessageListener {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PersonService personService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        try {
            //模拟耗时操作
            TimeUnit.SECONDS.sleep(5);
            logger.info("##### = {}", new String(message.getBody()));
            //
            SimpleMessageConverter simpleMessageConverter = new SimpleMessageConverter();
            PersonDO personDO = (PersonDO) simpleMessageConverter.fromMessage(message);

//            ObjectMapper objectMapper = new ObjectMapper();
//            PersonDO personDO = objectMapper.readValue(new String(message.getBody()), PersonDO.class);
            boolean result = personService.addPerson(personDO);
            if (!result) {
                logger.error("消息消费失败");
                throw new Exception("消息消费失败");
            } else {
                logger.info("消息消费成功");
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//手动消息应答
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);//true 重新放入队列
        }
    }


}
