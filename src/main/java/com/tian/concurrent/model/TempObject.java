package com.tian.concurrent.model;

/**
 * @author David Tian
 * @since 2019-04-21
 */
public class TempObject {

    private int index;

    public TempObject() {
    }

    public TempObject(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "TempObject{" +
                "index=" + index +
                '}';
    }
}
