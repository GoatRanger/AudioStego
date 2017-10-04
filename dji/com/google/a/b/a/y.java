package com.google.a.b.a;

import com.google.a.r;

public final class y extends u {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public h a(r rVar) {
        String str = null;
        String c = u.c(rVar);
        if (!c.startsWith("smtp:") && !c.startsWith("SMTP:")) {
            return null;
        }
        String substring = c.substring(5);
        int indexOf = substring.indexOf(58);
        if (indexOf >= 0) {
            c = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
            indexOf = c.indexOf(58);
            if (indexOf >= 0) {
                str = c.substring(indexOf + 1);
                c = c.substring(0, indexOf);
            }
        } else {
            c = null;
        }
        return new h(substring, c, str, "mailto:" + substring);
    }
}
