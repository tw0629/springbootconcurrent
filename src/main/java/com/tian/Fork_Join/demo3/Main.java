package com.tian.Fork_Join.demo3;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 11:32
 */
public class Main {

    public static void main(String[] args) {
        DocumentMock mock = new DocumentMock();
        String[][] document = mock.generateDocument(100, 1000, "the");
        DocumentTask task = new DocumentTask(document, 0, 100, "the");
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        //显示线程进展信息
        try {
            while(!task.isDone()){
                System.out.println("***************************************************");
                System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
                System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
                System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
                System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
                System.out.println("***************************************************");
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //关闭线程池
        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            System.out.printf("Main: The word appears %d times in the document", task.get());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
//Main: Parallelism: 8
//        Main: Active Threads: 0
//        Main: Task Count: 0
//        Main: Steal Count: 26
