package com.tian.disruptor.demo8_error;

/**
 * 第一种 Producer 的实现：直接触发事件处理；
 *
 * @author David Tian
 * @since 2019-05-13
 */
public class DirectingPublisher implements EventPublisher {

    private TestHandler handler;
    private TestEvent event = new TestEvent();

    public DirectingPublisher(TestHandler handler) {
        this.handler = handler;
    }

    @Override
    public void publish(int data) throws Exception {
        event.setValue(data);
        handler.process(event);
    }

    //省略其它代码；
}
