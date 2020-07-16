package com.tian.concurrent.queue.ArrayBlockingQueue_demo;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 有限阻塞队列 ArrayBlockingQueue
 *
 * 一个由数组支持的有界阻塞队列。此队列按 FIFO（先进先出）原则对元素进行排序。新元素插入到队列的尾部，队列获取操作则是从队列头部开始获得元素。
 * 这是一个典型的“有界缓存区”，固定大小的数组在其中保持生产者插入的元素和使用者提取的元素。
 * 一旦创建了这样的缓存区，就不能再增加其容量。试图向已满队列中放入元素会导致操作受阻塞；试图从空队列中提取元素将导致类似阻塞。
 *
 *
 * @author David Tian
 * @since 2019-04-19
 */
public class ArrayBlockingQueueTest {

    @Test
    public void test1(){

        // 我们创建了一个ArrayBlockingQueue，并且设置队列空间为2
            ArrayBlockingQueue<Object> arrayQueue = new ArrayBlockingQueue<Object>(2);
        try {
            // 插入第一个对象
            arrayQueue.put(new Object());
            // 插入第二个对象
            arrayQueue.put(new Object());
            // 插入第三个对象时，这个操作线程就会被阻塞。     因为初始化申请的空间大小为2
            //arrayQueue.put(new Object());
            // 请不要使用add操作，和SynchronousQueue的add操作一样，它们都使用了AbstractQueue中的add实现


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("===============>"+arrayQueue);

        System.out.println("===============>"+arrayQueue.poll());


        System.out.println("===============>"+arrayQueue);
        System.out.println("===============>"+arrayQueue.toString());
    }
}
