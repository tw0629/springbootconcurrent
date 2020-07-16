package com.tian.concurrent.thread.demo6;

/**
 * @author David Tian
 * @since 2019-04-16
 */
class TestRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程被调用了。");
        while (true) {
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
