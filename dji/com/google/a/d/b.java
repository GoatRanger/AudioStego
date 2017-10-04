package com.google.a.d;

import com.google.a.a;
import com.google.a.d.c.e;
import com.google.a.d.c.i;
import com.google.a.d.c.j;
import com.google.a.d.c.k;
import com.google.a.d.c.l;
import com.google.a.f;
import com.google.a.g;
import com.google.a.v;
import java.util.Map;

public final class b implements v {
    public com.google.a.c.b a(String str, a aVar, int i, int i2) {
        return a(str, aVar, i, i2, null);
    }

    public com.google.a.c.b a(String str, a aVar, int i, int i2, Map<g, ?> map) {
        f fVar = null;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (aVar != a.DATA_MATRIX) {
            throw new IllegalArgumentException("Can only encode DATA_MATRIX, but got " + aVar);
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        } else {
            f fVar2;
            l lVar = l.FORCE_NONE;
            if (map != null) {
                l lVar2 = (l) map.get(g.DATA_MATRIX_SHAPE);
                if (lVar2 != null) {
                    lVar = lVar2;
                }
                f fVar3 = (f) map.get(g.MIN_SIZE);
                if (fVar3 != null) {
                    fVar2 = fVar3;
                } else {
                    fVar2 = null;
                }
                fVar3 = (f) map.get(g.MAX_SIZE);
                if (fVar3 != null) {
                    fVar = fVar3;
                }
            } else {
                fVar2 = null;
            }
            String a = j.a(str, lVar, fVar2, fVar);
            k a2 = k.a(a.length(), lVar, fVar2, fVar, true);
            e eVar = new e(i.a(a, a2), a2.d(), a2.e());
            eVar.d();
            return a(eVar, a2);
        }
    }

    private static com.google.a.c.b a(e eVar, k kVar) {
        int d = kVar.d();
        int e = kVar.e();
        com.google.a.i.c.b bVar = new com.google.a.i.c.b(kVar.f(), kVar.g());
        int i = 0;
        int i2 = 0;
        while (i < e) {
            int i3;
            int i4;
            int i5;
            if (i % kVar.c == 0) {
                i3 = 0;
                for (i4 = 0; i4 < kVar.f(); i4++) {
                    boolean z;
                    if (i4 % 2 == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    bVar.a(i3, i2, z);
                    i3++;
                }
                i4 = i2 + 1;
            } else {
                i4 = i2;
            }
            i3 = 0;
            for (i2 = 0; i2 < d; i2++) {
                if (i2 % kVar.b == 0) {
                    bVar.a(i3, i4, true);
                    i3++;
                }
                bVar.a(i3, i4, eVar.a(i2, i));
                i5 = i3 + 1;
                if (i2 % kVar.b == kVar.b - 1) {
                    boolean z2;
                    if (i % 2 == 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    bVar.a(i5, i4, z2);
                    i3 = i5 + 1;
                } else {
                    i3 = i5;
                }
            }
            i5 = i4 + 1;
            if (i % kVar.c == kVar.c - 1) {
                i3 = 0;
                for (i4 = 0; i4 < kVar.f(); i4++) {
                    bVar.a(i3, i5, true);
                    i3++;
                }
                i4 = i5 + 1;
            } else {
                i4 = i5;
            }
            i++;
            i2 = i4;
        }
        return a(bVar);
    }

    private static com.google.a.c.b a(com.google.a.i.c.b bVar) {
        int b = bVar.b();
        int a = bVar.a();
        com.google.a.c.b bVar2 = new com.google.a.c.b(b, a);
        bVar2.a();
        for (int i = 0; i < b; i++) {
            for (int i2 = 0; i2 < a; i2++) {
                if (bVar.a(i, i2) == (byte) 1) {
                    bVar2.b(i, i2);
                }
            }
        }
        return bVar2;
    }
}
