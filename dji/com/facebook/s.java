package com.facebook;

import com.alipay.sdk.j.i;

public class s extends k {
    private static final long c = 1;
    private final n a;

    public s(n nVar, String str) {
        super(str);
        this.a = nVar;
    }

    public final n a() {
        return this.a;
    }

    public final String toString() {
        return "{FacebookServiceException: " + "httpResponseCode: " + this.a.b() + ", facebookErrorCode: " + this.a.c() + ", facebookErrorType: " + this.a.e() + ", message: " + this.a.f() + i.d;
    }
}
