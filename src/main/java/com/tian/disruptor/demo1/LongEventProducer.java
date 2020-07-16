package com.tian.disruptor.demo1;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author David Tian
 * @since 2019-05-06
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb)
    {
        // Grab the next sequence
        long sequence = ringBuffer.next();
        try
        {
            // Get the entry in the Disruptor
            LongEvent event = ringBuffer.get(sequence);
            // for the sequence
            // Fill with data
            event.set(bb.getLong(0));
        }
        finally
        {
            ringBuffer.publish(sequence);
        }
    }
}
