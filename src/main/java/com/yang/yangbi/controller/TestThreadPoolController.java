package com.yang.yangbi.controller;

import com.yang.yangbi.common.BaseResponse;
import com.yang.yangbi.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 测试线程池
 */
@RestController
@RequestMapping("/testthreadpool")
@Slf4j
public class TestThreadPoolController {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 测试线程池
     *
     * @return
     */
    @GetMapping("/test")
    public BaseResponse<Boolean> testThreadPool() {
        CompletableFuture.runAsync(() -> {
            log.info("线程池启动！");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, threadPoolExecutor);

        return ResultUtils.success(true);
    }
}
