package com.tian.disruptor.demo6;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * ������ˮ������
 *
 * @author David Tian
 * @since 2019-05-09
 *
 */
public class InnerDBHandler implements EventHandler<TransactionEvent>, WorkHandler<TransactionEvent> {

    @Override
    public void onEvent(TransactionEvent arg0, long arg1, boolean arg2) throws Exception {
        // TODO Auto-generated method stub
        this.onEvent(arg0);
    }

    @Override
    public void onEvent(TransactionEvent arg0) throws Exception {
        arg0.setSeq(arg0.getSeq()*10000);
        System.out.println("���������ˮ��------------  "+arg0.getSeq());
    }
}