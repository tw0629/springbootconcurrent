package com.tian.concurrent.thread.demo6;

import java.util.concurrent.Callable;

/**
 * @author David Tian
 * @since 2019-04-16
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    /**
     * ����ľ�����̣�һ�����񴫸�ExecutorService��submit��������÷����Զ���һ���߳���ִ�С�
     *
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {

        System.out.println("call()�������Զ�����,�ɻ����             " + Thread.currentThread().getName());
        //һ��ģ���ʱ�Ĳ���
        for (int i = 999999; i > 0; i--){} ;

        return "call()�������Զ����ã�����Ľ���ǣ�" + id + "    " + Thread.currentThread().getName();
    }
}
