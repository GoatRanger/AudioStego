package com.facebook;

import com.alipay.sdk.j.i;

public class j extends k {
    static final long a = 1;
    private int c;
    private String d;

    public j(String str, int i, String str2) {
        super(str);
        this.c = i;
        this.d = str2;
    }

    public int a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public final String toString() {
        return "{FacebookDialogException: " + "errorCode: " + a() + ", message: " + getMessage() + ", url: " + b() + i.d;
    }
}
