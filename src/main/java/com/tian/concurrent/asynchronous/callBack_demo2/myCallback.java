package com.tian.concurrent.asynchronous.callBack_demo2;

/**
 * @author David Tian
 * @since 2019-04-07
 */
public class myCallback implements Callback {

    //实现Callback接口
    @Override
    public void process(int status) {
        System.out.println("处理成功，返回状态为：" + status);
    }
}
