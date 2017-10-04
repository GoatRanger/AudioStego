package com.alibaba.sdk.android.model;

import com.alibaba.sdk.android.ResultCode;
import dji.pilot.usercenter.protocol.d;

public class Result<T> {
    public int code;
    public T data;
    public String message;

    public Result(int i, String str, T t) {
        this.code = i;
        this.message = str;
        this.data = t;
    }

    public static <T> Result<T> result(T t) {
        return result(ResultCode.SUCCESS.code, null, t);
    }

    public static <T> Result<T> result(int i, String str, T t) {
        return new Result(i, str, t);
    }

    public static <T> Result<T> result(int i, String str) {
        return result(i, str, null);
    }

    public static <T> Result<T> result(Result result) {
        return result(result.code, result.message);
    }

    public boolean isSuccess() {
        return this.code < 1000;
    }

    public String toString() {
        return "Result [code=" + this.code + ", message=" + this.message + ", data=" + this.data + d.H;
    }
}
