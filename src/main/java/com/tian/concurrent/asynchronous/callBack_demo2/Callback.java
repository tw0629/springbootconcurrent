package com.tian.concurrent.asynchronous.callBack_demo2;

/**
 * @author David Tian
 * @since 2019-04-07
 */
public interface Callback {

    //消息处理
    void process(int status);
}
