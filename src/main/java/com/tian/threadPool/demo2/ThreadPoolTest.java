package com.tian.threadPool.demo2;

import io.netty.util.concurrent.SingleThreadEventExecutor;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-22 08:50
 */
public class ThreadPoolTest {

    @Test
    public void test5() {

        int corePoolSize = 10;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize + 1, 10l, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000));
        //executor.execute();
        //executor.submit()



        //ThreadPoolExecutor--->AbstractExecutorService--->ExecutorService--->Executor


        ExecutorService newSingle = Executors.newSingleThreadExecutor();
            ThreadPoolExecutor single = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        ExecutorService newFixed = Executors.newFixedThreadPool(10);
            ThreadPoolExecutor fixed = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        ExecutorService newCached = Executors.newCachedThreadPool();
            ThreadPoolExecutor cached = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        ExecutorService newScheduled = Executors.newScheduledThreadPool(10);
            //ScheduledThreadPoolExecutor schedul = new ScheduledThreadPoolExecutor(corePoolSize);
            //ThreadPoolExecutor scheduled = new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS, new ScheduledThreadPoolExecutor.DelayedWorkQueue());


        //ForkJoinPool--->AbstractExecutorService--->ExecutorService--->Executor
        ExecutorService newWorkStealing = Executors.newWorkStealingPool();
            ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);

        ExecutorService newSingleThreadScheduled = Executors.newSingleThreadScheduledExecutor();
            //ThreadPoolExecutor singleThreadScheduled = new ThreadPoolExecutor(1, Integer.MAX_VALUE, 0, NANOSECONDS, new ScheduledThreadPoolExecutor.DelayedWorkQueue());



        //ScheduledThreadPoolExecutor|--->ThreadPoolExecutor--->AbstractExecutorService--->ExecutorService--->Executor
        //                           |--->ScheduledExecutorService--->ExecutorService--->Executor
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
            //ThreadPoolExecutor scheduledThread = new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS, new ScheduledThreadPoolExecutor.DelayedWorkQueue());



        //new FixedThreadPool();
        //new SingleThreadExecutor();

        //DelayQueue和DelayedWorkQueue
        //https://www.cnblogs.com/txmfz/p/10338334.html
        //DelayQueue延迟队列只允许放入实现了Delayed接口的实例，它是优先级队列针对计时的一种运用，所以它是基于优先级队列 + Leader-Follower的线程等待模式，只允许取出延迟结束的队列元素。获取head的线程(往往是第一个消费线程)由于head是整个队列中最先延迟结束的元素，所以线程只等待特定的剩余的延迟时间它即是leader，而其他后来的消费线程则无限期等待即follower，直到leader取走head时随机唤醒一个follower使其拿到新的head变成新的leader或者新入队了一个剩余延迟时间更短的元素导致leader失去领导权也会随机唤醒一个线程成为新的leader，如此往复等待唤醒。
        //放入DelayQueue队列的元素必须实现Delayed接口
        //DelayQueue
        //DelayedWorkQueue

        //至于DelayedWorkQueue也是一种设计为定时任务的延迟队列，它的实现和DelayQueue一样，不过是将优先级队列和DelayQueue的实现过程迁移到本身方法体中，从而可以在该过程当中灵活的加入定时任务特有的方法调用。


        //ScheduledThreadPoolExecutor的内部类DelayedWorkQueue也说一下把，它也是一种无界延迟阻塞队列，它主要用于线程池定时或周期任务的使用，关于线程池和定时任务在后面的章节介绍，本文仅限于分析DelayedWorkQueue。从DelayQueue的特性很容易想到它适合定时任务的实现，所以Java并发包中调度定时任务的线程池队列是基于这种实现的，它就是DelayedWorkQueue，为什么不直接使用DelayQueue而要重新实现一个DelayedWorkQueue呢，可能是了方便在实现过程中加入一些扩展。放入该延迟队列中的元素是特殊的


        //放入该延迟队列中的元素是特殊的，例如DelayedWorkQueue中放的元素是线程运行时代码RunnableScheduledFuture。
        //RunnableScheduledFuture接口继承了ScheduledFuture接口，ScheduledFuture接口继承了Delayed接口。



        //Future
        //FutureTask
        //LinkedTransferQueue
        //LinkedBlockingDeque

    }


    public static void main(String[] args) {
        // 获取 Java 线程管理 MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程 ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " +
                    threadInfo.getThreadName());
        }

        //Thread
        //ThreadLocal

        //Integer
        //Unsafe
        //LockSupport.park()
        //ReentrantLock
        ReentrantLock reentrantLock = new ReentrantLock(true);
        /*reentrantLock.lock();
        Condition condition = reentrantLock.newCondition();
        condition.await();
        condition.signal();*/

        //ReentrantLock reentrantLock = new ReentrantLock(true);
        reentrantLock.lock();
        ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rw.readLock();
        readLock.lock();

        CountDownLatch countDownLatch = new CountDownLatch(2);
        countDownLatch.countDown();

        //String





    }

}
