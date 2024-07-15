package com.yang.yangbi.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class DirectConsumer {
    public static final String EXCHANGE_NAME = "direct-exchange";

    public static void main(String[] args) throws Exception {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("49.232.17.169");
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setPort(5673);
        factory.setConnectionTimeout(30000000);
        factory.setHandshakeTimeout(30000000);
        Connection connection = factory.newConnection();
        Channel channel1 = connection.createChannel();

        // 声明交换器
        channel1.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 创建队列，随机分配一个队列名称
        String queueName = "xiaoyu_queue";
        channel1.queueDeclare(queueName, true, false, false, null);
        channel1.queueBind(queueName, EXCHANGE_NAME, "xiaoyu");

        // 创建队列，随机分配一个队列名称
        String queueName2 = "xiaopi_queue";
        channel1.queueDeclare(queueName2, true, false, false, null);
        channel1.queueBind(queueName2, EXCHANGE_NAME, "xiaopi");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback xiaoyuDeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [xiaoyu] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        DeliverCallback xiaopiDeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [xiaopi] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        channel1.basicConsume(queueName, true, xiaoyuDeliverCallback, consumerTag -> {
        });
        channel1.basicConsume(queueName2, true, xiaopiDeliverCallback, consumerTag -> {
        });
    }
}
