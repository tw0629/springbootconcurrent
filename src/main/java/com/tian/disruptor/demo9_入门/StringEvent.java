package com.tian.disruptor.demo9_入门;

/**
 * @author David Tian
 * @since 2019-05-14
 */
public class StringEvent {
    private Integer id;
    private String value;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StringEvent [id=" + id + ", value=" + value + "]";
    }
}
