package com.tian.Fork_Join.demo1;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 09:18
 */
public class RecursiveTaskTest extends RecursiveTask<Integer> {
    /**
     * ��ֵ��ÿ��С�����������
     */
    private static final int THRESHOLD = 5;
    private int[] array;
    private int low;
    private int high;

    private RecursiveTaskTest(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (high - low <= THRESHOLD) {

            // С����ֵ��ֱ�Ӽ���
            for (int i = low; i < high; i++) {
                System.out.println(Thread.currentThread().getName() + "��iֵ:" + low );

                sum += array[i];

            }
        } else {

            System.out.println(Thread.currentThread().getName() + "====" + low + "====" + high);

            // һ��������ָ������������
            int mid = (low + high) >>> 1;
            RecursiveTaskTest left = new RecursiveTaskTest(array, low, mid);
            RecursiveTaskTest right = new RecursiveTaskTest(array, mid + 1, high);

            // ���в��м���
            left.fork();
            right.fork();

            try {
                System.out.println(Thread.currentThread().getName() + "==left.join==" + left.join() + "==left.get==" + left.get());
                System.out.println(Thread.currentThread().getName() + "==right.join==" + right.join() + "==right.get==" + right.get());

            } catch (Exception e) {
                e.printStackTrace();
            }


            // ����ϲ�
            sum = left.join() + right.join();

        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(100);
        }
        System.out.println(Arrays.toString(array));

        // ��ʼ��������
        RecursiveTaskTest sumTask = new RecursiveTaskTest(array, 0, array.length - 1);
        // ����ForkJoinPool�̳߳�
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // �ύ�����̳߳�
        forkJoinPool.submit(sumTask);
        // ��ȡ�����get����������
        Integer result = sumTask.get();
        System.out.println("��������" + result);
    }
}
