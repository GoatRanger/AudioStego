package com.tencent.android.tpush.service.channel.exception;

public class UnexpectedDataException extends Exception {
    public UnexpectedDataException(String str) {
        super(str);
    }

    public UnexpectedDataException(String str, Throwable th) {
        super(str, th);
    }
}
