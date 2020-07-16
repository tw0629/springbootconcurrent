package com.tian.concurrent.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author David Tian
 * @since 2019-04-24
 */
public class CyclicBarrierTest4 {

    /**
     * 4 CyclicBarrier�ǿ������õ�
     *
     *
     * ��ִ�н�����Կ������ڳ��ε�4���߳�Խ��barrier״̬���ֿ�������������һ�ֵ�ʹ�á���CountDownLatch�޷������ظ�ʹ�á�
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);

        for(int i=0;i<N;i++) {
            new Writer(barrier).start();
        }

        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier����");

        for(int i=0;i<N;i++) {
            new Writer(barrier).start();
        }
    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("�߳�"+Thread.currentThread().getName()+"����д������...");
            try {
                Thread.sleep(5000);      //��˯����ģ��д�����ݲ���
                System.out.println("�߳�"+Thread.currentThread().getName()+"д��������ϣ��ȴ������߳�д�����");

                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"�����߳�д����ϣ�����������������...");
        }
    }

    /**��ӡ�����
     *
     * �߳�Thread-0����д������...
     * �߳�Thread-2����д������...
     * �߳�Thread-1����д������...
     * �߳�Thread-3����д������...
     * �߳�Thread-0д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-3д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-2д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-1д��������ϣ��ȴ������߳�д�����
     * Thread-0�����߳�д����ϣ�����������������...
     * Thread-2�����߳�д����ϣ�����������������...
     * Thread-3�����߳�д����ϣ�����������������...
     * Thread-1�����߳�д����ϣ�����������������...
     * CyclicBarrier����
     * �߳�Thread-4����д������...
     * �߳�Thread-5����д������...
     * �߳�Thread-6����д������...
     * �߳�Thread-7����д������...
     * �߳�Thread-6д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-7д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-5д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-4д��������ϣ��ȴ������߳�д�����
     * Thread-4�����߳�д����ϣ�����������������...
     * Thread-7�����߳�д����ϣ�����������������...
     * Thread-6�����߳�д����ϣ�����������������...
     * Thread-5�����߳�д����ϣ�����������������...
     */
}
