package com.tian.concurrent.queue.blockinQeque.LinkedTransferQueue_demo;

import com.tian.concurrent.model.TempObject;
import org.junit.Test;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 无限阻塞队列 LinkedTransferQueue
 *
 * @author David Tian
 * @since 2019-04-21
 */
public class LinkedTransferQueueTest {

    /**
     * LinkedTransferQueue 也是一个无限阻塞队列，它除了具有一般队列的操作特性外（先进先出），
     * 还具有一个阻塞特性：
     * LinkedTransferQueue可以由一对生产者/消费者线程进行操作，当生产者将一个新的元素插入队列后，生产者线程将会一直等待，
     * 直到某一个消费者线程将这个元素取走，反之亦然。
     *
     * LinkedTransferQueue 的操作特性可以由下面这段代码提现。
     * 在下面的代码片段中，有两中类型的线程：生产者和消费者，这两类线程互相等待对方的操作：
     *
     */
    @Test
    public void test(){
        LinkedTransferQueue<TempObject> linkedQueue = new LinkedTransferQueue<TempObject>();
        // 这是一个生产者线程
        Thread producerThread = new Thread(new ProducerRunnable(linkedQueue));
        // 这里有两个消费者线程
        Thread consumerRunnable1 = new Thread(new ConsumerRunnable(linkedQueue));
        Thread consumerRunnable2 = new Thread(new ConsumerRunnable(linkedQueue));

        // 开始运行
        producerThread.start();
        consumerRunnable1.start();
        consumerRunnable2.start();

        // 这里只是为了main/test 不退出，没有任何演示含义
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
     * 生产者线程
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
                    System.out.println("线程（" + currentThread.getId() + "）向LinkedTransferQueue队列插入一个新的元素 index = "+index);


                    // 向LinkedTransferQueue队列插入一个新的元素
                    // 然后生产者线程就会等待，直到有一个消费者将这个元素从队列中取走
                    this.linkedQueue.transfer(new TempObject(index));

                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            }
        }
    }

    /**
     * 消费者线程
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
                    // 等待，直到从LinkedTransferQueue队列中得到一个元素
                    TempObject targetObject = this.linkedQueue.take();

                    System.out.println("线程（" + currentThread.getId() + "）取得targetObject.index = " + targetObject.getIndex());

                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            }
        }
    }
}
