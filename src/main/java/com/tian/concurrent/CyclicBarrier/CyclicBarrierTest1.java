package com.tian.concurrent.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/** �������� CyclicBarrier
 *
 * ������˼�ػ�դ����ͨ��������ʵ����һ���̵߳ȴ���ĳ��״̬֮����ȫ��ͬʱִ��,�����ػ�����Ϊ�����еȴ��̶߳����ͷ��Ժ�
 * CyclicBarrier���Ա����á��������Ұ����״̬�ͽ���barrier��������await()����֮���߳̾ʹ���barrier�ˡ�
 *
 *
 * @author David Tian
 * @since 2019-04-24
 */
public class CyclicBarrierTest1 {

    /**
     * 1 ���������ɸ��̶߳�Ҫ����д���ݲ���������ֻ�������̶߳����д���ݲ���֮��
     *   ��Щ�̲߳��ܼ�������������飬��ʱ�Ϳ�������CyclicBarrier��.
     *
     *
     *
     * ���������������Կ�����ÿ��д���߳�ִ����д���ݲ���֮�󣬾��ڵȴ������߳�д�������ϡ�
     * �������߳��߳�д��������֮�������߳̾ͼ������к����Ĳ����ˡ�
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);
        for(int i=0;i<N;i++)
            new Writer(barrier).start();
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
            System.out.println("�����߳�д����ϣ�����������������...");
        }
    }

    /**  ��ӡ�����
     *
     * �߳�Thread-0����д������...
     * �߳�Thread-1����д������...
     * �߳�Thread-2����д������...
     * �߳�Thread-3����д������...
     * �߳�Thread-1д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-2д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-0д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-3д��������ϣ��ȴ������߳�д�����
     * �����߳�д����ϣ�����������������...
     * �����߳�д����ϣ�����������������...
     * �����߳�д����ϣ�����������������...
     * �����߳�д����ϣ�����������������...
     */
}
