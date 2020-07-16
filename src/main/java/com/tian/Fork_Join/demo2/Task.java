package com.tian.Fork_Join.demo2;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 11:09
 */
public class Task extends RecursiveAction {

    private static final long serialVersionUID = 1L;
    private List<Product> products;
    private int first;
    private int last;
    private double increasement;

    public Task(List<Product> products, int first, int last, double increasement){
        this.products = products;
        this.first = first;
        this.last = last;
        this.increasement = increasement;
    }

    @Override
    protected void compute() {
        if(last-first<10){
            System.out.println(Thread.currentThread().getName() + "==<10==" + first + "====" + last);

            updatePrices();
        }else{
            System.out.println(Thread.currentThread().getName() + "====" + first + "====" + last);

            int middle = (last+first)/2;
            System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
            Task t1 = new Task(products, first, middle+1,increasement);
            Task t2 = new Task(products, middle+1, last, increasement);
            invokeAll(t1, t2);
        }

    }

    private void updatePrices(){
        for(int i=first;i<last;i++){
            Product product = products.get(i);
            product.setPrice(product.getPrice()*(1+increasement));
        }
    }

}
