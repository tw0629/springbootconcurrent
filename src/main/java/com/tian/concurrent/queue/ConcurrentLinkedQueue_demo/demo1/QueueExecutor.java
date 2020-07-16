package com.tian.concurrent.queue.ConcurrentLinkedQueue_demo.demo1;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;

/**
 * @author David Tian
 * @since 2019-04-30
 */
public interface QueueExecutor {

    /**
     *
     * @param flag flag that indicates that mesages will be executed
     * as soon as they are added to the queue. Default is true
     */
    void setDispatchOnArrive(boolean flag);

    /**
     *
     * @return flag that indicates that mesages will be executed
     * as soon as they are added to the queue
     */
    boolean isDispatchOnArrive();

    /**
     *
     * @param threads Number of threads that Executor will have
     */
    void init(int threads);

    /**
     * This method add an objet with Callable Interface implemented so it can
     * be added to a concurrent Queue that will process all mesages with a
     * multithread Executor
     * @param message Any object that implement Callable Interface so
     * it can be procesed by an Executor
     */
    void addToQueue(Callable message);

    /**
     * Only if isDispatchOnArrive flag is set to true this method
     * should be called to process mesages in Queue
     */
    void processAllMesages();

    /**
     * This method return a ConcurrentLinkedQueue<Future> with all mesages that
     * was been already procesed.
     * @return Queue with procesed Mesages
     */
    ConcurrentLinkedQueue<Future> getQueueProcesedMsg();

}
