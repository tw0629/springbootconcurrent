package com.tian.concurrent.queue.LinkedTransferQueue_demo;

import com.tian.concurrent.model.TempObject;
import org.junit.Test;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 无限阻塞队列 LinkedTransferQueue
 *
 * @author David Tian
 * @since 2019-04-21
 */
public class LinkedTransferQueueTest2 {

    @Test
    public void test() {
        LinkedTransferQueue<TempObject> linkedQueue = new LinkedTransferQueue<TempObject>();
        //put()
        linkedQueue.put(new TempObject(1));
        linkedQueue.put(new TempObject(2));
        linkedQueue.put(new TempObject(3));

        System.out.println("===============>"+linkedQueue);
        System.out.println("===============>"+linkedQueue.toString());

        //peek()
        System.out.println("===============>"+linkedQueue.peek());
        System.out.println("===============>"+linkedQueue);

        //add()
        linkedQueue.add(new TempObject(4));
        System.out.println("===============>"+linkedQueue);

        //poll()
        System.out.println("===============>"+linkedQueue.poll());
        System.out.println("===============>"+linkedQueue);


    }
}
