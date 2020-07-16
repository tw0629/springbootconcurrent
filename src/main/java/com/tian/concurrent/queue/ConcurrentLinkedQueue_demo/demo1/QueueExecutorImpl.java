package com.tian.concurrent.queue.ConcurrentLinkedQueue_demo.demo1;

import java.util.concurrent.*;

/**
 *
 * @author David Tian
 * @since 2019-04-30
 */
public class QueueExecutorImpl implements QueueExecutor {

    private ExecutorService executor;
    private ConcurrentLinkedQueue<Callable> queue;
    private ConcurrentLinkedQueue<Future> queueFuture;
    private boolean flag;

    @Override
    public void setDispatchOnArrive(boolean flag) {
        this.flag = flag;
    }

    @Override
    public boolean isDispatchOnArrive() {
        return this.flag;
    }

    @Override
    public void addToQueue(Callable message) {
        queue.add(message);
        if (isDispatchOnArrive()) {
            processAllMesages();
        }
    }

    @Override
    public void init(int threads) {
        queue = new ConcurrentLinkedQueue<>();
        queueFuture = new ConcurrentLinkedQueue<>();
        executor = Executors.newFixedThreadPool(threads);
        //setDispatchOnArrive(true);
        setDispatchOnArrive(false);
    }

    @Override
    public void processAllMesages() {
        //检测在初始化的 是否是入队一个处理一个
        if(queue.isEmpty()){
            System.out.println("queue已经空了,来晚了");
        }

        while (!queue.isEmpty()) {
            queueFuture.add(executor.submit(queue.poll()));
        }
    }

    @Override
    public ConcurrentLinkedQueue<Future> getQueueProcesedMsg() {
        return queueFuture;
    }

}
