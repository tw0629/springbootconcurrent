package com.tian.AQS_CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author David Tian
 * @desc https://www.dazhuanlan.com/2019/12/09/5dee01250d38c/
 * @since 2020-03-08 14:16
 */
public class ABA_Test {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    //构造方法，第一个值100是value,第二个参数1是版本号/标记值
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100, 1);


    public static void main(String[] args) {

        System.out.println("==========ABA问题============");

        new Thread(() -> {

            System.out.println("t1第一次修改状态：" + atomicReference.compareAndSet(100, 101) + "，t1修改后的值：" + atomicReference.get());
            System.out.println("t1第二次修改状态：" + atomicReference.compareAndSet(101, 100) + "，t1修改后的值：" + atomicReference.get());

        }, "t1").start();

        new Thread(() -> {

            System.out.println("t2修改状态：" + atomicReference.compareAndSet(100, 2019) + "，t2修改后的值：" + atomicReference.get());

        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("===========ABA的解决==========");

        /**
         * 这里模拟的两个线程去操作同一个变量，引出规避ABA的场景
         *
         * 首先介绍一下这个方法：boolean java.util.concurrent.atomic.AtomicStampedReference.compareAndSet(Integer expectedReference, Integer newReference, int expectedStamp, int newStamp)
         *
         * @param expectedReference the expected value of the reference 	期望值
         * @param newReference the new value for the reference				修改的新值
         * @param expectedStamp the expected value of the stamp				期望版本
         * @param newStamp the new value for the stamp						修改的新版本
         * @return {@code true} if successful
         *
         * t3线程第一次atomicStampedReference.getStamp()的版本肯定是上面的初始化时指定的 1
         * t3compareAndSet第一次，将100修改成101并且期望的版本号是1，并将newStamp的值加1，第一次正确，执行过后atomicStampedReference.getStamp()=2
         * t3compareAndSet第二次，将101修改成100并且期望的版本号是2，并将newStamp的值加1，第一次正确，执行过后atomicStampedReference.getStamp()=3
         *
         * t4的场景是，它与t3同时从主内存拿到数据值是100，数据版本是1，而此时t3在它执行之前完成了一次以上ABA场景后它想去将数据100，修改成2019，携带的版本是1时，发现主内存此时是100、版本是3，compareAndSet时不通过
         * 故修改失败
         */
        new Thread(() -> {
            System.out.println("t3修改前的标记值："+atomicStampedReference.getStamp());
            System.out.println("t3第一次修改状态：" + atomicStampedReference.compareAndSet(100, 101, 1, atomicStampedReference.getStamp()+1) + "，t3修改后的值：" + atomicStampedReference.getReference());
            System.out.println("t3第一次修改后的标记值："+atomicStampedReference.getStamp());
            System.out.println("t3第二次修改状态：" + atomicStampedReference.compareAndSet(101, 100, 2, atomicStampedReference.getStamp()+1) + "，t3修改后的值：" + atomicStampedReference.getReference());
            System.out.println("t3第二次修改后的标记值："+atomicStampedReference.getStamp());

        }, "t3").start();

        new Thread(() -> {
            System.out.println("t4修改前的标记值："+atomicStampedReference.getStamp());
            System.out.println("t4修改状态：" + atomicStampedReference.compareAndSet(100, 2019, 1, atomicStampedReference.getStamp()+1) + "，t3修改后的值：" + atomicStampedReference.getReference());
            System.out.println("t4的标记值："+atomicStampedReference.getStamp());

        }, "t4").start();

    }

}
