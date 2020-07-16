package com.tian.disruptor.demo6;

import com.lmax.disruptor.EventFactory;

/**
 * Event Factory ���������ʵ����ǰ���1���ж�����¼�(Event)
 * Disruptor ͨ�� EventFactory �� RingBuffer ��Ԥ���� Event ��ʵ����
 * һ�� Event ʵ��ʵ���ϱ�����һ�������ݲۡ��������߷���ǰ���ȴ� RingBuffer ���һ�� Event ��ʵ����
 * Ȼ���� Event ʵ����������ݣ�֮���ٷ����� RingBuffer�У�֮���� Consumer ��ø� Event ʵ�������ж�ȡ���ݡ�
 *
 * @author David Tian
 * @since 2019-05-09
 *
 */
public class TransactionEventFactory implements EventFactory<TransactionEvent> {

    @Override
    public TransactionEvent newInstance() {
        // TODO Auto-generated method stub
        return new TransactionEvent();
    }
}