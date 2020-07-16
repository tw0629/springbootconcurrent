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

                //1 ֮ǰ��
                //function1();

                //2 �޸�֮���
                try {
                    function1();
                } catch (Exception e) {
                    //�쳣����
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
            //3 ֮ǰ��
            //cdl.await();

            //4 �޸�֮���  ��ֹĳ���߳������ڵ� û��ִ��ֹͣ
            cdl.await(8, TimeUnit.SECONDS);


            System.out.println("����ִ���߳̽���");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("ִ���߳��쳣");
        }
        finally {
            executorService.shutdown();
            System.out.println("ִ���̹߳ر�");
        }


    }

    private static void function1(){
        int i = 10/0;
        System.out.println("����һ���");
    }

    private static void function2(){
        System.out.println("���������");
    }

    private static void function3(){
        System.out.println("���������");
    }

}
