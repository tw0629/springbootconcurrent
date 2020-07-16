package com.tian.concurrent.queue.SynchronousQueue_demo;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

/**
 *  SynchronousQueue的put和take是阻塞的，一个线程put，然后阻塞，等待另一个线程take；或者一个线程take，然后阻塞，等待另一个线程put。
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
        //put线程
        class Putter implements Runnable {
            String title;
            public Putter(String title) {
                this.title = title;
            }
            @Override
            public void run() {
                try {
                    System.out.println("=====入队元素是=====>"+title);
                    q.put(this.title);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        new Thread(new Putter("h1")).start();
        //new Thread(new Putter("h2")).start();



        //take线程
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
