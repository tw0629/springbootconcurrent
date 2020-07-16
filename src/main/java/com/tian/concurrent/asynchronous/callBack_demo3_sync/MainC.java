package com.tian.concurrent.asynchronous.callBack_demo3_sync;

/**
 * @author David Tian
 * @since 2019-04-07
 *
 *
 * https://blog.csdn.net/james4563221/article/details/79194110
 *
 * 小明a->小芳b->小明a的回答
 *
 */
public class MainC {

    public static void main(String[] args){

        System.out.println("单线程同步回调");

        XiaoMing xiaoming = new XiaoMing();
        XiaoFang x2 = new XiaoFang(xiaoming);
        x2.doSome();

        System.out.println("---小明问完，等待小芳的回复，才继续做其他事了");

    }
}
