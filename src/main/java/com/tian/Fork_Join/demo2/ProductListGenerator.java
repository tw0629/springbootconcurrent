package com.tian.Fork_Join.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 11:09
 */
public class ProductListGenerator {

    public List<Product> generate(int size){
        List<Product> ret = new ArrayList<Product>();
        for(int i=0;i<size;i++){
            Product product = new Product();
            product.setName("Product " + i);
            product.setPrice(10);
            ret.add(product);
        }
        return ret;
    }
}
