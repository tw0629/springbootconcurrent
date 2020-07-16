package com.tian.future.demo1;

import java.util.concurrent.*;

/**
 * @author David Tian
 * @desc
 * @since 2020-04-27 15:09
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }

        System.out.println("FutureTestһ���߳���ִ������");

        try {

            System.out.println("FutureTestһtask���н��"+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("��������ִ�����");
    }
}
