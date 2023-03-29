package com.tian.concurrent.AQS_CAS;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author David Tian
 * @desc
 * @since 2020-03-06 21:17
 */
public class AQS_CAS_Test {

    public static void main(String[] args){

        AtomicInteger atomicInteger = new AtomicInteger(1);

        System.out.println(atomicInteger.getAndIncrement());

        System.out.println(atomicInteger.addAndGet(2));

        System.out.println(atomicInteger.compareAndSet(4,1));

        System.out.println(atomicInteger.getAndSet(10));

        atomicInteger.lazySet(20);

        System.out.println(atomicInteger.get());


    }

    public void test(){

        AbstractQueuedSynchronizer aqs = new AbstractQueuedSynchronizer() {
            /**
             * public abstract class AbstractQueuedSynchronizer
             *     extends AbstractOwnableSynchronizer
             *     implements java.io.Serializable
             */
            @Override
            protected boolean tryAcquire(int arg) {
                return super.tryAcquire(arg);
            }

            @Override
            protected boolean tryRelease(int arg) {
                return super.tryRelease(arg);
            }

            @Override
            protected int tryAcquireShared(int arg) {
                return super.tryAcquireShared(arg);
            }

            @Override
            protected boolean tryReleaseShared(int arg) {
                return super.tryReleaseShared(arg);
            }

            @Override
            protected boolean isHeldExclusively() {
                return super.isHeldExclusively();
            }

            /**
             * 任何类都实现了Object类,都可以去重写
             */
            @Override
            public String toString() {
                return super.toString();
            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }


            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }

            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        };

        /*
        boolean heldExclusively = aqs.isHeldExclusively();
        boolean b = ((AbstractQueuedSynchronizer) aqs).tryAcquire();
        int i = ((AbstractQueuedSynchronizer) aqs).tryAcquireShared(i);
        boolean b1 = ((AbstractQueuedSynchronizer) aqs).tryReleaseShared();
        aqs...

        */


        ReentrantLock lock = new ReentrantLock();
        lock.lock();


        //javax.servlet 和 javax.servlet.http

        //javax.servlet
        //javax.servlet.http

        //Connector

        //HttpConnector

        //HttpProcessor

        //Tomcat
        //HttpConnector

        //
        // RequestFacade

        //Container container =

    }

}








