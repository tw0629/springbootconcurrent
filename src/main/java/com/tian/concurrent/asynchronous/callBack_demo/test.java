package com.tian.concurrent.asynchronous.callBack_demo;

/**
 * @author David Tian
 * @since 2019-04-07
 *
 * https://blog.csdn.net/u014209205/article/details/80410039
 * 测试异步回调
 *
 * 注意:是两个功能： 异步 和 回调
 *
 * 就是 不阻塞的,不等待的  ======> 目前看来就是起个线程实现的
 *
 */
public class test {

    public static void main(String[] args) {
        Server server = new Server();

        ClientA clientA = new ClientA(server);
        clientA.process(5,6);

        ClientB clientB = new ClientB(server);
        clientB.process(50,60);


        Thread current = Thread.currentThread();
        System.out.println("=========>"+"我是主线程:"+current.getName());

        /*
        System.out.println(current.getPriority());
        System.out.println(current.getName());
        System.out.println(current.activeCount());
        System.out.println(current.getId());
        System.out.println(current.getThreadGroup());
        System.out.println(current.getStackTrace());
        System.out.println(current.hashCode());
        System.out.println(current.toString());
        */
    }
}
