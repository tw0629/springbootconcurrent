package com.tian.concurrent.thread.demo6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * 在Java5之后，任务分两类：一类是实现了Runnable接口的类，一类是实现了Callable接口的类。
 * 两者都可以被ExecutorService执行，但是Runnable任务没有返回值，而Callable任务有返回值。
 * 并且Callable的call()方法只能通过ExecutorService的submit(Callable<T> task) 方法来执行，并且返回一个 <T> Future<T>，
 * 是表示任务等待完成的 Future。
 *
 *
 *  https://blog.51cto.com/lavasoft/115112
 *
 *
 * @author David Tian
 * @since 2019-04-16
 */
public class CallableDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        //new ThreadPoolExecutor()

        List<Future<String>> resultList = new ArrayList<Future<String>>();

        //创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future = executorService.submit(new TaskWithResult(i));
            //将任务执行结果存储到List中
            resultList.add(future);
        }

        //遍历任务的结果
        for (Future<String> fs : resultList) {
            try {
                System.out.println(fs.get());     //打印各个线程（任务）执行的结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。
                executorService.shutdown();
            }
        }
    }
}
