package cn.ipx.consumer;

import cn.ipx.producer.ProducerClient;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;

import java.util.Properties;

/**
 * ${DESCRIPTION}
 *
 * @author joels
 * @create 2017-03-23 11:24
 **/
public class ConsumerTest {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(ProducerClient.class.getClassLoader().getResourceAsStream("aliyunMq.aliyunMq.properties"));
        properties.put(PropertyKeyConst.ConsumerId, properties.getProperty("aliyunMq.ConsumerId"));
        properties.put(PropertyKeyConst.AccessKey, properties.getProperty("aliyunMq.AccessKey"));
        properties.put(PropertyKeyConst.SecretKey, properties.getProperty("aliyunMq.SecretKey"));
        Consumer consumer = ONSFactory.createConsumer(properties);
//        consumer.subscribe("Topic_IPX_Test", "*", new MessageListener() {
//            public Action consume(Message message, ConsumeContext context) {
//                System.out.println("Receive: " + message);
//                return Action.CommitMessage;
//            }
//        });
        consumer.subscribe(properties.getProperty("aliyunMq.Topic"), "*", (message, context) -> {
            System.out.println("Receive: " + message);
            System.out.println("message: " + new String(message.getBody()));
            return Action.CommitMessage;
        });
        consumer.start();
        System.out.println("Consumer Started");
    }
}
