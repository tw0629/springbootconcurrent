package com.tian.disruptor.demo8_error;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * 第二种 Producer 的实现：通过 ArrayBlockinigQueue 实现；
 *
 * @author David Tian
 * @since 2019-05-13
 */
public class BlockingQueuePublisher implements EventPublisher {

    private ArrayBlockingQueue<TestEvent> queue ;
    private TestHandler handler;

    public BlockingQueuePublisher(int maxEventSize, TestHandler handler) {
        this.queue = new ArrayBlockingQueue<TestEvent>(maxEventSize);
        this.handler = handler;
    }

    public void start(){
        Thread thrd = new Thread(new Runnable() {
            @Override
            public void run() {
                handle();
            }
        });
        thrd.start();
    }

    private void handle(){
        try {
            TestEvent evt ;
            while (true) {
                evt = queue.take();
                if (evt != null && handler.process(evt)) {
                    //完成后自动结束处理线程；
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void publish(int data) throws Exception {
        TestEvent evt = new TestEvent();
        evt.setValue(data);
        queue.put(evt);
    }

    //省略其它代码；
}
