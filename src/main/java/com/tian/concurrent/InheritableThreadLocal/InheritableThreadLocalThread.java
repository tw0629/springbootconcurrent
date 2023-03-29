package com.tain.concurrent.InheritableThreadLocal;

/**
 * @author David Tian
 * @since 2019-06-28
 *
 *
 * ??????:
 *      ThreadLocal : ????????§Ö????????
 *      InheritableThreadLocal : ???????§Ö????????
 *
 *      ????????:
 *
 */
public class InheritableThreadLocalThread implements Runnable{

    public static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static final InheritableThreadLocal<Long> inheritableThreadLocal = new InheritableThreadLocal<>();


    @Override
    public void run() {
        System.out.println("=====????????====");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("============>????? threadLocal"+threadLocal.get());
        System.out.println("============>????? inheritableThreadLocal"+inheritableThreadLocal.get());

        System.out.println("=====????????====");

    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=====????????====");

        //new Thread(new InheritableThreadLocalThread()).start();

        threadLocal.set(11111L);
        inheritableThreadLocal.set(66666L);

        new Thread(new InheritableThreadLocalThread()).start();

        threadLocal.set(11112L);
        inheritableThreadLocal.set(66667L);


        System.out.println("============>????? threadLocal"+threadLocal.get());
        System.out.println("============>????? inheritableThreadLocal"+inheritableThreadLocal.get());
        System.out.println("=====????????====");
    }

    /**
     * =====????????====
     * ============>????? threadLocal11112
     * ============>????? inheritableThreadLocal66667
     * =====????????====
     * =====????????====
     * ============>????? threadLocalnull
     * ============>????? inheritableThreadLocal66666
     * =====????????====
     */
}
