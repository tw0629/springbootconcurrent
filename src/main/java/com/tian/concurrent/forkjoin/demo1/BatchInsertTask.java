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
 * 批量插入数据任务类
 * 利用fork-join框架对数据插入任务进行分组，变成分组插入
 *
 *
 * https://blog.csdn.net/ouyunwen/article/details/82846946
 *
 */
public class BatchInsertTask extends RecursiveTask<Integer> {
    //要插入的数据
    List<Integer> records;

    public BatchInsertTask(List<Integer> records) {
        this.records = records;
    }

    @Override
    protected Integer compute() {
        //当要插入的数据少于3，则直接插入
        if (records.size() < 3) {
            return computeDirectly();
        } else {
            //如果要插入的数据大于等于3，则进行分组插入
            int size = records.size();

            //第一个分组
            BatchInsertTask aTask = new BatchInsertTask(records.subList(0, size / 2));
            //第二个分组
            BatchInsertTask bTask = new BatchInsertTask(records.subList(size / 2, records.size()));

            //两个任务并发执行起来
            invokeAll(aTask, bTask);
            // 并行执行
            //aTask.fork();
            //bTask.fork();

            //两个分组的插入的行数加起来
            return aTask.join() + bTask.join();
        }
    }

    /**
     * 真正插入数据的逻辑
     */
    private int computeDirectly() {
        try {
            Thread.sleep((long) (records.size() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"   插入了：" + Arrays.toString(records.toArray()));
        return records.size();
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //parallelism，并行度，也可以说是工作线程数量，默认是系统可用处理器的数量，也就是逻辑CPU的个数，最小是1；
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);

        List<Integer> list = new ArrayList<>();

//        for(int i=0; i<13;i++){
//            list.add(1);
//        }
        list.add(1);list.add(2);list.add(3);list.add(4);list.add(5);list.add(6);list.add(7);list.add(8);list.add(9);list.add(10);list.add(11);list.add(12);list.add(13);

        int size = list.size();
        //只是测试
        System.out.println("============>"+list.subList(0, size / 2));
        System.out.println("============>"+list.subList(size / 2, list.size()));


        BatchInsertTask batchInsertTask = new BatchInsertTask(list);

        long start = System.currentTimeMillis();
        System.out.println("============>开始计算");

        ForkJoinTask<Integer> reslut = forkJoinPool.submit(batchInsertTask);
        System.out.println("============>计算完成 result:"+reslut.get());

        System.out.println("耗时 costTime:"+(System.currentTimeMillis()-start)+"ms");
    }

}
