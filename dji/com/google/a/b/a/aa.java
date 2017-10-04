package com.google.a.b.a;

import com.google.a.r;

public final class aa extends u {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public z a(r rVar) {
        String c = u.c(rVar);
        if (!c.startsWith("tel:") && !c.startsWith("TEL:")) {
            return null;
        }
        String str;
        if (c.startsWith("TEL:")) {
            str = "tel:" + c.substring(4);
        } else {
            str = c;
        }
        int indexOf = c.indexOf(63, 4);
        return new z(indexOf < 0 ? c.substring(4) : c.substring(4, indexOf), str, null);
    }
}
