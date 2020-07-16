package com.tian.Fork_Join.demo3;

import java.util.concurrent.RecursiveTask;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 11:31
 */
public class LineTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;
    private String[] line;
    private int start, end;
    private String word;

    public LineTask(String[] line, int start, int end, String word){
        this.line = line;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if(end-start<100){
            result = count(line, start, end, word);
        }
        else{
            int mid = (start+end)/2;
            LineTask task1 = new LineTask(line, start, mid+1, word);
            LineTask task2 = new LineTask(line, mid+1, end, word);
            //执行两个任务
            invokeAll(task1, task2);

            try {
                result = groupResults(task1.get(), task2.get());
                //result = task1.join()+task1.join();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private int count(String[] line, int start, int end, String word){
        int cnt = 0;
        for(int i=start;i<end;i++){
            if(line[i].equals(word)) {
                cnt++;
            }
        }
        return cnt;
    }

    private int groupResults(int cnt1, int cnt2){
        return cnt1+cnt2;
    }
}
