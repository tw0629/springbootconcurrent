package com.tain.concurrent.thread.demo6_threadInThread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * 2.�����̳߳صķ�ʽ
 *
 * �򵥵ĺ�̨�̣߳��ػ��̣߳���ϰ������ϰ��Ҫ��ʾ���Ǻ�̨�߳������һ���Ǻ�̨�߳̽�����Ҳ�ᱻ�ر�
 * @author David Tian
 * @since 2019-06-17
 */
public class threadInThread2 implements Runnable {

    @Override
    public void run() {
        try {
            //ExecutorService exec = Executors.newCachedThreadPool(4);
            ExecutorService exec = Executors.newFixedThreadPool(4);
            exec.execute(new WWWThread2());
            exec.shutdown();//�ر��̳߳�
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("tianwei --- SimpleDaemons is Daemons:"+Thread.currentThread().isDaemon());
            System.out.println(Thread.currentThread() +" "+this );
            System.out.println("              ");

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("sleep() Interrupted");
        }
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0 ; i < 3;i++){
            Thread daemons = new Thread(new threadInThread2());
            daemons.setDaemon(true);
            daemons.start();

            System.out.println("======================");
            System.out.println("======================");
        }
        System.out.println("daemons start!");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("============>> 1s ��      ");

    }

}


/**
 * У���ػ��߳��д������߳��Ƿ����ػ��߳�
 */
class WWWThread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("WWWThread2 is Daemon:"+Thread.currentThread().isDaemon());
    }
}
