package com.tian.concurrent.CountDownLatch.demo1;

import java.util.concurrent.CountDownLatch;

/**
 * @author David Tian
 * @since 2019-04-24
 */
public class CacheHealthChecker extends BaseHealthChecker
{
    public CacheHealthChecker (CountDownLatch latch)
    {
        super("Cache Service", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
