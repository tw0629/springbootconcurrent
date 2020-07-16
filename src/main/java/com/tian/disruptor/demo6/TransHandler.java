package com.tian.disruptor.demo6;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * �����ߨC�����¼�����ľ���ʵ��
 * ���ؽ�����ˮ
 *
 * @author David Tian
 * @since 2019-05-09
 *
 */
public class TransHandler implements EventHandler<TransactionEvent>, WorkHandler<TransactionEvent> {

    @Override
    public void onEvent(TransactionEvent transactionEvent) throws Exception {
        System.out.println("������ˮ��Ϊ��"+transactionEvent.getSeq()+"||���׽��Ϊ:"+transactionEvent.getAmount());
    }

    @Override
    public void onEvent(TransactionEvent arg0, long arg1, boolean arg2) throws Exception {
        // TODO Auto-generated method stub
        this.onEvent(arg0);
    }
}