package com.yang.yangbi.Throttling;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

public class SlideTimeWindow {

    /**
     * 定义初始化记录的事件
     */
    public static long recordTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000;

    /**
     * 获取限流的时间戳 毫秒 十秒内内只能访问五次
     */
    public static final long LIMIT_TIME = LocalDateTime.now().plusSeconds(10).toEpochSecond(ZoneOffset.UTC) * 1000 - recordTime;

    /**
     * 分割为十个时间单位
     */
    public static final int UNITS_OF_TIME = 10;

    /**
     * 定义一小时能可以进行调用的次数
     */
    public static final int LIMIT_TIMES = 5;

    /**
     * 存储用户调用的次数
     */
    public static int[] userTimeArr = new int[UNITS_OF_TIME];

    /**
     * 数组求和
     */
    public static int arrSum() {
        return Arrays.stream(userTimeArr).sum();
    }

    /**
     * 根据时间进行数组清零
     * @param number 时间
     */
    public static void arrCleanZero(int number) {
        for (int i = 0; i <= number; i++) {
            userTimeArr[i] = 0;
        }
    }

    /**
     * 定义滑动窗框限流
     */
    public static boolean slideTimeWindowLimit() {
        // 获取当前时间
        long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000;

        // 1. 当前时间 - 上次调用记录时间 > 10秒 清零 -> 允许调用
        if (now - recordTime >= LIMIT_TIME) {
            userTimeArr = new int[UNITS_OF_TIME];
        }

        // 2. 当前时间 - 上次调用记录时间 < 10秒
        if (now - recordTime < LIMIT_TIME) {
            // 2.1 数组求和 > 限制次数 滚蛋
            if (arrSum() >= LIMIT_TIMES) {
                // 一定要更新记录时间
                recordTime = now;
                return false;
            }
            // 2.2 数组求和 < 限制次数 允许调用
        }
        // 3. 调用规整

        // 3.0 给哪个时间加这个次数
        int timeNumber = (int) Math.floor((double) (now - recordTime) / 1000);
        // 处理过界情况
        if (timeNumber > 10) {
            timeNumber -= 10;
        }

        // 3.1 统一进行加1
        userTimeArr[timeNumber] += 1;
        // 3.2 更新记录时间
        recordTime = now;

        return true;
    }

    public static void getNiuma() {
        System.out.println("牛马");
    }

    @Test
    void testGetNiuma() throws InterruptedException {
        boolean result;

        for (int i = 0; i <= 10; i++) {
            Thread.sleep(500);
            result = slideTimeWindowLimit();
            if (!result) {
                System.out.println("超出调用限制");
            } else {
                getNiuma();
            }
        }

        Thread.sleep(5000);

        for (int i = 0; i <= 10; i++) {
            Thread.sleep(500);
            result = slideTimeWindowLimit();
            if (!result) {
                System.out.println("超出调用限制");
            } else {
                getNiuma();
            }
        }

        Thread.sleep(10000);

        for (int i = 0; i <= 10; i++) {
            Thread.sleep(500);
            result = slideTimeWindowLimit();
            if (!result) {
                System.out.println("超出调用限制");
            } else {
                getNiuma();
            }
        }
    }
}
