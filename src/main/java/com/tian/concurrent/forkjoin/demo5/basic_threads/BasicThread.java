package com.tian.concurrent.forkjoin.demo5.basic_threads;

public class BasicThread {

    public static void main(String[] args) throws InterruptedException {
        
        Thread thread = new Thread() {
            @Override public void run() {
                System.out.println(">>> I am running in a separate thread!");
            }
        };
        
        thread.start();
        thread.join();
    }    
}