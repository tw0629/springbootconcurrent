package com.tian.future.demo1;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author David Tian
 * @desc
 * @since 2020-04-27 15:10
 */
public class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++) {
            sum += i;
        }

        ArrayList arrayList = new ArrayList();
        arrayList.add(new Object());
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add(new Object());

        StringBuffer s = new StringBuffer();
        s.append("");

        return sum;
    }


}
