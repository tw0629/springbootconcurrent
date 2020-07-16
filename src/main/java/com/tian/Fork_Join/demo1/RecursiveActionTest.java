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
     * ��ֵ��ÿ��"С����"���ֻ��ӡ5����
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
        // ��end-start��ֵС��MAXʱ�򣬿�ʼ��ӡ
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "��iֵ:" + i);
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "====" + start + "====" + end);

            // ��������ֽ������С����
            int middle = (start + end) / 2;
            RecursiveActionTest left = new RecursiveActionTest(start, middle);
            RecursiveActionTest right = new RecursiveActionTest(middle, end);

            // ����ִ������С����
            left.fork();
            right.fork();

            //��ΪRecursiveActionû�з��ؽ��
            //left.join()
        }
    }

    public static void main(String[] args) throws Exception {
        // Ĭ���߳��� �߼�CPU�ĸ���
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // �ύ�ɷֽ��PrintTask����
        forkJoinPool.submit(new RecursiveActionTest(0, 20));
        // ������ǰ�߳�ֱ�� ForkJoinPool �����е�����ִ�н���
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        // �ر��̳߳�
        forkJoinPool.shutdown();
    }
}