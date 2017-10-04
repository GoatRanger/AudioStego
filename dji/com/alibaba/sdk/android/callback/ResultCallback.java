package com.alibaba.sdk.android.callback;

public interface ResultCallback<T> extends FailureCallback {
    void onSuccess(T t);
}
