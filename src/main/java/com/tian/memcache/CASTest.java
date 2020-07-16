package com.tian.memcache;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author David Tian
 * @since 2019-05-17
 */
public class CASTest {

    private static MemcachedClient client = null;

    static {
        try {//11211  8080
            client = new MemcachedClient(
                    new InetSocketAddress("localhost", 18080));
        } catch (IOException o) {
            o.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //Firstly, the key should exist.
        //key is "number", value is Integer 1, 7845 is expire time
        client.set("number", 7845, 1);


        CASTest testObj = new CASTest();
        //start the multithread environment
        for (int i = 0; i < 10; i++) {
            testObj.new ThreadTest("Thread-" + (i + 1)).start();
        }
    }

    /**
     * Each thread runs many times
     */
    private class ThreadTest extends Thread {

        private  MemcachedClient client = null;
        ThreadTest(String name) throws IOException {
            super(name);
            client = new MemcachedClient(
                    new InetSocketAddress("localhost", 18080));
        }

        @Override
        public void run() {
            int i = 0;
            int success = 0;
            while (i < 10) {
                i++;
                CASValue<Object> uniqueValue =client.gets("number");
                CASResponse response = client.cas("number",
                        uniqueValue.getCas(), (Integer)uniqueValue.getValue() + 1);

                if (response.toString().equals("OK")) {
                    success++;
                }
                System.out.println(Thread.currentThread().getName() + " " +  i
                        + " time " + " update oldValue : " + uniqueValue
                        +  " , result : " + response);
            }

            if (success < 10) {
                System.out.println(Thread.currentThread().getName()
                        + " unsuccessful times : " + (10 - success ));
            }
        }
    }
}
