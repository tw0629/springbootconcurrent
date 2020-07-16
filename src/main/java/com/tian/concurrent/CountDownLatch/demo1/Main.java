package com.tian.concurrent.CountDownLatch.demo1;

/**
 * CountDownLatch是一个同步辅助工具
 *
 * https://www.jianshu.com/p/4b6fbdf5a08f
 * https://howtodoinjava.com/java/multi-threading/when-to-use-countdownlatch-java-concurrency-example-tutorial/
 *
 *
 * @author David Tian
 * @since 2019-04-24
 */
public class Main {

    /**
     * 模拟一个应用程序启动类，开始就启动N个线程，去检查N个外部服务是否正常并通知闭锁；
     * 启动类一直在闭锁上等待，一旦验证和检查了所有外部服务，就恢复启动类执行。
     *
     * 有了它，规划整个初始化工作将简单而优雅
     *
     * @param args
     */
    public static void main(String[] args)
    {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
    }

}
