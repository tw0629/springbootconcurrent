package com.tian.disruptor.demo6;

/**
 * 事件(Event)就是通过 Disruptor 进行交换的数据类型。
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