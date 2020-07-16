package com.tian.concurrent.queue.SynchronousQueue_demo;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

/**
 *  SynchronousQueue��put��take�������ģ�һ���߳�put��Ȼ���������ȴ���һ���߳�take������һ���߳�take��Ȼ���������ȴ���һ���߳�put��
 *
 *
 *
 * @author David Tian
 * @since 2019-04-21
 */
public class SyschronousQueueTest2 {

    @Test
    public void test(){

        final SynchronousQueue<String> q = new SynchronousQueue<String>();
        //put�߳�
        class Putter implements Runnable {
            String title;
            public Putter(String title) {
                this.title = title;
            }
            @Override
            public void run() {
                try {
                    System.out.println("=====���Ԫ����=====>"+title);
                    q.put(this.title);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        new Thread(new Putter("h1")).start();
        //new Thread(new Putter("h2")).start();



        //take�߳�
        class Taker implements Runnable {
            @Override
            public void run() {
                String take;
                try {
                    take = q.take();
                    System.out.printf("%s take %s\n", Thread.currentThread().getName(), take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        new Thread(new Taker()).start();
        new Thread(new Taker()).start();


    }

}
