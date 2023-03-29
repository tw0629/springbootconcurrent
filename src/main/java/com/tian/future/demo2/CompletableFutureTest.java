package com.tian.future.demo2;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author David Tian
 * @desc https://cloud.tencent.com/developer/article/1366581
 * @since 2020-04-27 17:48
 */
public class CompletableFutureTest {

    public static String getThreadName(){
        return Thread.currentThread().getName();
    }


    //new CompletableFuture<String>()
    //completableFuture.complete("success")
    @Test
    public void test(){
        CompletableFuture<String> completableFuture=new CompletableFuture<String>();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println(getThreadName()+"执行.....");
                    //在子线程中完成主线程completableFuture的完成
                    completableFuture.complete("success");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1=new Thread(runnable);
        t1.start();//启动子线程

        String result= null;//主线程阻塞，等待完成
        try {
            System.out.println(getThreadName()+"=====XXX===test1===");
            result = completableFuture.get();
            System.out.println(getThreadName()+"=====XXX===test2===");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(getThreadName()+"1x:  "+result);
    }


    //CompletableFuture.runAsync(new Runnable() {})
    @Test
    public void test2(){

        CompletableFuture<Void> future=CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(getThreadName()+"正在执行一个没有返回值的异步任务。");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        try {
            System.out.println(getThreadName()+"======XXX===test1===");
            future.get();
            System.out.println(getThreadName()+"======XXX===test2===");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(getThreadName()+" 结束。");

    }


    //CompletableFuture.supplyAsync(new Supplier<String>() {})
    @Test
    public void test3(){

        CompletableFuture<String> future=CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                try {
                    System.out.println(getThreadName()+"正在执行一个有返回值的异步任务。");
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "OK";
            }
        });

        String result= null;
        try {
            System.out.println(getThreadName()+"======XXX===test1===");
            result = future.get();
            System.out.println(getThreadName()+"======XXX===test2===");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(getThreadName()+"  结果："+result);
    }


    //asyncCallback
    //CompletableFuture.supplyAsync(new Supplier<String>() {})
    //task.thenApply()
    @Test
    public void test4(){

        CompletableFuture<String> task = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(getThreadName()+"    supplyAsync");
                return "123";
            }
        });

        System.out.println("======XXXYYY======");
        CompletableFuture<Integer> result1 = task.thenApply(number->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getThreadName()+"    thenApply1"+"   number:"+number);
            return Integer.parseInt(number);
        });

        System.out.println("======XXXYYY2======");
        CompletableFuture<Integer> result2 = result1.thenApply(number->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getThreadName()+"    thenApply2"+"   number:"+number);
            return number*2;
        });

        System.out.println("======XXXYYY3======");
        try {
            System.out.println(getThreadName()+" => "+result2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     *
     * 打印：
     * ======XXXYYY======
     * ======XXXYYY2======
     * ======XXXYYY3======
     * ForkJoinPool.commonPool-worker-1supplyAsync
     * ForkJoinPool.commonPool-worker-1    thenApply1   number:123
     * ForkJoinPool.commonPool-worker-1    thenApply2   number:123
     * main => 246
     *
     */


    //asyncCallback2
    //CompletableFuture.supplyAsync(new Supplier<String>() {})
    //task.thenApply()
    //chain2.thenAccept()
    @Test
    public void test5(){

        CompletableFuture<String> task=CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(getThreadName()+"    supplyAsync");
                return "123";
            }
        });

        System.out.println("======XXXYYY======");
        CompletableFuture<Integer> chain1 = task.thenApply(number->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getThreadName()+"    thenApply1");
            return Integer.parseInt(number);
        });

        System.out.println("======XXXYYY2======");
        CompletableFuture<Integer> chain2 = chain1.thenApply(number->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getThreadName()+"    thenApply2");
            return number*2;
        });

        System.out.println("======XXXYYY3======");
        CompletableFuture<Void> result=chain2.thenAccept(product->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(getThreadName()+"    chain2.get()="+chain2.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getThreadName()+"    thenAccept="+product);
        });

        try {
            System.out.println("======XXXYYY4======");
            result.get();
            System.out.println("======XXXYYY5======");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(getThreadName()+"    end");

    }

}
