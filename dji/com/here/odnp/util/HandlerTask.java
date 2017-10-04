package com.here.odnp.util;

public abstract class HandlerTask<T> implements Runnable {
    private static final String TAG = "odnp.util.HandlerTask";
    private T mResult;

    public abstract T onRun();

    public void run() {
        try {
            setResult(onRun());
        } catch (Exception e) {
            onException(e);
        }
    }

    public T getResult() {
        return this.mResult;
    }

    protected void onException(Exception exception) {
    }

    protected void setResult(T t) {
        this.mResult = t;
    }
}
