package com.tian.concurrent.asynchronous.callBack_demo2;

/**
 * @author David Tian
 * @since 2019-04-07
 */
public class Client {

    Server server;

    public Client(Server server) {
        this.server = server;
    }

    public void sendMsg(final String msg) {

        System.out.println("客户端正在发生消息：" + msg);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //调用server类的获取消息方法，并且传入myCallback对象
                    server.getMsg(new myCallback(), msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("上面再等server返回信息, 我先干别的了");
    }

}
