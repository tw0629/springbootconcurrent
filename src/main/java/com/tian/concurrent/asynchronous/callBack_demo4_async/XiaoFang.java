package com.tian.concurrent.asynchronous.callBack_demo4_async;

/**
 * @author David Tian
 * @since 2019-04-07
 *
 *
 *  说明: 异步回调的异步 是 用线程实现的
 */
public class XiaoFang implements Runnable{

    public CallBack callback;

    public XiaoFang (CallBack callback){
        this.callback = callback;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---过了两秒小芳才回答");
        System.out.println("小芳说：我去喝奶茶了");

        //回调小明
        callback.result();
    }

}
