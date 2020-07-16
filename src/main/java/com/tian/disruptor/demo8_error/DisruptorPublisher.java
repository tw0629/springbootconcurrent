package com.tian.disruptor.demo8_error;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 第三种 Producer 的实现：通过 Disruptor 实现;
 *
 * @author David Tian
 * @since 2019-05-13
 */
public class DisruptorPublisher implements EventPublisher {



    private class TestEventHandler implements EventHandler<TestEvent> {

        private TestHandler handler;

        public TestEventHandler(TestHandler handler) {
            this.handler = handler;
        }

        @Override
        public void onEvent(TestEvent event, long sequence, boolean endOfBatch)
                throws Exception {
            handler.process(event);
        }

    }

    private static final WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();

    private Disruptor<TestEvent> disruptor;
    private TestEventHandler handler;
    private RingBuffer<TestEvent> ringbuffer;
    private ExecutorService executor;

    public static class TianEventFactory implements EventFactory<TestEvent> {
        @Override
        public TestEvent newInstance() {
            return new TestEvent();
        }
    }

    public DisruptorPublisher(int bufferSize, TestHandler handler) {
        this.handler = new TestEventHandler(handler);
        executor = Executors.newSingleThreadExecutor();
        disruptor = new Disruptor<TestEvent>(new TianEventFactory(), bufferSize,
                executor, ProducerType.SINGLE,
                YIELDING_WAIT);
    }

    @SuppressWarnings("unchecked")
    public void start() {
        disruptor.handleEventsWith(handler);
        disruptor.start();
        ringbuffer = disruptor.getRingBuffer();
    }

    @Override
    public void publish(int data) throws Exception {
        long seq = ringbuffer.next();
        try {
            TestEvent evt = ringbuffer.get(seq);
            evt.setValue(data);
        } finally {
            ringbuffer.publish(seq);
        }
    }

    //省略其它代码；
}