package com.tian.disruptor.demo10;

import com.lmax.disruptor.EventHandler;

/**
 * @author David Tian
 * @since 2019-05-15
 */
public class MessageHandler implements EventHandler<Message> {

    @Override
    public void onEvent(Message event, long sequence, boolean endOfBatch)
    {
        System.out.println("Event: " + event);
    }
}
