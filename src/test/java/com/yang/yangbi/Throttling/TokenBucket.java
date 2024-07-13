package com.yang.yangbi.Throttling;

import com.yang.yangbi.Throttling.multithread.MyCallable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TokenBucket {

    /**
     * 定义固定速率
     */
    public static final int SPEED = 1;

    /**
     * 定义令牌前缀
     */
    public static final String TOKEN_FRONT = "token";

    /**
     * 定义令牌桶
     */
    public static List<String> tokenBucket = new ArrayList<>();

    /**
     * 生成令牌
     * @param key 关键key
     */
    public static String productToken(String key) {
        String token = TOKEN_FRONT + key;
        tokenBucket.add(token);
        return token;
    }


    /**
     * 初始化令牌
     * @param number 初始化令牌数量
     */
    public static void initToken(int number) {
        for (int i = 0; i < number; i++) {
            productToken("niuma" + i);
        }
    }

    /**
     * 校验令牌
     * @param token 令牌
     * @return false 限流 true 成功
     */
    public static boolean tokenBucketLimit(String token) {
        // 校验无令牌
        if (token == null || token.isEmpty() || token.equals(" ") || !tokenBucket.contains(token)) {
            return false;
        }

        return true;
    }

    public static String getNiuma(String name) {
        return "线程名: " + name + ", 牛马。";
    }

    @Test
    void testGetNiuma() throws ExecutionException, InterruptedException {
        // 初始化令牌
        initToken(3);

        // 初始化多线程
        MyCallable callable1 = new MyCallable("线程1");
        MyCallable callable2 = new MyCallable("线程2");
        FutureTask<String> futureTask1 = new FutureTask<>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<>(callable2);

        // 校验令牌
        if (tokenBucketLimit(TOKEN_FRONT + "niuma1")) {
            new Thread(futureTask1).start();
            new Thread(futureTask2).start();
            System.out.println(futureTask1.get());
            System.out.println(futureTask2.get());
        }

        if (tokenBucketLimit("duaisbdiasu")) {
            new Thread(futureTask2).start();
            System.out.println(futureTask2.get());
        }
    }
}
