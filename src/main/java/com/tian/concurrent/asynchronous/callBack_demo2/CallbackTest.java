package com.tian.concurrent.asynchronous.callBack_demo2;

/**
 * @author David Tian
 * @since 2019-04-07
 *
 * https://blog.csdn.net/hjiacheng/article/details/72796907
 *
 * 回调的原理可以简单理解为：A发送消息给B，B处理完后告诉A处理结果。再简单点就是A调用B，B调用A。
 *
 * 那么是如何实现的呢？一般而言，处理消息的类是唯一的，而发送消息的类却是各种各样的，
 * 所以一般的做法是将回调方法做成一个接口，不同的发送者去实现该接口，并且把自己的接口实现类的对象在发送消息时传递给消息处理者。
 *
 */
public class CallbackTest {

    public static void main(String[] args) {

        Server server = new Server();
        Client client = new Client(server);
        client.sendMsg("hello");


        Thread current = Thread.currentThread();
        System.out.println("=========>"+"我是主线程:"+current.getName());
    }
}
