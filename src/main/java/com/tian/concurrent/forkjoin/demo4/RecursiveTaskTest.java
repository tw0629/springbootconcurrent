package com.tian.concurrent.forkjoin.demo4;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/** RecursiveTask有返回值
 *
 * 计算一个大的整数数组的和
 *
 * @author David Tian
 * @since 2019-05-23
 */
public class RecursiveTaskTest extends RecursiveTask<Integer> {
    /**
     * 阈值，每个小任务处理的数量
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
            // 小于阈值则直接计算
            for (int i = low; i < high; i++) {
                sum += array[i];
            }
        } else {
            // 一个大任务分割成两个子任务
            int mid = (low + high) >>> 1;
            RecursiveTaskTest left = new RecursiveTaskTest(array, low, mid);
            RecursiveTaskTest right = new RecursiveTaskTest(array, mid + 1, high);

            // 进行并行计算
            left.fork();
            right.fork();

            // 结果合并
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

        // 开始创建任务
        RecursiveTaskTest sumTask = new RecursiveTaskTest(array, 0, array.length - 1);
        // 创建ForkJoinPool线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 提交任务到线程池
        forkJoinPool.submit(sumTask);
        // 获取结果，get方法会阻塞
        Integer result = sumTask.get();
        System.out.println("计算结果：" + result);
    }
}