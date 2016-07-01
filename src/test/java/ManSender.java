
import com.liubo.demo.rabbitmq.AmqpConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;


/**
 * Created by Administrator on 2016/5/12.
 */
public class ManSender {

    protected Channel channel;
    protected Connection connection;
    protected String queueName;

    // 消息队列名称
    private final static String userName = "guest";
    private final static String password = "guest";
    private final static String virtualHost = "/";
    private final static int port = 5672;
    private final static String host = "127.0.0.1";

    public ManSender(String queueName) throws Exception {
        this.queueName = queueName;
        // 创建链接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setVirtualHost(virtualHost);
        factory.setUsername(userName);
        factory.setPassword(password);
        // 创建链接
        connection = factory.newConnection();

        // 创建消息信道
        channel = connection.createChannel();

        // 生命消息队列
        channel.queueDeclare(queueName, true, false, false, null);
    }

    /**
     * 方法说明 关闭channel和connection。并非必须，因为隐含是自动调用的。
     *
     * @throws Exception
     * @author liubo
     * @version create time：2015年8月7日 下午9:59:41
     */
    public void close() throws Exception {
        this.channel.close();
        this.connection.close();
    }


    public void sendMessage(Message object) throws Exception {
        // 发布消息，第一个参数表示路由（Exchange名称），未""则表示使用默认消息路由
        channel.basicPublish(AmqpConfig.EXCHANGE, AmqpConfig.ROUTINGKEY, null,
                object.getBody());

    }

    public static void main(String[] args) throws Exception {
        ManSender manSender = new ManSender("spring-boot-queue");
        String str = "hello,this is a message";
        byte[] bytes = str.getBytes();
        Message message = new Message(bytes, new MessageProperties());
        for (int i = 0; i < 10; i++) {
            manSender.sendMessage(message);
        }
        manSender.close();
    }


}
