package com.google.a.b.a;

import com.google.a.r;

public final class x extends u {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public w a(r rVar) {
        String c = u.c(rVar);
        if (!c.startsWith("smsto:") && !c.startsWith("SMSTO:") && !c.startsWith("mmsto:") && !c.startsWith("MMSTO:")) {
            return null;
        }
        String substring = c.substring(6);
        int indexOf = substring.indexOf(58);
        if (indexOf >= 0) {
            c = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
        } else {
            c = null;
        }
        return new w(substring, null, null, c);
    }
}
