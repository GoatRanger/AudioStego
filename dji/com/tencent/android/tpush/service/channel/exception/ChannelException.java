package com.tencent.android.tpush.service.channel.exception;

public class ChannelException extends Exception {
    public int errorCode;

    public ChannelException(int i, String str) {
        super(str);
        this.errorCode = i;
    }

    public ChannelException(int i, String str, Throwable th) {
        super(str, th);
        this.errorCode = i;
    }
}
