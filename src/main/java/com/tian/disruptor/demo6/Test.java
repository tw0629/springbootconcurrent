package com.tian.disruptor.demo6;

/**
 *
 * https://www.cnblogs.com/0813lichenyu/p/9244410.html
 *
 * 测试类
 *
 * @author David Tian
 * @since 2019-05-09
 *
 */
public class Test {

    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();

        TransactionEventProducer producer = new TransactionEventProducer();
        for (int i = 0; i < 100; i++){
            producer.disruptorManage();
        }

        System.out.println("======总耗时======>"+(System.currentTimeMillis()-startTime)+"ms");
        System.out.println("--------------------------------------------------");
    }
}
