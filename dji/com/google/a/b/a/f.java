package com.google.a.b.a;

import com.google.a.r;

public final class f extends a {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public ac a(r rVar) {
        String a = rVar.a();
        if (!a.startsWith("MEBKM:")) {
            return null;
        }
        String b = a.b("TITLE:", a, true);
        String[] a2 = a.a("URL:", a, true);
        if (a2 == null) {
            return null;
        }
        a = a2[0];
        if (ad.a(a)) {
            return new ac(a, b);
        }
        return null;
    }
}
