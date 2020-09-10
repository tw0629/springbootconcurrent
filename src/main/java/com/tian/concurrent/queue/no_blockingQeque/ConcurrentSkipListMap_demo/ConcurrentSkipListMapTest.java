package com.tian.concurrent.queue.no_blockingQeque.ConcurrentSkipListMap_demo;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author David Tian
 * @desc
 * @since 2020-07-16 21:24
 */
public class ConcurrentSkipListMapTest {

    public static void main(String[] args) {

        Map<String, String> map = new ConcurrentSkipListMap<>(Collections.reverseOrder());
        map.put("a", "abstract");
        map.put("c", "call");
        map.put("b", "basic");
        System.out.println(map.toString());

//        ConcurrentSkipListMap
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

    }
}
