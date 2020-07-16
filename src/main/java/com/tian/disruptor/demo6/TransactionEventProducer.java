package com.tian.disruptor.demo6;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.*;

/**
 * �����ߡ������¼�
 *
 * @author David Tian
 * @since 2019-05-09
 *
 */
public class TransactionEventProducer implements Runnable {
    // �߳�ͬ�������� - ����һ�������߳�һֱ�ȴ�
    CountDownLatch cdl;
    Disruptor disruptor;

    public TransactionEventProducer(CountDownLatch cdl, Disruptor disruptor) {
        super();
        this.cdl = cdl;
        this.disruptor = disruptor;
    }

    public TransactionEventProducer() {
        super();
        // TODO Auto-generated constructor stub
    }


    @Override
    public void run() {
        AmountTrasfer th;
        try {
            //Event�����ʼ����
            th = new AmountTrasfer();
            //�����¼�
            disruptor.publishEvent(th);
        } finally {
            // �ݼ��������ļ��� -������������㣬���ͷ����еȴ����̡߳�
            cdl.countDown();
        }
    }

    // ���廷��С��2�ı���
    private static final int BUFFER_SIZE = 1024;
    // ���崦���¼����̻߳��̳߳�
    ExecutorService pool = Executors.newFixedThreadPool(7);

    /**
     * ������ģʽ
     * @throws Exception
     */
    public void BatchDeal() throws Exception {
        //����һ���������ߵ�ringBuffer
        final RingBuffer<TransactionEvent> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<TransactionEvent>() {

            @Override
            public TransactionEvent newInstance() {
                return new TransactionEvent();
            }
            //���õȴ����ԣ�YieldingWaitStrategy ����������õģ��ʺ����ڵ��ӳٵ�ϵͳ��
        }, BUFFER_SIZE,new YieldingWaitStrategy());
        //����SequenceBarrier
        SequenceBarrier barrier = ringBuffer.newBarrier();
        //������Ϣ������
        BatchEventProcessor<TransactionEvent> eventProcessor = new BatchEventProcessor<TransactionEvent>(ringBuffer,barrier,new InnerDBHandler());
        //���췴��������eventProcessor֮��û��������ϵ����Խ�Sequenceֱ�Ӽ���
        ringBuffer.addGatingSequences(eventProcessor.getSequence());
        //�ύ��Ϣ������
        pool.submit(eventProcessor);
        //�ύһ���з���ֵ����������ִ�У�����һ����ʾ�����δ������� Future��
        Future<Void> submit = pool.submit(new Callable<Void>() {
            //������������޷����������׳��쳣
            @Override
            public Void call() throws Exception {
                long seq;
                for (int i=0;i<7000;i++) {
                    System.out.println("�����ߣ�"+i);
                    //����һ�����õ�����
                    seq=ringBuffer.next();
                    //Ϊ����Ķ���ֵ
                    ringBuffer.get(seq).setAmount(Math.random()*10);
                    System.out.println("TransactionEvent:   "+ringBuffer.get(seq).toString());
                    //���������������ݣ�
                    ringBuffer.publish(seq);
                }
                return null;
            }
        });
        //�ȴ�������ɣ�Ȼ���ȡ������
        submit.get();
        Thread.sleep(1000);
        //�ر���Ϣ������
        eventProcessor.halt();
        //�ر��̳߳�
        pool.shutdown();
    }

    /**
     * ������ģʽ
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void poolDeal() throws Exception {
        RingBuffer<TransactionEvent> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<TransactionEvent>() {

            @Override
            public TransactionEvent newInstance() {
                return new TransactionEvent();
            }
        }, BUFFER_SIZE, new YieldingWaitStrategy());
        SequenceBarrier barrier = ringBuffer.newBarrier();
        //����һ���������̳߳�
        ExecutorService pool2 = Executors.newFixedThreadPool(5);
        //������ˮ������
        WorkHandler<TransactionEvent> innerDBHandler = new InnerDBHandler();
        ExceptionHandler arg2;
        WorkerPool<TransactionEvent> workerPool = new WorkerPool<TransactionEvent>(ringBuffer, barrier, new IgnoreExceptionHandler(), innerDBHandler);
        workerPool.start(pool2);
        long seq;
        for(int i =0;i<7;i++){
            seq = ringBuffer.next();
            ringBuffer.get(seq).setAmount(Math.random()*99);
            ringBuffer.publish(seq);
        }
        Thread.sleep(1000);
        workerPool.halt();
        pool2.shutdown();
    }

    /**
     * disruptor������������װ�����ߺ�������
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void disruptorManage() throws Exception{
        //�������ڴ����¼����̳߳�
        ExecutorService pool2 = Executors.newFixedThreadPool(7);
        //����disruptor����
        /**
         * ����ָ��������������һ�����Ƕ������������ѡֵProducerType.SINGLE��ProducerType.MULTI
         * BusySpinWaitStrategy��һ���ӳ���ͣ����CPU�Ĳ��ԡ�ͨ�����������߳���С��CPU���ĳ���
         */
        Disruptor<TransactionEvent> disruptor2 = new Disruptor<TransactionEvent>(new EventFactory<TransactionEvent>() {

            @Override
            public TransactionEvent newInstance() {
                return new TransactionEvent();
            }
        },BUFFER_SIZE,pool2, ProducerType.SINGLE,new BusySpinWaitStrategy());
        //�����������飬��ִ�����ؽ�����ˮ��������
        EventHandlerGroup<TransactionEvent> eventsWith = disruptor2.handleEventsWith(new InnerDBHandler(),new TransHandler());
        //�ڽ��з��ս��׵�2����֤����
        eventsWith.then(new SendMsgHandler());
        //����disruptor
        disruptor2.start();
        //���߳���ͨ�� await()֮ǰ��������� countDown() �Ĵ���
        CountDownLatch latch = new CountDownLatch(1);
        //����װ�õ�TransactionEventProducer���ύ
        pool2.submit(new TransactionEventProducer(latch,disruptor2));
        //ʹ��ǰ�߳�������������������֮ǰһֱ�ȴ�,�Ա�֤������������ȫ���ѵ�
        latch.await();
        //�ر�disruptorҵ���߼�������
        disruptor2.shutdown();
        //�����̳߳�
        pool2.shutdown();
    }
}