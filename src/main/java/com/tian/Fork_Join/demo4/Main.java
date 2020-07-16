package com.tian.Fork_Join.demo4;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 12:08
 */
public class Main {

    public static void main(String[] args) {
        //创建线程池
        ForkJoinPool pool = new ForkJoinPool();
        //创建三个任务
        FolderProcessor system = new FolderProcessor("C:\\Windows", "log");
        FolderProcessor apps = new FolderProcessor("C:\\Program Files", "log");
        FolderProcessor documents = new FolderProcessor("C:\\Documents And Settings", "log");
        //执行任务
        pool.execute(system);
        pool.execute(apps);
        pool.execute(documents);
        //显示线程进展信息
        try {
            while((!system.isDone())||(!apps.isDone())||(!documents.isDone())){
                System.out.println("***************************************************");
                System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
                System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
                System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
                System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
                System.out.println("***************************************************");
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //关闭线程池
        pool.shutdown();
        List<String> results;
        results = system.join();
        System.out.printf("System: %d files found.\n", results.size());
        results = apps.join();
        System.out.printf("Apps: %d files found.\n", results.size());
        results = documents.join();
        System.out.printf("Documents: %d files found.\n", results.size());
    }
}
