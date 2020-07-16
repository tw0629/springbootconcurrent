package com.tian.concurrent.threadlocal;

public class ThreadLocalTest4 {

    private static ThreadLocal<Integer> firstNum = new ThreadLocal<Integer>();
    private static ThreadLocal<Integer> secondNum = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 21;
        }
    };
    private static ThreadLocal<Integer> thirdNum = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 31;
        }
    };

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "---"+"getNum : " + firstNum.get());
        firstNum.set(11);
        System.out.println(Thread.currentThread().getName() + "---"+"getNum : " + firstNum.get());
        System.out.println(Thread.currentThread().getName() + "---"+"getNum : " + secondNum.get());
        System.out.println(Thread.currentThread().getName() + "---"+"getNum : " + thirdNum.get());
    }

}
