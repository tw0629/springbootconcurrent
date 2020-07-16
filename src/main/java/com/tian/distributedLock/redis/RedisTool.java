package com.tian.distributedLock.redis;


import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author David Tian
 * @since 2019-10-23 17:12
 *
 * 一  这种实现方式主要有以下几个要点：
 *
 * 1  set 命令要用 set key value px milliseconds nx，替代 setnx + expire 需要分两次执行命令的方式，保证了原子性，
 * 2  value 要具有唯一性，可以使用UUID.randomUUID().toString()方法生成，用来标识这把锁是属于哪个请求加的，在解锁的时候就可以有依据；
 * 3  释放锁时要验证 value 值，防止误解锁；
 * 4  通过 Lua 脚本来避免 Check And Set 模型的并发问题，因为在释放锁的时候因为涉及到多个Redis操作 （利用了eval命令执行Lua脚本的原子性）；
 *
 *
 * 二  String set(String key, String value, String nxxx, String expx, long time);
 * 该方法是： 存储数据到缓存中，并制定过期时间和当Key存在时是否覆盖。
 *
 * nxxx： 只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set
 * expx： 只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒。
 * time： 过期时间，单位是expx所代表的单位。
 *
 * 判断redis是否存在方法：
 * 1: jedis.get("ts1")的是否为-2，如果是-2,则不存在键。为-1键存在。
 * 2：jedis.exists("ts1")，值为true,存在，否则不存在。
 *
 */

public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 获取分布式锁(加锁代码)
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean getDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {


        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 释放分布式锁(解锁代码)
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        //！！！lua脚本的语法  =====> 具有原子性
        //将Lua代码传到jedis.eval()方法里，并使参数KEYS[1]赋值为lockKey，ARGV[1]赋值为requestId。
        // 在执行的时候，首先会获取锁对应的value值，检查是否与requestId相等，如果相等则解锁（删除key）。
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        Object result = jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

}