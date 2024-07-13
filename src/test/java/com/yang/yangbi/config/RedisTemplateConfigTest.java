package com.yang.yangbi.config;

import com.yang.yangbi.model.entity.Chart;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RedisTemplateConfigTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testListAndMap() {

        // 构造对象
        Chart chart = new Chart();
        chart.setId(123456l);

        // 构造hashMap
        Map<String, Chart> chartHashMap = new HashMap<>();
        chartHashMap.put("123156", chart);

        // 存入redis
        redisTemplate.opsForHash().putAll("redisMapTest", chartHashMap);

        // 获取缓存内容
        Map<Object, Object> resultMap = redisTemplate.opsForHash().entries("redisMapTest");

        Chart redisChart = (Chart) resultMap.get("123156");
        System.out.println(redisChart.getId());
    }
}