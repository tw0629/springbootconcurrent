package com.tian.threadPool.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 移植至Dubbo.
 *
 * Abort Policy. Log warn info when abort.
 */
public class AbortPolicyWithReport extends ThreadPoolExecutor.AbortPolicy {

  protected static final Logger logger = LoggerFactory.getLogger(AbortPolicyWithReport.class);
  private static volatile long lastPrintTime = 0;
  private static Semaphore guard = new Semaphore(1);
  private final String threadName;

  public AbortPolicyWithReport(String threadName) {
    this.threadName = threadName;
  }

  @Override
  public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
    String msg = String.format(
        "Thread pool is EXHAUSTED!"
            + " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d),"
            + " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)!",
        threadName, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(),
        e.getLargestPoolSize(), e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(),
        e.isTerminating());
    logger.warn(msg);
    throw new RejectedExecutionException(msg);
  }
}
