package com.tian.concurrent.forkjoin.demo7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author David Tian
 * @since 2019-05-26
 */

/**同步用法*/
public class SumArray {
    private static class SumTask extends RecursiveTask<Integer> {

        //private final static int THRESHOLD = MakeArray.ARRAY_LENGTH/10;
        private final static int THRESHOLD = 2;

        private int[] src; //表示我们要实际统计的数组
        private int fromIndex;//开始统计的下标
        private int toIndex;//统计到哪里结束的下标

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if(toIndex-fromIndex < THRESHOLD) {
                int count = 0;
                for(int i=fromIndex;i<=toIndex;i++) {
                    //SleepTools.ms(1);
                    count = count + src[i];
                }
                return count;
            }else {
                //fromIndex....mid....toIndex
                //1...................70....100
                int mid = (fromIndex+toIndex)/2;
                //将任务一分为二
                SumTask left = new SumTask(src,fromIndex,mid);
                SumTask right = new SumTask(src,mid+1,toIndex);
                invokeAll(left,right);   //提交任务
                return left.join()+right.join();
            }
        }
    }

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();
        //int[] src = MakeArray.makeArray();
        int[] src = new int[9];

        SumTask innerFind = new SumTask(src,0,src.length-1);

        long start = System.currentTimeMillis();

        //同步调用
        int a = pool.invoke(innerFind);


        System.out.println("Task is Running.....");
        System.out.println("The count is "+innerFind.join()
                +" spend time:"+(System.currentTimeMillis()-start)+"ms"+a);
    }
}