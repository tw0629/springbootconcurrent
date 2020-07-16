package com.tian.concurrent.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/** �������� CyclicBarrier
 *
 * @author David Tian
 * @since 2019-04-24
 */
public class CyclicBarrierTest3 {

    /**
     * 3 ��һ��Ϊawaitָ��ʱ���Ч��
     *
     *
     * ����Ĵ�����main������forѭ���У����������һ���߳������ӳ٣���Ϊ��ǰ�������̶߳��ﵽbarrier֮��
     * �ȴ���ָ����ʱ�䷢�ֵ��ĸ��̻߳�û�дﵽbarrier�����׳��쳣������ִ�к��������
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);

        for(int i=0;i<N;i++) {
            if(i<N-1)
                new Writer(barrier).start();
            else {
                try {
                    //���һ���̶߳����5��
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Writer(barrier).start();
            }
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
                try {

                    //todo  ˵�����ȴ�2��
                    cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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
     * �߳�Thread-1����д������...
     * �߳�Thread-2����д������...
     * �߳�Thread-0д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-2д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-1д��������ϣ��ȴ������߳�д�����
     * �߳�Thread-3����д������...
     * java.util.concurrent.BrokenBarrierException
     * 	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:250)
     * 	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
     * 	at com.tain.concurrent.CyclicBarrier.CyclicBarrierTest3$Writer.run(CyclicBarrierTest3.java:50)
     * Thread-1�����߳�д����ϣ�����������������...
     * java.util.concurrent.BrokenBarrierException
     * 	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:250)
     * 	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
     * 	at com.tain.concurrent.CyclicBarrier.CyclicBarrierTest3$Writer.run(CyclicBarrierTest3.java:50)Thread-2�����߳�д����ϣ�����������������...
     *
     * java.util.concurrent.TimeoutException
     * 	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:257)
     * 	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
     * 	at com.tain.concurrent.CyclicBarrier.CyclicBarrierTest3$Writer.run(CyclicBarrierTest3.java:50)
     * Thread-0�����߳�д����ϣ�����������������...
     * �߳�Thread-3д��������ϣ��ȴ������߳�д�����
     * java.util.concurrent.BrokenBarrierException
     * 	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:207)
     * 	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
     * 	at com.tain.concurrent.CyclicBarrier.CyclicBarrierTest3$Writer.run(CyclicBarrierTest3.java:50)
     * Thread-3�����߳�д����ϣ�����������������...
     */
}
