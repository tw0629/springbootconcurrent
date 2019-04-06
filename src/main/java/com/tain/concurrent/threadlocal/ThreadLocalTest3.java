package com.tain.concurrent.threadlocal;

/**
 *
 * https://www.imooc.com/article/26795?block_id=tuijian_wz
 *
 * 思考一下：为什么会这样？
 * 观察下结果，在主线程main中设置了一个InheritableThreadLocal实例，并在main主线程中设置了值1，然后main主线程及二个子线程t1,t2均正常get到了该值。
 *
 *
 * ThreadLocal还有一个派生的子类：InheritableThreadLocal ，可以允许线程及该线程创建的子线程均可以访问同一个变量(有些OOP中的proteced的意味)，这么解释可能理解起来比较费劲，还是直接看代码吧：
 *
 *
 */
public class ThreadLocalTest3 {

    private static InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<Integer>();

    public static class MyRunnable implements Runnable {

        private String _name = "";

        public MyRunnable(String name) {
            _name = name;
            System.out.println(name + " => " + Thread.currentThread().getName() + ":" + threadLocal.get());
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }
    }


    public static void main(String[] args) {
        threadLocal.set(1);

        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        Thread t1 = new Thread(new MyRunnable("R-A"), "A");
        Thread t2 = new Thread(new MyRunnable("R-B"), "B");

        t1.start();
        t2.start();
    }
}
