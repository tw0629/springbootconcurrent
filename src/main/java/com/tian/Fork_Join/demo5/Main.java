package com.tian.Fork_Join.demo5;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 12:10
 */
public class Main {

    public static void main(String[] args) {
        //创建线程池
        ForkJoinPool pool = new ForkJoinPool();
        int array[] = new int[100];
        Task task = new Task(array, 0, 100);
        //执行任务
        pool.execute(task);
        pool.shutdown();
        //等待任务执行结束
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //判断是否非正常结束
        if(task.isCompletedAbnormally()){
            System.out.println("Main: An exception has ocurred");
            //获取异常信息
            System.out.println("Main: "+task.getException());
        }
        System.out.println("Main: Result: "+task.join());
    }
}
