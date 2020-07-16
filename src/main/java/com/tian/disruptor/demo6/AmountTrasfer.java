package com.tian.disruptor.demo6;

import com.lmax.disruptor.EventTranslator;

/**
 * �¼�������-������ˮ��ʼ��
 *
 * @author David Tian
 * @since 2019-05-09
 *
 */
public class AmountTrasfer implements EventTranslator<TransactionEvent> {

    @Override
    public void translateTo(TransactionEvent arg0, long arg1) {
        arg0.setAmount(Math.random()*99);
        arg0.setCallNumber(17088888888L);
        arg0.setSeq(System.currentTimeMillis());
        System.out.println("���ý�����ˮ:"+arg0.getSeq());
    }
}