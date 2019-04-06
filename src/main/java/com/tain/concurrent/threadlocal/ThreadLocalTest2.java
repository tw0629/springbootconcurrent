package com.tain.concurrent.threadlocal;

/**
 *
 * https://www.imooc.com/article/26795?block_id=tuijian_wz
 *
 * 思考一下：为什么会这样？
 * MyRunnable的构造函数是由main主线程调用的，所以TheadLocal的set方法，实际上是在main主线程的环境中完成的，因此也只能在main主线程中get到，
 * 而run方法运行的上下文是子线程本身，由于run方法中并没有使用set方法赋值，因此get到的是默认空值null.
 *
 *
 */
public class ThreadLocalTest2 {

    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

        public MyRunnable(){
            threadLocal.set((int) (Math.random() * 100D));
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }

        @Override
        public void run() {
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
