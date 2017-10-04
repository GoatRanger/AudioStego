package com.google.a.b.a;

import com.google.a.a;
import com.google.a.g.z;
import com.google.a.r;

public final class t extends u {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public s a(r rVar) {
        a d = rVar.d();
        if (d != a.UPC_A && d != a.UPC_E && d != a.EAN_8 && d != a.EAN_13) {
            return null;
        }
        String c = u.c(rVar);
        if (!u.a((CharSequence) c, c.length())) {
            return null;
        }
        String b;
        if (d == a.UPC_E && c.length() == 8) {
            b = z.b(c);
        } else {
            b = c;
        }
        return new s(c, b);
    }
}
