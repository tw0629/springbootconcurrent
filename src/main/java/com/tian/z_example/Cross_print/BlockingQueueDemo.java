package com.tian.z_example.Cross_print;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-26 18:19
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        //读线程
        new Thread(() -> {
            int i =0;
            while (true) {
                try {
                    String item = queue.take();
                    System.out.print(Thread.currentThread().getName() + ": " + i + " ");
                    System.out.println(item);
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //写线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    String item = "go"+i;
                    System.out.print(Thread.currentThread().getName() + ": " + i + " ");
                    System.out.println(item);
                    queue.put(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
