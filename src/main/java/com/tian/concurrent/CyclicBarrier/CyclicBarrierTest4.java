package com.tian.concurrent.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author David Tian
 * @since 2019-04-24
 */
public class CyclicBarrierTest4 {

    /**
     * 4 CyclicBarrier是可以重用的
     *
     *
     * 从执行结果可以看出，在初次的4个线程越过barrier状态后，又可以用来进行新一轮的使用。而CountDownLatch无法进行重复使用。
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);

        for(int i=0;i<N;i++) {
            new Writer(barrier).start();
        }

        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");

        for(int i=0;i<N;i++) {
            new Writer(barrier).start();
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

                cyclicBarrier.await();
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
     * 线程Thread-2正在写入数据...
     * 线程Thread-1正在写入数据...
     * 线程Thread-3正在写入数据...
     * 线程Thread-0写入数据完毕，等待其他线程写入完毕
     * 线程Thread-3写入数据完毕，等待其他线程写入完毕
     * 线程Thread-2写入数据完毕，等待其他线程写入完毕
     * 线程Thread-1写入数据完毕，等待其他线程写入完毕
     * Thread-0所有线程写入完毕，继续处理其他任务...
     * Thread-2所有线程写入完毕，继续处理其他任务...
     * Thread-3所有线程写入完毕，继续处理其他任务...
     * Thread-1所有线程写入完毕，继续处理其他任务...
     * CyclicBarrier重用
     * 线程Thread-4正在写入数据...
     * 线程Thread-5正在写入数据...
     * 线程Thread-6正在写入数据...
     * 线程Thread-7正在写入数据...
     * 线程Thread-6写入数据完毕，等待其他线程写入完毕
     * 线程Thread-7写入数据完毕，等待其他线程写入完毕
     * 线程Thread-5写入数据完毕，等待其他线程写入完毕
     * 线程Thread-4写入数据完毕，等待其他线程写入完毕
     * Thread-4所有线程写入完毕，继续处理其他任务...
     * Thread-7所有线程写入完毕，继续处理其他任务...
     * Thread-6所有线程写入完毕，继续处理其他任务...
     * Thread-5所有线程写入完毕，继续处理其他任务...
     */
}
