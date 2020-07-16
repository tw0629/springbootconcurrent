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
        //��һ�ַ�ʽ
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        //ע�⣺��Task��װ��FutureTask
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executor.submit(futureTask);
        executor.shutdown();

        //�ڶ��ַ�ʽ��ע�����ַ�ʽ�͵�һ�ַ�ʽЧ�������Ƶģ�ֻ����һ��ʹ�õ���ExecutorService��һ��ʹ�õ���Thread
        /*Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();*/

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }

        System.out.println("FutureTaskTestһ���߳���ִ������");

        try {
            System.out.println("FutureTaskTestһtask���н��"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("��������ִ�����");
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
        String s = task.get();//���������������ֱ���������ʱ�᷵��
        boolean done1 = task.isDone();
        long l2 = System.currentTimeMillis();

        System.out.println("���񷵻ؽ�� = "+done+"   "+done1+"    time = "+(l2-l1)+"   "+s);





        //ForkJoinPool
        //public class ForkJoinPool extends AbstractExecutorService {

        //ForkJoinTask
        //pblic abstract class ForkJoinTask<V> implements Future<V>, Serializable {

    }
}
