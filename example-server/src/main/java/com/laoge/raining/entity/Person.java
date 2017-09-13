package com.laoge.raining.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by yuhou on 2017/9/12.
 */
public class Person implements Serializable {

    private int id;

    private String name;

    private Map<String, String> ret;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getRet() {
        return ret;
    }

    public void setRet(Map<String, String> ret) {
        this.ret = ret;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", ret=").append(ret);
        sb.append('}');
        return sb.toString();
    }
}
