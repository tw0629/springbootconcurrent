package com.tian.concurrent.queue.LinkedBlockingDeque_demo;

import com.tian.concurrent.model.TempObject;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author David Tian
 * @since 2019-04-25
 */
public class LinkedBlockingDequeTest {

    /** 无限阻塞队列 LinkedBlockingDeque
     *
     *   是一个基于链表的双端队列。
     *
     * 区别：
     * LinkedBlockingQueue 的内部结构决定了它只能从队列尾部插入，从队列头部取出元素；
     * 但是 LinkedBlockingDeque 既可以从尾部插入/取出元素，还可以从头部插入元素/取出元素。
     */
    @Test
    public void test(){

        //头部操作 插入push()  取出poll()

        LinkedBlockingDeque<TempObject> linkedDeque = new LinkedBlockingDeque<TempObject>();
        //可以从队列的头部插入元素 ======> push操作
        linkedDeque.push(new TempObject(1));
        linkedDeque.push(new TempObject(2));
        linkedDeque.push(new TempObject(3));
        // poll ， 可以从队列的头部取出元素
        TempObject tempObject = linkedDeque.poll();
        // 这里会打印 tempObject.index = 3
        System.out.println("tempObject.index = " + tempObject.getIndex());


        //尾部操作======> 插入put()  取出pollLast()
        try {
            linkedDeque.put(new TempObject(4));
            linkedDeque.put(new TempObject(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //可以从队列尾部取出元素 ======> pollLast操作
        tempObject = linkedDeque.pollLast();
        // 这里会打印 tempObject.index = 5
        System.out.println("tempObject.index = " + tempObject.getIndex());

    }
}
