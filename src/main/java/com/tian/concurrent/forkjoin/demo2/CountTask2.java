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
 * 例子的功能描述:
 * 计算1+2+3+……1000000000的和，即1000000000！的结果
 *
 */
public class CountTask2 extends RecursiveTask<Long> {

    //最大计算范围 一亿
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
            //如果这次计算的范围大于了计算时规定的最大范围，则进行拆分
            //每次拆分时，都拆分成原来任务范围的一半
            //如1-10,则拆分为1-5,6-10
            Long middle = (startNum + endNum) / 2;
            CountTask2 subTask1 = new CountTask2(startNum, middle);
            CountTask2 subTask2 = new CountTask2(middle + 1, endNum);
            //拆分后，执行fork
            subTask1.fork();
            subTask2.fork();

            sum += subTask2.join();
            sum += subTask1.join();

        } else {
            //在范围内，则进行计算
            for (; startNum <= endNum; startNum++) {
                sum += startNum;
            }
        }

        return sum;
    }

    public static void main(String[] args) {

        Long startNum = 1L;
        //十亿
        Long endNum = 1000000000L;

        long startTime = System.currentTimeMillis();
        System.out.println("============>开始计算");

        CountTask2 countTask = new CountTask2(startNum, endNum);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Long> result = forkJoinPool.submit(countTask);
        try {

            System.out.println("============>计算完成 result:" + result.get());
            long endTime = System.currentTimeMillis();
            System.out.println("耗时 costTime:" + (endTime - startTime)+"ms");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * ============>开始计算
     * ============>计算完成 result:500000000500000000
     * 耗时 costTime:3447ms
     */
}
