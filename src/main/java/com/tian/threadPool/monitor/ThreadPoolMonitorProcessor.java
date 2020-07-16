package com.tian.threadPool.monitor;

import com.tian.threadPool.autoconfig.ThreadPoolRegistrar;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@Component
public class ThreadPoolMonitorProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolMonitorProcessor.class);

    private String host;
    @Value("${server.port}")
    private int port;

    @Autowired
    private InfluxDB influxDB;

    @PostConstruct
    public void init() {
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            logger.error("get host&port error", e);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void threadPoolMetricsRecord() {
        // 线程池指标数据
        ThreadPoolRegistrar.threadPools().forEach(threadPoolMonitorConsumer());
    }

    private BiConsumer<String, ThreadPoolExecutor> threadPoolMonitorConsumer() {
        return (name, threadPool) -> {

            // 当前活跃线程数
            int currentActive = threadPool.getActiveCount();
            // 当前线程池大小
            int currentPoolSize = threadPool.getPoolSize();
            // 最大线程数
            long maximumPoolSize = threadPool.getMaximumPoolSize();
            // 核心线程数
            long corePoolSize = threadPool.getCorePoolSize();
            // 队列容量
            int queueSize = threadPool.getQueue().size();


            /**
             *       measurement相当于表名
             *       tag是用于统计或分类的参数，这里用ip+端口进行标记，用于后续图表展示
             *       field相当于列，存储各类指标值
             */
            influxDB.write(Point.measurement("threadPool")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag("host", host)
                    .tag("port", port + "")
                    .tag("addr", host + ":" + port)
                    .tag("group", name)
                    .addField("currentPoolSize", currentPoolSize)
                    .addField("currentActive", currentActive)
                    .addField("corePoolSize", corePoolSize)
                    .addField("maximumPoolSize", maximumPoolSize)
                    .addField("queneSize", queueSize)
                    .build());
        };
    }

}
