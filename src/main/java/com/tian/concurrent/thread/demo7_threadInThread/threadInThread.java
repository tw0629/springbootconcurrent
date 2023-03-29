package com.tain.concurrent.thread.demo6_threadInThread;

import java.util.concurrent.TimeUnit;


/**
 * 1 如果在线程中使用Thread开启一个线程
 *
 * 简单的后台线程（守护线程Daemons）练习，该练习主要演示的是后台线程在最后一个非后台线程结束后，也会被关闭
 * @author David Tian
 * @since 2019-06-17
 *
 */
public class threadInThread implements Runnable {

    @Override
    public void run() {
        try {
            Thread thread = new Thread(new WWWThread());
            //thread.run();
            thread.start();
            while(true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println("tianwei --- SimpleDaemons is Daemons:"+Thread.currentThread().isDaemon()+"---"+Thread.currentThread().getName()+"---"+Thread.currentThread().getThreadGroup()+" XXXXXX");
                System.out.println(Thread.currentThread() +" "+this +" XXXXXX 2");
                System.out.println("              ");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("sleep() Interrupted");
        }
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0 ; i < 3;i++){
            Thread daemons = new Thread(new threadInThread());
            daemons.setDaemon(true);
            daemons.start();
            System.out.println("===========mian==========="+"---"+Thread.currentThread().getName()+"---"+Thread.currentThread().getThreadGroup()+" ZZZZZZ");
            System.out.println("============mian==========");
            System.out.println("                          ");
        }
        System.out.println("daemons start!");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("============>> 1s 后      ");

    }

}


/**
 * 校验守护线程中创建的线程是否是守护线程
 */
class WWWThread implements Runnable {
    @Override
    public void run() {
        System.out.println("WWWThread is Daemon:"+Thread.currentThread().isDaemon()+"---"+Thread.currentThread().getName()+"---"+Thread.currentThread().getThreadGroup()+" YYYYYY");
        Thread thread = new Thread(new inner());
        //thread.run();
        thread.start();
    }
}


class inner implements Runnable {

    @Override
    public void run() {
        System.out.println("innerThread is Daemon:"+Thread.currentThread().isDaemon()+"---"+Thread.currentThread().getName()+"---"+Thread.currentThread().getThreadGroup()+" 111111");
    }
}