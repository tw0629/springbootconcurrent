package com.tian.concurrent.asynchronous.callBack_demo;

/**
 * @author David Tian
 * @since 2019-04-07
 *
 * https://blog.csdn.net/u014209205/article/details/80410039
 */
public interface Client {

    // 调用服务提供者的处理方法
    void process(int a,int b);

    //  异步回调方法
    void result(int i);
}
