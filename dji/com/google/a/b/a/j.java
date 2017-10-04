package com.google.a.b.a;

import com.google.a.r;
import java.util.regex.Pattern;

public final class j extends a {
    private static final Pattern a = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");

    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public h a(r rVar) {
        String c = u.c(rVar);
        if (!c.startsWith("MATMSG:")) {
            return null;
        }
        String[] a = a.a("TO:", c, true);
        if (a == null) {
            return null;
        }
        String str = a[0];
        if (a(str)) {
            return new h(str, a.b("SUB:", c, false), a.b("BODY:", c, false), "mailto:" + str);
        }
        return null;
    }

    static boolean a(String str) {
        return str != null && a.matcher(str).matches() && str.indexOf(64) >= 0;
    }
}
