package com.tian.distributedLock.redis;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author David Tian
 * @desc
 * @since 2019-10-30 00:28
 *
 *
 * 普通分布式实现非常简单，无论是那种架构，向Redis通过EVAL命令执行LUA脚本即可。
 *
 *
 * Redis发展到现在，几种常见的部署架构有：
 * 1 单机模式；
 * 2 主从模式；
 * 3 哨兵模式；
 * 4 集群模式；
 */
public class redisFramework {

    /**
     * 单机模式
     */
    @Test
    public void test() {

        // 构造redisson实现分布式锁必要的Config
        Config config = new Config();
        config.useSingleServer().setAddress("redis://172.29.1.180:5379").setPassword("a123456").setDatabase(0);
        // 构造RedissonClient
        RedissonClient redissonClient = Redisson.create(config);
        // 设置锁定资源名称
        RLock disLock = redissonClient.getLock("DISLOCK");
        boolean isLock;
        try {
            //尝试获取分布式锁
            isLock = disLock.tryLock(500, 15000, TimeUnit.MILLISECONDS);
            if (isLock) {
                //TODO if get lock success, do something;
                Thread.sleep(15000);
            }
        } catch (Exception e) {
        } finally {
            // 无论如何, 最后都要解锁
            disLock.unlock();
        }

    }

    /**
     * 哨兵模式
     *
     * 即sentinel模式，实现代码和单机模式几乎一样，唯一的不同就是Config的构造：
     */
    @Test
    public void test2() {

        Config config = new Config();
        config.useSentinelServers().addSentinelAddress(
                "redis://172.29.3.245:26378","redis://172.29.3.245:26379", "redis://172.29.3.245:26380")
                .setMasterName("mymaster")
                .setPassword("a123456").setDatabase(0);
    }

    /**
     * 集群模式
     */
    @Test
    public void test3() {

        Config config = new Config();
        config.useClusterServers().addNodeAddress(
                "redis://172.29.3.245:6375","redis://172.29.3.245:6376", "redis://172.29.3.245:6377",
                "redis://172.29.3.245:6378","redis://172.29.3.245:6379", "redis://172.29.3.245:6380")
                .setPassword("a123456").setScanInterval(5000);
    }


    /**
     *  Redlock分布式锁
     *
     *  那么Redlock分布式锁如何实现呢？以单机模式Redis架构为例，直接看实现代码：
     */
    @Test
    public void test4(){
        Config config1 = new Config();
        config1.useSingleServer().setAddress("redis://172.29.1.180:5378")
                .setPassword("a123456").setDatabase(0);
        RedissonClient redissonClient1 = Redisson.create(config1);

        Config config2 = new Config();
        config2.useSingleServer().setAddress("redis://172.29.1.180:5379")
                .setPassword("a123456").setDatabase(0);
        RedissonClient redissonClient2 = Redisson.create(config2);

        Config config3 = new Config();
        config3.useSingleServer().setAddress("redis://172.29.1.180:5380")
                .setPassword("a123456").setDatabase(0);
        RedissonClient redissonClient3 = Redisson.create(config3);

        String resourceName = "REDLOCK";
        RLock lock1 = redissonClient1.getLock(resourceName);
        RLock lock2 = redissonClient2.getLock(resourceName);
        RLock lock3 = redissonClient3.getLock(resourceName);

        RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);
        boolean isLock;
        try {
            isLock = redLock.tryLock(500, 30000, TimeUnit.MILLISECONDS);
            System.out.println("isLock = "+isLock);
            if (isLock) {
                //TODO if get lock success, do something;
                Thread.sleep(30000);
            }
        } catch (Exception e) {
        } finally {
            // 无论如何, 最后都要解锁
            System.out.println("");
            redLock.unlock();
        }
    }

}
