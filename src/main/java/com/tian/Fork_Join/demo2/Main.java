package com.tian.Fork_Join.demo2;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 11:10
 */
public class Main {

    public static void main(String[] args) {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(100);
        Task task = new Task(products, 0, products.size(), 0.20);
        //通过无参的类构造器创建一个ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();
        //调用execute()方法执行任务
        pool.execute(task);


        //显示关于线程池演变的信息
        try {
            while(!task.isDone()){
                System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
                System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
                System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        //关闭线程池
        pool.shutdown();
        if(task.isCompletedNormally()){
            System.out.println("Main: The process has completed normally.");
        }


        //确认是否所有的价格都已经改变
        for(int i=0;i<products.size();i++){
            Product product = products.get(i);
            if(product.getPrice()!=12) {
                System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
            }
        }
        System.out.println("Main: End of the program.");
    }
}
