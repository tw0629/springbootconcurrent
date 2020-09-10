package com.tian.concurrent.queue.blockinQeque.LinkedBlockingQueue_demo;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 无限阻塞队列 LinkedBlockingQueue
 *
 * @author David Tian
 * @since 2019-04-21
 */
public class LinkedBlockingQueueTest {

    /** 无限阻塞队列 LinkedBlockingQueue
     *
     * LinkedBlockingQueue 是我们在 ThreadPoolExecutor线程池中常用的等待队列。它可以指定容量也可以不指定容量。
     * 由于它具有“无限容量”的特性，所以我还是将它归入了无限队列的范畴（实际上任何无限容量的队列/栈都是有容量的，这个容量就是Integer.MAX_VALUE）。
     * LinkedBlockingQueue 的实现是基于链表结构，而不是类似 ArrayBlockingQueue 那样的数组。但实际使用过程中，
     * 不需要关心它的内部实现，如果指定了LinkedBlockingQueue 的容量大小，那么它反映出来的使用特性就和 ArrayBlockingQueue 类似了。
     */
    @Test
    public void test(){
        //指定了大小
        LinkedBlockingQueue<Object> linkedQueue = new LinkedBlockingQueue<Object>(2);
        try {

            linkedQueue.put(new Object());
            // 插入第二个对象
            linkedQueue.put(new Object());
            // 插入第三个对象时，这个操作线程就会被阻塞。     ===>因为初始化申请的空间大小为2
            linkedQueue.put(new Object());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("===============>"+linkedQueue);


        //未指定大小
        linkedQueue = new LinkedBlockingQueue<Object>();
        try {
            linkedQueue.put(new Object());
            // 插入第二个对象
            linkedQueue.put(new Object());
            // 插入第N个对象时，都不会阻塞
            linkedQueue.put(new Object());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("===============>"+linkedQueue);

    }

}
