package com.laoge.raining.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by yuhou on 2017/9/12.
 */
@Data
public class Person implements Serializable {

    private int id;

    private String name;

    private Map<String, String> ret;

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
