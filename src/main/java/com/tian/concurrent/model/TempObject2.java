package com.tian.concurrent.model;

/**
 * @author David Tian
 * @since 2019-04-21
 */
public class TempObject2 implements Comparable<TempObject2>{

    private int index;

    public TempObject2(int index) {
        this.index = index;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(TempObject2 o) {
        return o.getIndex() - this.index;
    }

    @Override
    public String toString() {
        return "TempObject2{" +
                "index=" + index +
                '}';
    }
}
