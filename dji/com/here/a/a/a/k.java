package com.here.a.a.a;

import com.here.a.a.a.a.ad;
import com.here.a.a.a.a.x;
import com.here.a.a.a.a.x.c;

public class k extends RuntimeException {
    public final ad<x> a = ad.a();

    public k(String str) {
        super(str);
    }

    public String getMessage() {
        return this.a.c() ? a((x) this.a.b()) : super.getMessage();
    }

    private static String a(x xVar) {
        String str = "Code: %s, Subcode: %s, Severity: %s, Message: %s";
        Object[] objArr = new Object[4];
        objArr[0] = xVar.a.toString();
        objArr[1] = xVar.c.c() ? ((c) xVar.c.b()).toString() : "none";
        objArr[2] = xVar.b.toString();
        objArr[3] = xVar.d.c("none");
        return String.format(str, objArr);
    }
}
