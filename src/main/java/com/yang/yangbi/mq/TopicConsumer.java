package com.yang.yangbi.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class TopicConsumer {

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

        // 创建队列
        String queueName = "frontend_queue";
        channel1.queueDeclare(queueName, true, false, false, null);
        channel1.queueBind(queueName, EXCHANGE_NAME, "#.前端.#");

        // 创建队列
        String queueName2 = "backend_queue";
        channel1.queueDeclare(queueName2, true, false, false, null);
        channel1.queueBind(queueName2, EXCHANGE_NAME, "#.后端.#");

        // 创建队列
        String queueName3 = "product_queue";
        channel1.queueDeclare(queueName3, true, false, false, null);
        channel1.queueBind(queueName3, EXCHANGE_NAME, "#.产品.#");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback xiaoaDeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [xiaoa] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        DeliverCallback xiaobDeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [xiaob] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        DeliverCallback xiaocDeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [xiaoc] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        channel1.basicConsume(queueName, true, xiaoaDeliverCallback, consumerTag -> {
        });
        channel1.basicConsume(queueName2, true, xiaobDeliverCallback, consumerTag -> {
        });
        channel1.basicConsume(queueName3, true, xiaocDeliverCallback, consumerTag -> {
        });
    }
}
