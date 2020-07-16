package com.tian.concurrent.queue.ConcurrentLinkedQueue_demo;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 无限非阻塞队列  ConcurrentLinkedQueue
 *
 * @author David Tian
 * @since 2019-04-22
 */
public class ConcurrentLinkedQueueTest {

    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
    private static int count = 100000;
    private static int count2 = 4; // 线程个数

    //
    private static CountDownLatch cd = new CountDownLatch(count2);


    public static void initQueue() {
        for (int i = 0; i < count; i++) {
            queue.offer(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(4);
        ConcurrentLinkedQueueTest.initQueue();

        for (int i = 0; i < count2; i++) {
            es.submit(new Poll());
        }

        cd.await();

        System.out.println("cost time " + (System.currentTimeMillis() - timeStart) + "ms");
        es.shutdown();
    }

    static class Poll implements Runnable {
        @Override
        public void run() {
 //           while (queue.size()>0) {                //============>6895ms
            while (!queue.isEmpty()) {            //============>886ms
                System.out.println(Thread.currentThread().getName()+ "======>" + queue.poll());
            }
            cd.countDown();
        }
    }
}
