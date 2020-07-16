package com.tian.concurrent.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/** 并发工具 CyclicBarrier
 *
 * @author David Tian
 * @since 2019-04-24
 */
public class CyclicBarrierTest3 {

    /**
     * 3 看一下为await指定时间的效果
     *
     *
     * 上面的代码在main方法的for循环中，故意让最后一个线程启动延迟，因为在前面三个线程都达到barrier之后，
     * 等待了指定的时间发现第四个线程还没有达到barrier，就抛出异常并继续执行后面的任务。
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);

        for(int i=0;i<N;i++) {
            if(i<N-1)
                new Writer(barrier).start();
            else {
                try {
                    //最后一个线程多等了5秒
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Writer(barrier).start();
            }
        }
    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                try {

                    //todo  说明最多等待2秒
                    cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"所有线程写入完毕，继续处理其他任务...");
        }
    }

    /**打印结果：
     *
     * 线程Thread-0正在写入数据...
     * 线程Thread-1正在写入数据...
     * 线程Thread-2正在写入数据...
     * 线程Thread-0写入数据完毕，等待其他线程写入完毕
     * 线程Thread-2写入数据完毕，等待其他线程写入完毕
     * 线程Thread-1写入数据完毕，等待其他线程写入完毕
     * 线程Thread-3正在写入数据...
     * java.util.concurrent.BrokenBarrierException
     * 	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:250)
     * 	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
     * 	at com.tain.concurrent.CyclicBarrier.CyclicBarrierTest3$Writer.run(CyclicBarrierTest3.java:50)
     * Thread-1所有线程写入完毕，继续处理其他任务...
     * java.util.concurrent.BrokenBarrierException
     * 	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:250)
     * 	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
     * 	at com.tain.concurrent.CyclicBarrier.CyclicBarrierTest3$Writer.run(CyclicBarrierTest3.java:50)Thread-2所有线程写入完毕，继续处理其他任务...
     *
     * java.util.concurrent.TimeoutException
     * 	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:257)
     * 	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
     * 	at com.tain.concurrent.CyclicBarrier.CyclicBarrierTest3$Writer.run(CyclicBarrierTest3.java:50)
     * Thread-0所有线程写入完毕，继续处理其他任务...
     * 线程Thread-3写入数据完毕，等待其他线程写入完毕
     * java.util.concurrent.BrokenBarrierException
     * 	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:207)
     * 	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
     * 	at com.tain.concurrent.CyclicBarrier.CyclicBarrierTest3$Writer.run(CyclicBarrierTest3.java:50)
     * Thread-3所有线程写入完毕，继续处理其他任务...
     */
}
