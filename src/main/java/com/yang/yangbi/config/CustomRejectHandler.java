package com.yang.yangbi.config;

import com.yang.yangbi.common.ErrorCode;
import com.yang.yangbi.exception.BusinessException;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池的拒绝策略
 */
public class CustomRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        throw new BusinessException(ErrorCode.SYSTEM_ERROR, "系统负载过载");
    }
}
