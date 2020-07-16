package com.tian.disruptor.demo10;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author David Tian
 * @since 2019-05-15
 */
public class MessageProducer
{
    private final RingBuffer<Message> ringBuffer;

    public MessageProducer(RingBuffer<Message> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<Message, ByteBuffer> TRANSLATOR =
            new EventTranslatorOneArg<Message, ByteBuffer>()
            {
                @Override
                public void translateTo(Message event, long sequence, ByteBuffer bb)
                {
                    event.set(bb.getLong(0));
                }
            };

    public void onData(ByteBuffer bb)
    {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}
