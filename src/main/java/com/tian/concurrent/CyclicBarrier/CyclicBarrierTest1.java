package com.tian.concurrent.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/** 并发工具 CyclicBarrier
 *
 * 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行,叫做回环是因为当所有等待线程都被释放以后，
 * CyclicBarrier可以被重用。我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
 *
 *
 * @author David Tian
 * @since 2019-04-24
 */
public class CyclicBarrierTest1 {

    /**
     * 1 假若有若干个线程都要进行写数据操作，并且只有所有线程都完成写数据操作之后，
     *   这些线程才能继续做后面的事情，此时就可以利用CyclicBarrier了.
     *
     *
     *
     * 从上面输出结果可以看出，每个写入线程执行完写数据操作之后，就在等待其他线程写入操作完毕。
     * 当所有线程线程写入操作完毕之后，所有线程就继续进行后续的操作了。
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);
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
            System.out.println("所有线程写入完毕，继续处理其他任务..."+Thread.currentThread().getName());
        }
    }

    /**  打印结果：
     *
     * 线程Thread-0正在写入数据...
     * 线程Thread-1正在写入数据...
     * 线程Thread-2正在写入数据...
     * 线程Thread-3正在写入数据...
     * 线程Thread-1写入数据完毕，等待其他线程写入完毕
     * 线程Thread-2写入数据完毕，等待其他线程写入完毕
     * 线程Thread-0写入数据完毕，等待其他线程写入完毕
     * 线程Thread-3写入数据完毕，等待其他线程写入完毕
     * 所有线程写入完毕，继续处理其他任务...
     * 所有线程写入完毕，继续处理其他任务...
     * 所有线程写入完毕，继续处理其他任务...
     * 所有线程写入完毕，继续处理其他任务...
     */
}
