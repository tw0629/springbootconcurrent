package com.tian.disruptor.demo9_入门;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author David Tian
 * @since 2019-05-14
 */
public class StringEventProducer {

    private final RingBuffer<StringEvent> ringBuffer;
    public StringEventProducer(RingBuffer<StringEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(ByteBuffer byteBuffer) {
        //ringBuffer就是用来存储数据的，具体可以看disruptor源码的数据结构，next就是获取下一个空事件索引
        long sequence = ringBuffer.next();
        //通过索引获取空事件
        try {
            StringEvent stringEvent = ringBuffer.get(sequence);
            //切换成读模式
            byteBuffer.flip();
            //从byteBuffer中读取传过来的值
            byte[] dst = new byte[byteBuffer.limit()];
            byteBuffer.get(dst, 0, dst.length);
            //为stringEvent赋值，填充数据
            stringEvent.setValue(new String(dst));
            stringEvent.setId((int) sequence);
            //clear一下缓冲区
            byteBuffer.clear();
        } finally {
            //发布事件，为确保安全，放入finally中，不会造成disruptor的混乱
            ringBuffer.publish(sequence);
        }
    }
}