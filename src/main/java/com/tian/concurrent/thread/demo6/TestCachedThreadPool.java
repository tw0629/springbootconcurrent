package com.tian.concurrent.thread.demo6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author David Tian
 * @since 2019-04-16
 */
public class TestCachedThreadPool {

    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            executorService.execute(new TestRunnable());
            System.out.println("************* a" + i + " *************");
        }
        executorService.shutdown();
    }
}
