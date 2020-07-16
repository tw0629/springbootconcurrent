package com.tian.concurrent.asynchronous.callBack_demo4_async;

/**
 * @author David Tian
 * @since 2019-04-07
 *
 *
 * https://blog.csdn.net/james4563221/article/details/79194110
 *
 * 异步回调与同步回调并没有太大的区别，唯一的区别便是小芳这次没有马上回答，而是等了一会才回了消息（新开线程）,
 * 但是回调的实现还是基本一模一样的，还是小明a->小芳b（等待后）->小明a的回答
 *
 * 说明: 异步回调的异步 是 用线程实现的
 *
 *
 */
public class MainA {

    public static void main(String[] args){

        System.out.println("多线程异步回调");


        XiaoMing x = new XiaoMing();
        XiaoFang xiaofang = new XiaoFang(x);
        new Thread(xiaofang).start();

        System.out.println("---小明问完就做其他事了");

    }
}
