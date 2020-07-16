package com.tian.Fork_Join.demo6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 12:11
 */
public class TaskManager {
    //存放任务
    private List<ForkJoinTask<Integer>> tasks;

    public TaskManager(){
        tasks = new ArrayList<>();
    }

    public void addTask(ForkJoinTask<Integer> task){
        tasks.add(task);
    }

    public void cancelTasks(ForkJoinTask<Integer> cancelTask){
        for(ForkJoinTask<Integer> task:tasks){
            if(task!=cancelTask){
                task.cancel(true);
                ((SearchNumberTask)task).writeCancelMessage();
            }
        }
    }
}
