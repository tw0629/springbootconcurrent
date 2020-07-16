package com.tian.disruptor.demo6;

/**
 * �¼�(Event)����ͨ�� Disruptor ���н������������͡�
 *
 * @author David Tian
 * @since 2019-05-09
 *
 */
public class TransactionEvent {
    private long seq;
    private double amount;
    private long callNumber;

    public long getCallNumber() {
        return callNumber;
    }

    @Override
    public String toString() {
        return "TransactionEvent [seq=" + seq + ", amount=" + amount + ", callNumber=" + callNumber + "]";
    }

    public void setCallNumber(long callNumber) {
        this.callNumber = callNumber;
    }
    public long getSeq() {
        return seq;
    }
    public void setSeq(long seq) {
        this.seq = seq;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}