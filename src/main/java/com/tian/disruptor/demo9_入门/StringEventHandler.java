package com.tian.disruptor.demo9_入门;

import com.lmax.disruptor.EventHandler;

/**
 *
 * @author David Tian
 * @since 2019-05-14
 */
public class StringEventHandler implements EventHandler<StringEvent> {

    @Override
    public void onEvent(StringEvent stringEvent, long sequence, boolean bool) throws Exception {
        System.out.println("StringEventHandler(消费者):  "  + stringEvent +", sequence= "+sequence+",bool="+bool);
    }
}