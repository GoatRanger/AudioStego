package com.google.a.b.a;

import com.google.a.r;

public final class ak extends u {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public aj a(r rVar) {
        String c = u.c(rVar);
        if (!c.startsWith("WIFI:")) {
            return null;
        }
        String b = u.b("S:", c, ';', false);
        if (b == null || b.isEmpty()) {
            return null;
        }
        String b2 = u.b("P:", c, ';', false);
        String b3 = u.b("T:", c, ';', false);
        if (b3 == null) {
            b3 = "nopass";
        }
        return new aj(b3, b, b2, Boolean.parseBoolean(u.b("H:", c, ';', false)));
    }
}
