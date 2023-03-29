package com.tian.concurrent.threadlocal.demo1;

import org.junit.Test;

/**
 * @author David Tian
 * @since 2019-06-12
 */
public class Profiler {

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue(){
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final Long end() {
        return System.currentTimeMillis()-TIME_THREADLOCAL.get();
    }

    public static void clear() {
        TIME_THREADLOCAL.remove();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        Thread.sleep(1000L);
        System.out.println("消耗时间："+Profiler.end()+"mills毫秒");

    }

}
