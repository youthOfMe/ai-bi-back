package com.yang.yangbi.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class SingleSend {

    public static final String QUEUE_NAME = "hello2";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("49.232.17.169");
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setPort(5673);
        factory.setConnectionTimeout(30000000);
        factory.setHandshakeTimeout(300000000);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "HelloWorld";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Send '" + message + "'");
        }
    }
}
