package com.tian.disruptor.demo12;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.tian.disruptor.demo9_入门.StringEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author David Tian
 * @since 2019-05-16
 */
public class disruptor_demo12 {

    /*

    public static void main(String[] args) {
        Disruptor<StringEvent> disruptor = new Disruptor<>(StringEvent::new, 1024,
                new PrefixThreadFactory("consumer-pool-", new AtomicInteger(0)), ProducerType.MULTI,
                new BlockingWaitStrategy());

        // 注册consumer并启动
        disruptor.handleEventsWith((EventHandler<StringEvent>) (event, sequence, endOfBatch) -> {
            System.out.println(Util.threadName() + "onEvent " + event);
        });
        disruptor.start();

        // publisher逻辑
        Executor executor = Executors.newFixedThreadPool(2,
                new PrefixThreadFactory("publisher-pool-", new AtomicInteger(0)));
        while (true) {
            for (int i = 0; i < 2; i++) {
                executor.execute(() -> {
                    Util.sleep(1);
                    disruptor.publishEvent((event, sequence, arg0) -> {
                        event.setValue(arg0 + " " + sequence);
                    }, "hello world");
                });
            }

            Util.sleep(1000);
        }
    }

    */
}
