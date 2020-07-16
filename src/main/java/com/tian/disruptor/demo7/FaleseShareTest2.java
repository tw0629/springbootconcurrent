package com.tian.disruptor.demo7;

/** 伪共享一填充缓存行
 *
 * https://blog.csdn.net/u012233832/article/details/79619300
 *
 * @author David Tian
 * @since 2019-05-13
 */
public final class FaleseShareTest2 implements Runnable{

        // change
        public final static int NUM_THREADS = 4;
        public final static long ITERATIONS = 500L * 1000L * 1000L;
        private final int arrayIndex;

        private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
        static
        {
            for (int i = 0; i < longs.length; i++)
            {
                longs[i] = new VolatileLong();
            }
        }

        public FaleseShareTest2(final int arrayIndex)
        {
            this.arrayIndex = arrayIndex;
        }

        public static void main(final String[] args) throws Exception
        {
            final long start = System.nanoTime();
            runTest();
            System.out.println("duration = " + (System.nanoTime() - start));
        }

        private static void runTest() throws InterruptedException
        {
            Thread[] threads = new Thread[NUM_THREADS];

            for (int i = 0; i < threads.length; i++)
            {
                threads[i] = new Thread(new FaleseShareTest2(i));
            }

            for (Thread t : threads)
            {
                t.start();
            }

            for (Thread t : threads)
            {
                t.join();
            }
        }

        @Override
        public void run()
        {
            long i = ITERATIONS + 1;
            while (0 != --i)
            {
                //longs[arrayIndex].set(i);
                //改为以下
                longs[arrayIndex].value = i;
            }
        }

        public static long sumPaddingToPreventOptimisation(final int index)
        {
            VolatileLong v = longs[index];
            return v.p1 + v.p2 + v.p3 + v.p4 + v.p5 + v.p6;
        }
        

        //方案1 jdk7以上使用此方法(jdk7的某个版本oracle对伪共享做了优化)
        public final static class VolatileLong
        {
            public volatile long value = 0L;
            public long p1, p2, p3, p4, p5, p6;

        }

        //方案2 jdk7以下使用此方法
        /*public final static class VolatileLong
        {
            public long p1, p2, p3, p4, p5, p6, p7; // cache line padding
            public volatile long value = 0L;
            public long p8, p9, p10, p11, p12, p13, p14; // cache line padding

        }*/

        /*
        //方案3 Java8中的解决方案 需要注意的是此注解默认是无效的,需要在jvm启动时设置-XX:-RestrictContended才会生效
        @sun.misc.Contended
        public final static class VolatileLong {
            public volatile long value = 0L;
            //public long p1, p2, p3, p4, p5, p6;
        }*/
}
