package com.tian.disruptor.demo8_error;

/**
 * @author David Tian
 * @since 2019-05-14
 */
public interface EventPublisher {

    void publish(int data) throws Exception ;
}
