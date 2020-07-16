package com.tian.concurrent.threadlocal;

/**
 *
 * 多线程应用中，如果希望一个变量隔离在某个线程内，即：该变量只能由某个线程本身可见，其它线程无法访问，那么ThreadLocal可以很方便的帮你做到这一点。
 *
 * 线程A与线程B中ThreadLocal保存的整型变量是各自独立的，互不相干，只要在每个线程内部使用set方法赋值，然后在线程内部使用get就能取到对应的值。
 *
 */
public class ThreadLocalTest1 {

    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 100D));
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable(), "A");
        Thread t2 = new Thread(new MyRunnable(), "B");
        t1.start();
        t2.start();
    }
}
