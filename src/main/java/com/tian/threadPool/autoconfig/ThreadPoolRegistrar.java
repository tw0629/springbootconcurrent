package com.tian.threadPool.autoconfig;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
//import edison.io.metric.threadpool.ThreadPoolUtils;
import com.tian.threadPool.threadpool.ThreadPoolUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class ThreadPoolRegistrar implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolRegistrar.class);

    private static Map<String, ThreadPoolExecutor> CONTAINER = Maps.newConcurrentMap();

    public static void register(String threadPoolName, ThreadPoolExecutor executorService) {
        Preconditions.checkNotNull(executorService);
        CONTAINER.put(StringUtils.defaultString(threadPoolName, UUID.randomUUID().toString()), executorService);
    }

    public static ThreadPoolExecutor getInstance(String threadPoolName) {
        return CONTAINER.get(threadPoolName);
    }

    public static Map<String, ThreadPoolExecutor> threadPools(){
        return CONTAINER;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (ExecutorService executorService : CONTAINER.values()) {
                try {
                    ThreadPoolUtils.gracefulShutdown(executorService, 10, TimeUnit.SECONDS);
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        }));
    }
}
