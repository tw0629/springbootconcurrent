package com.tian.concurrent.queue.SynchronousQueue_demo;



import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

/**
 * 有限阻塞队列 SynchronousQueue
 *
 * 是这样 一种阻塞队列，其中每个 put 必须等待一个 take，反之亦然。同步队列没有任何内部容量。
 * 翻译一下：这是一个内部没有任何容量的阻塞队列，任何一次插入操作的元素都要等待相对的删除/读取操作，否则进行插入操作的线程就要一直等待，反之亦然。
 *
 *
 * SynchronousQueue<Object> queue = new SynchronousQueue<Object>();
 * // 不要使用add，因为这个队列内部没有任何容量，所以会抛出异常“IllegalStateException”
 * // queue.add(new Object());
 * // 操作线程会在这里被阻塞，直到有其他操作线程取走这个对象
 * queue.put(new Object());
 *
 *
 * @author David Tian
 * @since 2019-04-19
 */
public class SynchronousQueueTest {


    @Test
    public void test1(){

        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<Object>();

        try {
            synchronousQueue.put(new Object());     // ====> 这个操作线程会在这里被阻塞，直到有其他操作线程取走这个对象

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("===============>"+synchronousQueue);
        System.out.println("===============>"+synchronousQueue.toString());

    }


    @Test
    public void test2(){
        System.out.println("==================>");
    }


}
