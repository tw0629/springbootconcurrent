package com.tian.concurrent.queue.blockinQeque.LinkedTransferQueue_demo;

import com.tian.concurrent.model.TempObject;
import org.junit.Test;

import java.util.concurrent.LinkedTransferQueue;

/**
 * ������������ LinkedTransferQueue
 *
 * @author David Tian
 * @since 2019-04-21
 */
public class LinkedTransferQueueTest {

    /**
     * LinkedTransferQueue Ҳ��һ�������������У������˾���һ����еĲ��������⣨�Ƚ��ȳ�����
     * ������һ���������ԣ�
     * LinkedTransferQueue������һ��������/�������߳̽��в������������߽�һ���µ�Ԫ�ز�����к��������߳̽���һֱ�ȴ���
     * ֱ��ĳһ���������߳̽����Ԫ��ȡ�ߣ���֮��Ȼ��
     *
     * LinkedTransferQueue �Ĳ������Կ�����������δ������֡�
     * ������Ĵ���Ƭ���У����������͵��̣߳������ߺ������ߣ��������̻߳���ȴ��Է��Ĳ�����
     *
     */
    @Test
    public void test(){
        LinkedTransferQueue<TempObject> linkedQueue = new LinkedTransferQueue<TempObject>();
        // ����һ���������߳�
        Thread producerThread = new Thread(new ProducerRunnable(linkedQueue));
        // �����������������߳�
        Thread consumerRunnable1 = new Thread(new ConsumerRunnable(linkedQueue));
        Thread consumerRunnable2 = new Thread(new ConsumerRunnable(linkedQueue));

        // ��ʼ����
        producerThread.start();
        consumerRunnable1.start();
        consumerRunnable2.start();

        // ����ֻ��Ϊ��main/test ���˳���û���κ���ʾ����
        Thread currentThread = Thread.currentThread();
        synchronized (currentThread) {
            try {
                currentThread.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * �������߳�
     */
    private static class ProducerRunnable implements Runnable {

        private LinkedTransferQueue<TempObject> linkedQueue;

        public ProducerRunnable(LinkedTransferQueue<TempObject> linkedQueue) {
            this.linkedQueue = linkedQueue;
        }

        @Override
        public void run() {
            for(int index = 1 ; index < 100; index++) {
                try {

                    Thread currentThread = Thread.currentThread();
                    System.out.println("�̣߳�" + currentThread.getId() + "����LinkedTransferQueue���в���һ���µ�Ԫ�� index = "+index);


                    // ��LinkedTransferQueue���в���һ���µ�Ԫ��
                    // Ȼ���������߳̾ͻ�ȴ���ֱ����һ�������߽����Ԫ�شӶ�����ȡ��
                    this.linkedQueue.transfer(new TempObject(index));

                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            }
        }
    }

    /**
     * �������߳�
     */
    private static class ConsumerRunnable implements Runnable {

        private LinkedTransferQueue<TempObject> linkedQueue;

        public ConsumerRunnable(LinkedTransferQueue<TempObject> linkedQueue) {
            this.linkedQueue = linkedQueue;
        }

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();

            while(!currentThread.isInterrupted()) {
                try {
                    // �ȴ���ֱ����LinkedTransferQueue�����еõ�һ��Ԫ��
                    TempObject targetObject = this.linkedQueue.take();

                    System.out.println("�̣߳�" + currentThread.getId() + "��ȡ��targetObject.index = " + targetObject.getIndex());

                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            }
        }
    }
}
