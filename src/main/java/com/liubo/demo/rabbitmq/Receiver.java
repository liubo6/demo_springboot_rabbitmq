package com.liubo.demo.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liubo.demo.rabbitmq.person.dao.PersonDao;
import com.liubo.demo.rabbitmq.person.model.PersonDO;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * 监听的业务类，实现接口MessageListener
 * Created by hzlbo on 2016/7/1.
 */

//@RabbitListener(containerFactory = "rabbitListenerContainer", queues = AmqpConfig.QUEUE_NAME)
public class Receiver implements ChannelAwareMessageListener {
    public Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private PersonDao personDao;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        try {

            logger.info("##### = {}", new String(message.getBody()));

            ObjectMapper objectMapper = new ObjectMapper();
            PersonDO personDO = objectMapper.readValue(new String(message.getBody()), PersonDO.class);
            //模拟耗时操作
            //TimeUnit.SECONDS.sleep(10);
            boolean result = personDao.addPerson(personDO) > 0;
            if (!result) {
                logger.error("消息消费失败");
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
