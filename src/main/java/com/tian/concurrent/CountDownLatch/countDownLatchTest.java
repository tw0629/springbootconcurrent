package com.tian.concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * https://blog.csdn.net/u010598327/article/details/82083038
 *
 * @author David Tian
 * @since 2019-04-22
 */
public class countDownLatchTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CountDownLatch cdl = new CountDownLatch(3);

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                //1 之前的
                //function1();

                //2 修改之后的
                try {
                    function1();
                } catch (Exception e) {
                    //异常处理
                    e.printStackTrace();
                }
                finally {
                    cdl.countDown();
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                function2();
                cdl.countDown();
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                function3();
                cdl.countDown();
            }
        });


        try {
            //3 之前的
            //cdl.await();

            //4 修改之后的  防止某个线程无限期的 没有执行停止
            cdl.await(8, TimeUnit.SECONDS);


            System.out.println("三个执行线程结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("执行线程异常");
        }
        finally {
            executorService.shutdown();
            System.out.println("执行线程关闭");
        }


    }

    private static void function1(){
        int i = 10/0;
        System.out.println("方法一完成");
    }

    private static void function2(){
        System.out.println("方法二完成");
    }

    private static void function3(){
        System.out.println("方法三完成");
    }

}
