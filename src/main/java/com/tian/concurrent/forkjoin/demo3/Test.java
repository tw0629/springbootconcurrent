package com.tian.concurrent.forkjoin.demo3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author David Tian
 * @since 2019-05-23
 *
 * Description: Fork/Joinִ����
 *
 *
 * http://developer.51cto.com/art/201708/547413.htm
 *
 */
public class Test {

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        String[] strings = {"a", "ah", "b", "ba", "ab", "ac", "sd", "fd", "ar", "te", "se", "te",
                "sdr", "gdf", "df", "fg", "gh", "oa", "ah", "qwe", "re", "ty", "ui"};
        List<String> stringList = new ArrayList<>(Arrays.asList(strings));

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinService<List<String>> forkJoinService = ForkJoinTest.getInstance(stringList, 20);

        long startTime = System.currentTimeMillis();
        System.out.println("============>��ʼ����");

        // �ύ�ɷֽ��ForkJoinTask����
        ForkJoinTask<List<String>> future = pool.submit(forkJoinService);

        System.out.println("��ʱ costTime:"+(System.currentTimeMillis()-startTime)+"ms");

        System.out.println(future.get());
        // �ر��̳߳�
        pool.shutdown();

    }

}