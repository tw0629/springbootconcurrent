package com.tian.concurrent.thread.demo1;

/**����һ���̳�Thread�ഴ���߳���**/
public class ThreadTest extends Thread{

	@Override
	public void run(){
		System.out.println("��ǰ���е��߳���:\t"+getName()); 		
	}
	
	public static void main(String[] args) {		
		 
		/**
		ThreadTest thread1=new ThreadTest();
		thread1.start();**/

		(new ThreadTest()).start();


		System.out.println("=========================>");


		int i = 0;

		//���MAC ����߳�����282926  371579    ��ȷ��
		while (true){

			if(i==1000000){
				break;
			}
			i++;
			(new ThreadTest()).start();
		}

	}

}
