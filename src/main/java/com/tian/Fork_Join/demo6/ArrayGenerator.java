package com.tian.Fork_Join.demo6;

import java.util.Random;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 12:10
 */
public class ArrayGenerator {

    public int[] generator(int size){
        int array[] = new int[size];
        Random random = new Random();
        for(int i=0;i<size;i++){
            array[i] = random.nextInt(10);
        }
        return array;
    }
}
