package com.tian.concurrent.asynchronous.callBack_demo3_sync;

/**
 * @author David Tian
 * @since 2019-04-07
 */
public class XiaoMing implements CallBackInterface {

    public XiaoMing(){

        System.out.println("小明说：小芳在做啥？");
    }

    @Override
    public void execute() {

        System.out.println("小明说：收到了！！" );
    }

}
