package com.tian.concurrent.thread.demo1;

/**??????????Thread?????????**/
public class ThreadTest extends Thread{

    private boolean flag = false;
    //private volatile boolean flag = false;

	@Override
	public void run(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("ThreadTest run:"+getName());
	}
	
	public static void main(String[] args) {

		ThreadTest thread1=new ThreadTest();
		thread1.start();

		//(new ThreadTest()).start();
		//System.out.println("=========================>");
		int i = 0;

		//???MAC ??????????282926  371579    ?????
        long startTime = System.currentTimeMillis();
        while (true){

			/*if(i==10){
				break;
			}*/

            if(thread1.flag){
                break;
            }
			i++;
			//(new ThreadTest()).start();
            System.out.println("ThreadTest main:"+i+" "+(System.currentTimeMillis()-startTime));
        }

	}

}
