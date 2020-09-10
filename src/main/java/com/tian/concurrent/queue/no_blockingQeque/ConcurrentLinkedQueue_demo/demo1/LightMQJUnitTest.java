package com.tian.concurrent.queue.no_blockingQeque.ConcurrentLinkedQueue_demo.demo1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/** 无限非阻塞队列  ConcurrentLinkedQueue
 *
 * 使用ConcurrentLinkedQueue 实现的轻量级Mq
 *
 * https://github.com/edwardX/lightMQ
 *
 * @author David Tian
 * @since 2019-04-30
 */
public class LightMQJUnitTest {

    QueueExecutor lightmq= null;

    public LightMQJUnitTest() {
    }


    @Before
    public void setUp() {
        lightmq = new QueueExecutorImpl();
        lightmq.init(10);
        for (int i = 0; i < 10000;i++) {
            lightmq.addToQueue(new WordToUpperCaseCallable("Message No. "+i));
        }

    }

    @Test
    public void testLightMQ() {
        long start = System.currentTimeMillis();

        lightmq.processAllMesages();
        System.out.println("处理所有信息 消耗的时间为"+(System.currentTimeMillis()-start)+"ms");
    }

    @After
    public void tearDown() throws InterruptedException, ExecutionException {
        ConcurrentLinkedQueue<Future> queueFuture = lightmq.getQueueProcesedMsg();
        while (!queueFuture.isEmpty()) {
            Future poll = queueFuture.poll();
            String getMsg = (String) poll.get();
            System.out.println("get poll Msg: " + getMsg);
        }
    }

    static class WordToUpperCaseCallable implements Callable {
        private String word;
        public WordToUpperCaseCallable(String word) {
            this.word = word;
        }

        @Override
        public String call() {
            return word.toUpperCase();
        }
    }
}
