package com.tian.concurrent.Semaphore;

import java.util.concurrent.Semaphore;

/** �������� Semaphore
 *
 * Semaphore��ʵ�����е����ƣ���һ�����ڿ��ƶ�ĳ����Դ�ķ���Ȩ�ޡ�
 *
 * 1 ����permits��ʾ�����Ŀ����ͬʱ������������߳̽��з���
 * public Semaphore(int permits) {
 *     sync = new NonfairSync(permits);
 * }
 * 2 �������һ������fair��ʾ�Ƿ��ǹ�ƽ�ģ����ȴ�ʱ��Խ�õ�Խ�Ȼ�ȡ���
 * public Semaphore(int permits, boolean fair) {
 *     sync = (fair)? new FairSync(permits) : new NonfairSync(permits);
 * }
 *
 * 3 ��ȡ���
 * //��ȡһ�����
 * public void acquire() throws InterruptedException {  }
 * //��ȡpermits�����
 * public void acquire(int permits) throws InterruptedException { }
 *
 * 4 �ͷ����
 * //�ͷ�һ�����
 * public void release() { }
 * //�ͷ�permits�����
 * public void release(int permits) { }
 *
 * acquire()������ȡһ����ɣ���������ܹ���ã����һֱ�ȴ���ֱ�������ɡ�
 * release()�����ͷ���ɡ�ע�⣬���ͷ����֮ǰ�������Ȼ�����ɡ�
 * ��4���������ᱻ����������������õ�ִ�н��������ʹ�����漸������
 *
 * 5 ������,�����õ�ִ�н��
 * ���Ի�ȡһ����ɣ�����ȡ�ɹ�������������true������ȡʧ�ܣ�����������false
 * public boolean tryAcquire() { };
 * ���Ի�ȡһ����ɣ�����ָ����ʱ���ڻ�ȡ�ɹ�������������true����������������false
 * public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };
 * ���Ի�ȡpermits����ɣ�����ȡ�ɹ�������������true������ȡʧ�ܣ�����������false
 * public boolean tryAcquire(int permits) { };
 * ���Ի�ȡpermits����ɣ�����ָ����ʱ���ڻ�ȡ�ɹ�������������true����������������false
 * public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { };
 *
 * 6
 * ���⻹����ͨ��availablePermits()�����õ����õ������Ŀ��
 *
 *
 * @author David Tian
 * @since 2019-04-25
 */
public class SemaphoreTest {

    /**
     * ����һ��������5̨������������8�����ˣ�һ̨����ͬʱֻ�ܱ�һ������ʹ�ã�ֻ��ʹ�����ˣ��������˲��ܼ���ʹ�á�
     * ��ô���ǾͿ���ͨ��Semaphore��ʵ��.
     *
     * Semaphore��ʵ�����е����ƣ���һ�����ڿ��ƶ�ĳ����Դ�ķ���Ȩ�ޡ�
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = 8;            //������
        Semaphore semaphore = new Semaphore(5); //������Ŀ
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
                System.out.println("����"+this.num+"ռ��һ������������...");
                Thread.sleep(2000);
                System.out.println("����"+this.num+"�ͷų�����");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** ��ӡ���
     *
     * ����0ռ��һ������������...
     * ����1ռ��һ������������...
     * ����2ռ��һ������������...
     * ����3ռ��һ������������...
     * ����4ռ��һ������������...
     * ����0�ͷų�����
     * ����1�ͷų�����
     * ����5ռ��һ������������...
     * ����6ռ��һ������������...
     * ����2�ͷų�����
     * ����3�ͷų�����
     * ����7ռ��һ������������...
     * ����4�ͷų�����
     * ����5�ͷų�����
     * ����6�ͷų�����
     * ����7�ͷų�����
     */
}
