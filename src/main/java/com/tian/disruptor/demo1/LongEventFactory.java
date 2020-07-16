package com.tian.disruptor.demo1;

import com.lmax.disruptor.EventFactory;

/**
 * @author David Tian
 * @since 2019-05-06
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance()
    {
        return new LongEvent();
    }
}
