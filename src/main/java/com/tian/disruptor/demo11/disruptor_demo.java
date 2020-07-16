package com.tian.disruptor.demo11;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.tian.disruptor.demo1.LongEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * https://niceaz.com/2017/10/03/disruptor/#%E5%8D%95%E7%94%9F%E4%BA%A7%E8%80%85%E5%8D%95%E6%B6%88%E8%B4%B9%E8%80%85
 *
 * @author David Tian
 * @since 2019-05-16
 */
public class disruptor_demo {

        /*
        //声明disruptor中事件类型及对应的事件工厂
        class LongEvent {
            private long value;

            public LongEvent() {
                this.value = 0L;
            }

            public void set(long value) {
                this.value = value;
            }

            public long get() {
                return this.value;
            }
        }

        EventFactory<LongEvent> eventFactory = new EventFactory<LongEvent>() {

            @Override
            public LongEvent newInstance() {
                return new LongEvent();
            }
        };

        //声明disruptor，
        int ringBufferSize = 1024;
        Executor executor = Executors.newFixedThreadPool(8);
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory, ringBufferSize, executor);

        //pubisher逻辑，将原始数据转换为event，publish到ringbuffer
        class Publisher implements EventTranslatorOneArg<LongEvent, String> {
            @Override
            public void translateTo(LongEvent event, long sequence, String arg0) {
                event.set(Long.parseLong(arg0));
            }
        }
        //consumer逻辑，获取event进行处理
        class Consumer implements EventHandler<LongEvent> {

            @Override
            public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
                long value = event.get();
                int index = (int) (value % Const.NUM_OF_FILE);
                fileWriter[index].write("" + value + "\n");

                if (value == Long.MAX_VALUE) {
                    isFinish = true;
                }
            }

        }
        //注册consumer启动disruptor
        disruptor.handleEventsWith(new Consumer());
        disruptor.start();

        //获取disruptor的ringbuffer，用于生产数据
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ringBuffer.publishEvent(new Publisher(), line);
    */






}
