package com.yang.yangbi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
@Slf4j
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("开始创建redis模板对象");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置redis的连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置redis key的序列化器 插入和查询数据使用同一个序列化器就不会出现乱码的情况
        redisTemplate.setKeySerializer(RedisSerializer.string());
        return redisTemplate;
    }
}
