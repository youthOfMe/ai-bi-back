package com.yang.yangbi.Throttling;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class FixedTimeWindows {

    public static long startTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000;

    /**
     * 获取限流的时间戳 毫秒 一小时内只能访问十次
     */
    public static final long LIMIT_TIME = LocalDateTime.now().plusSeconds(10).toEpochSecond(ZoneOffset.UTC) * 1000 - startTime;

    /**
     * 定义一小时能可以进行调用的次数
     */
    public static final int LIMIT_TIMES = 5;

    /**
     * 存储用户调用的次数
     */
    public static int userTimes = 0;

    public static void getNiuma() {
        System.out.println("牛马");
    }

    @Test
    void testGetNiuma() throws InterruptedException {

        for (int i = 0; i <= 20; i++) {
            long userTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000;
            if (userTime - startTime < LIMIT_TIME) {
                Thread.sleep(1 * 1000);
                if (userTimes >= LIMIT_TIMES) {
                    System.out.println("超出调用时间");
                    continue;
                }
                userTimes++;
                getNiuma();

            } else {
                // 更新时间限制
                System.out.println("更新时间限制");
                startTime += 10 * 1000;
                userTimes = 0;
            }
        }
    }
}
