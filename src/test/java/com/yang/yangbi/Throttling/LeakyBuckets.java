package com.yang.yangbi.Throttling;

import java.util.ArrayList;
import java.util.List;

public class LeakyBuckets {

    /**
     * 定义固定速率
     */
    public static final int SPEED = 1;

    /**
     * 定义桶容量
     */
    public static final int CAPACITY = 10;

    /**
     * 定义桶
     */
    public static List<Integer> bucketArr = new ArrayList<>();

    /**
     * 定义漏桶限流
     * false 执行限流 true 不执行限流
     * @return
     */
    public static boolean leakyBucketsLimit() {
        // 1. 将请求加入到漏桶中
        // 判断桶是否满了
        if (bucketArr.size() >= CAPACITY) {
            // 执行限流
            System.out.println("============== 桶满啦 ==============");
            return false;
        }
        // 实际过程中可以进行加入请求标识等
        bucketArr.add(1);

        // 2. 以固定流速进行处理请求
        // 这里只进行实现单线程的 实际请求中固定速率高的时候应该开多线程
        return true;
    }

    public static void getNiuma() {
        System.out.println("牛马");
    }
}
