package com.tian.disruptor.demo9_入门;

import com.lmax.disruptor.EventFactory;

/**
 * @author David Tian
 * @since 2019-05-14
 */
public class StringEventFactory implements EventFactory<StringEvent> {
    @Override
    public StringEvent newInstance() {
        return new StringEvent();
    }
}