package com.tian.concurrent.threadlocal;

import java.util.Random;

public class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws InterruptedException {
        final Person p=new Person();
        //这样做其实就是在操作同一个对象，如果需要实现多线程应该像下下面的注释一样，这样就针对于每一个线程创建一个独立的Person对象
        final ThreadLocal<Person> t=new ThreadLocal<Person>(){
            public Person initialValue() {
                //return new Person();
                return p;
            }
        };

        p.setName("小明");
        for(int i=0;i<3;i++)
        {
            new Thread(new Runnable() {         //ThreadLocal.set() ThreadLocal.get() ||  ThreadLocal.get().setName() ThreadLocal.get().getName()
                @Override
                public void run() {
                    t.set(p);
                    t.get().setName(new Random().nextInt(100)+"");
                    System.out.println(t.get().getName()+"=="+t.get());
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(p.getName());
    }
}
