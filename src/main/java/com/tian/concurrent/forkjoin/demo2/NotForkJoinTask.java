package com.tian.concurrent.forkjoin.demo2;

/**
 * @author David Tian
 * @since 2019-05-23
 */
public class NotForkJoinTask {

    public static void main(String[] args) {

        new Thread(()->{
            Long sum = 0L;
            Long maxSize = 1000000000L;

            long startTime = System.currentTimeMillis();
            System.out.println("============>开始计算");

            for (Long i = 1L; i <= maxSize; i++) {
                sum += i;
            }
            System.out.println("============>计算完成 result:"+sum);

            long endTime = System.currentTimeMillis();
            System.out.println("耗时 costTime:"+(endTime-startTime)+"ms");

        }).start();

    }

    /**
     * ============>开始计算
     * ============>计算完成 result:500000000500000000
     * 耗时 costTime:7147ms
     */
}
