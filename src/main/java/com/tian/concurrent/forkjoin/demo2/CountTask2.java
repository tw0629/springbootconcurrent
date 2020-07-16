package com.tian.concurrent.forkjoin.demo2;

import com.tian.concurrent.forkjoin.demo1.CountTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author David Tian
 * @since 2019-05-23
 *
 *
 * ���ӵĹ�������:
 * ����1+2+3+����1000000000�ĺͣ���1000000000���Ľ��
 *
 */
public class CountTask2 extends RecursiveTask<Long> {

    //�����㷶Χ һ��
    Long maxCountRange = 100000000L;
    Long startNum, endNum;

    public CountTask2(Long startNum, Long endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    @Override
    protected Long compute() {
        long range = endNum - startNum;
        long sum = 0;

        if (range >= maxCountRange) {
            //�����μ���ķ�Χ�����˼���ʱ�涨�����Χ������в��
            //ÿ�β��ʱ������ֳ�ԭ������Χ��һ��
            //��1-10,����Ϊ1-5,6-10
            Long middle = (startNum + endNum) / 2;
            CountTask2 subTask1 = new CountTask2(startNum, middle);
            CountTask2 subTask2 = new CountTask2(middle + 1, endNum);
            //��ֺ�ִ��fork
            subTask1.fork();
            subTask2.fork();

            sum += subTask2.join();
            sum += subTask1.join();

        } else {
            //�ڷ�Χ�ڣ�����м���
            for (; startNum <= endNum; startNum++) {
                sum += startNum;
            }
        }

        return sum;
    }

    public static void main(String[] args) {

        Long startNum = 1L;
        //ʮ��
        Long endNum = 1000000000L;

        long startTime = System.currentTimeMillis();
        System.out.println("============>��ʼ����");

        CountTask2 countTask = new CountTask2(startNum, endNum);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Long> result = forkJoinPool.submit(countTask);
        try {

            System.out.println("============>������� result:" + result.get());
            long endTime = System.currentTimeMillis();
            System.out.println("��ʱ costTime:" + (endTime - startTime)+"ms");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * ============>��ʼ����
     * ============>������� result:500000000500000000
     * ��ʱ costTime:3447ms
     */
}
