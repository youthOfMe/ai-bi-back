package com.yang.yangbi.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;

public class MultiProducer {

    public static final String TASK_QUEUE_NAME = "multi_queue";

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

        // 发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.nextLine();
            channel.basicPublish("", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));
            System.out.println("[x] Sent '" + message + "'");
        }
    }
}
