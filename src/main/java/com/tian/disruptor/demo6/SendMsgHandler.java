package com.tian.disruptor.demo6;

import com.lmax.disruptor.EventHandler;

/**
 * ������֤����
 *
 * @author David Tian
 * @since 2019-05-09
 *
 */
public class SendMsgHandler implements EventHandler<TransactionEvent> {

    @Override
    public void onEvent(TransactionEvent arg0, long arg1, boolean arg2) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("���ֻ���:"+arg0.getCallNumber()+"������֤����......");

    }
}