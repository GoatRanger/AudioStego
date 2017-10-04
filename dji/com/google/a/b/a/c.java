package com.google.a.b.a;

import com.google.a.r;

public final class c extends a {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public d a(r rVar) {
        String c = u.c(rVar);
        if (!c.startsWith("MECARD:")) {
            return null;
        }
        String[] a = a.a("N:", c, true);
        if (a == null) {
            return null;
        }
        String a2 = a(a[0]);
        String b = a.b("SOUND:", c, true);
        String[] a3 = a.a("TEL:", c, true);
        String[] a4 = a.a("EMAIL:", c, true);
        String b2 = a.b("NOTE:", c, false);
        String[] a5 = a.a("ADR:", c, true);
        String b3 = a.b("BDAY:", c, true);
        if (!u.a((CharSequence) b3, 8)) {
            b3 = null;
        }
        String[] a6 = a.a("URL:", c, true);
        return new d(u.b(a2), null, b, a3, null, a4, null, null, b2, a5, null, a.b("ORG:", c, true), b3, null, a6, null);
    }

    private static String a(String str) {
        int indexOf = str.indexOf(44);
        if (indexOf >= 0) {
            return str.substring(indexOf + 1) + ' ' + str.substring(0, indexOf);
        }
        return str;
    }
}
