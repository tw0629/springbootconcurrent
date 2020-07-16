package com.tian.concurrent.forkjoin.demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author David Tian
 * @since 2019-05-23
 *
 *
 * ������������������
 * ����fork-join��ܶ����ݲ���������з��飬��ɷ������
 *
 *
 * https://blog.csdn.net/ouyunwen/article/details/82846946
 *
 */
public class BatchInsertTask extends RecursiveTask<Integer> {
    //Ҫ���������
    List<Integer> records;

    public BatchInsertTask(List<Integer> records) {
        this.records = records;
    }

    @Override
    protected Integer compute() {
        //��Ҫ�������������3����ֱ�Ӳ���
        if (records.size() < 3) {
            return computeDirectly();
        } else {
            //���Ҫ��������ݴ��ڵ���3������з������
            int size = records.size();

            //��һ������
            BatchInsertTask aTask = new BatchInsertTask(records.subList(0, size / 2));
            //�ڶ�������
            BatchInsertTask bTask = new BatchInsertTask(records.subList(size / 2, records.size()));

            //�������񲢷�ִ������
            invokeAll(aTask, bTask);
            // ����ִ��
            //aTask.fork();
            //bTask.fork();

            //��������Ĳ��������������
            return aTask.join() + bTask.join();
        }
    }

    /**
     * �����������ݵ��߼�
     */
    private int computeDirectly() {
        try {
            Thread.sleep((long) (records.size() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"   �����ˣ�" + Arrays.toString(records.toArray()));
        return records.size();
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //parallelism�����жȣ�Ҳ����˵�ǹ����߳�������Ĭ����ϵͳ���ô�������������Ҳ�����߼�CPU�ĸ�������С��1��
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);

        List<Integer> list = new ArrayList<>();

//        for(int i=0; i<13;i++){
//            list.add(1);
//        }
        list.add(1);list.add(2);list.add(3);list.add(4);list.add(5);list.add(6);list.add(7);list.add(8);list.add(9);list.add(10);list.add(11);list.add(12);list.add(13);

        int size = list.size();
        //ֻ�ǲ���
        System.out.println("============>"+list.subList(0, size / 2));
        System.out.println("============>"+list.subList(size / 2, list.size()));


        BatchInsertTask batchInsertTask = new BatchInsertTask(list);

        long start = System.currentTimeMillis();
        System.out.println("============>��ʼ����");

        ForkJoinTask<Integer> reslut = forkJoinPool.submit(batchInsertTask);
        System.out.println("============>������� result:"+reslut.get());

        System.out.println("��ʱ costTime:"+(System.currentTimeMillis()-start)+"ms");
    }

}
