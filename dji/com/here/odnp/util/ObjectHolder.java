package com.here.odnp.util;

public class ObjectHolder<T> {
    private volatile T mObject;

    public ObjectHolder(T t) {
        this.mObject = t;
    }

    public T get() {
        return this.mObject;
    }

    public void set(T t) {
        this.mObject = t;
    }
}
