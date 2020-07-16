package com.tian.concurrent.threadlocal.demo1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author David Tian
 * @since 2019-06-12
 */
public class CallableThreadTest implements Callable<Long> {

    @Override
    public Long call() throws Exception {
        Profiler.begin();
        Thread.sleep(1000L);
        return Profiler.end();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Long> ft = new FutureTask<>(new CallableThreadTest());
        for (int i = 0; i < 100; i++) {
            new Thread(ft).start();
            System.out.println(Thread.currentThread().getName()+"_线程的返回值："+ft.get());

        }
    }
}
