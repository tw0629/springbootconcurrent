package com.tian.threadPool.autoconfig;

//import edison.io.metric.threadpool.ThreadPoolBuilder;
import com.tian.threadPool.threadpool.ThreadPoolBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService httpThreadPool() {
        return ThreadPoolBuilder.pool().setCoreSize(5).setMaxSize(9)
                .setDaemon(false).setKeepAliveSecs(60).setThreadNamePrefix("httpTask")
                .setUseTtl(true).setWorkQueue(new ArrayBlockingQueue<>(1000))
                .setThreadPoolName("httpPool").build();
    }
}
