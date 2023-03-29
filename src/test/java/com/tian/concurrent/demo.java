package com.tian.concurrent;

import org.junit.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.slf4j.Logger;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author David Tian
 * @since 2019-03-13
 */
public class demo {

    //https://www.cnblogs.com/zhujiabin/p/6168671.html?utm_source=itdadao&utm_medium=referral
    @Test
    public void test1() {

        //���� һ
        Long time1 = System.currentTimeMillis();
        System.out.println("============System.currentTimeMillis()==========>"+time1);

        //���� ��
        long time2 = Calendar.getInstance().getTimeInMillis();

        System.out.println("=============Calendar.getInstance().getTimeInMillis()=========>"+time2);

        //���� ��
        long time3 = new Date().getTime();
        System.out.println("============new Date().getTime()==========>"+time3);

    }


    @Test
    public void test2() {
        int trycache = trycache();
        System.out.println("==================>"+trycache);

    }

    public int trycache(){
        try{
            int b=4/1;
            return 2;
        }catch(Exception e){

            return 3;
        }finally {
            System.out.println("��������ô�����Ҷ���Ҫִ��");
        }

    }

    @Test
    public void test3(){

        //9223372036854775807
        System.out.println(Long.MIN_VALUE);
        System.out.println(Long.MAX_VALUE);

        long minValue = 0x8000000000000000l;
        long maxValue = 0x7fffffffffffffffl;

        System.out.println(minValue);
        System.out.println(maxValue);


        //index��long���ͣ���ʹ100��QPS�Ĵ����ٶȣ�Ҳ��Ҫ30����������ꡣ

        Long max = 9223372036854775807L;
        //QPS: 1000000
        long QPS_YEAR = 365*24*3600*1000000L;

        float f = (float)max/QPS_YEAR;
        //292471.2 == 30����
        System.out.println("============>"+QPS_YEAR);
        System.out.println("============>"+f);

        /**��ӡ�����
         *
         * -9223372036854775808
         * 9223372036854775807
         * -9223372036854775808
         * 9223372036854775807
         * ============>31536000000000
         * ============>292471.2
         */
    }


    @Test
    public void test4(){
        //LinkedHashMap
        HashSet set = new HashSet();
        set.add(new Object());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.keySet();
        linkedHashMap.entrySet();
        linkedHashMap.entrySet().iterator();

        int[] a = new int[10];



        System.out.println("        "+ diao());

    }

    public String diao(){

        System.out.println("        "+Runtime.getRuntime().availableProcessors());


        String workNumber = "false";

        //���ù����ŷ��񲦴�绰
        try{
            workNumber = 2/0+"";
            //workNumber = 2/1+"";
            System.out.println("���ù����ŷ��񷵻صĹ����� workNumber:{}"+workNumber);
        }catch (Exception e){
            System.out.println("�����Ų�����/�޷�����");
        }finally {
            return workNumber;
        }
    }

    @Test
    public void test5(){


    }

}
