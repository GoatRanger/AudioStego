package com.facebook;

import com.alipay.sdk.j.i;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class l extends k {
    private final v a;

    public l(v vVar, String str) {
        super(str);
        this.a = vVar;
    }

    public final v a() {
        return this.a;
    }

    public final String toString() {
        n a = this.a != null ? this.a.a() : null;
        StringBuilder append = new StringBuilder().append("{FacebookGraphResponseException: ");
        String message = getMessage();
        if (message != null) {
            append.append(message);
            append.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        if (a != null) {
            append.append("httpResponseCode: ").append(a.b()).append(", facebookErrorCode: ").append(a.c()).append(", facebookErrorType: ").append(a.e()).append(", message: ").append(a.f()).append(i.d);
        }
        return append.toString();
    }
}
