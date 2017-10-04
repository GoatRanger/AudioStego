package com.google.a.b.a;

import com.google.a.a;
import com.google.a.r;

public final class p extends u {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public o a(r rVar) {
        if (rVar.d() != a.EAN_13) {
            return null;
        }
        String c = u.c(rVar);
        if (c.length() != 13) {
            return null;
        }
        if (c.startsWith("978") || c.startsWith("979")) {
            return new o(c);
        }
        return null;
    }
}
