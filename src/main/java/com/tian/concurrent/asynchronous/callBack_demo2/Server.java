package com.tian.concurrent.asynchronous.callBack_demo2;

/**
 * @author David Tian
 * @since 2019-04-07
 */
public class Server {

    public void getMsg(Callback callback, String msg) throws InterruptedException {

        System.out.println("服务端获得消息：" + msg);

        //模拟处理消息过程，等待两秒
        Thread.sleep(2000);
        System.out.println("服务端处理成功，返回状态为200");

        //处理完消息，调用回调方法，告知客户端
        callback.process(200);
    }

}
