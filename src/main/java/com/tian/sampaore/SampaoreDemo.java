package com.tian.sampaore;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author David Tian
 * @desc
 * @since 2020-08-28 20:41
 */
public class SampaoreDemo {

    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<>());


    public static void main(String[] args) {

        AtomicInteger atoValue = new AtomicInteger();

        Thread t1= new Thread(()->{
            int x=0;
            while (x<500){
                int i = atoValue.getAndIncrement();
                set.add(i);
                System.out.println(Thread.currentThread().getName()+"   "+i);
                atoValue.getAndIncrement();
                x++;
            }
        });

        Thread t3= new Thread(()->{
            int x=0;
            while (x<500){
                int i =atoValue.getAndIncrement();
                set.add(i);
                System.out.println(Thread.currentThread().getName()+"   "+i);
                atoValue.getAndIncrement();
                x++;

            }
        });

        Thread t2= new Thread(()->{
            int x=0;
            while (x<500){
                int i = atoValue.getAndIncrement();
                set.add(i);
                System.out.println(Thread.currentThread().getName()+"   "+i);

                x++;

            }
        });

        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            System.out.println(set.size());
    }


    @Test
    public void test(){
        //Map<String, AtomicInteger> map = new ConcurrentHashMap<>();
        Map<String, AtomicInteger> map = new HashMap<>();

        for(int i=0 ; i < 50; i++){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    AtomicInteger a = sharedVisit3(map,"a1");
                    System.out.println("a1"+"======>"+a);
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    AtomicInteger a = sharedVisit3(map,"a2");
                    System.out.println("a2"+"======>"+a);
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    AtomicInteger a = sharedVisit3(map,"a3");
                    System.out.println("a3"+"======>"+a);
                }
            }).start();

        }
    }


    @Test
    public void test2(){
        Map<String, Integer> map = new ConcurrentHashMap<>();

        for(int i=0 ; i < 50; i++){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Integer a = sharedVisit4(map,"a1");
                    System.out.println("a1"+"======>"+a);
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Integer a = sharedVisit4(map,"a2");
                    System.out.println("a2"+"======>"+a);
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Integer a = sharedVisit4(map,"a3");
                    System.out.println("a3"+"======>"+a);
                }
            }).start();

        }
    }

    private AtomicInteger sharedVisit(Map<String, AtomicInteger> map,String key) {
        Semaphore s = new Semaphore(10);
        s.acquireUninterruptibly();
        if(map.get(key)==null){
            map.put(key,new AtomicInteger(1));
        }else {
            map.get(key).getAndIncrement();
            map.put( key, map.get(key));
        }

        s.release();

        return map.get(key);
    }

    private AtomicInteger sharedVisit2(Map<String, AtomicInteger> map,String key) {

        if(map.get(key)==null){
            map.put(key,new AtomicInteger(1));
        }else {
            map.get(key).getAndIncrement();
            //map.put( key, map.get(key));
        }

        return map.get(key);
    }

    private AtomicInteger sharedVisit3(Map<String, AtomicInteger> map,String key) {

        if(map.get(key)==null){
            map.put(key,new AtomicInteger(30));
        }else if(map.get(key).get()<1){
            return new AtomicInteger(0);
        }else {
            map.get(key).getAndDecrement();
            map.put( key, map.get(key));
        }

        return map.get(key);
    }

    private Integer sharedVisit4(Map<String, Integer> map,String key) {

        if(map.get(key)==null){
            map.put(key,30);
        }else if(map.get(key)<1){
            return 0;
        }else {
            map.put(key, map.get(key)-1);
        }

        return map.get(key);
    }

}
