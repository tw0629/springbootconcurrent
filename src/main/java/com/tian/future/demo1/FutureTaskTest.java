package com.tian.future.demo1;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author David Tian
 * @desc
 * @since 2020-04-27 15:12
 */
public class FutureTaskTest {

    public static void main(String[] args) {
        //第一种方式
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        //注意：将Task包装成FutureTask
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executor.submit(futureTask);
        executor.shutdown();

        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        /*Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();*/

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }

        System.out.println("FutureTaskTest一主线程在执行任务");

        try {
            System.out.println("FutureTaskTest一task运行结果"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }


    /**
     * public class FutureTask<V> implements RunnableFuture<V>
     */
    @Test
    public void testFutureTask() throws Exception {
        FutureTask<String> task = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"gogo");
        new Thread(task).start();

        boolean done = task.isDone();
        long l1 = System.currentTimeMillis();
        String s = task.get();//这个方法会阻塞，直到任务完成时会返回
        boolean done1 = task.isDone();
        long l2 = System.currentTimeMillis();

        System.out.println("任务返回结果 = "+done+"   "+done1+"    time = "+(l2-l1)+"   "+s);





        //ForkJoinPool
        //public class ForkJoinPool extends AbstractExecutorService {

        //ForkJoinTask
        //pblic abstract class ForkJoinTask<V> implements Future<V>, Serializable {

    }
}
