package com.yang.yangbi.Throttling.multithread;

import com.yang.yangbi.Throttling.TokenBucket;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    private String name;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        return TokenBucket.getNiuma(name);
    }
}
