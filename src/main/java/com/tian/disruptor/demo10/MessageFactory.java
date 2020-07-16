package com.tian.disruptor.demo10;

import com.lmax.disruptor.EventFactory;

/**
 * @author David Tian
 * @since 2019-05-15
 */
public class MessageFactory implements EventFactory<Message> {

    @Override
    public Message newInstance()
    {
        return new Message();
    }
}
