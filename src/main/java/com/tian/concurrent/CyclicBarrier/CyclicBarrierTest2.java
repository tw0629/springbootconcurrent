package com.tian.concurrent.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/** 并发工具 CyclicBarrier
 *
 * @author David Tian
 * @since 2019-04-24
 */
public class CyclicBarrierTest2 {

    /**
     * 2 如果说想在所有线程写入操作完之后，进行额外的其他操作可以为CyclicBarrier提供Runnable参数
     *
     *
     * 从结果可以看出，当四个线程都到达barrier状态后，会从四个线程中选择一个线程去执行Runnable。
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N,new Runnable() {
            //？？为什么只有一个线程执行
            @Override
            public void run() {
                System.out.println("当前线程"+Thread.currentThread().getName());
            }
        });

        for(int i=0;i<N;i++)
            new Writer(barrier).start();
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
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }

    /**打印结果：
     *
     * 线程Thread-0正在写入数据...
     * 线程Thread-1正在写入数据...
     * 线程Thread-2正在写入数据...
     * 线程Thread-3正在写入数据...
     * 线程Thread-1写入数据完毕，等待其他线程写入完毕
     * 线程Thread-3写入数据完毕，等待其他线程写入完毕
     * 线程Thread-2写入数据完毕，等待其他线程写入完毕
     * 线程Thread-0写入数据完毕，等待其他线程写入完毕
     * 当前线程Thread-0
     * 所有线程写入完毕，继续处理其他任务...
     * 所有线程写入完毕，继续处理其他任务...
     * 所有线程写入完毕，继续处理其他任务...
     * 所有线程写入完毕，继续处理其他任务...
     */
}
