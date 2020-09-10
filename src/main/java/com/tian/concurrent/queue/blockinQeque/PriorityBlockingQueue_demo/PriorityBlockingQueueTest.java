package com.tian.concurrent.queue.blockinQeque.PriorityBlockingQueue_demo;

import com.tian.concurrent.model.TempObject2;
import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 无限阻塞队列 PriorityBlockingQueue
 *
 * @author David Tian
 * @since 2019-04-21
 */
public class PriorityBlockingQueueTest {

    /**
     * PriorityBlockingQueue 是一个按照优先级进行内部元素排序的无限阻塞队列。
     * 存放在PriorityBlockingQueue 中的元素必须实现 Comparable 接口，这样才能通过实现compareTo()方法进行排序。
     * 优先级最高的元素将始终排在队列的头部；PriorityBlockingQueue 不会保证优先级一样的元素的排序，
     * 也不保证当前队列中除了优先级最高的元素以外的元素,随时处于正确排序的位置。
     *
     * 这是什么意思呢？ PriorityBlockingQueue并不保证除了队列头部以外的元素排序一定是正确的。  <======== 注意
     */
    @Test
    public void test1(){

        PriorityBlockingQueue<TempObject2> priorityQueue = new PriorityBlockingQueue<TempObject2>();
        priorityQueue.put(new TempObject2(-5));
        priorityQueue.put(new TempObject2(5));
        priorityQueue.put(new TempObject2(-1));
        priorityQueue.put(new TempObject2(1));

        System.out.println("===============>"+priorityQueue);
        System.out.println("===============>"+priorityQueue.toString());


        // 第一个元素是5
        // 实际上在还没有执行priorityQueue.poll()语句的时候，队列中的第二个元素不一定是1   <======== 注意：执行完poll()后,才进行重排序
        TempObject2 targetTempObject2 = priorityQueue.poll();
        System.out.println("tempObject.index = " + targetTempObject2.getIndex());
        System.out.println("===============>"+priorityQueue);


        // 第二个元素是1
        targetTempObject2 = priorityQueue.poll();
        System.out.println("tempObject.index = " + targetTempObject2.getIndex());
        System.out.println("===============>"+priorityQueue);


        // 第三个元素是-1
        targetTempObject2 = priorityQueue.poll();
        System.out.println("tempObject.index = " + targetTempObject2.getIndex());
        System.out.println("===============>"+priorityQueue);


        // 第四个元素是-5
        targetTempObject2 = priorityQueue.poll();
        System.out.println("tempObject.index = " + targetTempObject2.getIndex());
        System.out.println("===============>"+priorityQueue);

    }
}
