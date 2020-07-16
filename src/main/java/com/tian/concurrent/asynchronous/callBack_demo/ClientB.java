package com.tian.concurrent.asynchronous.callBack_demo;

/**
 * @author David Tian
 * @since 2019-04-07
 */
public class ClientB implements Client {

    // 初始化一个私有服务提供者
    private Server server;

    public ClientB(Server server){
        this.server = server;
    }

    @Override
    public void process(int a,int b) {
        ClientB c = this;
        // 这里启用另一个线程，让此线程继续执行
        new Thread(new Runnable() {

            @Override
            public void run() {
                server.add(a,b,c);

            }
        }).start();;
        System.out.println("ClientB process end");
    }

    //异步返回结果
    @Override
    public void result(int i) {
        System.out.println("ClientB result is :"+i);

    }

}
