package com.iassistant.android.http;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * Created by lan on 11/21/14.
 */
public class Result<T> {
    private int statusCode;
    private String message;
    private int count;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    private List<T> objects = Lists.newArrayList();

    public boolean add(T t) {
        boolean ret = objects.add(t);
        count = objects.size();
        return ret;
    }

    public boolean addAll(Collection<T> all) {
        boolean ret = objects.addAll(all);
        count = objects.size();
        return ret;
    }

    public static <E> Result<E> newResult() {
        return new Result();
    }
}
