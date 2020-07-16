package com.tian.concurrent.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/** �������� CyclicBarrier
 *
 * @author David Tian
 * @since 2019-04-24
 */
public class CyclicBarrierTest2 {

    /**
     * 2 ���˵���������߳�д�������֮�󣬽��ж����������������ΪCyclicBarrier�ṩRunnable����
     *
     *
     * �ӽ�����Կ��������ĸ��̶߳�����barrier״̬�󣬻���ĸ��߳���ѡ��һ���߳�ȥִ��Runnable��
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N,new Runnable() {
            //����Ϊʲôֻ��һ���߳�ִ��
            @Override
            public void run() {
                System.out.println("��ǰ�߳�"+Thread.currentThread().getName());
            }
        });

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

    /**��ӡ�����
     *
     * �߳�Thread-0����д������...
     * �߳�Thread-1����д������...
     * �߳�Thread-2����д������...
     * �߳�Thread-3����д������...
     * �߳�Thread-1д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-3д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-2д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-0д��������ϣ��ȴ������߳�д�����
     * ��ǰ�߳�Thread-0
     * �����߳�д����ϣ�����������������...
     * �����߳�д����ϣ�����������������...
     * �����߳�д����ϣ�����������������...
     * �����߳�д����ϣ�����������������...
     */
}
