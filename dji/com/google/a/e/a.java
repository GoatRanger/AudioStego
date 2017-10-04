package com.google.a.e;

import com.google.a.c.b;
import com.google.a.d;
import com.google.a.e;
import com.google.a.e.a.c;
import com.google.a.h;
import com.google.a.m;
import com.google.a.p;
import com.google.a.r;
import com.google.a.s;
import com.google.a.t;
import java.util.Map;

public final class a implements p {
    private static final t[] a = new t[0];
    private static final int b = 30;
    private static final int c = 33;
    private final c d = new c();

    public r a(com.google.a.c cVar) throws m, d, h {
        return a(cVar, null);
    }

    public r a(com.google.a.c cVar, Map<e, ?> map) throws m, d, h {
        if (map == null || !map.containsKey(e.PURE_BARCODE)) {
            throw m.a();
        }
        com.google.a.c.e a = this.d.a(a(cVar.c()), map);
        r rVar = new r(a.b(), a.a(), a, com.google.a.a.MAXICODE);
        String d = a.d();
        if (d != null) {
            rVar.a(s.ERROR_CORRECTION_LEVEL, d);
        }
        return rVar;
    }

    public void a() {
    }

    private static b a(b bVar) throws m {
        int[] c = bVar.c();
        if (c == null) {
            throw m.a();
        }
        int i = c[0];
        int i2 = c[1];
        int i3 = c[2];
        int i4 = c[3];
        b bVar2 = new b(30, 33);
        for (int i5 = 0; i5 < 33; i5++) {
            int i6 = i2 + (((i5 * i4) + (i4 / 2)) / 33);
            for (int i7 = 0; i7 < 30; i7++) {
                if (bVar.a(((((i7 * i3) + (i3 / 2)) + (((i5 & 1) * i3) / 2)) / 30) + i, i6)) {
                    bVar2.b(i7, i5);
                }
            }
        }
        return bVar2;
    }
}
