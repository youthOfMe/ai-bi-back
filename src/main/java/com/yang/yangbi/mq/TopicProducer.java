package com.yang.yangbi.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

public class TopicProducer {

    public static final String EXCHANGE_NAME = "topic-exchange";

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

        // 声明交换机
        channel1.exchangeDeclare(EXCHANGE_NAME, "topic");

        // 进行生产消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            String[] strings = userInput.split(" ");
            if (strings.length < 1) {
                continue;
            }
            String message = strings[0];
            String routingKey = strings[1];

            channel1.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + " with routing:" + routingKey + "'");
        }
    }
}
