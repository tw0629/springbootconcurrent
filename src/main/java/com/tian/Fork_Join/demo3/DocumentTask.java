package com.tian.Fork_Join.demo3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 11:31
 */
public class DocumentTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;
    private String document[][];
    private String word;
    private int start, end;

    public DocumentTask(String document[][], int start, int end, String word){
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if(end-start<10){
            result = processLines(document, start, end, word);
        }
        else{
            int mid = (start+end)/2;
            DocumentTask task1 = new DocumentTask(document, start, mid+1, word);
            DocumentTask task2 = new DocumentTask(document, mid+1, end, word);
            invokeAll(task1, task2);

            try {
                result = groupResults(task1.get(), task2.get());
                //result = task1.join()+task2.join();


            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        return result;
    }

    private int processLines(String[][] document, int start, int end, String word){
        List<LineTask> tasks = new ArrayList<>();
        for(int i=start;i<end;i++){
            LineTask task = new LineTask(document[i], 0, document[i].length, word);
            tasks.add(task);
        }
        //执行列表中所有任务
        invokeAll(tasks);
        int result=0;
        try {
            for(int i=0;i<tasks.size();i++){
                LineTask task = tasks.get(i);
                result += task.get();
                //result += task.join();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    private int groupResults(int num1, int num2){
        int result;
        result = num1 + num2;
        return result;
    }
}