package com.tian.Fork_Join.demo5;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 12:09
 */
public class Task extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;
    private int array[];
    private int start, end;

    public Task(int array[], int start, int end){
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        System.out.printf("Task: Start from %d to %d\n", start, end);
        if(end-start<10){
            if(start<3&&end>3){
                throw new RuntimeException("This task throws an Exception: Task from "+start+" to "+end);
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            int mid = (start+end)/2;
            Task task1 = new Task(array, start, mid);
            Task task2 = new Task(array, mid, end);
            //执行两个任务并等待完成
            invokeAll(task1, task2);
        }
        System.out.printf("Task: End from %d to %d\n", start, end);
        return 0;
    }

}
