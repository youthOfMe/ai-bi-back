package com.yang.yangbi.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class FanoutConsumer {

    public static final String EXCHANGE_NAME = "fanout-exchange";

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
        Channel channel2 = connection.createChannel();

        // 声明交换机
        channel1.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 创建队列，随机分配一个队列名称
        String queueName = "xiaowang_queue";
        channel1.queueDeclare(queueName, true, false, false, null);
        channel1.queueBind(queueName, EXCHANGE_NAME, "");

        String queueName2 = "xiaoli_queue";
        channel2.queueDeclare(queueName2, true, false, false, null);
        channel2.queueBind(queueName2, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 获取到信息
        DeliverCallback deliverCallback1 = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [小王] Received '" + message + "'");
        };

        DeliverCallback deliverCallback2 = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [小李] Received '" + message + "'");
        };

        // 监听队列进行消费
        channel1.basicConsume(queueName, true, deliverCallback1, consumberTag -> {});
        channel2.basicConsume(queueName2, true, deliverCallback2, consumberTag -> {});
    }
}
