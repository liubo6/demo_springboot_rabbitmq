package com.liubo.demo.rabbitmq;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
@EnableRabbit
@Configuration
public class AmqpConfig {
    public static final String EXCHANGE = "spring-boot-exchange";
    public static final String ROUTINGKEY = "spring-boot-routingKey";
    public static final String QUEUE_NAME = "spring-boot-queue";

    //RabbitMQ的配置信息
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;


    //建立一个连接容器，类型数据库的连接池
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(true);// 确认机制
        //发布确认，template要求CachingConnectionFactory的publisherConfirms属性设置为true
        return connectionFactory;
    }

    // RabbitMQ的使用入口
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(this.connectionFactory());
        template.setMessageConverter(this.jsonMessageConverter());
        template.setMandatory(true);
        return template;
    }

    /**
     * 交换机
     * 针对消费者配置
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     * HeadersExchange ：通过添加属性key-value匹配
     * DirectExchange:按照routingkey分发到指定队列
     * TopicExchange:多关键字匹配
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    /**
     * 队列
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true); //队列持久

    }


    /**
     * 绑定
     *
     * @return
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue()).to(exchange()).with(AmqpConfig.ROUTINGKEY);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 声明一个监听容器
     *
     * @return
     */
    @Bean(name="rabbitListenerContainer")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setMessageConverter(jsonMessageConverter());
        factory.setConnectionFactory(connectionFactory());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);//设置消费者手动应答
        return factory;
    }
}
