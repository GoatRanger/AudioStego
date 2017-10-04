package com.google.a.d;

import com.google.a.c;
import com.google.a.c.b;
import com.google.a.c.g;
import com.google.a.d.a.d;
import com.google.a.e;
import com.google.a.h;
import com.google.a.m;
import com.google.a.p;
import com.google.a.r;
import com.google.a.s;
import com.google.a.t;
import java.util.List;
import java.util.Map;

public final class a implements p {
    private static final t[] a = new t[0];
    private final d b = new d();

    public r a(c cVar) throws m, com.google.a.d, h {
        return a(cVar, null);
    }

    public r a(c cVar, Map<e, ?> map) throws m, com.google.a.d, h {
        com.google.a.c.e a;
        t[] e;
        if (map == null || !map.containsKey(e.PURE_BARCODE)) {
            g a2 = new com.google.a.d.b.a(cVar.c()).a();
            a = this.b.a(a2.d());
            e = a2.e();
        } else {
            a = this.b.a(a(cVar.c()));
            e = a;
        }
        r rVar = new r(a.b(), a.a(), e, com.google.a.a.DATA_MATRIX);
        List c = a.c();
        if (c != null) {
            rVar.a(s.BYTE_SEGMENTS, c);
        }
        String d = a.d();
        if (d != null) {
            rVar.a(s.ERROR_CORRECTION_LEVEL, d);
        }
        return rVar;
    }

    public void a() {
    }

    private static b a(b bVar) throws m {
        int[] d = bVar.d();
        int[] e = bVar.e();
        if (d == null || e == null) {
            throw m.a();
        }
        int a = a(d, bVar);
        int i = d[1];
        int i2 = e[1];
        int i3 = d[0];
        int i4 = ((e[0] - i3) + 1) / a;
        i2 = ((i2 - i) + 1) / a;
        if (i4 <= 0 || i2 <= 0) {
            throw m.a();
        }
        int i5 = a >> 1;
        i += i5;
        int i6 = i3 + i5;
        b bVar2 = new b(i4, i2);
        for (i5 = 0; i5 < i2; i5++) {
            int i7 = i + (i5 * a);
            for (i3 = 0; i3 < i4; i3++) {
                if (bVar.a((i3 * a) + i6, i7)) {
                    bVar2.b(i3, i5);
                }
            }
        }
        return bVar2;
    }

    private static int a(int[] iArr, b bVar) throws m {
        int f = bVar.f();
        int i = iArr[0];
        int i2 = iArr[1];
        while (i < f && bVar.a(i, i2)) {
            i++;
        }
        if (i == f) {
            throw m.a();
        }
        i -= iArr[0];
        if (i != 0) {
            return i;
        }
        throw m.a();
    }
}
