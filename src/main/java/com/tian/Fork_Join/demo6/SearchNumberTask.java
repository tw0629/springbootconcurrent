package com.tian.Fork_Join.demo6;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 12:12
 */
public class SearchNumberTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;
    private int array[]; //要查找的数组
    private int start, end;
    private int number; //要查找的数字
    private TaskManager manager; //用来取消所有任务
    //声明一个int常量，并初始化为-1，当任务找不到数字时将返回这个常量
    private final static int  NOT_FOUND = -1;

    public SearchNumberTask(int array[], int start, int end, int number, TaskManager manager){
        this.array = array;
        this.start = start;
        this.end = end;
        this.number = number;
        this.manager = manager;
    }

    @Override
    protected Integer compute() {
        System.out.println("Task: "+start+":"+end);
        int ret;
        if(end-start>10){
            ret = launchTasks();
        }else{
            ret = lookForNumber();
        }
        return ret;
    }

    private int launchTasks(){
        int mid = (start+end)/2;
        SearchNumberTask task1 = new SearchNumberTask(array, start, mid, number, manager);
        SearchNumberTask task2 = new SearchNumberTask(array, mid, end, number, manager);
        manager.addTask(task1);
        manager.addTask(task2);
        //采用异步方式执行两个任务
        task1.fork();
        task2.fork();
        int ret;
        ret = task1.join();
        if(ret!=-1){
            return ret;
        }
        ret = task2.join();
        return ret;
    }

    private int lookForNumber(){
        try {
            for(int i=start;i<end;i++){
                if(array[i]==number){
                    System.out.printf("SearchNumberTask: Number %d found in position %d\n", number, i);
                    manager.cancelTasks(this);
                    return i;
                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return NOT_FOUND;
    }

    public void writeCancelMessage(){
        System.out.printf("Task: Cancelled task from %d to %d\n", start, end);
    }
}
