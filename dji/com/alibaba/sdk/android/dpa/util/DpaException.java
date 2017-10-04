package com.alibaba.sdk.android.dpa.util;

public class DpaException extends Exception {
    private static final long serialVersionUID = 1;

    public DpaException(String str) {
        super(str);
    }

    public DpaException(String str, Throwable th) {
        super(str, th);
    }
}
