package com.tian.concurrent.Semaphore;

import java.util.concurrent.Semaphore;

/** 并发工具 Semaphore
 *
 * Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。
 *
 * 1 参数permits表示许可数目，即同时可以允许多少线程进行访问
 * public Semaphore(int permits) {
 *     sync = new NonfairSync(permits);
 * }
 * 2 这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
 * public Semaphore(int permits, boolean fair) {
 *     sync = (fair)? new FairSync(permits) : new NonfairSync(permits);
 * }
 *
 * 3 获取许可
 * //获取一个许可
 * public void acquire() throws InterruptedException {  }
 * //获取permits个许可
 * public void acquire(int permits) throws InterruptedException { }
 *
 * 4 释放许可
 * //释放一个许可
 * public void release() { }
 * //释放permits个许可
 * public void release(int permits) { }
 *
 * acquire()用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可。
 * release()用来释放许可。注意，在释放许可之前，必须先获获得许可。
 * 这4个方法都会被阻塞，如果想立即得到执行结果，可以使用下面几个方法
 *
 * 5 被阻塞,立即得到执行结果
 * 尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
 * public boolean tryAcquire() { };
 * 尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
 * public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };
 * 尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
 * public boolean tryAcquire(int permits) { };
 * 尝试获取permits个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
 * public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { };
 *
 * 6
 * 另外还可以通过availablePermits()方法得到可用的许可数目。
 *
 *
 * @author David Tian
 * @since 2019-04-25
 */
public class SemaphoreTest {

    /**
     * 假若一个工厂有5台机器，但是有8个工人，一台机器同时只能被一个工人使用，只有使用完了，其他工人才能继续使用。
     * 那么我们就可以通过Semaphore来实现.
     *
     * Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = 8;            //工人数
        Semaphore semaphore = new Semaphore(5); //机器数目
        for(int i=0;i<N;i++)
            new Worker(i,semaphore).start();
    }

    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** 打印结果
     *
     * 工人0占用一个机器在生产...
     * 工人1占用一个机器在生产...
     * 工人2占用一个机器在生产...
     * 工人3占用一个机器在生产...
     * 工人4占用一个机器在生产...
     * 工人0释放出机器
     * 工人1释放出机器
     * 工人5占用一个机器在生产...
     * 工人6占用一个机器在生产...
     * 工人2释放出机器
     * 工人3释放出机器
     * 工人7占用一个机器在生产...
     * 工人4释放出机器
     * 工人5释放出机器
     * 工人6释放出机器
     * 工人7释放出机器
     */
}
