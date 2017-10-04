package com.google.a.b.a;

import com.google.a.r;

public final class ae extends u {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public ac a(r rVar) {
        String str = null;
        String c = u.c(rVar);
        if (!c.startsWith("urlto:") && !c.startsWith("URLTO:")) {
            return null;
        }
        int indexOf = c.indexOf(58, 6);
        if (indexOf < 0) {
            return null;
        }
        if (indexOf > 6) {
            str = c.substring(6, indexOf);
        }
        return new ac(c.substring(indexOf + 1), str);
    }
}
