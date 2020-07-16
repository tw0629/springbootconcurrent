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
            System.out.println("============>��ʼ����");

            for (Long i = 1L; i <= maxSize; i++) {
                sum += i;
            }
            System.out.println("============>������� result:"+sum);

            long endTime = System.currentTimeMillis();
            System.out.println("��ʱ costTime:"+(endTime-startTime)+"ms");

        }).start();

    }

    /**
     * ============>��ʼ����
     * ============>������� result:500000000500000000
     * ��ʱ costTime:7147ms
     */
}
