package com.yang.yangbi.Throttling;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class FixedTimeWindows {

    public static long recordTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000;

    /**
     * 获取限流的时间戳 毫秒 十秒内只能访问五次
     */
    public static final long LIMIT_TIME = LocalDateTime.now().plusSeconds(10).toEpochSecond(ZoneOffset.UTC) * 1000 - recordTime;

    /**
     * 定义一小时能可以进行调用的次数
     */
    public static final int LIMIT_TIMES = 5;

    /**
     * 存储用户调用的次数
     */
    public static int userTimes = 0;

    /**
     * 实现固定窗口限流算法
     */
    public static boolean fixedTimeWindowLimit() {
        // 获取当前时间
        long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000;

        if (now - recordTime > LIMIT_TIME) {
            // 更新时间限制
            System.out.println("更新时间限制");
            userTimes = 0;
            recordTime = now;
        } else {
            if (userTimes >= LIMIT_TIMES) {
                return false;
            }
        }
        userTimes++;
        return true;
    }

    public static void getNiuma() {
        System.out.println("牛马");
    }

    @Test
    void testGetNiuma() throws InterruptedException {

        for (int i = 0; i <= 20; i++) {
            Thread.sleep(1 * 1000);
            boolean result = fixedTimeWindowLimit();
            if (!result) {
                System.out.println("超出调用限制");
            } else {
                getNiuma();
            }
        }
    }
}
