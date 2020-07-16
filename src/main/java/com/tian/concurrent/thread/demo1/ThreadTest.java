package com.tian.concurrent.thread.demo1;

/**方法一：继承Thread类创建线程类**/
public class ThreadTest extends Thread{

	@Override
	public void run(){
		System.out.println("当前运行的线程名:\t"+getName()); 		
	}
	
	public static void main(String[] args) {		
		 
		/**
		ThreadTest thread1=new ThreadTest();
		thread1.start();**/

		(new ThreadTest()).start();


		System.out.println("=========================>");


		int i = 0;

		//测出MAC 最大线程数是282926  371579    不确定
		while (true){

			if(i==1000000){
				break;
			}
			i++;
			(new ThreadTest()).start();
		}

	}

}
