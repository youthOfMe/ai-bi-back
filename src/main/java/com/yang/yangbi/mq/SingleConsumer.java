package com.yang.yangbi.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class SingleConsumer {

    public static final String QUEUE_NAME = "hello2";

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
        Channel channel = connection.createChannel();

        // 创建队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C ");
        // 定义了如何进行处理消息
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        // 消费消息, 会持续阻塞
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
