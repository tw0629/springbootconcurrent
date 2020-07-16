package com.tian.Atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author David Tian
 * @desc
 * @since 2020-03-07 12:03
 */
public class AtomicTest {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.compareAndSet(10, 20);
        System.out.println("======> "+atomicInteger);
        System.out.println("======> "+atomicInteger.get());
        atomicInteger.compareAndSet(30, 40);
        System.out.println("======> "+atomicInteger.get());
        atomicInteger.compareAndSet(20, 40);
        System.out.println("======> "+atomicInteger.get());

        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        atomicBoolean.compareAndSet(true, false);

        AtomicLong atomicLong = new AtomicLong(10L);
        atomicLong.compareAndSet(10, 20);

        System.out.println(atomicLong);
        System.out.println("======>");

    }

    public static AtomicReference<User> atomicReference = new AtomicReference<User>();

    @Test
    public void test(){

        User user = new User("tian", 10);
        atomicReference.set(user);
        User user2= new User("wei", 18);
        atomicReference.compareAndSet(user,user2);

        System.out.println(atomicReference.get().getName());
        System.out.println(atomicReference.get().getOld());
        System.out.println("======>");


        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        lock.unlock();


    }

    static class User{

        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }
}
