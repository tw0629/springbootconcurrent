package com.tian.concurrent.asynchronous.callBack_demo3_sync;

/**
 * @author David Tian
 * @since 2019-04-07
 */
public class XiaoFang {

    private CallBackInterface callback;

    public XiaoFang(){

    }

    public XiaoFang(CallBackInterface callback){
        this.callback = callback;
    }

    public void doSome(){

        System.out.println("小芳说：我刚才去洗碗了！");

        //回调小明 I can call you back
        callback.execute();
    }

}
