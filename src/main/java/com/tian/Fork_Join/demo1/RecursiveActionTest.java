package com.tian.Fork_Join.demo1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @author David Tian
 * @desc ?????????
 * @since 2020-06-23 09:12
 */
public class RecursiveActionTest extends RecursiveAction {
    /**
     * 阈值，每个"小任务"最多只打印5个数
     */
    private static final int MAX = 5;

    private int start;
    private int end;

    private RecursiveActionTest(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        // 当end-start的值小于MAX时候，开始打印
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "的i值:" + i);
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "====" + start + "====" + end);

            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            RecursiveActionTest left = new RecursiveActionTest(start, middle);
            RecursiveActionTest right = new RecursiveActionTest(middle, end);

            // 并行执行两个小任务
            left.fork();
            right.fork();

            //因为RecursiveAction没有返回结果
            //left.join()
        }
    }

    public static void main(String[] args) throws Exception {
        // 默认线程数 逻辑CPU的个数
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 提交可分解的PrintTask任务
        forkJoinPool.submit(new RecursiveActionTest(0, 20));
        // 阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        // 关闭线程池
        forkJoinPool.shutdown();
    }
}