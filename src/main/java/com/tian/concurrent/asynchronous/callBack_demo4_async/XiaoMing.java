package com.tian.concurrent.asynchronous.callBack_demo4_async;

/**
 * @author David Tian
 * @since 2019-04-07
 */
public class XiaoMing implements CallBack{

    public XiaoMing(){
        System.out.println("小明说：小芳在做啥？");
    }

    @Override
    public void result() {
        System.out.println("小明说：收到！");
    }
}
