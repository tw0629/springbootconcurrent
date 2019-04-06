package com.tain.concurrent;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author David Tian
 * @since 2019-03-13
 */
public class demo {

    //https://www.cnblogs.com/zhujiabin/p/6168671.html?utm_source=itdadao&utm_medium=referral
    @Test
    public void test1() {

        //方法 一
        Long time1 = System.currentTimeMillis();
        System.out.println("============System.currentTimeMillis()==========>"+time1);

        //方法 二
        long time2 = Calendar.getInstance().getTimeInMillis();

        System.out.println("=============Calendar.getInstance().getTimeInMillis()=========>"+time2);

        //方法 三
        long time3 = new Date().getTime();
        System.out.println("============new Date().getTime()==========>"+time3);

    }
}
