package com.google.a.i;

import com.google.a.c;
import com.google.a.c.b;
import com.google.a.c.g;
import com.google.a.d;
import com.google.a.h;
import com.google.a.i.a.e;
import com.google.a.i.a.i;
import com.google.a.m;
import com.google.a.p;
import com.google.a.r;
import com.google.a.s;
import com.google.a.t;
import java.util.List;
import java.util.Map;

public class a implements p {
    private static final t[] a = new t[0];
    private final e b = new e();

    protected final e b() {
        return this.b;
    }

    public r a(c cVar) throws m, d, h {
        return a(cVar, null);
    }

    public final r a(c cVar, Map<com.google.a.e, ?> map) throws m, d, h {
        com.google.a.c.e a;
        t[] e;
        if (map == null || !map.containsKey(com.google.a.e.PURE_BARCODE)) {
            g b = new com.google.a.i.b.c(cVar.c()).b(map);
            a = this.b.a(b.d(), (Map) map);
            e = b.e();
        } else {
            a = this.b.a(a(cVar.c()), (Map) map);
            e = a;
        }
        if (a.g() instanceof i) {
            ((i) a.g()).a(e);
        }
        r rVar = new r(a.b(), a.a(), e, com.google.a.a.QR_CODE);
        List c = a.c();
        if (c != null) {
            rVar.a(s.BYTE_SEGMENTS, c);
        }
        String d = a.d();
        if (d != null) {
            rVar.a(s.ERROR_CORRECTION_LEVEL, d);
        }
        if (a.h()) {
            rVar.a(s.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(a.j()));
            rVar.a(s.STRUCTURED_APPEND_PARITY, Integer.valueOf(a.i()));
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
        float a = a(d, bVar);
        int i = d[1];
        int i2 = e[1];
        int i3 = d[0];
        int i4 = e[0];
        if (i3 >= i4 || i >= i2) {
            throw m.a();
        }
        if (i2 - i != i4 - i3) {
            i4 = (i2 - i) + i3;
        }
        int round = Math.round(((float) ((i4 - i3) + 1)) / a);
        int round2 = Math.round(((float) ((i2 - i) + 1)) / a);
        if (round <= 0 || round2 <= 0) {
            throw m.a();
        } else if (round2 != round) {
            throw m.a();
        } else {
            int i5 = (int) (a / 2.0f);
            int i6 = i + i5;
            i = i3 + i5;
            i4 = (((int) (((float) (round - 1)) * a)) + i) - (i4 - 1);
            if (i4 <= 0) {
                i3 = i;
            } else if (i4 > i5) {
                throw m.a();
            } else {
                i3 = i - i4;
            }
            i4 = (((int) (((float) (round2 - 1)) * a)) + i6) - (i2 - 1);
            if (i4 <= 0) {
                i4 = i6;
            } else if (i4 > i5) {
                throw m.a();
            } else {
                i4 = i6 - i4;
            }
            b bVar2 = new b(round, round2);
            for (i = 0; i < round2; i++) {
                i5 = i4 + ((int) (((float) i) * a));
                for (i6 = 0; i6 < round; i6++) {
                    if (bVar.a(((int) (((float) i6) * a)) + i3, i5)) {
                        bVar2.b(i6, i);
                    }
                }
            }
            return bVar2;
        }
    }

    private static float a(int[] iArr, b bVar) throws m {
        int g = bVar.g();
        int f = bVar.f();
        int i = iArr[0];
        boolean z = true;
        int i2 = iArr[1];
        int i3 = i;
        int i4 = 0;
        while (i3 < f && i2 < g) {
            boolean z2;
            if (z != bVar.a(i3, i2)) {
                i = i4 + 1;
                if (i == 5) {
                    break;
                }
                boolean z3;
                if (z) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                int i5 = i;
                z2 = z3;
                i4 = i5;
            } else {
                z2 = z;
            }
            i3++;
            i2++;
            z = z2;
        }
        if (i3 != f && i2 != g) {
            return ((float) (i3 - iArr[0])) / 7.0f;
        }
        throw m.a();
    }
}
