package com.tian.concurrent.forkjoin.demo3;

import java.util.concurrent.RecursiveTask;

/**
 * @author David Tian
 * @since 2019-05-23
 *
 * Description: ForkJoin接口
 *
 * 定义抽象类(用于拓展，此例中没有实际作用，可以不定义此类)：
 *
 */
public abstract class ForkJoinService<T> extends RecursiveTask<T> {
    @Override
    protected abstract T compute();
}